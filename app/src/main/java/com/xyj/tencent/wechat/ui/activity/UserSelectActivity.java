package com.xyj.tencent.wechat.ui.activity;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.xyj.tencent.R;
import com.xyj.tencent.common.base.BaseActivity;
import com.xyj.tencent.common.base.MyApp;
import com.xyj.tencent.wechat.model.bean.LoginFriendGroups;
import com.xyj.tencent.wechat.ui.adapter.UserSelectAdapter;

public class UserSelectActivity extends BaseActivity {

    private RecyclerView rv;

    @Override
    public int getLayoutRes() {
        return R.layout.activity_select_user;
    }

    @Override
    public void initView() {
        rv = findView(R.id.rv);
        rv.setLayoutManager(new LinearLayoutManager(this));

    }

    @Override
    public void initListener() {

    }

    @Override
    public void initData() {
        LoginFriendGroups groupFriendsBean = MyApp.getGroupFriendsBean();
        if (groupFriendsBean!=null){
            rv.setAdapter(new UserSelectAdapter(this,groupFriendsBean.getResult()));
        }


    }

    @Override
    public void onClick(View v, int id) {

    }
}
