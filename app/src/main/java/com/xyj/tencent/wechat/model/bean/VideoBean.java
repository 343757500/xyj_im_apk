package com.xyj.tencent.wechat.model.bean;

public class VideoBean {


    /**
     * msg : 查询成功
     * code : 200
     * success : true
     * result : {"wxno":"a54414512","wxid":"wxid_kwd8tbhbbgsr22","fromAccount":"054c6eda53784528af3e20b992319a98","toAccount":"2d9921a545024472b10c6f929e9ad3d7","content":"http://upyun.ijucaimao.cn/videoforapp/wxid_kwd8tbhbbgsr22/Receive/20181212/1044551212189a6b3c852293.mp4","conversationTime":1544582694000,"type":"43","loaded":true,"msgId":"c52c82574126414b855c7617f0f035e9","id":"9841501a428a4b80b402a63beadf4443"}
     */

    private String msg;
    private String code;
    private boolean success;
    private ResultBean result;

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public ResultBean getResult() {
        return result;
    }

    public void setResult(ResultBean result) {
        this.result = result;
    }

    public static class ResultBean {
        /**
         * wxno : a54414512
         * wxid : wxid_kwd8tbhbbgsr22
         * fromAccount : 054c6eda53784528af3e20b992319a98
         * toAccount : 2d9921a545024472b10c6f929e9ad3d7
         * content : http://upyun.ijucaimao.cn/videoforapp/wxid_kwd8tbhbbgsr22/Receive/20181212/1044551212189a6b3c852293.mp4
         * conversationTime : 1544582694000
         * type : 43
         * loaded : true
         * msgId : c52c82574126414b855c7617f0f035e9
         * id : 9841501a428a4b80b402a63beadf4443
         */

        private String wxno;
        private String wxid;
        private String fromAccount;
        private String toAccount;
        private String content;
        private long conversationTime;
        private String type;
        private boolean loaded;
        private String msgId;
        private String id;

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

        public String getType() {
            return type;
        }

        public void setType(String type) {
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
}
