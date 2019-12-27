package com.app.spotcheck.network;


import com.app.spotcheck.moudle.bean.CheckExceptionBean;
import com.app.spotcheck.moudle.bean.HomeBean;
import com.app.spotcheck.moudle.bean.HomeScanBean;
import com.app.spotcheck.moudle.bean.KeyWordsBean;
import com.app.spotcheck.moudle.bean.LoginBean;
import com.app.spotcheck.moudle.bean.LubAllBean;
import com.app.spotcheck.moudle.bean.PatralCheckBean;
import com.app.spotcheck.moudle.bean.ScanCheckBean;
import com.app.spotcheck.moudle.bean.ScanLubBean;
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
import retrofit2.http.Query;

public interface NetService {
    //登录
    @POST("login/doLogin")
    Call<BaseCallModel<LoginBean>> login(@Body RequestBody body);
    //首页
    @POST("main/getInfo")
    Call<BaseCallModel<HomeBean>> index();

    //全部点检部位
    @POST("check/getCheckPlanList")
    Call<BaseCallModel<SpotCheckAllBean>> getCheckPlanList(@Body RequestBody body);

    //未点检部位
    @POST("check/getUnCheckPlanList")
    Call<BaseCallModel<SpotCheckAllBean>> getUnCheckPlanList(@Body RequestBody body);

    //已点检部位
    @POST("check/getCheckedPlanList")
    Call<BaseCallModel<SpotCheckAllBean>> getCheckedPlanList(@Body RequestBody body);

    //点检扫描结果
    @POST("check/getUnCheckItemList")
    Call<BaseCallModel<ScanCheckBean>> getUnCheckItemList(@Body RequestBody body);

    //巡检扫描结果
    @POST("repair/gotoAddRepair")
    Call<BaseCallModel<PatralCheckBean>> gotoAddRepair(@Body RequestBody body);

    //点检扫描保存
    @POST("check/saveAllItem")
    Call<BaseCallModel> saveAllItem(@Body RequestBody body);


    //润滑列表
    @POST("lub/getLubList")
    Call<BaseCallModel<LubAllBean>> getLubPlanList(@Body RequestBody body);

    //润滑详细
    @POST("lub/getLubricationInfo")
    Call<BaseCallModel<ScanLubBean>> getLubricationInfo(@Body RequestBody body);

    //润滑详细保存
    @POST("lub/saveLubrication")
    Call<BaseCallModel> saveLubrication(@Body RequestBody body);
    //点检异常
    @POST("check/getUnCheckItemInfo")
    Call<BaseCallModel<CheckExceptionBean>> getUnCheckItemInfo(@Body RequestBody body);

    //点检异常保存
    @Multipart
    @POST("check/saveCheckItem")
    Call<BaseCallModel> saveCheckItem(@PartMap Map<String, RequestBody> params, @Part() List<MultipartBody.Part> parts);

    //巡检异常保存
    @Multipart
    @POST("repair/addRepairSave")
    Call<BaseCallModel> addRepairSave(@PartMap Map<String, RequestBody> params, @Part() List<MultipartBody.Part> parts);


    //个人工作信息
    @POST("person/getInfo")
    Call<BaseCallModel<MineBean>> getInfo(@Body RequestBody body);

    //查询关键字

    @POST("keywords/getInfo")
    Call<BaseCallModel<KeyWordsBean>> getKeyWordInfo(@Body RequestBody body);

    //保存关键字
    @POST("keywords/saveKeyword")
    Call<BaseCallModel> saveKeyword(@Body RequestBody body);
}
