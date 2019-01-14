package com.xyj.tencent.wechat.ui.holder;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.makeramen.roundedimageview.RoundedImageView;
import com.squareup.picasso.Picasso;
import com.xyj.tencent.R;
import com.xyj.tencent.common.ui.BaseAdapterRV;
import com.xyj.tencent.common.ui.BaseHolderRV;
import com.xyj.tencent.wechat.model.bean.LoginFriendGroups;
import com.xyj.tencent.wechat.ui.activity.HomeActivityVersion1;

public class UserSelectFriendGroupHolder extends BaseHolderRV<LoginFriendGroups.ResultBean.GroupsBean.FriendsBean> {

    private ImageView iv_user;
    private TextView tv_nickname;
    private TextView tv_lastMessage;
    private ImageView iv_state;
    private TextView tv_state;

    public UserSelectFriendGroupHolder(Context context, ViewGroup parent, BaseAdapterRV adapter, int itemType) {
        super(context, parent, adapter, itemType, R.layout.item_myconversation2);
    }

    @Override
    public void onFindViews(View itemView) {
        iv_user = itemView.findViewById(R.id.iv_user);
        tv_nickname = itemView.findViewById(R.id.tv_nickname);
    }

    @Override
    protected void onRefreshView(LoginFriendGroups.ResultBean.GroupsBean.FriendsBean bean, int position) {
        Picasso.with(context).load(bean.getHeadImgUrl()).into(iv_user);
        tv_nickname.setText(bean.getNickname());
    }


}
