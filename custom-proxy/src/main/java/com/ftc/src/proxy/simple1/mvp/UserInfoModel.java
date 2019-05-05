package com.ftc.src.proxy.simple1.mvp;


import com.ftc.src.proxy.httpClient.RetrofitClient;
import com.ftc.src.proxy.model.BaseResult;
import com.ftc.src.proxy.model.User;
import com.ftc.src.proxy.simple1.base.BaseModel;

import retrofit2.Call;

/**
 * Description:
 * Data：3/27/2018-9:58 AM
 *
 *
 */
public class UserInfoModel extends BaseModel implements UserInfoContract.UserModel {
    @Override
    public Call<BaseResult<User>> getUserInfo(String userName) {
        return RetrofitClient.getServiceApi().getUser(userName);
    }
}
