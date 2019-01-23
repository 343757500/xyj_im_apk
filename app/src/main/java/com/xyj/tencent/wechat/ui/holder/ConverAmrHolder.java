package com.xyj.tencent.wechat.ui.holder;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;

import com.xyj.tencent.R;
import com.xyj.tencent.common.ui.BaseAdapterRV;
import com.xyj.tencent.common.ui.BaseHolderRV;

public class ConverAmrHolder extends BaseHolderRV {
    public ConverAmrHolder(Context context, ViewGroup parent, BaseAdapterRV adapter, int itemType) {
        super(context, parent, adapter, itemType, R.layout.item_chating_receiver_voice_success);
    }

    @Override
    public void onFindViews(View itemView) {

    }

    @Override
    protected void onRefreshView(Object bean, int position) {

    }
}
