package com.example.bsty.wechat.activity;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.NotificationCompat;
import android.view.View;

import com.easemob.chat.EMChatManager;
import com.easemob.chat.EMMessage;
import com.easemob.util.EasyUtils;
import com.example.bsty.wechat.fx.MainActivity;
import com.example.bsty.wechat.utils.CommonUtils;

/**
 * Created by bsty on 2/1/16.
 */
public class BaseActivity extends FragmentActivity {
    private static final int notifiId = 11;
    protected NotificationManager notificationManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
    }

    @Override
    protected void onResume() {
        super.onResume();
        //onResume时，取消notification显示
        EMChatManager.getInstance().activityResumed();
    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    /**
     * 当应用在前台时，如果当时消息不是属于当前对话，在状态栏提示一下，如果不需要，注释掉即可
     *
     * @param message
     */
    protected void notifyNewMessage(EMMessage message) {
        //如果设置了不提现数目的群组(这个是app里保存这个数据的，demo里不做判断)
        //以及设置了setShowNotificationinBackGroup:false(设置为false后，后台时sdk也发送广播)
        if (!EasyUtils.isAppRunningForeground(this)) {
            return;
        }

        NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(this).setSmallIcon(getApplicationInfo().icon).setWhen(System.currentTimeMillis()).setAutoCancel(true);

        String ticker = CommonUtils.getMessageDigest(message, this);
        if (message.getType() == EMMessage.Type.TXT)
            ticker = ticker.replaceAll("\\[.{2,3}\\]", "[表情]");
        //设置状态栏提示
        mBuilder.setTicker(message.getFrom() + ": " + ticker);
        //必须设置pendingintent， 否则在2.3的机器上会有bug
        Intent intent = new Intent(this, MainActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        PendingIntent pendingIntent = PendingIntent.getActivity(this, notifiId, intent, PendingIntent.FLAG_ONE_SHOT);
        mBuilder.setContentIntent(pendingIntent);

        Notification notification = mBuilder.build();
        notificationManager.notify(notifiId, notification);
        notificationManager.cancel(notifiId);
    }

    /**
     * 返回
     *
     * @param view
     */
    public void back(View view) {
        finish();
    }
}
