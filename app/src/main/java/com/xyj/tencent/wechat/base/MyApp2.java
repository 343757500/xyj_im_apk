package com.xyj.tencent.wechat.base;


import com.xyj.tencent.common.base.MyApp;

/**
 * @author WJQ
 */
public class MyApp2 extends MyApp {

    @Override
    public void onCreate() {
        super.onCreate();

       /* // 短信验证登录
        SMSSDK.initSDK(this, "1c32a690937b0",
                "490f625347ea37b1ff872bc25e05aef7");

        // 初始化极光SDK
        JPushInterface.setDebugMode(true);
        JPushInterface.init(this);


        SkinCompatManager.withoutActivity(this)                         // 基础控件换肤初始化
                .addStrategy(new CustomSDCardLoader())                  // 自定义加载策略，指定SDCard路径[可选]
               // .addHookInflater(new SkinHookAutoLayoutViewInflater())  // hongyangAndroid/AndroidAutoLayout[可选]
                .addInflater(new SkinMaterialViewInflater())            // material design 控件换肤初始化[可选]
                .addInflater(new SkinConstraintViewInflater())          // ConstraintLayout 控件换肤初始化[可选]
                .addInflater(new SkinCardViewInflater())                // CardView v7 控件换肤初始化[可选]
                //.addInflater(new SkinCircleImageViewInflater())         // hdodenhof/CircleImageView[可选]
                //.addInflater(new SkinFlycoTabLayoutInflater())          // H07000223/FlycoTabLayout[可选]
              //  .setSkinStatusBarColorEnable(false)                     // 关闭状态栏换肤，默认打开[可选]
              //  .setSkinWindowBackgroundEnable(false)                   // 关闭windowBackground换肤，默认打开[可选]
                .loadSkin();*/
    }
}
