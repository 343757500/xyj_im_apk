package com.xyj.tencent.wechat.ui.fragment;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.makeramen.roundedimageview.RoundedImageView;
import com.xyj.tencent.R;
import com.xyj.tencent.common.base.BaseFragment;
import com.xyj.tencent.common.base.MyApp;
import com.xyj.tencent.wechat.event.OnlineEvent;
import com.xyj.tencent.wechat.util.VersionUtils;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

public class MainFragment3 extends BaseFragment {

    private RoundedImageView iv_user;
    private TextView tv_name;
    private TextView tv_account;
    private TextView tv_version;

    @Override
    public int getLayoutRes() {
        return R.layout.fragment_setting_version2;
    }

    @Override
    public void initView() {
        EventBus.getDefault().register(this);
        iv_user = findView(R.id.imageView1);
        tv_name = findView(R.id.tv_name);
        tv_account = findView(R.id.tv_account);
        tv_version = findView(R.id.tv_version);
    }

    @Override
    public void initListener() {

    }

    @Override
    public void initData() {
        Glide.with(getContext()).load(MyApp.getGroupFriendsBean().getResult().get(MyApp.getIndexUser()).getHeadImgUrl()).into(iv_user);
        tv_name.setText(MyApp.getGroupFriendsBean().getResult().get(MyApp.getIndexUser()).getNickname());
        tv_account.setText("账号:   "+MyApp.getGroupFriendsBean().getResult().get(MyApp.getIndexUser()).getWxno());
        tv_version.setText(VersionUtils.getVersion(getContext()));
    }

    @Override
    public void onClick(View v, int id) {

    }


    @Override
    public void onDestroy() {
        EventBus.getDefault().unregister(this);
        super.onDestroy();
    }


    @Subscribe(threadMode = ThreadMode.MAIN,sticky = true)
    public void onOnlineEvent(OnlineEvent onlineEvent){
        Glide.with(getContext()).load(MyApp.getGroupFriendsBean().getResult().get(MyApp.getIndexUser()).getHeadImgUrl()).into(iv_user);
        tv_name.setText(MyApp.getGroupFriendsBean().getResult().get(MyApp.getIndexUser()).getNickname());
        tv_account.setText("账号:   "+MyApp.getGroupFriendsBean().getResult().get(MyApp.getIndexUser()).getWxno());
        tv_version.setText(VersionUtils.getVersion(getContext()));
    }
}
