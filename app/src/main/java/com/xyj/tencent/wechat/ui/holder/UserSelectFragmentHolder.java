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
import com.xyj.tencent.common.base.MyApp;
import com.xyj.tencent.common.ui.BaseAdapterRV;
import com.xyj.tencent.common.ui.BaseHolderRV;
import com.xyj.tencent.wechat.model.bean.LoginFriendGroups;
import com.xyj.tencent.wechat.ui.activity.HomeActivityVersion1;

public class UserSelectFragmentHolder extends BaseHolderRV<LoginFriendGroups.ResultBean> {

    private ImageView iv;

    public UserSelectFragmentHolder(Context context, ViewGroup parent, BaseAdapterRV adapter, int itemType) {
        super(context, parent, adapter, itemType, R.layout.item_subtitle_user_select);
    }

    @Override
    public void onFindViews(View itemView) {
        iv = itemView.findViewById(R.id.iv);
    }

    @Override
    protected void onRefreshView(LoginFriendGroups.ResultBean bean, int position) {
        Picasso.with(context).load(bean.getHeadImgUrl()).into(iv);
    }

    @Override
    protected void onItemClick(View itemView, int position, LoginFriendGroups.ResultBean bean) {
        super.onItemClick(itemView, position, bean);
        MyApp.setIndexUser(position);
    }
}
