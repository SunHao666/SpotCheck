package com.app.spotcheck.base.wrapper;

import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.PopupWindow;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.app.spotcheck.R;
import com.app.spotcheck.base.adapter.ProTypeAdapter;
import com.app.spotcheck.base.utils.CommonAdapter;
import com.app.spotcheck.moudle.bean.CheckExceptionBean;
import com.app.spotcheck.moudle.bean.PROBLEMKINDBean;

import java.util.List;

/**
 * @ClassName: PopuwindowListView
 * @Description: java类作用描述
 * @Author: 作者名
 * @CreateDate: 2019/12/21 15:06
 */
public class PopuwindowListView extends PopupWindow {
    public Context context;
    public  List<PROBLEMKINDBean> data;
    public PopuwindowListView(Context context, List<PROBLEMKINDBean> data) {
        super(context);
        this.context = context;
        this.data = data;
        init();
    }

    private void init() {
        View conentView = LayoutInflater.from(context).inflate(R.layout.popu_list, null);
        RecyclerView rv_list = conentView.findViewById(R.id.rv_list);
        rv_list.setLayoutManager(new LinearLayoutManager(context));
        ProTypeAdapter adapter = new ProTypeAdapter(context,data);
        rv_list.setAdapter(adapter);
        // 设置SelectPicPopupWindow的View
        this.setContentView(conentView);
        // 设置SelectPicPopupWindow弹出窗体的宽
        this.setWidth(ViewGroup.LayoutParams.MATCH_PARENT);
        //    this.setWidth(view.getWidth());
        // 设置SelectPicPopupWindow弹出窗体的高
        this.setHeight(ViewGroup.LayoutParams.WRAP_CONTENT);
        // 设置SelectPicPopupWindow弹出窗体可点击
        this.setFocusable(true);
        // 刷新状态
        this.update();
        this.setOutsideTouchable(true);
        // 实例化一个ColorDrawable颜色为半透明
        ColorDrawable dw = new ColorDrawable(0000000000);
        // 点back键和其他地方使其消失,设置了这个才能触发OnDismisslistener ，设置其他控件变化等操作
        this.setBackgroundDrawable(dw);

        adapter.setonProClickListener(new ProTypeAdapter.onProClickListener() {
            @Override
            public void onClick(int position) {
                listener.onClick(position);
                dismiss();
            }
        });

    }

    public void showPopupWindow(View parent) {
        if (!this.isShowing()) {
            // 以下拉方式显示popupwindow
            //  this.showAsDropDown(parent);
            // this.showAsDropDown(parent,0,10);
            this.showAsDropDown(parent);
        }
    }


    onPOPClickListener listener;
    public void setonPOPClickListener(onPOPClickListener listener){
        this.listener = listener;
    }
    public interface onPOPClickListener{
        void onClick(int position);
    }

}
