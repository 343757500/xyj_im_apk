package com.xyj.tencent.wechat.model.db;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.xyj.tencent.common.base.MyApp;
import com.xyj.tencent.wechat.model.bean.ImMessageBean;
import com.xyj.tencent.wechat.util.IsReadUtil;

import org.apache.commons.lang3.StringUtils;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public class DBUtils {
    //      Cursor cursor = MyApp.getDbs().rawQuery("select * from tb_chat where id in (select Max(id) from tb_chat group by wxno) and fromAccount =?" , new String[]{wechatId});
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



    public static ArrayList<ImMessageBean> getNewHistory( String wxid, String wechatId){
        ArrayList<ImMessageBean> imMessageBeans=new ArrayList<>();
        Cursor cursor = MyApp.getDbs().rawQuery("select * from tb_chat where wxid =? and fromAccount =? order by conversationTime asc" , new String[]{wxid,wechatId});
        while (cursor.moveToNext()){
            Log.e("111","-----"+cursor.getString(4)) ;
            ImMessageBean imMessageBean =new ImMessageBean();
            for (int i = 0; i < 11; i++) {
                imMessageBean.setWxno(cursor.getString(1));
                imMessageBean.setFromAccount(cursor.getString(2));
                imMessageBean.setToAccount(cursor.getString(3));
                imMessageBean.setContent(cursor.getString(4));
                imMessageBean.setConversationTime(Long.parseLong(cursor.getString(5)));
                imMessageBean.setHeadUrl(cursor.getString(8));
                imMessageBean.setNickName(cursor.getString(9));
                imMessageBean.setRemarkName(cursor.getString(10));
                imMessageBean.setWxid(cursor.getString(7));
                imMessageBean.setType(cursor.getString(6));
                imMessageBean.setReceiverState(cursor.getString(11));
                imMessageBean.setId(cursor.getString(12));
                imMessageBean.setMsgState(cursor.getString(13));
                imMessageBean.setF_id(cursor.getString(14));
            }
            //if (StringUtils.isNotBlank(cursor.getString(4))) {
                imMessageBeans.add(imMessageBean);
            //}
        }

        return imMessageBeans;
    }


    public static String selectFbyWxid(String wxid){
        Cursor cursor = MyApp.getDbs().rawQuery("select * from tb_chat where wxid =? " , new String[]{wxid});
        while (cursor.moveToNext()){
            return cursor.getString(2);
        }
        return "";

    }


    public static void insertImMessageBean(ImMessageBean messageBean) {
        ContentValues values=new ContentValues();
        values.put("wxno",messageBean.getWxno());
        values.put("fromAccount",messageBean.getFromAccount());
        values.put("toAccount",messageBean.getToAccount());
        values.put("content",messageBean.getContent());
        values.put("conversationTime",messageBean.getConversationTime());
        values.put("type",messageBean.getType());
        values.put("wxid",messageBean.getWxid());
        values.put("headUrl",messageBean.getHeadUrl());
        values.put("nickName",messageBean.getNickName());
        values.put("remarkName",messageBean.getRemarkName());
        values.put("fid",messageBean.getId());
        values.put("msgState",messageBean.getMsgState());
        Log.e("is", IsReadUtil.isConverActivity+":"+messageBean.getId()+":"+IsReadUtil.fid);
        if (IsReadUtil.isConverActivity&&messageBean.getId().equals(IsReadUtil.fid)){
            values.put("receiverState","0");//(接收状态 0接收成功且已读 1接收失败 2接收成功但是未读)
        }else {
            values.put("receiverState","2");
        }
        MyApp.getDbs().insert("tb_chat",null,values);
    }

    /**
     * 根据wechatId查询有多少条未读消息
     * **/
    public static  int getUnReadMsgByWechatId(String wechatId){
        SQLiteDatabase db = MyApp.getDbs();
        Cursor cursor = db.rawQuery("select id from tb_chat where fromAccount=? and receiverState=?", new String[]{wechatId, "2"});
        int count = cursor.getCount();
        cursor.close();
        return count;
    }

}
