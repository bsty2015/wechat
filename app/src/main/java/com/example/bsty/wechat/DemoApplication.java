package com.example.bsty.wechat;

import android.app.Application;
import android.content.Context;

/**
 * Created by bsty on 2/2/16.
 */
public class DemoApplication extends Application {
    public static Context applicationContext;
    private static DemoApplication instance;

    @Override
    public void onCreate() {
        super.onCreate();
        applicationContext = this;
        instance = this;

        /**
         * this function will initialize the HuanXin SDK
         *
         * @return boolean true if caller can continue to call HuanXin related APIs after calling onInit, otherwise false.
         *
         * 环信初始化SDK帮助函数
         * 返回true如果正确初始化，否则false，如果返回为false，请在后续的调用中不要调用任何和环信相关的代码
         *
         * for example:
         * 例子：
         *
         * public class DemoHXSDKHelper extends HXSDKHelper
         *
         * HXHelper = new DemoHXSDKHelper();
         * if(HXHelper.onInit(context)){
         *     // do HuanXin related work
         * }
         */


    }
}
