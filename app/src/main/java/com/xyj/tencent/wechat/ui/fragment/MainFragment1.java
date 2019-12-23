package com.xyj.tencent.wechat.ui.fragment;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.alibaba.fastjson.JSONException;
import com.alibaba.fastjson.JSONObject;
import com.google.gson.Gson;
import com.squareup.picasso.Picasso;
import com.tencent.imsdk.TIMElem;
import com.tencent.imsdk.TIMElemType;
import com.tencent.imsdk.TIMManager;
import com.tencent.imsdk.TIMMessage;
import com.tencent.imsdk.TIMMessageListener;
import com.tencent.imsdk.TIMMessageOfflinePushSettings;
import com.tencent.imsdk.TIMTextElem;
import com.tencent.imsdk.TIMUserProfile;
import com.xyj.tencent.R;
import com.xyj.tencent.common.base.BaseFragment;
import com.xyj.tencent.common.base.MyApp;
import com.xyj.tencent.common.util.SharedPreUtil;
import com.xyj.tencent.wechat.event.OnlineEvent;
import com.xyj.tencent.wechat.model.bean.ImMessageBean;
import com.xyj.tencent.wechat.model.bean.LoginFriendGroups;
import com.xyj.tencent.wechat.model.db.DBUtils;
import com.xyj.tencent.wechat.model.db.MyDbHelper;
import com.xyj.tencent.wechat.ui.activity.ConverActivity;
import com.xyj.tencent.wechat.ui.adapter.ChatGroupAdapter;
import com.xyj.tencent.wechat.ui.adapter.UserSelectAdapter;
import com.xyj.tencent.wechat.ui.adapter.UserSelectFragementAdapter;
import com.xyj.tencent.wechat.ui.adapter.UserSelectFriendGroupAdapter;
import com.xyj.tencent.wechat.util.IsReadUtil;

import org.apache.commons.lang3.StringUtils;
import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.io.UnsupportedEncodingException;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

public class MainFragment1 extends BaseFragment {

    private RecyclerView rv_select;
    private RecyclerView rv_list;
    private TextView tv_current;
    private ImageView iv_current;
    private ArrayList<ImMessageBean> imMessageBeans=new ArrayList<>();
    //private static List<List<ImMessageBean>> list ;
    private List<String> imMessageName=new ArrayList<>();
    private ChatGroupAdapter chatGroupAdapter;
    private UserSelectFragementAdapter userSelectFragementAdapter;

    @Override
    public int getLayoutRes() {
        return R.layout.fragment_conversation_version2;
    }

    @Override
    public void initView() {
        EventBus.getDefault().register(this);

        rv_select = findView(R.id.rv_select);
        rv_list = findView(R.id.rv_list);
        tv_current = findView(R.id.tv_current);
        iv_current = findView(R.id.iv_current);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        rv_select.setLayoutManager(linearLayoutManager);
        userSelectFragementAdapter = new UserSelectFragementAdapter(getActivity(), MyApp.getGroupFriendsBean().getResult());
        rv_select.setAdapter(userSelectFragementAdapter);

        rv_list.setLayoutManager(new LinearLayoutManager(getActivity()));
        //rv_list.setAdapter(new UserSelectFriendGroupAdapter());

        NewMessageListener();


    }

