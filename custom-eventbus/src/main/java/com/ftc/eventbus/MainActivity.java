package com.ftc.eventbus;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import com.ftc.eventbus.EventBus.EventBus;
import com.ftc.eventbus.EventBus.Subscribe;

import static com.ftc.eventbus.EventBus.ThreadMode.MAIN_THREAD;

public class MainActivity extends AppCompatActivity
        implements View.OnClickListener {

    private TextView tv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        EventBus.getDefault().register(this);//注册
        tv = (TextView) findViewById(R.id.ac_main_tv);
        findViewById(R.id.ac_main_btn).setOnClickListener(this);

    }

    @Subscribe(threadMode = MAIN_THREAD)
    public void testFoo(TestEvent event) {//接收
        tv.setText(event.getText());
        tv.setTextColor(getResources().getColor(R.color.colorAccent));
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unRegister(this);//反注册
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.ac_main_btn:
                startActivity(new Intent(MainActivity.this,
                        SecondActivity.class));
                break;
        }
    }
}
