package com.xyj.tencent.wechat.ui.adapter;

import android.content.Context;
import android.view.ViewGroup;

import com.xyj.tencent.common.ui.BaseAdapterRV;
import com.xyj.tencent.common.ui.BaseHolderRV;
import com.xyj.tencent.wechat.model.bean.ImMessageBean;
import com.xyj.tencent.wechat.ui.holder.ConverAmrHolder;
import com.xyj.tencent.wechat.ui.holder.ConverFileHolder;
import com.xyj.tencent.wechat.ui.holder.ConverHolder;
import com.xyj.tencent.wechat.ui.holder.ConverPicHolder;
import com.xyj.tencent.wechat.ui.holder.ConverSendFileHolder;
import com.xyj.tencent.wechat.ui.holder.ConverSendPicHolder;
import com.xyj.tencent.wechat.ui.holder.ConverSendTextHolder;
import com.xyj.tencent.wechat.ui.holder.ConverSendVideoHolder;
import com.xyj.tencent.wechat.ui.holder.ConverVideoHolder;

import java.util.List;

public class ConverAdapter extends BaseAdapterRV {

    public ConverAdapter(Context context, List listData) {
        super(context, listData);
    }

    @Override
    public BaseHolderRV createViewHolder(Context context, ViewGroup parent, int viewType) {
        if (viewType==1){
            return new ConverHolder(context,parent,this,viewType);
        }

        if (viewType==2){
            return new ConverSendTextHolder(context,parent,this,viewType);
        }

        if (viewType==3){
            return new ConverPicHolder(context,parent,this,viewType);
        }

        if (viewType==4){
            return new ConverSendPicHolder(context,parent,this,viewType);
        }

        if (viewType==34){
            return new ConverAmrHolder(context,parent,this,viewType);
        }


        if (viewType==48){
            return new ConverSendFileHolder(context,parent,this,viewType);
        }
        if (viewType==49){
            return new ConverFileHolder(context,parent,this,viewType);
        }

        if (viewType==43){
            return new ConverVideoHolder(context,parent,this,viewType);
        }

        if (viewType==44){
            return new ConverSendVideoHolder(context,parent,this,viewType);
        }

        return new ConverHolder(context,parent,this,viewType);
    }


    @Override
    public int getItemViewType(int position) {
        ImMessageBean item = (ImMessageBean) getItem(position);
        if (item.getType().equals("1")){
            if ("0".equals(item.getMsgState())){
                return 2;
            }else{
                return 1;
            }

        }else if (item.getType().equals("2")) {
            return 2;
        }else if (item.getType().equals("3")){
            if ("0".equals(item.getMsgState())){
                return 4;
            }else{
                return 3;
            }
        } else if (item.getType().equals("34")){
            return 34;
        }else if (item.getType().equals("43")){
            if ("0".equals(item.getMsgState())){
                return 44;
            }else{
                return 43;
            }
        }else if (item.getType().equals("49")){
            if ("0".equals(item.getMsgState())){
                return 48;
            }else{
                return 49;
            }
        }
        return 1;
    }
}
