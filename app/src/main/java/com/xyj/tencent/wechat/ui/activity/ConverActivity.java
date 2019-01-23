package com.xyj.tencent.wechat.ui.activity;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Message;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.tencent.imsdk.TIMConversation;
import com.tencent.imsdk.TIMConversationType;
import com.tencent.imsdk.TIMManager;
import com.tencent.imsdk.TIMMessage;
import com.tencent.imsdk.TIMTextElem;
import com.tencent.imsdk.TIMValueCallBack;
import com.tencent.imsdk.ext.message.TIMMessageDraft;
import com.xyj.tencent.R;
import com.xyj.tencent.common.base.BaseActivity;
import com.xyj.tencent.common.base.MyApp;
import com.xyj.tencent.common.util.SharedPreUtil;
import com.xyj.tencent.wechat.model.bean.ImMessageBean;
import com.xyj.tencent.wechat.model.bean.SendMsgBean;
import com.xyj.tencent.wechat.model.db.DBUtils;
import com.xyj.tencent.wechat.presenter.ChatPresenter;
import com.xyj.tencent.wechat.ui.adapter.ConverAdapter;
import com.xyj.tencent.wechat.ui.widget.ChatInput;
import com.xyj.tencent.wechat.ui.widget.ChatView;
import com.xyj.tencent.wechat.util.IsReadUtil;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;

public class ConverActivity extends BaseActivity implements ChatView {

    private TextView tv_title;
    private RecyclerView re_list;
    private List<ImMessageBean> imMessageList =new ArrayList<>();
    private SendMsgBean sendMsgBean;
    private ChatInput input_panel;
    private ChatPresenter chatPresenter;
    private ConverAdapter converAdapter;
    private String headUrl;
    private String wxid;
    private String fromAccount;

    @Override
    public int getLayoutRes() {
        return R.layout.activity_chating;
    }

    @Override
    public void initView() {
        EventBus.getDefault().register(this);
        IsReadUtil.isConverActivity=true;
        tv_title = findView(R.id.tv_title);
        re_list = findView(R.id.list);
        input_panel = findView(R.id.input_panel);
        input_panel.setChatView(this);

    }

    @Override
    public void initListener() {

    }

    @Override
    public void initData() {
        Intent intent = getIntent();
        String nickname = intent.getStringExtra("nickName");
        String fid = intent.getStringExtra("fid");
        wxid = intent.getStringExtra("wxid");
        fromAccount = SharedPreUtil.getString(this,"wechatId","");
        headUrl = intent.getStringExtra("headUrl");
        chatPresenter = new ChatPresenter(this, fromAccount, TIMConversationType.C2C);
        imMessageList = DBUtils.getNewHistory(wxid, fromAccount);

        IsReadUtil.fid=fid;
        tv_title.setText(nickname);
        //更新数据库里面已读的标志
        updateReceiverState(fid);

        re_list.setLayoutManager(new LinearLayoutManager(this));
        converAdapter = new ConverAdapter(this, imMessageList);

        re_list.setAdapter(converAdapter);

    }

    private void updateReceiverState(String fid) {
        SQLiteDatabase db = MyApp.getDbs();
        db.execSQL("update tb_chat set receiverState=?  where fid=?",new Object[]{0,fid});
    }

    @Override
    public void onClick(View v, int id) {

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        IsReadUtil.isConverActivity=false;
        EventBus.getDefault().unregister(this);
    }


    @Subscribe(threadMode = ThreadMode.MAIN,sticky = true)
    public void getData(List<ImMessageBean> tableChatList){
        this.imMessageList=tableChatList;

    }

    @Override
    public void showMessage(TIMMessage message) {

    }

    @Override
    public void showMessage(List<TIMMessage> messages) {

    }

    @Override
    public void clearAllMessage() {

    }

    @Override
    public void onSendMessageSuccess(TIMMessage message,SendMsgBean sendMsgBean) {
        ImMessageBean imMessageBean =new ImMessageBean();
        imMessageBean.setMsgState("0");//消息:0发送消息 1接收消息
        imMessageBean.setContent(sendMsgBean.getContent());
        imMessageBean.setType(sendMsgBean.getType()+"");
        imMessageBean.setFromAccount(sendMsgBean.getToAccount());
        imMessageBean.setToAccount(sendMsgBean.getFromAccount());
        imMessageBean.setWxid(sendMsgBean.getWxid());
        imMessageBean.setWxno(sendMsgBean.getWxno());
        imMessageBean.setRemarkName(sendMsgBean.getRemarkName());
        imMessageBean.setNickName(sendMsgBean.getNickName());
        imMessageBean.setId(sendMsgBean.getId());
        imMessageBean.setConversationTime(System.currentTimeMillis());//获取系统时间的10位的时间戳);
        imMessageBean.setHeadUrl(sendMsgBean.getHeadUrl());
        imMessageList.add(imMessageBean);
        converAdapter.notifyDataSetChanged();

        DBUtils.insertImMessageBean(imMessageBean);
    }

    @Override
    public void onSendMessageFail(int code, String desc, TIMMessage message) {

    }

    @Override
    public void sendImage() {

    }

    @Override
    public void sendPhoto() {

    }

    @Override
    public void sendText() {
        String sendMessage = input_panel.getText().toString();
        //String wxno = imMessageList.get(0).getWxno();
       // String fromAccount = imMessageList.get(0).getFromAccount();
        String toAccount =  SharedPreUtil.getString(this,"relationId","");
        //String id = imMessageList.get(0).getId();
        //String headUrl = imMessageList.get(0).getHeadUrl();
       // String nickName = imMessageList.get(0).getNickName();
       // String remarkName = imMessageList.get(0).getRemarkName();
        Log.e("111",wxid+":"+fromAccount+":"+toAccount+":"+sendMessage);
        //im   frimAccount  来至这个apk的IM
        final SendMsgBean sendMsgBean=new SendMsgBean(wxid,wxid,toAccount,fromAccount,sendMessage,1542625414005L,1,true,"000000",headUrl,"","");
        String json = JSON.toJSONString(sendMsgBean);
            String peer = fromAccount;  //获取与用户 "sample_user_1" 的会话   //621c62f470e94160a4f9417fe82966b2
            TIMConversation conversation = TIMManager.getInstance().getConversation(
                    TIMConversationType.C2C,    //会话类型：单聊
                    peer);                      //会话对方用户帐号//对方id
            //构造一条消息
            TIMMessage msg = new TIMMessage();
            //添加文本内容
            TIMTextElem elem = new TIMTextElem();
            elem.setText(json);
            //将elem添加到消息
            if (msg.addElement(elem) != 0) {
                Log.d("111", "addElement failed");
                return;
            }
            //发送消息
            conversation.sendMessage(msg, new TIMValueCallBack<TIMMessage>() {//发送消息回调
                @Override
                public void onError(int code, String desc) {//发送消息失败
                    Log.e("111", "send message failed. code: " + code + " errmsg: " + desc);
                }

                @Override
                public void onSuccess(TIMMessage msg) {//发送消息成功
                    Log.e("111","发送消息成功"+msg.getMsgId());
                    chatPresenter.sendMessage(msg,sendMsgBean);
                    input_panel.setText("");
                }
            });


        }

    @Override
    public void sendFile() {

    }

    @Override
    public void startSendVoice() {

    }

    @Override
    public void endSendVoice() {

    }

    @Override
    public void sendVideo(String fileName) {

    }

    @Override
    public void cancelSendVoice() {

    }

    @Override
    public void sending() {

    }

    @Override
    public void showDraft(TIMMessageDraft draft) {

    }
}