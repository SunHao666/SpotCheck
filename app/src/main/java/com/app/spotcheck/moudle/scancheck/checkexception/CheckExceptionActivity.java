package com.app.spotcheck.moudle.scancheck.checkexception;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.app.spotcheck.R;
import com.app.spotcheck.base.BaseActivity;
import com.app.spotcheck.base.adapter.FullyGridLayoutManager;
import com.app.spotcheck.base.adapter.GridImageAdapter;
import com.app.spotcheck.base.adapter.TakePhotoAdapter;
import com.app.spotcheck.base.utils.GlideEngine;
import com.app.spotcheck.base.utils.SPUtils;
import com.app.spotcheck.base.wrapper.PopuwindowListView;
import com.app.spotcheck.base.wrapper.ToastWrapper;
import com.app.spotcheck.moudle.MainActivity;
import com.app.spotcheck.moudle.bean.CheckExceptionBean;
import com.app.spotcheck.moudle.bean.PROBLEMKINDBean;
import com.luck.picture.lib.PictureSelector;
import com.luck.picture.lib.config.PictureConfig;
import com.luck.picture.lib.config.PictureMimeType;
import com.luck.picture.lib.entity.LocalMedia;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;

public class CheckExceptionActivity extends BaseActivity<CheckExceptionPresenter> implements CheckExceptionView {
    @BindView(R.id.tv_set_name)
    TextView tvSetName;
    @BindView(R.id.tv_set_place)
    TextView tvSetPlace;
    @BindView(R.id.tv_check_project)
    TextView tvCheckProject;
    @BindView(R.id.tv_check_content)
    TextView tvCheckContent;
    @BindView(R.id.tv_pro_type)
    TextView tvProType;
    @BindView(R.id.et_info)
    EditText etInfo;
    @BindView(R.id.btn_ex_save)
    Button btnExSave;
    @BindView(R.id.lay_pro_type)
    LinearLayout layProType;
    CheckExceptionBean bean;
    public List<PROBLEMKINDBean> data = new ArrayList<>();
    @BindView(R.id.rv_take_photo)
    RecyclerView mRecyclerView;
    private int maxSelectNum = 3;//最多到几张还可以选择
    private List<LocalMedia> selectList = new ArrayList<>();
    private int id;
    private GridImageAdapter adapter;

    @Override
    protected void initData() {
        id = getIntent().getIntExtra("id", 0);
        mPresenter.fetch(id);
    }

    @Override
    protected CheckExceptionPresenter initPresenter() {
        return new CheckExceptionPresenter();
    }

    @Override
    protected int getContentViewLayout() {
        return R.layout.activity_check_exception;
    }

