package com.xyj.tencent.wechat.ui.holder;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.squareup.picasso.Picasso;
import com.xyj.tencent.R;
import com.xyj.tencent.common.base.MyApp;
import com.xyj.tencent.common.ui.BaseAdapterRV;
import com.xyj.tencent.common.ui.BaseHolderRV;
import com.xyj.tencent.wechat.model.bean.ImMessageBean;

import java.text.SimpleDateFormat;
import java.util.Date;

public class ConverVideoHolder extends BaseHolderRV {
    private ImageView iv_user_receiver;
    private TextView tv_time;
    public ConverVideoHolder(Context context, ViewGroup parent, BaseAdapterRV adapter, int itemType) {
        super(context, parent, adapter, itemType, R.layout.item_chating_receiver_video_success);
    }

    @Override
    public void onFindViews(View itemView) {
        iv_user_receiver = itemView.findViewById(R.id.iv_user_receiver);
        tv_time = itemView.findViewById(R.id.tv_time);
    }

    @Override
    protected void onRefreshView(Object bean, int position) {
        ImMessageBean imMessageBean= (ImMessageBean) bean;
        Picasso.with(context).load(imMessageBean.getHeadUrl()).into(iv_user_receiver);
        tv_time.setText(new SimpleDateFormat("yyyy年MM月dd日 HH:mm").format(new Date((System.currentTimeMillis()))));
    }
}
