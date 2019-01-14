package com.xyj.tencent.wechat.presenter;

import com.xyj.tencent.wechat.base.BasePresenter;
import com.xyj.tencent.wechat.base.BaseView;

import java.util.Map;

public class LoginPresenter extends BasePresenter {
    public LoginPresenter(BaseView baseView) {
        super(baseView);
    }

    public void login(Map<String ,String> map){
        mProtocol.login(mBaseCallback,map);
    }

    public void loginTicket(String ticket){
        mProtocol.loginTest(mBaseCallback,ticket);
    }

    public void loginFriendGroup(String ticket){
        mProtocol.loginFriendGroup(mBaseCallback,ticket);
    }
}
