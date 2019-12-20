package com.app.spotcheck.moudle.spotcheck;

import com.app.spotcheck.base.BasePresenter;
import com.app.spotcheck.moudle.bean.SpotCheckAllBean;
import com.app.spotcheck.network.BaseCallback;
import com.app.spotcheck.network.NetManager;

import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName: SpotCheckPresenter
 * @Description: java类作用描述
 * @Author: 作者名
 * @CreateDate: 2019/12/18 16:15
 */
public class SpotCheckPresenter extends BasePresenter<SpotCheckView> {

    public void getCheckPlanList(String mainid,String mainname){
        NetManager.getInstance().api().getCheckPlanList(mainid,mainname)
                .enqueue(new BaseCallback<SpotCheckAllBean>() {
                    @Override
                    protected void onSuccess(SpotCheckAllBean bean) {

                    }

                    @Override
                    protected void onFailed(int code, String msg) {
                        SpotCheckAllBean bean1 = new SpotCheckAllBean();
                        List<SpotCheckAllBean.SearchListBean> searchList = new ArrayList<>();
                        searchList.add(new SpotCheckAllBean.SearchListBean("111","222",14,2));
                        searchList.add(new SpotCheckAllBean.SearchListBean("111","222",14,2));
                        searchList.add(new SpotCheckAllBean.SearchListBean("111","222",14,2));
                        bean1.setSearchList(searchList);
                        mView.showSuccess(bean1);
//                        mView.showError(msg);
                    }
                });
    }

    public void getUnCheckPlanList(String mainid,String mainname){
        NetManager.getInstance().api().getUnCheckPlanList(mainid,mainname)
                .enqueue(new BaseCallback<SpotCheckAllBean>() {
                    @Override
                    protected void onSuccess(SpotCheckAllBean bean) {
                        SpotCheckAllBean bean1 = new SpotCheckAllBean();
                        List<SpotCheckAllBean.SearchListBean> searchList = new ArrayList<>();
                        searchList.add(new SpotCheckAllBean.SearchListBean("111","222",14,0));
                        searchList.add(new SpotCheckAllBean.SearchListBean("111","222",14,0));
                        searchList.add(new SpotCheckAllBean.SearchListBean("111","222",14,0));
                        mView.showSuccess(bean1);
                    }

                    @Override
                    protected void onFailed(int code, String msg) {
                        SpotCheckAllBean bean1 = new SpotCheckAllBean();
                        List<SpotCheckAllBean.SearchListBean> searchList = new ArrayList<>();
                        searchList.add(new SpotCheckAllBean.SearchListBean("111","222",14,2));
                        searchList.add(new SpotCheckAllBean.SearchListBean("111","222",14,2));
                        searchList.add(new SpotCheckAllBean.SearchListBean("111","222",14,2));
                        bean1.setSearchList(searchList);
                        mView.showSuccess(bean1);
//                        mView.showError(msg);
                    }
                });
    }


    public void getCheckedPlanList(String mainid,String mainname){
        NetManager.getInstance().api().getCheckedPlanList(mainid,mainname)
                .enqueue(new BaseCallback<SpotCheckAllBean>() {
                    @Override
                    protected void onSuccess(SpotCheckAllBean bean) {
                        SpotCheckAllBean bean1 = new SpotCheckAllBean();
                        List<SpotCheckAllBean.SearchListBean> searchList = bean1.getSearchList();
                        searchList.add(new SpotCheckAllBean.SearchListBean("111","222",14,0));
                        searchList.add(new SpotCheckAllBean.SearchListBean("111","222",14,0));
                        searchList.add(new SpotCheckAllBean.SearchListBean("111","222",14,0));
                        mView.showSuccess(bean1);
                    }

                    @Override
                    protected void onFailed(int code, String msg) {
                        SpotCheckAllBean bean1 = new SpotCheckAllBean();
                        List<SpotCheckAllBean.SearchListBean> searchList = new ArrayList<>();
                        searchList.add(new SpotCheckAllBean.SearchListBean("111","222",14,2));
                        searchList.add(new SpotCheckAllBean.SearchListBean("111","222",14,2));
                        searchList.add(new SpotCheckAllBean.SearchListBean("111","222",14,2));
                        bean1.setSearchList(searchList);
                        mView.showSuccess(bean1);
//                        mView.showError(msg);
                    }
                });
    }
}
