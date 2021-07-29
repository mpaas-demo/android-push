package com.mpaas.demo.push;

import com.alipay.pushsdk.content.MPPushMsgServiceAdapter;
import com.alipay.pushsdk.data.MPPushMsg;
import com.alipay.pushsdk.data.PushOsType;
import com.alipay.pushsdk.rpc.net.ResultBean;
import com.mpaas.mps.adapter.api.MPPush;

import alipay.yunpushcore.rpc.ResultPbPB;

public class MyPushMsgService extends MPPushMsgServiceAdapter {

    public static String token;
    public static String channelToken;
    public static PushOsType channel;

    /**
     * 收到自建通道 token 的回调
     *
     * @param token 自建通道 token
     */
    @Override
    protected void onTokenReceive(String token) {
        MyPushMsgService.token = token;
        PLog.d("收到自建通道 token: " + token);

        // 收到 token 后，绑定 userId
        // 开发者可缓存 token，在获取到自己的 userId 后再绑定
        ResultPbPB bindResult = MPPush.bind(getApplicationContext(), MainActivity.userId, token);
        PLog.d("userId: " + MainActivity.userId);
        PLog.d("绑定 userId " + (bindResult.success ? "成功" : ("错误：" + bindResult.code)));
    }

    /**
     * 收到厂商通道 token 的回调
     *
     * @param channelToken 厂商通道 token
     * @param channel      厂商通道类型
     */
    @Override
    protected void onChannelTokenReceive(String channelToken, PushOsType channel) {
        MyPushMsgService.channelToken = channelToken;
        MyPushMsgService.channel = channel;
        PLog.d("收到厂商通道 token: " + channelToken);
        PLog.d("厂商: " + channel.getName());
    }

    /**
     * 厂商通道 token 上报结果的回调
     *
     * @param result 上报结果
     */
    @Override
    protected void onChannelTokenReport(ResultBean result) {
        PLog.d("上报厂商 token " + (result.success ? "成功" : ("错误：" + result.code)));
    }

    /**
     * 自建通道收到消息的回调（非厂商通道）
     *
     * @param msg 收到的消息
     * @return 是否处理了消息：
     * 返回 true，则 sdk 不会处理消息，开发者需自行处理收到的消息，包括发通知和通知点击后的跳转
     * 返回 false，则 sdk 会自动发通知和添加通知点击后的跳转
     */
    @Override
    protected boolean onMessageReceive(MPPushMsg msg) {
        PLog.d("从自建通道收到消息：" + msg.toString());
        return false;
    }

    /**
     * 厂商通道的消息展示在通知栏，通知被点击后的回调
     *
     * @param msg 收到的消息
     * @return 是否处理了消息的点击：
     * 返回 true，则 sdk 不会处理厂商通道的通知点击，开发者需自行处理点击后的跳转
     * 返回 false，则 sdk 会自动处理通知点击后的跳转
     */
    @Override
    protected boolean onChannelMessageClick(MPPushMsg msg) {
        PLog.d("从厂商通道到的消息被点击：" + msg.toString());
        return false;
    }

}
