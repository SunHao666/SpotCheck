package com.app.spotcheck.network;


import com.app.spotcheck.moudle.bean.CheckExceptionBean;
import com.app.spotcheck.moudle.bean.HomeBean;
import com.app.spotcheck.moudle.bean.LoginBean;
import com.app.spotcheck.moudle.bean.ScanCheckBean;
import com.app.spotcheck.moudle.bean.SpotCheckAllBean;
import com.app.spotcheck.moudle.bean.MineBean;

import java.util.List;
import java.util.Map;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.Headers;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.PartMap;

public interface NetService {
    //登录
//    @POST("login/doLogin")
//    Call<LoginBean> login(@Field("userid") String userid, @Field("userpwd") String userpwd);
    @Headers({"Content-Type: application/json","Accept: application/json"})//需要添加头
    @POST("login/doLogin")
    Call<LoginBean> login(@Body RequestBody body);
    //首页
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

    //点检扫描结果
    @FormUrlEncoded
    @POST("check/getUnCheckItemList")
    Call<BaseCallModel<ScanCheckBean>> getUnCheckItemList(@Field("execid")String execid);

    //点检扫描保存
    @FormUrlEncoded
    @POST("check/saveAllItem")
    Call<LoginBean> saveAllItem(@Field("execid")String execid,@Field("execman")String execman,@Field("recidList") List<String> recidList);


    //点检异常
    @FormUrlEncoded
    @POST("check/getUnCheckItemInfo")
    Call<BaseCallModel<CheckExceptionBean>> getUnCheckItemInfo(@Field("id")int id);

    //点检异常保存
    @Multipart
    @POST("check/saveCheckItem")
    Call<LoginBean> saveCheckItem(@PartMap Map<String, RequestBody> params, @Part() List<MultipartBody.Part> parts);

    //个人工作信息
    @FormUrlEncoded
    @POST("person/getInfo")
    Call<BaseCallModel<MineBean>> getInfo(@Field("workman")String workman);

}
