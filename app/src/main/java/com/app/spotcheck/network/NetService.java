package com.app.spotcheck.network;


import com.app.spotcheck.moudle.bean.HomeBean;
import com.app.spotcheck.moudle.bean.LoginBean;
import com.app.spotcheck.moudle.bean.SpotCheckAllBean;
import com.app.spotcheck.moudle.mine.MineBean;

import javax.security.auth.callback.Callback;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface NetService {
    //登录
    @FormUrlEncoded
    @POST("login/doLogin")
    Call<LoginBean> login(@Field("userid") String userid, @Field("userpwd") String userpwd);

    //首页
    @FormUrlEncoded
    @POST("main/getInfo")
    Call<BaseCallModel<HomeBean>> index();

    //全部点检部位
    @FormUrlEncoded
    @POST("check/getCheckPlanList")
    Call<BaseCallModel<SpotCheckAllBean>> getCheckPlanList(@Field("mainid")String mainid,@Field("mainname")String mainname);

    //未点检部位
    @FormUrlEncoded
    @POST("check/getUnCheckPlanList")
    Call<BaseCallModel<SpotCheckAllBean>> getUnCheckPlanList(@Field("mainid")String mainid,@Field("mainname")String mainname);

    //已点检部位
    @FormUrlEncoded
    @POST("check/getCheckedPlanList")
    Call<BaseCallModel<SpotCheckAllBean>> getCheckedPlanList(@Field("mainid")String mainid,@Field("mainname")String mainname);


    //个人工作信息
    @FormUrlEncoded
    @POST("person/getInfo")
    Call<BaseCallModel<MineBean>> getInfo(@Field("workman")String workman);

}
