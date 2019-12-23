package com.xyj.tencent.wechat.ui.adapter;

import android.content.Context;
import android.text.TextUtils;
import android.view.ViewGroup;

import com.xyj.tencent.common.ui.BaseAdapterRV;
import com.xyj.tencent.common.ui.BaseHolderRV;
import com.xyj.tencent.wechat.model.bean.LoginFriendGroups;
import com.xyj.tencent.wechat.ui.holder.UserSelectFragmentHolder;
import com.xyj.tencent.wechat.ui.holder.UserSelectHolder;

import java.util.List;

public class UserSelectFragementAdapter extends BaseAdapterRV<LoginFriendGroups.ResultBean> {
    public UserSelectFragementAdapter(Context context, List<LoginFriendGroups.ResultBean> listData) {
        super(context, listData);
    }

    @Override
    public BaseHolderRV<LoginFriendGroups.ResultBean> createViewHolder(Context context, ViewGroup parent, int viewType) {
        return new UserSelectFragmentHolder(context,parent,this,viewType);
    }

}
