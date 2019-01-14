package com.xyj.tencent.wechat.model.bean;

public class LoginTicket {
    /**
     * msg : 查询成功
     * code : 200
     * success : true
     * result : {"sig":"eJxFkFFPgzAUhf8LrzN6W2hZfUPEBBXHNk3UF9K1RToCq6VDFuN-FwmL9-F85*TmnG-v*XF7yY3RsuCu8K30rj3wLiZZDUZbVfDSKTvKiBCCAc60V7bTh3YEGBBB2Af4h1qq1ulST0EsGcOIk4AADoIQ7xAIWjLMFOPSl*Gc6fTHaM6Stzhdx-mOform6gE1WWM3o6ldJK9PPCWGRcu1oVEKELwcmai-0irKTvQ9XDWa7sFW8V0Hq75PqvamIYs6v9WDgzwbtlrs*-vzM1kXU*2-YgEACtB4M3S6UVNhny6ZHwKddS7E4di6wp2Mmnb6*QVfn11y","sdkAppId":"1400141111","name":"20180914","nickname":"20180914","relationId":"2d9921a545024472b10c6f929e9ad3d7","type":"0"}
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
         * sig : eJxFkFFPgzAUhf8LrzN6W2hZfUPEBBXHNk3UF9K1RToCq6VDFuN-FwmL9-F85*TmnG-v*XF7yY3RsuCu8K30rj3wLiZZDUZbVfDSKTvKiBCCAc60V7bTh3YEGBBB2Af4h1qq1ulST0EsGcOIk4AADoIQ7xAIWjLMFOPSl*Gc6fTHaM6Stzhdx-mOform6gE1WWM3o6ldJK9PPCWGRcu1oVEKELwcmai-0irKTvQ9XDWa7sFW8V0Hq75PqvamIYs6v9WDgzwbtlrs*-vzM1kXU*2-YgEACtB4M3S6UVNhny6ZHwKddS7E4di6wp2Mmnb6*QVfn11y
         * sdkAppId : 1400141111
         * name : 20180914
         * nickname : 20180914
         * relationId : 2d9921a545024472b10c6f929e9ad3d7
         * type : 0
         */

        private String sig;
        private String sdkAppId;
        private String name;
        private String nickname;
        private String relationId;
        private String type;

        public String getSig() {
            return sig;
        }

        public void setSig(String sig) {
            this.sig = sig;
        }

        public String getSdkAppId() {
            return sdkAppId;
        }

        public void setSdkAppId(String sdkAppId) {
            this.sdkAppId = sdkAppId;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getNickname() {
            return nickname;
        }

        public void setNickname(String nickname) {
            this.nickname = nickname;
        }

        public String getRelationId() {
            return relationId;
        }

        public void setRelationId(String relationId) {
            this.relationId = relationId;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }
    }
}