    private void NewMessageListener() {
        TIMManager.getInstance().addMessageListener(new TIMMessageListener() {
            @Override
            public boolean onNewMessages(List<TIMMessage> list) {

                for (int i = 0; i < list.size(); i++) {
                    TIMMessage timMessage = list.get(i);
                    String msgId = timMessage.getMsgId();
                    for (int j = 0; j < timMessage.getElementCount(); j++) {
                        TIMElem timElem = timMessage.getElement(j);
                        //获取当前元素的类型
                        TIMElemType elemType = timElem.getType();
                        Log.d("111", "elem type: " + elemType.name());
                        if (elemType == TIMElemType.Text) {
                            //处理文本消息
                            TIMUserProfile sendUser = timMessage.getSenderProfile();
                            String sender = timMessage.getSender();
                            final TIMTextElem textElem = (TIMTextElem) timElem;
                            if (!timMessage.isSelf()) {
                                if (!TextUtils.isEmpty(textElem.getText().replaceAll("&quot;", "\""))) {
                                    String s = textElem.getText().replaceAll("&quot;", "\"");
                                    ImMessageBean messageBean = new Gson().fromJson(textElem.getText().replaceAll("&quot;", "\""), ImMessageBean.class);
                                    if (!"202".equals(messageBean.getType())){
                                        Log.e("111","收到消息了。。。。。" + textElem.getText().replaceAll("&quot;", "\""));
                                        boolean contains = imMessageName.contains(messageBean.getWxno());
                                        Log.e("111",contains+"");
                                        //这里处理数据集合中存在则删除的逻辑，防止同一个聊天recelyview显示多条item
                                        for (int k = 0; k < imMessageBeans.size(); k++) {
                                            if (imMessageBeans.get(k).getWxid().contains(messageBean.getWxid())){
                                                imMessageBeans.remove(k);
                                            }
                                        }
                                            //从数据库中获取判断
                                            Cursor cursor = MyApp.getDbs().query("im_message", new String[]{"wxid","headUrl","nickName","remarkName","imwechatId"}, null, null, null, null, null, null);
                                            while (cursor.moveToNext()){
                                                String dbWxid = cursor.getString(0);
                                                if (dbWxid.equals(messageBean.getWxid())&&cursor.getString(4).equals(messageBean.getFromAccount())){
                                                    messageBean.setHeadUrl(cursor.getString(1));
                                                    messageBean.setNickName(cursor.getString(2));
                                                    messageBean.setRemarkName(cursor.getString(3));
                                                }
                                            }

                                            imMessageBeans.add(messageBean);
                                            imMessageName.add(messageBean.getWxid());
                                            insertImMessageBean(messageBean,timMessage);
                                            initData();

                                             EventBus.getDefault().postSticky(imMessageBeans);

                                     //接收到在线状态
                                    }else if ("202".equals(messageBean.getType())){


                                    }
                                }
                            }
                        }
                    }

                }
                return true;
            }
        });
    }

    private void insertImMessageBean(ImMessageBean messageBean,TIMMessage TIMMessage) {
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
        values.put("msgState","1");
        values.put("fmsgId",messageBean.getMsgId());
        Log.e("is",IsReadUtil.isConverActivity+":"+messageBean.getId()+":"+IsReadUtil.fid);
        if (IsReadUtil.isConverActivity&&messageBean.getId().equals(IsReadUtil.fid)){
            values.put("receiverState","0");//(接收状态 0接收成功且已读 1接收失败 2接收成功但是未读)
        }else {
            values.put("receiverState","2");
        }
        MyApp.getDbs().insert("tb_chat",null,values);
    }

    @Override
    public void initListener() {

    }

    @Override
    public void initData() {
       ArrayList<ArrayList<ImMessageBean>> list=new ArrayList<>();

        List<LoginFriendGroups.ResultBean> result = MyApp.getGroupFriendsBean().getResult();
        int selectindex = SharedPreUtil.getInt(getActivity(), "selectindex", -1);
        String headImgUrl = result.get(selectindex).getHeadImgUrl();
        String nickname = result.get(selectindex).getNickname();
        String wechatId = result.get(selectindex).getWechatId();

        SharedPreUtil.saveString(getActivity(),"wechatId",wechatId);
        tv_current.setText(nickname+"  的好友");
        Picasso.with(getActivity()).load(headImgUrl).into(iv_current);


        imMessageBeans.clear();
        //初始化聊天记录最新一条
        //select * from tb_chat where id in (select Max(id) from tb_chat group by wxno);
        //imMessageBeans = DBUtils.getNewestHistory(imMessageBeans, wechatId);

        int size = MyApp.getGroupFriendsBean().getResult().get(selectindex).getGroups().size();
        for (int i = 0; i < size; i++) {
            LoginFriendGroups.ResultBean.GroupsBean groupsBean = MyApp.getGroupFriendsBean().getResult().get(selectindex).getGroups().get(i);
            List<LoginFriendGroups.ResultBean.GroupsBean.FriendsBean> friends = groupsBean.getFriends();
            for (int j = 0; j < friends.size(); j++) {
                ArrayList<ImMessageBean> newHistory = DBUtils.getNewHistory(friends.get(j).getWxid(), wechatId);
                if (newHistory.size()>0){
                    list.add(newHistory);
                }
            }
        }
       // Collections.reverse(list);
        chatGroupAdapter = new ChatGroupAdapter(getActivity(), list);
        rv_list.setAdapter(chatGroupAdapter);
        chatGroupAdapter.notifyDataSetChanged();






        SQLiteDatabase dbs = MyApp.getDbs();
        dbs.delete("im_message",null,null);

        List<LoginFriendGroups.ResultBean.GroupsBean> groups = result.get(selectindex).getGroups();
        for (int i = 0; i < groups.size(); i++) {
            for (int j = 0; j < groups.get(i).getFriends().size(); j++) {
                ContentValues values=new ContentValues();
                values.put("wxno",groups.get(i).getFriends().get(j).getWxno());
                values.put("headUrl",groups.get(i).getFriends().get(j).getHeadImgUrl());
                values.put("wxid",groups.get(i).getFriends().get(j).getWxid());
                values.put("nickName",groups.get(i).getFriends().get(j).getNickname());
                values.put("remarkName",groups.get(i).getFriends().get(j).getRemarkname());
                values.put("imwechatId",result.get(selectindex).getWechatId());
                dbs.insert("im_message",null,values);
            }
        }


    }

