package com.ftc.eventbus.EventBus;

import java.lang.reflect.Method;


public class Subscription {

    public int mode;
    public Method method;
    public Object subscriber;

    public Subscription(Method method, Object subscriber, int mode) {
        this.method = method;
        this.subscriber = subscriber;
        this.mode = mode;
    }

}
