package com.xyj.tencent.wechat.ui.holder;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.xyj.tencent.R;
import com.xyj.tencent.common.ui.BaseAdapterRV;
import com.xyj.tencent.common.ui.BaseHolderRV;
import com.xyj.tencent.wechat.model.bean.LoginFriendGroups;
import com.xyj.tencent.wechat.model.db.DBUtils;
import com.xyj.tencent.wechat.ui.activity.ConverActivity;

import org.apache.commons.lang3.StringUtils;
import org.greenrobot.eventbus.EventBus;

public class MainFragment2Holder extends BaseHolderRV<LoginFriendGroups.ResultBean.GroupsBean.FriendsBean> {

    private ImageView iv;
    private TextView tv;

    public MainFragment2Holder(Context context, ViewGroup parent, BaseAdapterRV<LoginFriendGroups.ResultBean.GroupsBean.FriendsBean> adapter, int itemType) {
        super(context, parent, adapter, itemType, R.layout.item_contact2);
    }

    @Override
    public void onFindViews(View itemView) {
        iv = itemView.findViewById(R.id.iv);
        tv = itemView.findViewById(R.id.tv);

    }

    @Override
    protected void onRefreshView(LoginFriendGroups.ResultBean.GroupsBean.FriendsBean bean, int position) {
        if (position==0){
            iv.setImageResource(R.mipmap.new_per);
        }else{
            Picasso.with(context).load(bean.getHeadImgUrl()).into(iv);

        }
        if (StringUtils.isNotBlank(bean.getRemarkname())){
            tv.setText(bean.getRemarkname());
        }else{
            tv.setText(bean.getNickname());
        }

    }

    @Override
    protected void onItemClick(View itemView, int position, LoginFriendGroups.ResultBean.GroupsBean.FriendsBean bean) {
        super.onItemClick(itemView, position, bean);
        Intent intent=new Intent(context,ConverActivity.class);
        intent.putExtra("nickName",bean.getNickname());
        intent.putExtra("wxid",bean.getWxid());
       // intent.putExtra("fromAccount",bean.getFromAccount());
        String fromAccount = DBUtils.selectFbyWxid(bean.getWxid());
        intent.putExtra("fromAccount",fromAccount);
        intent.putExtra("fid",bean.getId());
        intent.putExtra("headUrl",bean.getHeadImgUrl());
        context.startActivity(intent);

        EventBus.getDefault().postSticky(bean);
    }
}
