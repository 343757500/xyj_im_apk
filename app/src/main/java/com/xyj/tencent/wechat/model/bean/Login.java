package com.xyj.tencent.wechat.model.bean;

public class Login {

    /**
     * msg : 登录成功
     * code : 200
     * success : true
     * result : 2p6kwcegh615zovf2ztcc26e1
     */

    private String msg;
    private String code;
    private boolean success;
    private String result;

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

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }
}
