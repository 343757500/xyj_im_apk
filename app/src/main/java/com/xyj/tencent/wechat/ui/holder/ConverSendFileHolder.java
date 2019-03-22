package com.xyj.tencent.wechat.ui.holder;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.squareup.picasso.Picasso;
import com.tencent.imsdk.protocol.msg;
import com.xyj.tencent.R;
import com.xyj.tencent.common.base.MyApp;
import com.xyj.tencent.common.ui.BaseAdapterRV;
import com.xyj.tencent.common.ui.BaseHolderRV;
import com.xyj.tencent.wechat.model.bean.ImMessageBean;
import com.xyj.tencent.wechat.ui.activity.ShowFileTypeActivity;

import java.text.SimpleDateFormat;
import java.util.Date;

public class ConverSendFileHolder extends BaseHolderRV {
    private ImageView iv_user_send;
    private TextView tv_time;
    private ImageView iv_type;
    private TextView tv_filename;
    private LinearLayout ll_container;

    public ConverSendFileHolder(Context context, ViewGroup parent, BaseAdapterRV adapter, int itemType) {
        super(context, parent, adapter, itemType, R.layout.item_chating_send_file_success);
    }

    @Override
    public void onFindViews(View itemView) {
        iv_user_send = itemView.findViewById(R.id.iv_user_send);
        tv_time = itemView.findViewById(R.id.tv_time);
        iv_type = itemView.findViewById(R.id.iv_type);
        tv_filename = itemView.findViewById(R.id.tv_filename);
        ll_container = itemView.findViewById(R.id.ll_container);
    }

    @Override
    protected void onRefreshView(Object bean, final int position) {
        ImMessageBean imMessageBean= (ImMessageBean) bean;
        final String c = imMessageBean.getContent();
        tv_time.setText(new SimpleDateFormat("yyyy年MM月dd日 HH:mm").format(new Date((System.currentTimeMillis()))));
        Picasso.with(context).load(MyApp.getGroupFriendsBean().getResult().get(MyApp.getIndexUser()).getHeadImgUrl()).into(iv_user_send);

        String n = c.substring(c.lastIndexOf("/") + 1);
        String suffix2 = n.substring(n.lastIndexOf(".") + 1);
        tv_filename.setText(n);
        if ("txt".equals(suffix2)) {
            Glide.with(context).load(R.mipmap.file_txt).into(iv_type);
        } else if ("doc".equals(suffix2)) {
            Glide.with(context).load(R.mipmap.file_doc).into(iv_type);
        } else if ("xls".equals(suffix2) || "xlsx".equals(n)) {
            Glide.with(context).load(R.mipmap.file_excel).into(iv_type);
        } else if ("ppt".equals(suffix2) || "pptx".equals(n)) {
            Glide.with(context).load(R.mipmap.file_ppt).into(iv_type);
        } else if ("mp3".equals(suffix2)||"amr".equals(suffix2)) {
            Glide.with(context).load(R.mipmap.file_mp3).into(iv_type);
        } else if ("avi".equals(suffix2) || "mp4".equals(n) || "wav".equals(n)) {
            Glide.with(context).load(R.mipmap.file_video).into(iv_type);
        } else if ("apk".equals(suffix2)) {
            Glide.with(context).load(R.mipmap.file_apk).into(iv_type);
        } else {
            Glide.with(context).load(R.mipmap.file_unknow).into(iv_type);
        }
        /*helper.addOnClickListener(R.id.ll_container);
        helper.addOnClickListener(R.id.iv_user_send);*/
        ll_container.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                context.startActivity(new Intent(context, ShowFileTypeActivity.class).putExtra("download", c));
            }
        });


    }
}
