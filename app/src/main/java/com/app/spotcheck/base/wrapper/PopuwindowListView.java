package com.app.spotcheck.base.wrapper;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.PopupWindow;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.app.spotcheck.R;
import com.app.spotcheck.base.adapter.ProTypeAdapter;
import com.app.spotcheck.base.utils.CommonAdapter;
import com.app.spotcheck.moudle.bean.CheckExceptionBean;

import java.util.List;

/**
 * @ClassName: PopuwindowListView
 * @Description: java类作用描述
 * @Author: 作者名
 * @CreateDate: 2019/12/21 15:06
 */
public class PopuwindowListView extends PopupWindow {
    public Context context;
    public  List<CheckExceptionBean.PROBLEMKINDBean> data;
    public PopuwindowListView(Context context, List<CheckExceptionBean.PROBLEMKINDBean> data) {
        super(context);
        this.context = context;
        this.data = data;
        init();
    }

    private void init() {
        View view = LayoutInflater.from(context).inflate(R.layout.popu_list, null);
        RecyclerView rv_list = view.findViewById(R.id.rv_list);
        rv_list.setLayoutManager(new LinearLayoutManager(context));
        ProTypeAdapter adapter = new ProTypeAdapter(context,data);
        adapter.setonProClickListener(new ProTypeAdapter.onProClickListener() {
            @Override
            public void onClick(int position) {
                listener.onClick(position);
                dismiss();
            }
        });
        rv_list.setAdapter(adapter);
        this.setContentView(view);
    }

    onPOPClickListener listener;
    public void setonPOPClickListener(onPOPClickListener listener){
        this.listener = listener;
    }
    public interface onPOPClickListener{
        void onClick(int position);
    }

}
