package com.ftc.src.proxy.httpClient;


import com.ftc.src.proxy.model.BaseResult;
import com.ftc.src.proxy.model.User;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

/**
 * Description:
 * Data：3/27/2018-10:02 AM
 *
 *
 */
public interface ServiceApi {
    @POST("LoginServlet")
    @FormUrlEncoded
    Call<BaseResult<User>> getUser(@Field("userName") String userName);
}
