package com.ftc.eventbus.EventBus;

import android.os.Looper;
import android.os.Message;

import java.lang.reflect.InvocationTargetException;

public class MainThreadHandler extends android.os.Handler {

    private Object event;
    private Subscription subscription;

    public MainThreadHandler(Looper looper) {
        super(looper);
    }

    /**
     * 发送事件
     * @param subscription 订阅者
     * @param event 事件
     */
    public void post(Subscription subscription, Object event) {
        this.subscription = subscription;
        this.event = event;
        sendMessage(Message.obtain());
    }

    @Override
    public void handleMessage(Message msg) {
        try {
            subscription.method.invoke(subscription.subscriber, event);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
    }

}
