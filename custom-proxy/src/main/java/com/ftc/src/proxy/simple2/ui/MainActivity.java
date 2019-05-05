package com.ftc.src.proxy.simple2.ui;

import android.widget.TextView;

import com.ftc.src.proxy.R;
import com.ftc.src.proxy.model.User;
import com.ftc.src.proxy.simple2.base.BaseMvpActivity;
import com.ftc.src.proxy.simple2.inject.InjectPresenter;
import com.ftc.src.proxy.simple2.mvp.UserInfoContract;
import com.ftc.src.proxy.simple2.mvp.UserInfoPresenter;


public class MainActivity extends BaseMvpActivity implements UserInfoContract.UserInfoView {
    //多个Presenter怎么处理 dagger处理，自己写dagger处理 自己写个注入
    //一个View 里面肯定有多个Presenter情况，怎么处理，Dagger处理
    @InjectPresenter
    private UserInfoPresenter mUserInfoPresenter;
    private TextView mTextView;

    @Override
    public int getLayoutId() {
        return R.layout.activity_main;
    }

    /**
     * 初始化View
     */
    @Override
    protected void initView() {
        mTextView = findViewById(R.id.tv);

    }

    /**
     * 在这里去请求数据
     */
    @Override
    protected void initData() {
        mUserInfoPresenter.getUserInfo("Steven");
    }

    /**
     * 显示一个加载的进度条
     */
    @Override
    public void onLoading() {

    }

    /**
     * 请求数据成功回调该方法
     *
     * @param user
     */
    @Override
    public void onSuccess(User user) {
        mTextView.setText("Hello：" + user.getUserName());
    }

    /**
     * 请求数据失败回调该方法
     */
    @Override
    public void onError(int code, String errorMessage) {

    }
}
