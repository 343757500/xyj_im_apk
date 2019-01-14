package com.xyj.tencent.wechat.util;

import com.xyj.tencent.wechat.model.bean.LoginFriendGroups;

import java.util.Comparator;

public class PinyinComparator implements Comparator<LoginFriendGroups.ResultBean.GroupsBean.FriendsBean> {
    @Override
    public int compare(LoginFriendGroups.ResultBean.GroupsBean.FriendsBean t1, LoginFriendGroups.ResultBean.GroupsBean.FriendsBean t2) {
        if (t1.getSortLetters().equals("@")||t2.getSortLetters().equals("#")){
            return -1;
        }else if (t1.getSortLetters().equals("#")||t2.getSortLetters().equals("@")){
            return 1;
        }else{
            return t1.getSortLetters().compareTo(t2.getSortLetters());
        }


    }
}
