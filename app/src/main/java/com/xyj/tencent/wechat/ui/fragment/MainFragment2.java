package com.xyj.tencent.wechat.ui.fragment;

import android.nfc.Tag;
import android.os.Message;
import android.support.v7.widget.LinearLayoutManager;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.xyj.tencent.R;
import com.xyj.tencent.common.base.BaseFragment;
import com.xyj.tencent.common.util.SharedPreUtil;
import com.xyj.tencent.wechat.model.bean.LoginFriendGroups;
import com.xyj.tencent.wechat.model.protocol.IHttpService;
import com.xyj.tencent.wechat.presenter.MainFragment2Presenter;
import com.xyj.tencent.wechat.ui.adapter.MainFragment2Adapter;
import com.xyj.tencent.wechat.ui.expand.StickyRecyclerHeadersDecoration;
import com.xyj.tencent.wechat.ui.widget.DividerDecoration;
import com.xyj.tencent.wechat.ui.widget.SideBar;
import com.xyj.tencent.wechat.ui.widget.TouchableRecyclerView;
import com.xyj.tencent.wechat.ui.widget.ZSideBar;
import com.xyj.tencent.wechat.util.CharacterParser;
import com.xyj.tencent.wechat.util.PinyinComparator;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class MainFragment2 extends BaseFragment {

    private MainFragment2Presenter mainFragment2Presenter;
    private TouchableRecyclerView rv_contact_member;
    private SideBar contact_sidebar;
    private MainFragment2Adapter mainFragment2Adapter;
    CharacterParser characterParser;
    private List<LoginFriendGroups.ResultBean.GroupsBean.FriendsBean> friendsBeans;
    private PinyinComparator pinyinComparator;
    private ZSideBar contact_zsidebar;

    @Override
    public int getLayoutRes() {
        return R.layout.fragment_contact_version2;
    }

    @Override
    public void initView() {
        characterParser = CharacterParser.getInstance();
        pinyinComparator = new PinyinComparator();
        mainFragment2Presenter = new MainFragment2Presenter(this);
        rv_contact_member = findView(R.id.contact_member);
        contact_sidebar = findView(R.id.contact_sidebar);
        contact_zsidebar = findView(R.id.contact_zsidebar);
    }

    @Override
    public void initListener() {

    }

    @Override
    public void initData() {
        String ticket = SharedPreUtil.getString(getActivity(), "ticket", null);
        if (ticket!=null) {
            mainFragment2Presenter.getConstactData(ticket);
        }else{
            Toast.makeText(mActivity, "ticket为null", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onClick(View v, int id) {

    }

    @Override
    public void onHttpSuccess(int reqType, Message obj) {
        super.onHttpSuccess(reqType, obj);
        if (reqType== IHttpService.TYPE_CONSTDATA){
            LoginFriendGroups loginFriendGroups= (LoginFriendGroups) obj.obj;
            rv_contact_member.setLayoutManager(new LinearLayoutManager(getActivity()));

            initFriendList(loginFriendGroups.getResult());
            mainFragment2Adapter = new MainFragment2Adapter(getActivity(), friendsBeans);
            rv_contact_member.setAdapter(mainFragment2Adapter);

            //设置字母开头title
            StickyRecyclerHeadersDecoration srhDecoration=new StickyRecyclerHeadersDecoration(mainFragment2Adapter);
            rv_contact_member.addItemDecoration(srhDecoration);
            rv_contact_member.addItemDecoration(new DividerDecoration(getActivity()));

            contact_zsidebar.setupWithRecycler(rv_contact_member);

            contact_sidebar.setOnTouchingLetterChangedListener(new SideBar.OnTouchingLetterChangedListener() {

                @Override
                public void onTouchingLetterChanged(String s) {
                    if (mainFragment2Adapter != null) {
                        mainFragment2Adapter.closeOpenedSwipeItemLayoutWithAnim();
                    }
                    int position = mainFragment2Adapter.getPositionForSection(s.charAt(0));
                    if (position != -1) {
                        rv_contact_member.getLayoutManager().scrollToPosition(position);
                    }

                }
            });
        }
    }

    private void initFriendList(List<LoginFriendGroups.ResultBean> result) {
        if (friendsBeans!=null) {
            friendsBeans.clear();
        }

        int selectindex = SharedPreUtil.getInt(getActivity(), "selectindex", 0);
        friendsBeans = new ArrayList<>();
        LoginFriendGroups.ResultBean.GroupsBean.FriendsBean friendsBeano=new LoginFriendGroups.ResultBean.GroupsBean.FriendsBean();
        friendsBeano.setSortLetters("!!");
        friendsBeano.setRemarkname("新的朋友");
        friendsBeano.setNickname("新的朋友");
        friendsBeans.add(0,friendsBeano);
        for (int i = 0; i < result.get(selectindex).getGroups().size(); i++) {
            List<LoginFriendGroups.ResultBean.GroupsBean.FriendsBean> friends = result.get(selectindex).getGroups().get(i).getFriends();
            for (int j = 0; j < friends.size(); j++) {
                LoginFriendGroups.ResultBean.GroupsBean.FriendsBean friendsBean = friends.get(j);
                String name="";
                String nickname = friendsBean.getNickname();
                String remarkname = friendsBean.getRemarkname();
                if (TextUtils.equals("",remarkname)){
                    name=nickname;
                }else{
                    name=remarkname;
                }
                String quanPin = characterParser.getSelling(name);
                String sortQuanpin = quanPin.substring(0, 1).toUpperCase();
                if (sortQuanpin.matches("[A-Z]")){
                    friendsBean.setSortLetters(sortQuanpin);
                }else{
                    friendsBean.setSortLetters("#");
                }
                friendsBeans.add(friendsBean);
            }
        }

        Log.e("111",friendsBeans.toString()+"");
        Collections.sort(friendsBeans,pinyinComparator);
        Log.e("111",friendsBeans.toString()+"");
    }

    @Override
    public void onHttpError(int reqType, String error) {
        super.onHttpError(reqType, error);
    }
}
