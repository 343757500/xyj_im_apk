package com.xyj.tencent.wechat.model.bean;

public class SendMsgBean {


    /**
     * wxno : a54414512
     * wxid : wxid_kwd8tbhbbgsr22
     * fromAccount : 054c6eda53784528af3e20b992319a98
     * toAccount : 2d9921a545024472b10c6f929e9ad3d7
     * content : 测
     * conversationTime : 1542625414000
     * type : 1
     * loaded : true
     * msgId : 3adb03ad2aed4a699726444db731b119
     * id : 9841501a428a4b80b402a63beadf4443
     */

    private String wxno;
    private String wxid;
    private String fromAccount;
    private String toAccount;
    private String content;
    private long conversationTime;
    private int type;
    private boolean loaded;
    private String msgId;
    private String id;
    private String nickName;
    private String remarkName;
    private String headUrl;

    public String getRemarkName() {
        return remarkName;
    }

    public void setRemarkName(String remarkName) {
        this.remarkName = remarkName;
    }


    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }



    public String getHeadUrl() {
        return headUrl;
    }

    public void setHeadUrl(String headUrl) {
        this.headUrl = headUrl;
    }



    public SendMsgBean() {
    }

    public SendMsgBean(String wxno, String wxid, String fromAccount, String toAccount, String content, long conversationTime, int type, boolean loaded, String id,String headUrl,String nickName,String remarkName) {
        this.wxno = wxno;
        this.wxid = wxid;
        this.fromAccount = fromAccount;
        this.toAccount = toAccount;
        this.content = content;
        this.conversationTime = conversationTime;
        this.type = type;
        this.loaded = loaded;
        this.msgId = msgId;
        this.id = id;
        this.headUrl=headUrl;
        this.nickName=nickName;
        this.remarkName=remarkName;

    }

    public String getWxno() {
        return wxno;
    }

    public void setWxno(String wxno) {
        this.wxno = wxno;
    }

    public String getWxid() {
        return wxid;
    }

    public void setWxid(String wxid) {
        this.wxid = wxid;
    }

    public String getFromAccount() {
        return fromAccount;
    }

    public void setFromAccount(String fromAccount) {
        this.fromAccount = fromAccount;
    }

    public String getToAccount() {
        return toAccount;
    }

    public void setToAccount(String toAccount) {
        this.toAccount = toAccount;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public long getConversationTime() {
        return conversationTime;
    }

    public void setConversationTime(long conversationTime) {
        this.conversationTime = conversationTime;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public boolean isLoaded() {
        return loaded;
    }

    public void setLoaded(boolean loaded) {
        this.loaded = loaded;
    }

    public String getMsgId() {
        return msgId;
    }

    public void setMsgId(String msgId) {
        this.msgId = msgId;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
