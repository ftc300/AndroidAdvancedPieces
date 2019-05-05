package com.ftc.eventbus;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.ftc.eventbus.EventBus.EventBus;


public class SecondActivity extends AppCompatActivity
        implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        findViewById(R.id.ac_second_btn1).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.ac_second_btn1:
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        EventBus.getDefault()
                                .post(new TestEvent("Hello EventBus!"));
                    }
                }).start();
                finish();
                break;
        }
    }

}
