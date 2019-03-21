package com.xyj.tencent.wechat.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.tencent.imsdk.TIMCallBack;
import com.tencent.imsdk.TIMLogLevel;
import com.tencent.imsdk.TIMLogListener;
import com.tencent.imsdk.TIMManager;
import com.tencent.imsdk.TIMOfflinePushSettings;
import com.tencent.imsdk.TIMSdkConfig;
import com.tencent.imsdk.TIMUser;
import com.xyj.tencent.R;
import com.xyj.tencent.common.base.BaseActivity;
import com.xyj.tencent.common.base.Global;
import com.xyj.tencent.common.base.MyApp;
import com.xyj.tencent.common.util.SharedPreUtil;
import com.xyj.tencent.wechat.model.bean.Login;
import com.xyj.tencent.wechat.model.bean.LoginFriendGroups;
import com.xyj.tencent.wechat.model.bean.LoginTicket;
import com.xyj.tencent.wechat.model.protocol.IHttpService;
import com.xyj.tencent.wechat.presenter.LoginPresenter;
import com.xyj.tencent.wechat.util.RSAUtils;
import com.xyj.tencent.wechat.util.ReadAssstsUtil;

import java.util.HashMap;
import java.util.Map;

public class LoginActivity extends BaseActivity {

    private EditText et_username;
    private EditText et_password;
    private Button btn_login;
    private LoginPresenter loginPresenter;
    private String ticket;

    @Override
    public int getLayoutRes() {
        return R.layout.activity_login;
    }

    @Override
    public void initView() {
        // 状态栏透明
        Global.setNoStatusBarFullMode(this);

        et_username = findView(R.id.et_username);
        et_password = findView(R.id.et_password);
        btn_login = findView(R.id.btn_login);
    }

    @Override
    public void initListener() {

    }

    @Override
    public void initData() {
        loginPresenter = new LoginPresenter(this);

    }

    @Override
    public void onClick(View v, int id) {
        if (id==R.id.btn_login){
            String username = et_username.getText().toString();
            String password = et_password.getText().toString();
            if (username.isEmpty()){
                showToast("用户名不能为空");
                return;
            }

            if (password.isEmpty()){
                showToast("用户密码不能为空");
                return;
            }

            login(username,password);
        }
    }


    private void login(String username,String password) {

        String publicKey= ReadAssstsUtil.readAssetsTxt(getApplicationContext());
        String passwordkey="";
        try {
            passwordkey= RSAUtils.encryptyPublicKey(password,publicKey);
            Log.e("111",passwordkey+"----");
        } catch (Exception e) {
            e.printStackTrace();
        }

        Map<String, String> map =new HashMap<String, String>();
        map.put("username",username);
        map.put("password",passwordkey);
        loginPresenter.login(map);
    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }


    @Override
    public void onHttpSuccess(int reqType, Message msg) {
        if (reqType == IHttpService.TYPE_LOGIN) {
            Login login = (Login) msg.obj;
            if (login.isSuccess()) {
                if ("200".equals(login.getCode())) {
                    // 请求后台获取登录IM的信息
                    getTickerInfo(login.getResult());
                }
            } else {
                showToast("请求后台失败");
            }

        }else if (reqType==IHttpService.TYPE_LOGINTICKET){
           LoginTicket loginTicket= (LoginTicket) msg.obj;
           initTMConfig(this,loginTicket.getResult().getSdkAppId(),loginTicket);
            SharedPreUtil.saveString(this,"relationId",loginTicket.getResult().getRelationId());
           //loginTencentIM(loginTicket);
        }else if (reqType==IHttpService.TYPE_LOGINFRIENDGROUP){
            LoginFriendGroups loginFriendGroups= (LoginFriendGroups) msg.obj;
            if ("200".equals(loginFriendGroups.getCode())){

                MyApp.setLoginFriendGroups(loginFriendGroups);


                Intent intent = new Intent(this,UserSelectActivity.class);
                startActivity(intent);
                finish();
            }

        }
    }




    @Override
    public void onHttpError(int reqType, String error) {
        showToast(reqType+error);
    }

    public void getTickerInfo(String ticket) {
        this.ticket=ticket;
        SharedPreUtil.saveString(this,"ticket",ticket);
        loginPresenter.loginTicket(ticket);

    }

    private void loginTencentIM(LoginTicket loginTicket) {
        TIMUser user=new TIMUser();
        //user.setIdentifier(loginTicket.getResult().getRelationId());

        //发起登录请求
        TIMManager manager = TIMManager.getInstance();
        manager.login(loginTicket.getResult().getRelationId(), loginTicket.getResult().getSig(), new TIMCallBack() {
            @Override
            public void onError(int i, String s) {
                showToast(i+":"+s);
            }

            @Override
            public void onSuccess() {
                showToast("登录IM成功");
                getFriendGroups(ticket);

                //集成离线推送
                Impush();

            }
        });
    }

    private void Impush() {
        TIMOfflinePushSettings settings = new TIMOfflinePushSettings();
//开启离线推送
        settings.setEnabled(true);
//设置收到 C2C 离线消息时的提示声音，这里把声音文件放到了 res/raw 文件夹下
        settings.setC2cMsgRemindSound(Uri.parse("android.resource://" + getPackageName() + "/" + R.raw.dudulu));
//设置收到群离线消息时的提示声音，这里把声音文件放到了 res/raw 文件夹下
        settings.setGroupMsgRemindSound(Uri.parse("android.resource://" + getPackageName() + "/" + R.raw.dudulu));

        TIMManager.getInstance().setOfflinePushSettings(settings);
    }

    /**
     * 初始化腾讯云IM 配置
     */
    private void initTMConfig(Context context,String sdkAppId,LoginTicket loginTicket) {
       //初始化SDK基本配置
        TIMSdkConfig config = new TIMSdkConfig(Integer.parseInt(sdkAppId))
                .enableCrashReport(false)
                .enableLogPrint(true)
                .setLogLevel(TIMLogLevel.DEBUG);
        //.setLogPath(Environment.getExternalStorageDirectory().getParent() + "/justfortest/");
        //初始化SDK
        TIMManager.getInstance().init(context, config);
        //2.初始化SDK配置
        TIMSdkConfig sdkConfig = TIMManager.getInstance().getSdkConfig();
        sdkConfig.setLogListener(new TIMLogListener() {
            @Override
            public void log(int i, String s, String s1) {

            }
        });

        loginTencentIM(loginTicket);
//2.初始化SDK配置
    }

    public void getFriendGroups(String ticket) {
        loginPresenter.loginFriendGroup(ticket);
    }
}
