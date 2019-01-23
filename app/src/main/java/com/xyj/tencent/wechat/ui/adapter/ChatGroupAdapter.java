package com.xyj.tencent.wechat.ui.adapter;

import android.content.Context;
import android.view.ViewGroup;

import com.xyj.tencent.common.ui.BaseAdapterRV;
import com.xyj.tencent.common.ui.BaseHolderRV;
import com.xyj.tencent.wechat.model.bean.ImMessageBean;
import com.xyj.tencent.wechat.model.bean.LoginFriendGroups;
import com.xyj.tencent.wechat.ui.holder.ChatGroupHolder;
import com.xyj.tencent.wechat.ui.holder.UserSelectFriendGroupHolder;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public class ChatGroupAdapter extends BaseAdapterRV<ArrayList<ImMessageBean>> {
    public ChatGroupAdapter(Context context, List<ArrayList<ImMessageBean>> listData) {
        super(context, listData);
    }

    @Override
    public BaseHolderRV<ArrayList<ImMessageBean>> createViewHolder(Context context, ViewGroup parent, int viewType) {
        return new ChatGroupHolder(context,parent,this,viewType);
    }

}
