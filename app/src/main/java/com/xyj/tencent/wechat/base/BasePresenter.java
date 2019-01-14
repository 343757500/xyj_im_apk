package com.xyj.tencent.wechat.base;

import android.os.Message;

import com.xyj.tencent.wechat.model.protocol.BaseProtocol;
import com.xyj.tencent.wechat.model.protocol.CommonProtocol;


/**
 * @author WJQ
 */
public class BasePresenter {
    // Model层
    public CommonProtocol mProtocol;
    // View层
    public BaseView baseView;

    public BasePresenter(BaseView baseView) {
        this.baseView = baseView;
        mProtocol = new CommonProtocol();
    }

    public BaseProtocol.OnHttpCallback mBaseCallback
            = new BaseProtocol.OnHttpCallback() {
        @Override
        public void onHttpSuccess(int reqType, Message obj) {
            // P层 调用 V层
            baseView.onHttpSuccess(reqType, obj);
        }

        @Override
        public void onHttpError(int reqType, String error) {
            baseView.onHttpError(reqType, error);
        }
    };
}
