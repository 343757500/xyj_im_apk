package com.xyj.tencent.wechat.ui.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.xyj.tencent.R;
import com.xyj.tencent.common.ui.BaseAdapterRV;
import com.xyj.tencent.common.ui.BaseHolderRV;
import com.xyj.tencent.wechat.model.bean.LoginFriendGroupString;
import com.xyj.tencent.wechat.model.bean.LoginFriendGroups;
import com.xyj.tencent.wechat.ui.expand.StickyRecyclerHeadersAdapter;
import com.xyj.tencent.wechat.ui.holder.MainFragment2Holder;
import com.xyj.tencent.wechat.ui.widget.IndexAdapter;
import com.xyj.tencent.wechat.ui.widget.SwipeItemLayout;

import java.util.ArrayList;
import java.util.List;

public class MainFragment2Adapter extends BaseAdapterRV<LoginFriendGroups.ResultBean.GroupsBean.FriendsBean> implements StickyRecyclerHeadersAdapter<RecyclerView.ViewHolder>,IndexAdapter {
    /**
     * 当前处于打开状态的item
     */
    private List<SwipeItemLayout> mOpenedSil = new ArrayList<>();

    public MainFragment2Adapter(Context context, List<LoginFriendGroups.ResultBean.GroupsBean.FriendsBean> listData) {
        super(context, listData);
    }

    @Override
    public BaseHolderRV<LoginFriendGroups.ResultBean.GroupsBean.FriendsBean> createViewHolder(Context context, ViewGroup parent, int viewType) {
            return new MainFragment2Holder(context,parent,this,viewType);
    }


    public void closeOpenedSwipeItemLayoutWithAnim() {
        for (SwipeItemLayout sil : mOpenedSil) {
            sil.closeWithAnim();
        }
        mOpenedSil.clear();
    }


    public int getPositionForSection(char section) {
        for (int i = 0; i < getItemCount(); i++) {
            String sortStr = listData.get(i).getSortLetters();
            char firstChar = sortStr.toUpperCase().charAt(0);
            if (firstChar == section) {
                return i;
            }
        }
        return -1;

    }

    @Override
    public long getHeaderId(int position) {
        return getItem(position).getSortLetters().charAt(0);
    }

    @Override
    public void onBindHeaderViewHolder(RecyclerView.ViewHolder holder, int position) {
        TextView textView= (TextView) holder.itemView;
        String showValue = String.valueOf(getItem(position).getSortLetters());
        if ("$".equals(showValue)) {
            textView.setText("群主");
        } else if ("%".equals(showValue)) {
            textView.setText("系统管理员");

        } else {
            textView.setText(showValue);
        }
    }

    @Override
    public RecyclerView.ViewHolder onCreateHeaderViewHolder(ViewGroup parent) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.contact_header, parent, false);
        return new RecyclerView.ViewHolder(view) {
        };
    }



}
