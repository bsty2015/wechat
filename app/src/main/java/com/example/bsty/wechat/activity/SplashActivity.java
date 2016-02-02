package com.example.bsty.wechat.activity;

import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.View;

import com.easemob.chat.EMChatManager;
import com.easemob.chat.EMGroupManager;
import com.example.bsty.wechat.DemoHXSDKHelper;
import com.example.bsty.wechat.R;
import com.example.bsty.wechat.fx.LoginActivity;
import com.example.bsty.wechat.fx.MainActivity;

import java.io.File;

public class SplashActivity extends BaseActivity {

    private static final int sleepTime = 2000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        final View view = View.inflate(this, R.layout.activity_splash, null);
        setContentView(view);
        super.onCreate(savedInstanceState);
        initFile();
    }

    public void initFile() {
        File dir = new File("/sdcard/fanxin");
        if (!dir.exists()) {
            dir.mkdirs();
        }
    }

    @Override
    protected void onStart() {
        super.onStart();

        new Thread(new Runnable() {
            @Override
            public void run() {
                if (DemoHXSDKHelper.getInstance().isLogined()) {
                    // ** 免登陆情况 加载所有本地群和会话
                    //不是必须的，不加sdk也会自动异步去加载(不会重复加载)；
                    //加上的话保证进了主页面会话和群组都已经load完毕
                    long start = System.currentTimeMillis();
                    EMGroupManager.getInstance().loadAllGroups();
                    EMChatManager.getInstance().loadAllConversations();
                    long costTime = System.currentTimeMillis() - start;

                    //等待sleeptime时长
                    if (sleepTime - costTime > 0) {
                        try {
                            Thread.sleep(sleepTime - costTime);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }

                    //进入主界面
                    startActivity(new Intent(SplashActivity.this, MainActivity.class));
                    finish();
                } else {
                    try {
                        Thread.sleep(sleepTime);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    startActivity(new Intent(SplashActivity.this, LoginActivity.class));
                    finish();
                }
            }
        }).start();
    }

    /**
     * 获取当前应用程序的版本号
     * @return
     */
    private String getVersion(){
        PackageManager pm =getPackageManager();
        try {
            PackageInfo packageInfo = pm.getPackageInfo(getPackageName(),0);
            String version = packageInfo.versionName;
            return version;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
            return "版本号错误";
        }
    }

}
