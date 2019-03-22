package com.xyj.tencent.wechat.ui.holder;

import android.content.Context;
import android.content.Intent;
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

public class ConverPicHolder extends BaseHolderRV {

    private ImageView iv;
    private TextView textView;
    private ImageView iv_user_receiver;

    public ConverPicHolder(Context context, ViewGroup parent, BaseAdapterRV adapter, int itemType) {
        super(context, parent, adapter, itemType, R.layout.item_chating_receiver_image_success);
    }

    @Override
    public void onFindViews(View itemView) {
        iv = itemView.findViewById(R.id.iv);
        textView = itemView.findViewById(R.id.tv_time);
        iv_user_receiver = itemView.findViewById(R.id.iv_user_receiver);

    }

    @Override
    protected void onRefreshView(Object bean, int position) {
        ImMessageBean imMessageBean= (ImMessageBean) bean;
        final String content = imMessageBean.getContent();
        //返回来的url是http的字符串截取拼接成https
        String a=  content.substring(0,4)+"s";
        String b= content.substring(4,content.length());
        Picasso.with(context).load(a+b).into(iv);
        textView.setText(new SimpleDateFormat("yyyy年MM月dd日 HH:mm").format(new Date((System.currentTimeMillis()))));
        Picasso.with(context).load(imMessageBean.getHeadUrl()).into(iv_user_receiver);
        iv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                context.startActivity(new Intent(context, ShowImagePicActivity.class).putExtra("img", content));
            }
        });


    }
}
