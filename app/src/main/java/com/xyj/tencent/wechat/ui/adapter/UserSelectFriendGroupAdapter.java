package com.xyj.tencent.wechat.ui.adapter;

import android.content.Context;
import android.view.ViewGroup;

import com.xyj.tencent.common.ui.BaseAdapterRV;
import com.xyj.tencent.common.ui.BaseHolderRV;
import com.xyj.tencent.wechat.model.bean.LoginFriendGroups;
import com.xyj.tencent.wechat.ui.holder.UserSelectFriendGroupHolder;

import java.util.List;

public class UserSelectFriendGroupAdapter extends BaseAdapterRV<LoginFriendGroups.ResultBean.GroupsBean.FriendsBean> {
    public UserSelectFriendGroupAdapter(Context context, List<LoginFriendGroups.ResultBean.GroupsBean.FriendsBean> listData) {
        super(context, listData);
    }

    @Override
    public BaseHolderRV<LoginFriendGroups.ResultBean.GroupsBean.FriendsBean> createViewHolder(Context context, ViewGroup parent, int viewType) {
        return new UserSelectFriendGroupHolder(context,parent,this,viewType);
    }

}
