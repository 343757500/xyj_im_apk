package com.xyj.tencent.wechat.ui.holder;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;
import com.xyj.tencent.R;
import com.xyj.tencent.common.ui.BaseAdapterRV;
import com.xyj.tencent.common.ui.BaseHolderRV;
import com.xyj.tencent.wechat.model.bean.ImMessageBean;

public class ConverPicHolder extends BaseHolderRV {

    private ImageView iv;

    public ConverPicHolder(Context context, ViewGroup parent, BaseAdapterRV adapter, int itemType) {
        super(context, parent, adapter, itemType, R.layout.item_chating_receiver_image_success);
    }

    @Override
    public void onFindViews(View itemView) {
        iv = itemView.findViewById(R.id.iv);

    }

    @Override
    protected void onRefreshView(Object bean, int position) {
        ImMessageBean imMessageBean= (ImMessageBean) bean;
        String content = imMessageBean.getContent();
        //返回来的url是http的字符串截取拼接成https
        String a=  content.substring(0,4)+"s";
        String b= content.substring(4,content.length());
        Picasso.with(context).load(a+b).into(iv);
    }
}
