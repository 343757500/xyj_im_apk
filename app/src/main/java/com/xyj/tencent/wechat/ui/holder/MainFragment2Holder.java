package com.xyj.tencent.wechat.ui.holder;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.xyj.tencent.R;
import com.xyj.tencent.common.ui.BaseAdapterRV;
import com.xyj.tencent.common.ui.BaseHolderRV;
import com.xyj.tencent.wechat.model.bean.LoginFriendGroups;

import org.apache.commons.lang3.StringUtils;

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
}
