package com.example.bsty.wechat.utils;

import android.app.ActivityManager;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Environment;

import com.easemob.chat.EMMessage;
import com.easemob.chat.TextMessageBody;
import com.example.bsty.wechat.Constant;
import com.example.bsty.wechat.R;

import java.util.List;

/**
 * Created by bsty on 2/1/16.
 */
public class CommonUtils {
    /**
     * 检查网络是否可用
     *
     * @param context
     * @return
     */
    public static boolean isNetWorkdConnected(Context context) {
        if (context != null) {
            ConnectivityManager mConnectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo mNetworkInfo = mConnectivityManager.getActiveNetworkInfo();
            if (mNetworkInfo != null) {
                return mNetworkInfo.isAvailable();
            }
        }
        return false;
    }

    /**
     * 检测sdcard是否存在
     *
     * @return
     */
    public static boolean isExitsSdcard() {
        if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED))
            return true;
        else
            return false;
    }

    /**
     * 根据消息内容和消息类型获取消息内容提示
     *
     * @param message
     * @param context
     * @return
     */
    public static String getMessageDigest(EMMessage message, Context context) {
        String digest = "";
        switch (message.getType()) {
            case LOCATION://位置消息
                if (message.direct == EMMessage.Direct.RECEIVE) {
                    //从sdk中提取到ui中，使用更简单不犯错的获取string方法
                    digest = getString(context, R.string.location_recv);
                    digest = String.format(digest, message.getFrom());
                    return digest;
                } else {
                    digest = getString(context, R.string.location_prefix);
                }
                break;
            case IMAGE://图片消息
                digest = getString(context, R.string.picture);
                break;
            case VOICE://语音消息
                digest = getString(context, R.string.voice);
                break;
            case VIDEO://视频消息
                digest = getString(context, R.string.video);
                break;
            case TXT://文本消息
                if (!message.getBooleanAttribute(Constant.MESSAGE_ATTR_IS_VOICE_CALL, false)) {
                    TextMessageBody txtBody = (TextMessageBody) message.getBody();
                    digest = txtBody.getMessage();
                } else {
                    TextMessageBody txtBody = (TextMessageBody) message.getBody();
                    digest = getString(context, R.string.voice_call) + txtBody.getMessage();
                }
                break;
            case FILE://普通文件消息
                digest = getString(context, R.string.file);
                break;
            default:
                System.err.println("error, unknow type");
                return "";

        }
        return digest;
    }

    static String getString(Context context, int resId) {
        return context.getResources().getString(resId);
    }

    public static String getTopActivity(Context context) {
        ActivityManager manager = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
        List<ActivityManager.RunningTaskInfo> runningTaskInfoList = manager.getRunningTasks(1);
        if (runningTaskInfoList != null) {
            return runningTaskInfoList.get(0).topActivity.getClassName();
        } else {
            return "";
        }
    }
}
