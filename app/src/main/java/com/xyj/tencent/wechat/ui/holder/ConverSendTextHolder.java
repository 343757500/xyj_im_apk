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
import com.xyj.tencent.wechat.model.bean.ImMessageBean;
import com.xyj.tencent.wechat.util.DateUtil;

public class ConverSendTextHolder extends BaseHolderRV {

    private TextView tv_content;
    private ImageView iv_user_send;
    private TextView tv_time;

    public ConverSendTextHolder(Context context, ViewGroup parent, BaseAdapterRV adapter, int itemType) {
        super(context, parent, adapter, itemType, R.layout.item_chating_send_text_success);
    }

    @Override
    public void onFindViews(View itemView) {
        tv_content = itemView.findViewById(R.id.tv_content);
        iv_user_send = itemView.findViewById(R.id.iv_user_send);
        tv_time = itemView.findViewById(R.id.tv_time);
    }

    @Override
    protected void onRefreshView(Object bean, int position) {
        ImMessageBean imMessageBean= (ImMessageBean) bean;
        tv_content.setText(imMessageBean.getContent());
        Picasso.with(context).load(imMessageBean.getHeadUrl()).into(iv_user_send);
        String s = DateUtil.longToString(imMessageBean.getConversationTime(), "MM月dd日 HH:mm");
        tv_time.setText(s + "");
    }
}
