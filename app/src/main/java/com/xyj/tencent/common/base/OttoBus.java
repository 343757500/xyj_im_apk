package com.xyj.tencent.common.base;


import com.squareup.otto.Bus;
import com.squareup.otto.ThreadEnforcer;

/**
 * Otto事件总线: 实现组件与组件之间的通令
 */
public class OttoBus {
    // new Bus(ThreadEnforcer.MAIN)： 默认即在主线程接收事件, 
    //             则只能在主线程发布事件，子线程发布事件会出错
    // new Bus(ThreadEnforcer.ANY)： 可以在任意的线程发布事件
    private static final Bus mBus = new Bus(ThreadEnforcer.ANY);

    public static Bus getDefault() {
        return mBus;
    }

    private OttoBus() {
    }
}