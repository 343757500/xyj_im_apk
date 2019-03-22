package com.xyj.tencent.wechat.ui.holder;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.xyj.tencent.R;
import com.xyj.tencent.common.base.MyApp;
import com.xyj.tencent.common.ui.BaseAdapterRV;
import com.xyj.tencent.common.ui.BaseHolderRV;
import com.xyj.tencent.wechat.model.bean.ImMessageBean;
import com.xyj.tencent.wechat.ui.activity.ShowImagePicActivity;

import java.text.SimpleDateFormat;
import java.util.Date;

public class ConverSendPicHolder extends BaseHolderRV {

    private ImageView iv;
    private TextView textView;
    private ImageView iv_user_send;

    public ConverSendPicHolder(Context context, ViewGroup parent, BaseAdapterRV adapter, int itemType) {
        super(context, parent, adapter, itemType, R.layout.item_chating_send_image_success);
    }

    @Override
    public void onFindViews(View itemView) {
        iv = itemView.findViewById(R.id.iv);
        textView = itemView.findViewById(R.id.tv_time);
        iv_user_send = itemView.findViewById(R.id.iv_user_send);
    }

    @Override
    protected void onRefreshView(Object bean, int position) {
        ImMessageBean imMessageBean= (ImMessageBean) bean;
        final String content = imMessageBean.getContent();
        Log.e("111","本地图片地址"+content);
        Picasso.with(context).load(content).into(iv);
        textView.setText(new SimpleDateFormat("yyyy年MM月dd日 HH:mm").format(new Date((System.currentTimeMillis()))));
        Picasso.with(context).load(MyApp.getGroupFriendsBean().getResult().get(MyApp.getIndexUser()).getHeadImgUrl()).into(iv_user_send);
        iv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                context.startActivity(new Intent(context, ShowImagePicActivity.class).putExtra("img", content));
            }
        });

    }
}
