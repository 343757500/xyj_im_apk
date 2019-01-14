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
import com.xyj.tencent.common.util.SharedPreUtil;
import com.xyj.tencent.wechat.model.bean.LoginFriendGroups;
import com.xyj.tencent.wechat.ui.activity.HomeActivityVersion1;

public class UserSelectHolder extends BaseHolderRV<LoginFriendGroups.ResultBean> {

    private RoundedImageView imageView1;
    private TextView tv_nickname;
    private TextView tv_wxno;
    private ImageView iv_state;
    private TextView tv_state;

    public UserSelectHolder(Context context, ViewGroup parent, BaseAdapterRV adapter, int itemType) {
        super(context, parent, adapter, itemType, R.layout.item_select_user);
    }

    @Override
    public void onFindViews(View itemView) {
        imageView1 = itemView.findViewById(R.id.imageView1);
        tv_nickname = itemView.findViewById(R.id.tv_nickname);
        tv_wxno = itemView.findViewById(R.id.tv_wxno);
        iv_state = itemView.findViewById(R.id.iv_state);
        tv_state = itemView.findViewById(R.id.tv_state);
    }

    @Override
    protected void onRefreshView(LoginFriendGroups.ResultBean bean, int position) {
        Picasso.with(context).load(bean.getHeadImgUrl()).into(imageView1);
        tv_nickname.setText(bean.getNickname());
        tv_wxno.setText(bean.getWxno());
        if ("0".equals(bean.getStatus())){
            Picasso.with(context).load(R.mipmap.online).into(iv_state);
            tv_state.setText("在线");
        }else{
            Picasso.with(context).load(R.mipmap.offline).into(iv_state);
            tv_state.setText("离线");
        }

    }

    @Override
    protected void onItemClick(View itemView, int position, LoginFriendGroups.ResultBean bean) {
        super.onItemClick(itemView, position, bean);
        SharedPreUtil.saveInt(context,"selectindex",position);
        Intent intent=new Intent(context, HomeActivityVersion1.class);
        context.startActivity(intent);
    }
}
