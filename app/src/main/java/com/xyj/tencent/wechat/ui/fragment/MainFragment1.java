package com.xyj.tencent.wechat.ui.fragment;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.squareup.picasso.Picasso;
import com.tencent.imsdk.TIMElem;
import com.tencent.imsdk.TIMElemType;
import com.tencent.imsdk.TIMManager;
import com.tencent.imsdk.TIMMessage;
import com.tencent.imsdk.TIMMessageListener;
import com.tencent.imsdk.TIMTextElem;
import com.tencent.imsdk.TIMUserProfile;
import com.xyj.tencent.R;
import com.xyj.tencent.common.base.BaseFragment;
import com.xyj.tencent.common.base.MyApp;
import com.xyj.tencent.common.util.SharedPreUtil;
import com.xyj.tencent.wechat.model.bean.ImMessageBean;
import com.xyj.tencent.wechat.model.bean.LoginFriendGroups;
import com.xyj.tencent.wechat.model.db.DBUtils;
import com.xyj.tencent.wechat.model.db.MyDbHelper;
import com.xyj.tencent.wechat.ui.adapter.ChatGroupAdapter;
import com.xyj.tencent.wechat.ui.adapter.UserSelectAdapter;
import com.xyj.tencent.wechat.ui.adapter.UserSelectFragementAdapter;
import com.xyj.tencent.wechat.ui.adapter.UserSelectFriendGroupAdapter;

import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

public class MainFragment1 extends BaseFragment {

    private RecyclerView rv_select;
    private RecyclerView rv_list;
    private TextView tv_current;
    private ImageView iv_current;
    private List<ImMessageBean> imMessageBeans=new ArrayList<>();
    private List<String> imMessageName=new ArrayList<>();
    private ChatGroupAdapter chatGroupAdapter;

    @Override
    public int getLayoutRes() {
        return R.layout.fragment_conversation_version2;
    }

    @Override
    public void initView() {
        rv_select = findView(R.id.rv_select);
        rv_list = findView(R.id.rv_list);
        tv_current = findView(R.id.tv_current);
        iv_current = findView(R.id.iv_current);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        rv_select.setLayoutManager(linearLayoutManager);
        rv_select.setAdapter(new UserSelectFragementAdapter(getActivity(), MyApp.getGroupFriendsBean().getResult()));

        rv_list.setLayoutManager(new LinearLayoutManager(getActivity()));
        //rv_list.setAdapter(new UserSelectFriendGroupAdapter());

        NewMessageListener();
    }

    private void NewMessageListener() {
        MyDbHelper myDBHelper = new MyDbHelper(getActivity());
        SQLiteDatabase writableDatabase = myDBHelper.getWritableDatabase();
        TIMManager.getInstance().addMessageListener(new TIMMessageListener() {


            @Override
            public boolean onNewMessages(List<TIMMessage> list) {

                for (int i = 0; i < list.size(); i++) {
                    TIMMessage timMessage = list.get(i);
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


                                        //if (!imMessageName.contains(messageBean.getWxid())) {
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
                                            Collections.sort(imMessageBeans);
                                            chatGroupAdapter = new ChatGroupAdapter(getActivity(), imMessageBeans);
                                            rv_list.setAdapter(chatGroupAdapter);
                                            chatGroupAdapter.notifyDataSetChanged();
                                            insertImMessageBean(messageBean);

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

    private void insertImMessageBean(ImMessageBean messageBean) {
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
        MyApp.getDbs().insert("tb_chat",null,values);
    }

    @Override
    public void initListener() {

    }

    @Override
    public void initData() {


        List<LoginFriendGroups.ResultBean> result = MyApp.getGroupFriendsBean().getResult();
        int selectindex = SharedPreUtil.getInt(getActivity(), "selectindex", -1);
        String headImgUrl = result.get(selectindex).getHeadImgUrl();
        String nickname = result.get(selectindex).getNickname();
        String wechatId = result.get(selectindex).getWechatId();
        tv_current.setText(nickname+"  的好友");
        Picasso.with(getActivity()).load(headImgUrl).into(iv_current);


        imMessageBeans.clear();
        //初始化聊天记录最新一条
        //select * from tb_chat where id in (select Max(id) from tb_chat group by wxno);
        imMessageBeans = DBUtils.getNewestHistory(imMessageBeans, wechatId);

        Collections.sort(imMessageBeans);
        chatGroupAdapter = new ChatGroupAdapter(getActivity(), imMessageBeans);
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
}