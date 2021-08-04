package com.app.spotcheck.network;


import com.app.spotcheck.moudle.bean.AddDeviceBean;
import com.app.spotcheck.moudle.bean.AddPersonBean;
import com.app.spotcheck.moudle.bean.CheckExceptionBean;
import com.app.spotcheck.moudle.bean.DepartmentBean;
import com.app.spotcheck.moudle.bean.DeviceInfoBean;
import com.app.spotcheck.moudle.bean.DeviceListBean;
import com.app.spotcheck.moudle.bean.HomeBean;
import com.app.spotcheck.moudle.bean.HomeScanBean;
import com.app.spotcheck.moudle.bean.KeyWordsBean;
import com.app.spotcheck.moudle.bean.LoginBean;
import com.app.spotcheck.moudle.bean.LubAllBean;
import com.app.spotcheck.moudle.bean.PatralCheckBean;
import com.app.spotcheck.moudle.bean.ProKindBean;
import com.app.spotcheck.moudle.bean.RepairDetailBean;
import com.app.spotcheck.moudle.bean.RepairDeviceListBean;
import com.app.spotcheck.moudle.bean.RepairItemBean;
import com.app.spotcheck.moudle.bean.RepairManListBean;
import com.app.spotcheck.moudle.bean.RepairReportScanBean;
import com.app.spotcheck.moudle.bean.ReportSearchBean;
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
import retrofit2.http.FieldMap;
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

    //全部设备
    @POST("repair/getDevList")
    Call<BaseCallModel<DeviceListBean>> getDeviceList(@Body RequestBody body);

    //设备问题
    @POST("repair/getRecordListByDevMain")
    Call<BaseCallModel<DeviceInfoBean>> getDeviceInfo(@Body RequestBody body);

    //设备保存
    @FormUrlEncoded
    @POST("repair/repairRecordFinish")
    Call<BaseCallModel<Object>> saveDeviceInfo(@FieldMap Map<String, String> map);

    //故障类型
    @FormUrlEncoded
    @POST("common/problemKindInfo")
    Call<BaseCallModel<ProKindBean>> problemKindInfo(@FieldMap Map<String, String> map);

    @POST("repair/getDevByQcode")
    Call<BaseCallModel<RepairReportScanBean>> getDevByQcode(@Body RequestBody body);

    //巡检异常保存
    @Multipart
    @POST("repair/saveRepairApply")
    Call<BaseCallModel> saveRepairApply(@PartMap Map<String, RequestBody> params, @Part() List<MultipartBody.Part> parts);

    @POST("repair/getRecordList")
    Call<BaseCallModel<RepairItemBean>> getRecordList(@Body RequestBody body);
    //待审核
    @POST("repair/getApplyCheckList")
    Call<BaseCallModel<RepairItemBean>> getApplyCheckList(@Body RequestBody body);

    //待派工
    @POST("repair/getDispatchList")
    Call<BaseCallModel<RepairItemBean>> getDispatchList(@Body RequestBody body);

    //维修中
    @POST("repair/getWorkRefirmList")
    Call<BaseCallModel<RepairItemBean>> geWorkRefirmList(@Body RequestBody body);

    //待完工t
    @POST("repair/getFinishRefirmList")
    Call<BaseCallModel<RepairItemBean>> geFinishRefirmList(@Body RequestBody body);

    //已完工
    @POST("repair/getFinishList")
    Call<BaseCallModel<RepairItemBean>> getFinishList(@Body RequestBody body);

    @POST("repair/gotoApplyCheck")
    Call<BaseCallModel<RepairDetailBean>> gotoApplyCheck(@Body RequestBody body);

    @POST("repair/saveApplyCommitDel")
    Call<BaseCallModel<String>> saveApplyCommitDel(@Body RequestBody body);

    @POST("repair/saveApplyCommit")
    Call<BaseCallModel<String>> saveApplyCommit(@Body RequestBody body);

    @POST("repair/gotoDispatch")
    Call<BaseCallModel<RepairDetailBean>> gotoDispatch(@Body RequestBody body);

    @POST("repair/saveDispatch")
    Call<BaseCallModel<String>> saveDispatch(@Body RequestBody body);

    //故障类型
    @FormUrlEncoded
    @POST("common/getDepartmentList")
    Call<BaseCallModel<DepartmentBean>> getDepartmentList(@FieldMap Map<String, String> map);


    @POST("repair/gotoRepairRec")
    Call<BaseCallModel<RepairDetailBean>> gotoRepairRec(@Body RequestBody body);

    @POST("repair/saveRepairRec")
    Call<BaseCallModel<String>> saveRepairRec(@Body RequestBody body);

    @POST("repair/gotoRepairRecRefirm")
    Call<BaseCallModel<RepairDetailBean>> gotoRepairRecRefirm(@Body RequestBody body);

    @POST("repair/saveRepairRecRefirm")
    Call<BaseCallModel<String>> saveRepairRecRefirm(@Body RequestBody body);

    @POST("repair/gotoDetail")
    Call<BaseCallModel<RepairDetailBean>> gotoDetail(@Body RequestBody body);
    @POST("repair/getdevParts")
    Call<BaseCallModel<ReportSearchBean>> getdevParts(@Body RequestBody body);

    @POST("spare/getSpareStoreList")
    Call<BaseCallModel<AddDeviceBean>> getSpareStoreList(@Body RequestBody body);

    @POST("spare/saveRepairSpareUse")
    Call<BaseCallModel<String>> saveRepairSpareUse(@Body RequestBody body);

    @POST("repair/getRepairApareListByRepId")
    Call<BaseCallModel<RepairDeviceListBean>> getRepairApareListByRepId(@Body RequestBody body);

    @POST("repair/getWorkerList")
    Call<BaseCallModel<AddPersonBean>> getWorkerList(@Body RequestBody body);

    @POST("repair/saveRepairMan")
    Call<BaseCallModel<String>> saveRepairMan(@Body RequestBody body);

    @POST("repair/getRepairManListByRepId")
    Call<BaseCallModel<RepairManListBean>> getRepairManListByRepId(@Body RequestBody body);


    @POST("spare/saveRepairSpareReturn")
    Call<BaseCallModel<String>> saveRepairSpareReturn(@Body RequestBody body);
    @POST("repair/deleteRepairMan")
    Call<BaseCallModel<String>> deleteRepairMan(@Body RequestBody body);
}
