package com.xyj.tencent.wechat.presenter;

import com.xyj.tencent.wechat.base.BasePresenter;
import com.xyj.tencent.wechat.base.BaseView;

public class MainFragment2Presenter extends BasePresenter {
    public MainFragment2Presenter(BaseView baseView) {
        super(baseView);
    }

    public void getConstactData(String ticket){
        mProtocol.contactData(mBaseCallback,ticket);

    }
}
