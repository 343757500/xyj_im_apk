package com.xyj.tencent.wechat.model.bean;

import android.support.annotation.NonNull;

public class ImMessageBean implements Comparable{
    /**
     * wxno : Lyh_HoiRab
     * fromAccount : 70faf75c6b174d32ba258686dfd8ffb7
     * toAccount : 9d1b86f5fdaf459b9dc773d8ef4663f1
     * content : hello
     * conversationTime : 1531897001302
     * type : 1
     */

    private String wxno;
    private String fromAccount;
    private String toAccount;
    private String content;
    private long conversationTime;
    private String type;
    private String wxid;
    private String headUrl;
    private String nickName;
    private String remarkName;
    private String receiverState;
    private String id;

    public String getMsgState() {
        return msgState;
    }

    public void setMsgState(String msgState) {
        this.msgState = msgState;
    }

    private String msgState;


    public String getWxid() {
        return wxid;
    }

    public void setWxid(String wxid) {
        this.wxid = wxid;
    }



    public String getWxno() {
        return wxno;
    }

    public void setWxno(String wxno) {
        this.wxno = wxno;
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

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getHeadUrl() {
        return headUrl;
    }

    public void setHeadUrl(String headUrl) {
        this.headUrl = headUrl;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getRemarkName() {
        return remarkName;
    }

    public void setRemarkName(String remarkName) {
        this.remarkName = remarkName;
    }

    @Override
    public int compareTo(@NonNull Object another) {
       return -1;
    }

    public String getReceiverState() {
        return receiverState;
    }

    public void setReceiverState(String receiverState) {
        this.receiverState = receiverState;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "ImMessageBean{" +
                "wxno='" + wxno + '\'' +
                ", fromAccount='" + fromAccount + '\'' +
                ", toAccount='" + toAccount + '\'' +
                ", content='" + content + '\'' +
                ", conversationTime=" + conversationTime +
                ", type='" + type + '\'' +
                ", wxid='" + wxid + '\'' +
                ", headUrl='" + headUrl + '\'' +
                ", nickName='" + nickName + '\'' +
                ", remarkName='" + remarkName + '\'' +
                ", receiverState='" + receiverState + '\'' +
                ", id='" + id + '\'' +
                ", msgState='" + msgState + '\'' +
                '}';
    }
}