    @Override
    protected void initView() {
        setTopTitle("点检异常问题登记");
        setTopLeftButton(R.drawable.back, new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        initWidget();
    }

    @Override
    public void showSuccess(CheckExceptionBean bean) {
        this.bean = bean;
        tvSetName.setText(bean.getMAINNAME());
        tvSetPlace.setText(bean.getPARTNAME());
        tvCheckProject.setText(bean.getITEMNAME());
        tvCheckContent.setText(bean.getCHECKCONTEXT());
        data.addAll(bean.getPROBLEMKIND());
    }

    @Override
    public void showError(String error) {
        ToastWrapper.show(error);
    }

    @Override
    public void save(String msg) {
        ToastWrapper.show(msg);
        finish();
    }


    @OnClick({R.id.lay_pro_type, R.id.btn_ex_save})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.lay_pro_type:
                showPopu();
                break;
            case R.id.btn_ex_save:
                save();
                break;
        }
    }

    private void save() {
        if (bean == null) {
            ToastWrapper.show("获取设备信息失败，请返回重新扫描");
            return;
        }
        showLoding();
//        photoList	照片列表
//        id	记录编号
//        mainid	设备编号
//        partid	部位编号
//        itemname	点检的项目
//        execman	点检人
//        problem	点检出的问题描述
//        problemkind	问题的类型

        String Loginname = SPUtils.getInstance(this).getString("Loginname");
        Map<String, RequestBody> map = new HashMap<>();
        map.put("id", toRequestBody(bean.getID() + ""));
        map.put("mainid", toRequestBody(bean.getMAINID()));
        map.put("partid", toRequestBody(bean.getPARTID()));
        map.put("execid",toRequestBody(bean.getEXECID()));
        map.put("itemname", toRequestBody(bean.getITEMNAME()));
        map.put("execman", toRequestBody(Loginname));
        map.put("problem", toRequestBody(etInfo.getText().toString()));
        map.put("problemkind", toRequestBody(tvProType.getText().toString()));

        List<MultipartBody.Part> parts = new ArrayList<>();
        for (int i = 0; i < selectList.size(); i++) {
            if(selectList.get(i).isCompressed()){
                File file = new File(selectList.get(i).getCompressPath());
                RequestBody requestFile = RequestBody.create(MediaType.parse("multipart/form-data"), file);
                MultipartBody.Part part = MultipartBody.Part.createFormData("photoList", file.getName(), requestFile);
                parts.add(part);
            }
        }
        mPresenter.save(map, parts);
    }

    public RequestBody toRequestBody(String value) {
        RequestBody requestBody = RequestBody.create(MediaType.parse("text/plain"), value);
        return requestBody;
    }

    private void showPopu() {
        PopuwindowListView popu = new PopuwindowListView(this, data);
        popu.setonPOPClickListener(new PopuwindowListView.onPOPClickListener() {
            @Override
            public void onClick(int position) {
                tvProType.setText(data.get(position).getName());
            }
        });
        popu.showPopupWindow(layProType);
    }

    private void initWidget() {
        FullyGridLayoutManager manager = new FullyGridLayoutManager(this, 4, GridLayoutManager.VERTICAL, false);
        mRecyclerView.setLayoutManager(manager);
        adapter = new GridImageAdapter(this, onAddPicClickListener);
        adapter.setList(selectList);
        adapter.setSelectMax(maxSelectNum);
        mRecyclerView.setAdapter(adapter);
        adapter.setOnItemClickListener((position, v) -> {
            if (selectList.size() > 0) {
                LocalMedia media = selectList.get(position);
                String mimeType = media.getMimeType();
                int mediaType = PictureMimeType.getMimeType(mimeType);
                switch (mediaType) {
                    case 11:
                        // 预览视频
                        PictureSelector.create(CheckExceptionActivity.this).externalPictureVideo(media.getPath());
                        break;
                    case 12:
                        // 预览音频
                        PictureSelector.create(CheckExceptionActivity.this).externalPictureAudio(media.getPath());
                        break;
                    default:
                        // 预览图片 可自定长按保存路径
//                        PictureWindowAnimationStyle animationStyle = new PictureWindowAnimationStyle();
//                        animationStyle.activityPreviewEnterAnimation = R.anim.picture_anim_up_in;
//                        animationStyle.activityPreviewExitAnimation = R.anim.picture_anim_down_out;
                        PictureSelector.create(CheckExceptionActivity.this)
                                .themeStyle(R.style.picture_default_style) // xml设置主题
                                //.setPictureStyle(mPictureParameterStyle)// 动态自定义相册主题
                                //.setPictureWindowAnimationStyle(animationStyle)// 自定义页面启动动画
                                .setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT)// 设置相册Activity方向，不设置默认使用系统
                                .isNotPreviewDownload(true)// 预览图片长按是否可以下载
                                .loadImageEngine(GlideEngine.createGlideEngine())// 外部传入图片加载引擎，必传项
                                .openExternalPreview(position, selectList);
                        break;
                }
            }
        });
    }

    private GridImageAdapter.onAddPicClickListener onAddPicClickListener = new GridImageAdapter.onAddPicClickListener() {
        @Override
        public void onAddPicClick() {
// 进入相册 以下是例子：不需要的api可以不写
            PictureSelector.create(CheckExceptionActivity.this)
                    .openGallery(PictureMimeType.ofImage())// 全部.PictureMimeType.ofAll()、图片.ofImage()、视频.ofVideo()、音频.ofAudio()
                    .loadImageEngine(GlideEngine.createGlideEngine())// 外部传入图片加载引擎，必传项
                    .isWeChatStyle(true)// 是否开启微信图片选择风格
                    .maxSelectNum(maxSelectNum)// 最大图片选择数量
                    .minSelectNum(1)// 最小选择数量
                    .imageSpanCount(3)// 每行显示个数
                    //.isAndroidQTransform(false)// 是否需要处理Android Q 拷贝至应用沙盒的操作，只针对compress(false); && enableCrop(false);有效,默认处理
                    .setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT)// 设置相册Activity方向，不设置默认使用系统
                    .previewImage(true)// 是否可预览图片
                    //.querySpecifiedFormatSuffix(PictureMimeType.ofJPEG())// 查询指定后缀格式资源
                    //.isMultipleSkipCrop(false)// 多图裁剪时是否支持跳过，默认支持
                    .isZoomAnim(true)// 图片列表点击 缩放效果 默认true
                    //.imageFormat(PictureMimeType.PNG)// 拍照保存图片格式后缀,默认jpe
                    .compress(true)
                    .compressQuality(80)// 图片压缩后输出质量 0~ 100
                    .synOrAsy(true)//同步false或异步true 压缩 默认同步
                    //.queryMaxFileSize(10)// 只查多少M以内的图片、视频、音频  单位M
                    //.compressSavePath(getPath())//压缩图片保存地址
                    //.sizeMultiplier(0.5f)// glide 加载图片大小 0~1之间 如设置 .glideOverride()无效 注：已废弃
                    //.glideOverride(160, 160)// glide 加载宽高，越小图片列表越流畅，但会影响列表图片浏览的清晰度 注：已废弃
                    .selectionMedia(selectList)// 是否传入已选图片
                    .cutOutQuality(90)// 裁剪输出质量 默认100
                    .minimumCompressSize(100)// 小于100kb的图片不压缩
                    .forResult(PictureConfig.CHOOSE_REQUEST);//结果回调onActivityResult code
        }
    };


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            switch (requestCode) {
                case PictureConfig.CHOOSE_REQUEST:
                    // 图片选择结果回调
                    selectList = PictureSelector.obtainMultipleResult(data);
                    // 例如 LocalMedia 里面返回五种path
                    // 1.media.getPath(); 为原图path
                    // 2.media.getCutPath();为裁剪后path，需判断media.isCut();是否为true
                    // 3.media.getCompressPath();为压缩后path，需判断media.isCompressed();是否为true
                    // 4.media.getOriginalPath()); media.isOriginal());为true时此字段才有值
                    // 5.media.getAndroidQToPath();为Android Q版本特有返回的字段，此字段有值就用来做上传使用
                    // 如果同时开启裁剪和压缩，则取压缩路径为准因为是先裁剪后压缩
                    adapter.setList(selectList);
                    adapter.notifyDataSetChanged();
                    break;
            }
        }
    }
}
