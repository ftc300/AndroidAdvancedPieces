package com.ftc.eventbus.EventBus;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;


@Retention(RetentionPolicy.RUNTIME)//加上这个才能被反射到
public @interface Subscribe {

    int threadMode() default ThreadMode.POST_THREAD;//默认在发送线程里

}
