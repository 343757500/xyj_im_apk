package com.xyj.tencent.wechat.ui.holder;

import android.content.Context;
import android.content.Intent;
import android.graphics.ColorMatrix;
import android.graphics.ColorMatrixColorFilter;
import android.text.TextUtils;
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
import com.xyj.tencent.common.util.SharedPreUtil;
import com.xyj.tencent.wechat.event.OnlineEvent;
import com.xyj.tencent.wechat.model.bean.LoginFriendGroups;
import com.xyj.tencent.wechat.model.db.DBUtils;
import com.xyj.tencent.wechat.ui.activity.HomeActivityVersion1;

import org.greenrobot.eventbus.EventBus;

public class UserSelectFragmentHolder extends BaseHolderRV<LoginFriendGroups.ResultBean> {

    private ImageView iv;
    private ImageView iv_red;

    public UserSelectFragmentHolder(Context context, ViewGroup parent, BaseAdapterRV adapter, int itemType) {
        super(context, parent, adapter, itemType, R.layout.item_subtitle_user_select);
    }

    @Override
    public void onFindViews(View itemView) {
        iv = itemView.findViewById(R.id.iv);
        iv_red = itemView.findViewById(R.id.iv_red);
    }

    @Override
    protected void onRefreshView(LoginFriendGroups.ResultBean bean, int position) {
        int unReadCountMsg = DBUtils.getUnReadMsgByWechatId(bean.getWechatId());
        if(unReadCountMsg>0){
            iv_red.setVisibility(View.VISIBLE);
        }else{
            iv_red.setVisibility(View.GONE);
        }


        ColorMatrix cm = new ColorMatrix();
        cm.setSaturation(0); // 设置饱和度
        ColorMatrixColorFilter grayColorFilter = new ColorMatrixColorFilter(cm);
        if(TextUtils.equals("1",bean.getStatus())){
            iv.setColorFilter(grayColorFilter); // 如果想恢复彩色显示，设置为null即可
        }else{
            iv.setColorFilter(null); // 如果想恢复彩色显示，设置为null即可
        }

        Picasso.with(context).load(bean.getHeadImgUrl()).into(iv);
    }

    @Override
    protected void onItemClick(View itemView, int position, LoginFriendGroups.ResultBean bean) {
        super.onItemClick(itemView, position, bean);
        MyApp.setIndexUser(position);
        SharedPreUtil.saveInt(context,"selectindex",position);
        MyApp.setIndexUser(position);
        //EventBus.getDefault().post(new OnlineEvent());
        EventBus.getDefault().postSticky(new OnlineEvent());
    }
}
