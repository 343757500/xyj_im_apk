package com.xyj.tencent.wechat.ui.holder;

import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.alibaba.fastjson.JSONObject;
import com.bumptech.glide.Glide;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.Response;
import com.xyj.tencent.R;
import com.xyj.tencent.common.base.Const;
import com.xyj.tencent.common.base.MyApp;
import com.xyj.tencent.common.ui.BaseAdapterRV;
import com.xyj.tencent.common.ui.BaseHolderRV;
import com.xyj.tencent.wechat.model.bean.ImMessageBean;
import com.xyj.tencent.wechat.model.bean.VideoBean;
import com.xyj.tencent.wechat.ui.activity.VideoActivity;

import java.text.SimpleDateFormat;
import java.util.Date;

public class ConverSendVideoHolder extends BaseHolderRV {

    private ImageView iv_user_send;
    private TextView tv_time;
    private RelativeLayout rl;

    public ConverSendVideoHolder(Context context, ViewGroup parent, BaseAdapterRV adapter, int itemType) {
        super(context, parent, adapter, itemType, R.layout.item_chating_send_video_success);
    }

    @Override
    public void onFindViews(View itemView) {
        iv_user_send = itemView.findViewById(R.id.iv_user_send);
        tv_time = itemView.findViewById(R.id.tv_time);
        rl = itemView.findViewById(R.id.rl);
    }

    @Override
    protected void onRefreshView(Object bean, int position) {
        tv_time.setText(new SimpleDateFormat("yyyy年MM月dd日 HH:mm").format(new Date((System.currentTimeMillis()))));
        Glide.with(context).load(MyApp.getGroupFriendsBean().getResult().get(MyApp.getIndexUser()).getHeadImgUrl()).into(iv_user_send);


    }

    @Override
    protected void onItemClick(View itemView, int position, Object bean) {
        final ImMessageBean imMessageBean= (ImMessageBean) bean;
        rl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String msgState = imMessageBean.getMsgState();//0发送 1接收
                String f_msgId = imMessageBean.getF_id();
                if ("0".equals(msgState)) {
                    Intent intent = new Intent(context, VideoActivity.class);
                    intent.putExtra("path", imMessageBean.getContent());
                    context.startActivity(intent);
                } else if ("1".equals(msgState)) {
                    OkGo.<String>
                            get(Const.SEARCH_MESSAGE + "/" + f_msgId)
                            .execute(new StringCallback() {
                                @Override
                                public void onSuccess(Response<String> response) {
                                    String body = response.body();
                                    VideoBean videoBean = JSONObject.parseObject(body, VideoBean.class);
                                    if (TextUtils.equals("200", videoBean.getCode())) {
                                        boolean isLoaded = videoBean.getResult().isLoaded();
                                        if (isLoaded) {
                                            String url = videoBean.getResult().getContent();
                                            Intent intent = new Intent(context, VideoActivity.class);
                                            intent.putExtra("path", url);
                                            context.startActivity(intent);
                                        } else {
                                            Toast.makeText(context, "视频还在上传中...", Toast.LENGTH_SHORT).show();
                                        }
                                    }


                                }

                                @Override
                                public void onError(Response<String> response) {
                                    super.onError(response);
                                }
                            });
                }
            }
        });
    }
}
