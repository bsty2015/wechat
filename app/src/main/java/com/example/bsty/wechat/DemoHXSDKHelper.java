package com.example.bsty.wechat;

import android.content.Intent;
import android.content.IntentFilter;

import com.easemob.chat.EMChatManager;
import com.easemob.chat.EMMessage;
import com.easemob.chat.OnMessageNotifyListener;
import com.easemob.chat.OnNotificationClickListener;
import com.example.bsty.wechat.applib.controller.HXSDKHelper;
import com.example.bsty.wechat.applib.model.HXSDKModel;
import com.example.bsty.wechat.domain.User;
import com.example.bsty.wechat.fx.others.TopUser;
import com.example.bsty.wechat.utils.CommonUtils;

import java.util.Map;

/**
 * Created by bsty on 2/2/16.
 */
public class DemoHXSDKHelper extends HXSDKHelper {
    /**
     * contact list in cache
     */
    private Map<String, User> contactList;
    private Map<String, TopUser> topUserList;


    @Override
    protected void initHXOptions() {
        super.initHXOptions();
        // you can also get EMChatOptions to set related SDK options
        // EMChatOptions options = EMChatManager.getInstance().getChatOptions();
    }

    @Override
    protected HXSDKModel createModel() {
        return null;
    }

    @Override
    protected OnMessageNotifyListener getMessageNotifyListener() {
        //取消注释，app在后台，有消息来时，状态栏的消息提示换成自己写的
        return new OnMessageNotifyListener() {
            @Override
            public String onNewMessageNotify(EMMessage emMessage) {
                //设置状态栏的消息提示，可以根据message的类型做相应提示
                String ticker = CommonUtils.getMessageDigest(emMessage, appContext);
                if (emMessage.getType() == EMMessage.Type.TXT)
                    ticker = ticker.replaceAll("\\[.{2,3}\\]", "[表情]");
                return emMessage.getFrom() + ": " + ticker;
            }

            @Override
            public String onLatestMessageNotify(EMMessage emMessage, int i, int i1) {
                return null;
                // return fromUsersNum + "个基友，发来了" + messageNum + "条消息";
            }

            @Override
            public String onSetNotificationTitle(EMMessage emMessage) {
                // 修改标题,这里使用默认
                return null;

            }

            @Override
            public int onSetSmallIcon(EMMessage emMessage) {
                //设置小图标
                return 0;
            }
        };
    }

    @Override
    protected OnNotificationClickListener getNotificationClickListener() {
        return new OnNotificationClickListener() {
            @Override
            public Intent onNotificationClick(EMMessage emMessage) {
//                Intent intent = new Intent(appContext, )
                return null;
            }
        };
    }

    @Override
    protected void onConnectionConflict() {
    }

    @Override
    protected void onCurrentAccountRemoved() {
        super.onCurrentAccountRemoved();
    }

    @Override
    protected void initListener() {
        super.initListener();
        IntentFilter callFilter = new IntentFilter(EMChatManager.getInstance().getIncomingCallBroadcastAction());
//
    }

}
