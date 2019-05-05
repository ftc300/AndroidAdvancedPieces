package com.ftc.src.proxy.simple2.mvp;

import com.ftc.src.proxy.httpClient.RetrofitClient;
import com.ftc.src.proxy.model.BaseResult;
import com.ftc.src.proxy.model.User;
import com.ftc.src.proxy.simple2.base.BaseModel;

import retrofit2.Call;

/**
 * Description:
 * Data：5/10/2018-9:20 AM
 *
 *
 */
public class UserInfoModel extends BaseModel implements UserInfoContract.UserModel {
    @Override
    public Call<BaseResult<User>> getUserInfo(String userName) {
        return RetrofitClient.getServiceApi().getUser(userName);
    }
}
