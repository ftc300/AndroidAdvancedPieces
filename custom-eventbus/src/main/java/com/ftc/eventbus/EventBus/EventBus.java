package com.ftc.eventbus.EventBus;

import android.os.Looper;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class EventBus {

    private volatile static EventBus instance;

    private MainThreadHandler handler;
    private Map<Class<?>, List<Subscription>> map;//用一个map保存订阅关系

    private EventBus() {
        map = new HashMap<>();
        handler = new MainThreadHandler(Looper.getMainLooper());//传入主looper
    }

    public static EventBus getDefault() {
        if (instance == null) {
            synchronized (EventBus.class) {
                if (instance == null) {
                    instance = new EventBus();
                }
            }
        }
        return instance;
    }

    /**
     * 注册
     * @param subscriber 订阅类
     */
    public void register(Object subscriber) {
        //反射获取订阅类里所有已声明的方法
        Class<?> clazz = subscriber.getClass();
        //
        Method[] methods = clazz.getDeclaredMethods();
        for (Method m : methods) {
            //如果这个方法加上了Subscribe注解，则把它加入集合，并保存在map里面
            if (m.isAnnotationPresent(Subscribe.class)) {
                Subscribe s = m.getAnnotation(Subscribe.class);
                Class<?> c = m.getParameterTypes()[0];
                List<Subscription> list = map.get(c);
                if (list == null) {
                    list = new ArrayList<>();
                    map.put(c, list);
                }
                //根据threadMode构造订阅者
                switch (s.threadMode()) {
                    case ThreadMode.POST_THREAD:
                        list.add(new Subscription(m, subscriber,
                                ThreadMode.POST_THREAD));
                        break;
                    case ThreadMode.MAIN_THREAD:
                        list.add(new Subscription(m, subscriber,
                                ThreadMode.MAIN_THREAD));
                        break;
                    default:
                        break;
                }
            }

        }
    }

    /**
     * 反注册
     * @param subscriber 订阅类
     */
    public void unRegister(Object subscriber) {
        Class<?> clazz = subscriber.getClass();
        Method[] methods = clazz.getDeclaredMethods();
        for (Method m : methods) {
            if (m.isAnnotationPresent(Subscribe.class)) {
                Class<?> c = m.getParameterTypes()[0];
                List<Subscription> list = map.get(c);
                if (list != null) {
                    for (Subscription s : list) {
                        if (s.subscriber == subscriber) {
                            list.remove(s);
                        }
                    }
                }
            }
        }
    }

    /**
     * 发送消息
     * @param event 事件类型
     */
    public void post(Object event) {
        Class<?> clazz = event.getClass();
        List<Subscription> list = map.get(clazz);
        if (list == null) {
            return;//这里建议抛异常或者打印日志
        }
        //拿到方法集合后遍历执行
        for (Subscription s : list) {
            switch (s.mode) {
                case ThreadMode.POST_THREAD:
                    try {
                        s.method.invoke(s.subscriber, event);
                    } catch (IllegalAccessException e) {
                        e.printStackTrace();
                    } catch (InvocationTargetException e) {
                        e.printStackTrace();
                    }
                    break;
                case ThreadMode.MAIN_THREAD:
                    handler.post(s, event);
                    break;
                default:
                    break;
            }
        }
    }

}
