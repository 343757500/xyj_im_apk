package com.xyj.tencent.wechat.model.db;

import android.database.Cursor;
import android.util.Log;

import com.xyj.tencent.common.base.MyApp;
import com.xyj.tencent.wechat.model.bean.ImMessageBean;

import org.apache.commons.lang3.StringUtils;

import java.util.List;

public class DBUtils {
    //获取每个聊天记录的最新的一条数据
    public static List<ImMessageBean> getNewestHistory(List imMessageBeans,String wechatId){
        Cursor cursor = MyApp.getDbs().rawQuery("select * from tb_chat where id in (select Max(id) from tb_chat group by wxno) and fromAccount =?" , new String[]{wechatId});
        while (cursor.moveToNext()){
            Log.e("111","-----"+cursor.getString(4)) ;
            ImMessageBean imMessageBean =new ImMessageBean();
            for (int i = 0; i < 11; i++) {
                imMessageBean.setWxno(cursor.getString(1));
                imMessageBean.setContent(cursor.getString(4));
                imMessageBean.setConversationTime(Long.parseLong(cursor.getString(5)));
                imMessageBean.setHeadUrl(cursor.getString(8));
                imMessageBean.setNickName(cursor.getString(9));
                imMessageBean.setRemarkName(cursor.getString(10));
                imMessageBean.setWxid(cursor.getString(7));
            }
            if (StringUtils.isNotBlank(imMessageBean.getWxid())) {
                imMessageBeans.add(imMessageBean);
            }
        }

        return imMessageBeans;
    }
}
