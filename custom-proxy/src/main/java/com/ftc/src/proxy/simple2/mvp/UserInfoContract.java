package com.ftc.src.proxy.simple2.mvp;

import com.ftc.src.proxy.model.BaseResult;
import com.ftc.src.proxy.model.User;
import com.ftc.src.proxy.simple1.BaseView;

import retrofit2.Call;

/**
 * Description:
 * Data：5/10/2018-9:20 AM
 *
 *
 */
public class UserInfoContract {
    //View层
   public interface UserInfoView extends BaseView {
        void onLoading();

        void onSuccess(User user);

        void onError(int code, String errorMessage);

    }

    //Presenter层
    interface UserInfoPresenter {
        void getUserInfo(String userName);
    }

    //Model层，外部只需关心Model返回的数据,无需关心内部细节
    interface UserModel {
        Call<BaseResult<User>> getUserInfo(String userName);
    }
}
