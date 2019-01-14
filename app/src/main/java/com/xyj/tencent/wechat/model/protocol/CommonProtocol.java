package com.xyj.tencent.wechat.model.protocol;


import com.xyj.tencent.wechat.model.bean.Login;
import com.xyj.tencent.wechat.model.bean.LoginFriendGroups;
import com.xyj.tencent.wechat.model.bean.LoginTicket;

import java.util.Map;

/**
 * 网络请求
 *
 * @author WJQ
 */
public class CommonProtocol extends BaseProtocol {

    /** 登录：使用账号密码登录 */
    public void login(final OnHttpCallback callback,
                      Map<String,String> map) {
        super.execute(super.getHttpService().login(map),
                callback, IHttpService.TYPE_LOGIN, Login.class);
    }


    /** 登录：使用账号密码登录 */
    public void loginTest(final OnHttpCallback callback,
                      String ticket) {
        super.execute(super.getHttpService().loginTicket(ticket),
                callback, IHttpService.TYPE_LOGINTICKET, LoginTicket.class);
    }

    /** 登录：使用账号密码登录 */
    public void loginFriendGroup(final OnHttpCallback callback,
                          String ticket) {
        super.execute(super.getHttpService().loginFriendGroup(ticket),
                callback, IHttpService.TYPE_LOGINFRIENDGROUP, LoginFriendGroups.class);
    }


    public void contactData(final OnHttpCallback callback,
                                 String ticket) {
        super.execute(super.getHttpService().contactData(ticket),
                callback, IHttpService.TYPE_CONSTDATA, LoginFriendGroups.class);
    }
}
