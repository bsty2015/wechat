package com.example.bsty.wechat.applib.model;

import android.content.Context;

/**
 * Created by bsty on 2/2/16.
 */
public class DefaultHXSDKModel extends HXSDKModel{
    public DefaultHXSDKModel(Context context) {
    }

    @Override
    public void setSettingMsgNotification(boolean paramBoolean) {

    }

    @Override
    public boolean getSettingMsgNotification() {
        return false;
    }

    @Override
    public void setSettingMsgSound(boolean paramBoolean) {

    }

    @Override
    public boolean getSettingMsgSound() {
        return false;
    }

    @Override
    public void setSettingMsgVibrate(boolean paramBoolean) {

    }

    @Override
    public boolean getSettingMsgVibrate() {
        return false;
    }

    @Override
    public void setSettingMsgSpeaker(boolean paramBoolean) {

    }

    @Override
    public boolean getSettingMsgSpeaker() {
        return false;
    }

    @Override
    public boolean saveHXId(String hxId) {
        return false;
    }

    @Override
    public String getHXId() {
        return null;
    }

    @Override
    public boolean savePassword(String pwd) {
        return false;
    }

    @Override
    public String getPwd() {
        return null;
    }

    @Override
    public String getAppProcessName() {
        return null;
    }
}
