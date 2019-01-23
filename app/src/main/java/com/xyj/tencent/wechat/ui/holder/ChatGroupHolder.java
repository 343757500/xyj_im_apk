package com.xyj.tencent.wechat.ui.holder;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.nostra13.universalimageloader.utils.L;
import com.squareup.picasso.Picasso;
import com.xyj.tencent.R;
import com.xyj.tencent.common.ui.BaseAdapterRV;
import com.xyj.tencent.common.ui.BaseHolderRV;
import com.xyj.tencent.wechat.model.bean.ImMessageBean;
import com.xyj.tencent.wechat.model.bean.LoginFriendGroups;
import com.xyj.tencent.wechat.model.db.DBUtils;
import com.xyj.tencent.wechat.ui.activity.ConverActivity;
import com.xyj.tencent.wechat.util.DateUtil;

import org.apache.commons.lang3.StringUtils;
import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ChatGroupHolder extends BaseHolderRV<ArrayList<ImMessageBean>> {

    private ImageView iv_user;
    private TextView tv_nickname;
    private TextView tvLastMessage;
    private TextView tv_time;
    private TextView tv_red_message;

    public ChatGroupHolder(Context context, ViewGroup parent, BaseAdapterRV adapter, int itemType) {
        super(context, parent, adapter, itemType, R.layout.item_myconversation2);
    }

    @Override
    public void onFindViews(View itemView) {
        iv_user = itemView.findViewById(R.id.iv_user);
        tv_nickname = itemView.findViewById(R.id.tv_nickname);
        tvLastMessage = itemView.findViewById(R.id.tv_lastMessage);
        tv_time = itemView.findViewById(R.id.tv_time);
        tv_red_message = itemView.findViewById(R.id.tv_red_message);
    }

    @Override
    protected void onRefreshView(ArrayList<ImMessageBean> bean, int position) {
        ImMessageBean imMessageBean = bean.get(bean.size()-1);
        if (imMessageBean!=null) {
            Picasso.with(context).load(imMessageBean.getHeadUrl()).into(iv_user);

            if (StringUtils.isNotBlank(imMessageBean.getRemarkName())) {
                tv_nickname.setText(imMessageBean.getRemarkName());
            } else {
                tv_nickname.setText(imMessageBean.getNickName());
            }
            String s = DateUtil.longToString(imMessageBean.getConversationTime(), "MM月dd日 HH:mm");
            tv_time.setText(s + "");

            Log.e("type", ""+imMessageBean.getType());

            if ("1".equals(imMessageBean.getType())) {
                tvLastMessage.setText(imMessageBean.getContent());
            } else if ("3".equals(imMessageBean.getType())) {
                tvLastMessage.setText("[图片]");
            } else if ("34".equals(imMessageBean.getType())) {
                tvLastMessage.setText("[语音]");
            } else if ("43".equals(imMessageBean.getType())) {
                tvLastMessage.setText("[视频]");
            } else if ("49".equals(imMessageBean.getType())) {
                tvLastMessage.setText("[文件]");
            } else {
                tvLastMessage.setText(imMessageBean.getContent());
            }

            int count=0;
            for (int i = 0; i < bean.size(); i++) {
                ImMessageBean imMessageBean1 = bean.get(i);
                Log.e("is",imMessageBean1.toString()+"");
                if ("1".equals(imMessageBean1.getMsgState())&&"2".equals(imMessageBean1.getReceiverState())){
                    count=count+1;
                }
            }
            Log.e("is",count+"");
            if (count!=0) {

                tv_red_message.setVisibility(View.VISIBLE);
                tv_red_message.setText("" + count);
            }else {
                tv_red_message.setVisibility(View.INVISIBLE);
            }

        }
    }

    @Override
    protected void onItemClick(View itemView, int position, ArrayList<ImMessageBean> bean) {
        super.onItemClick(itemView, position, bean);
        ImMessageBean imMessageBean = bean.get(bean.size()-1);
        Intent intent=new Intent(context,ConverActivity.class);
        intent.putExtra("nickName",imMessageBean.getNickName());
        intent.putExtra("wxid",imMessageBean.getWxid());
        intent.putExtra("fromAccount",imMessageBean.getFromAccount());
        intent.putExtra("fid",imMessageBean.getId());
        intent.putExtra("headUrl",imMessageBean.getHeadUrl());
        context.startActivity(intent);

        EventBus.getDefault().postSticky(bean);
    }

}
