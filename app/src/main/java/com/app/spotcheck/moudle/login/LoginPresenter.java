package com.app.spotcheck.moudle.login;

import com.app.spotcheck.base.BasePresenter;
import com.app.spotcheck.base.utils.SPUtils;
import com.app.spotcheck.moudle.bean.LoginBean;
import com.app.spotcheck.network.BaseCallModel;
import com.app.spotcheck.network.BaseCallback;
import com.app.spotcheck.network.Contant;
import com.app.spotcheck.network.NetManager;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import okhttp3.MediaType;
import okhttp3.RequestBody;
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
        Map<String,String> map = new HashMap<>();
        map.put("userid",userid);
        map.put("userpwd",userpwd);
        NetManager.getInstance().api().login(convertMapToBody(map))
                .enqueue(new BaseCallback<LoginBean>() {
                    @Override
                    protected void onSuccess(LoginBean loginBean) {
                        mView.showResult("登录成功",loginBean);
                        Contant.LOGTIME = loginBean.getLogtime()+"";
                    }

                    @Override
                    protected void onFailed(int code, String msg) {
                        mView.showError(msg);
                    }
                });

    }

}
