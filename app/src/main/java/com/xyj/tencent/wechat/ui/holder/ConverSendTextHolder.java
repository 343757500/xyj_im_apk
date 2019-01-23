package com.xyj.tencent.wechat.ui.holder;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.xyj.tencent.R;
import com.xyj.tencent.common.ui.BaseAdapterRV;
import com.xyj.tencent.common.ui.BaseHolderRV;
import com.xyj.tencent.wechat.model.bean.ImMessageBean;

public class ConverSendTextHolder extends BaseHolderRV {

    private TextView tv_content;

    public ConverSendTextHolder(Context context, ViewGroup parent, BaseAdapterRV adapter, int itemType) {
        super(context, parent, adapter, itemType, R.layout.item_chating_send_text_success);
    }

    @Override
    public void onFindViews(View itemView) {
        tv_content = itemView.findViewById(R.id.tv_content);
    }

    @Override
    protected void onRefreshView(Object bean, int position) {
        ImMessageBean imMessageBean= (ImMessageBean) bean;
        tv_content.setText(imMessageBean.getContent()   );
    }
}
