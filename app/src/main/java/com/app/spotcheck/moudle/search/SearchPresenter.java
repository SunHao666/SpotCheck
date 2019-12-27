package com.app.spotcheck.moudle.search;

import android.text.TextUtils;

import com.app.spotcheck.base.BasePresenter;
import com.app.spotcheck.moudle.bean.KeyWordsBean;
import com.app.spotcheck.network.BaseCallModel;
import com.app.spotcheck.network.BaseCallback;
import com.app.spotcheck.network.NetManager;

import java.util.HashMap;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * @ClassName: SearchPresenter
 * @Description: java类作用描述
 * @Author: 作者名
 * @CreateDate: 2019/12/23 18:41
 */
public class SearchPresenter extends BasePresenter<SearchView> {
    public void getCheckSearch(String usekind, String userid) {
        Map<String,String> map = new HashMap<>();
        map.put("usekind",usekind);
        map.put("userid",userid);
        NetManager.getInstance().api().getKeyWordInfo(convertMapToBody(map))
                .enqueue(new BaseCallback<KeyWordsBean>() {
                    @Override
                    protected void onSuccess(KeyWordsBean keyWordsBean) {
                        if(!TextUtils.isEmpty(userid)){
                            mView.showSearchSuccess(keyWordsBean);
                        }else{
                            mView.showSearchAllSuccess(keyWordsBean);
                        }
                    }

                    @Override
                    protected void onFailed(int code, String msg) {
                        mView.showError(msg);
                    }
                });


    }

    public void saveKey(String usekind, String userid, String keywords) {

        Map<String,String> map = new HashMap<>();
        map.put("usekind",usekind);
        map.put("userid",userid);
        map.put("keywords",keywords);
        NetManager.getInstance().api().saveKeyword(convertMapToBody(map))
                .enqueue(new Callback<BaseCallModel>() {
                    @Override
                    public void onResponse(Call<BaseCallModel> call, Response<BaseCallModel> response) {
                        mView.showSaveSuccess(keywords);
                    }

                    @Override
                    public void onFailure(Call<BaseCallModel> call, Throwable t) {
                        mView.showSaveError(t.getMessage(),keywords);
                    }
                });
    }
}
