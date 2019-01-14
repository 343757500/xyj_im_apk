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
import com.xyj.tencent.wechat.model.bean.LoginFriendGroups;
import com.xyj.tencent.wechat.model.db.DBUtils;
import com.xyj.tencent.wechat.util.DateUtil;

import org.apache.commons.lang3.StringUtils;

import java.util.Date;

public class ChatGroupHolder extends BaseHolderRV<ImMessageBean> {

    private ImageView iv_user;
    private TextView tv_nickname;
    private TextView tvLastMessage;
    private TextView tv_time;

    public ChatGroupHolder(Context context, ViewGroup parent, BaseAdapterRV adapter, int itemType) {
        super(context, parent, adapter, itemType, R.layout.item_myconversation);
    }

    @Override
    public void onFindViews(View itemView) {
        iv_user = itemView.findViewById(R.id.iv_user);
        tv_nickname = itemView.findViewById(R.id.tv_nickname);
        tvLastMessage = itemView.findViewById(R.id.tv_lastMessage);
        tv_time = itemView.findViewById(R.id.tv_time);
    }

    @Override
    protected void onRefreshView(ImMessageBean bean, int position) {
        Picasso.with(context).load(bean.getHeadUrl()).into(iv_user);
        tvLastMessage.setText(bean.getContent());
        if (StringUtils.isNotBlank(bean.getRemarkName())){
            tv_nickname.setText(bean.getRemarkName());
        }else{
            tv_nickname.setText(bean.getNickName());
        }
        String s = DateUtil.longToString(bean.getConversationTime(), "MM月dd日 HH:mm");
        tv_time.setText(s+"");

    }


}
