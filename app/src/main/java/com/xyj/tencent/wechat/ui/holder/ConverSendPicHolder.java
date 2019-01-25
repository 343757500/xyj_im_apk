package com.xyj.tencent.wechat.ui.holder;

import android.content.Context;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;
import com.xyj.tencent.R;
import com.xyj.tencent.common.ui.BaseAdapterRV;
import com.xyj.tencent.common.ui.BaseHolderRV;
import com.xyj.tencent.wechat.model.bean.ImMessageBean;

public class ConverSendPicHolder extends BaseHolderRV {

    private ImageView iv;

    public ConverSendPicHolder(Context context, ViewGroup parent, BaseAdapterRV adapter, int itemType) {
        super(context, parent, adapter, itemType, R.layout.item_chating_send_image_success);
    }

    @Override
    public void onFindViews(View itemView) {
        iv = itemView.findViewById(R.id.iv);
    }

    @Override
    protected void onRefreshView(Object bean, int position) {
        ImMessageBean imMessageBean= (ImMessageBean) bean;
        String content = imMessageBean.getContent();
        String sTemp = content.substring(1, content.length()-1);
        String[] sArray = sTemp.split(",");
        Log.e("111","本地图片地址"+sArray[0]);
        Picasso.with(context).load("file://"+sArray[0]).into(iv);
    }
}