    @Override
    public void onClick(View v, int id) {

    }


    @Override
    public void onResume() {
        super.onResume();
       initData();
    }


    @Subscribe(threadMode = ThreadMode.MAIN,sticky = true)
    public void onOnlineEvent(OnlineEvent onlineEvent){
        ArrayList<ArrayList<ImMessageBean>> list=new ArrayList<>();

        List<LoginFriendGroups.ResultBean> result = MyApp.getGroupFriendsBean().getResult();
        int selectindex = SharedPreUtil.getInt(getActivity(), "selectindex", -1);
        String headImgUrl = result.get(selectindex).getHeadImgUrl();
        String nickname = result.get(selectindex).getNickname();
        String wechatId = result.get(selectindex).getWechatId();

        SharedPreUtil.saveString(getActivity(),"wechatId",wechatId);
        tv_current.setText(nickname+"  的好友");
        Picasso.with(getActivity()).load(headImgUrl).into(iv_current);


        imMessageBeans.clear();
        //初始化聊天记录最新一条
        //select * from tb_chat where id in (select Max(id) from tb_chat group by wxno);
        //imMessageBeans = DBUtils.getNewestHistory(imMessageBeans, wechatId);

        int size = MyApp.getGroupFriendsBean().getResult().get(selectindex).getGroups().size();
        for (int i = 0; i < size; i++) {
            LoginFriendGroups.ResultBean.GroupsBean groupsBean = MyApp.getGroupFriendsBean().getResult().get(selectindex).getGroups().get(i);
            List<LoginFriendGroups.ResultBean.GroupsBean.FriendsBean> friends = groupsBean.getFriends();
            for (int j = 0; j < friends.size(); j++) {
                ArrayList<ImMessageBean> newHistory = DBUtils.getNewHistory(friends.get(j).getWxid(), wechatId);
                if (newHistory.size()>0){
                    list.add(newHistory);
                }
            }
        }
        // Collections.reverse(list);
        chatGroupAdapter = new ChatGroupAdapter(getActivity(), list);
        rv_list.setAdapter(chatGroupAdapter);
        chatGroupAdapter.notifyDataSetChanged();






        SQLiteDatabase dbs = MyApp.getDbs();
        dbs.delete("im_message",null,null);

        List<LoginFriendGroups.ResultBean.GroupsBean> groups = result.get(selectindex).getGroups();
        for (int i = 0; i < groups.size(); i++) {
            for (int j = 0; j < groups.get(i).getFriends().size(); j++) {
                ContentValues values=new ContentValues();
                values.put("wxno",groups.get(i).getFriends().get(j).getWxno());
                values.put("headUrl",groups.get(i).getFriends().get(j).getHeadImgUrl());
                values.put("wxid",groups.get(i).getFriends().get(j).getWxid());
                values.put("nickName",groups.get(i).getFriends().get(j).getNickname());
                values.put("remarkName",groups.get(i).getFriends().get(j).getRemarkname());
                values.put("imwechatId",result.get(selectindex).getWechatId());
                dbs.insert("im_message",null,values);
            }
        }


    }

    @Override
    public void onDestroy() {

        EventBus.getDefault().unregister(this);
        super.onDestroy();
    }
}
