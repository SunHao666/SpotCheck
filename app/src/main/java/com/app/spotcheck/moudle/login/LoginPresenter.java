package com.app.spotcheck.moudle.login;

import com.app.spotcheck.base.BasePresenter;
import com.app.spotcheck.moudle.bean.LoginBean;
import com.app.spotcheck.network.BaseCallModel;
import com.app.spotcheck.network.BaseCallback;
import com.app.spotcheck.network.NetManager;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * @ClassName: LoginPresenter
 * @Description: 登录
 * @Author: 作者名
 * @CreateDate: 2019/12/18 14:42
 */
public class LoginPresenter extends BasePresenter<LoginView> {


    public void login(String userid,String userpwd){

        NetManager.getInstance().api().login(userid,userid)
                .enqueue(new Callback<LoginBean>() {
                    @Override
                    public void onResponse(Call<LoginBean> call, Response<LoginBean> response) {
                        if(mView == null){
                            return;
                        }
                        if(response != null){
                            mView.showError("数据异常");
                            return;
                        }
                        LoginBean body = response.body();
                        if(response.isSuccessful()){
                            mView.showResult(body.getResult_message());
                        }else{
                            mView.showError(body.getResult_message());
                        }
                    }

                    @Override
                    public void onFailure(Call<LoginBean> call, Throwable t) {
                        mView.showError(t.getMessage());
                    }
                });

    }

}
