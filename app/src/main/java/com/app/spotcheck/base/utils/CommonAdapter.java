package com.app.spotcheck.base.utils;

import android.content.Context;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

/**
 * @ClassName: CommonAdapter
 * @Description: java类作用描述
 * @Author: 作者名
 * @CreateDate: 2019/12/19 20:25
 */
public abstract class CommonAdapter<T> extends RecyclerView.Adapter<CommonAdapter.ViewHolder>
{
    protected Context mContext;
    protected int mLayoutId;
    protected List<T> mDatas;
    protected LayoutInflater mInflater;


    public CommonAdapter(Context context, int layoutId, List<T> datas)
    {
        mContext = context;
        mInflater = LayoutInflater.from(context);
        mLayoutId = layoutId;
        mDatas = datas;
    }

    @Override
    public ViewHolder onCreateViewHolder(final ViewGroup parent, int viewType)
    {
        ViewHolder viewHolder = ViewHolder.get(mContext, parent, mLayoutId);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position)
    {
        convert(holder, mDatas.get(position));
    }

    public abstract void convert(ViewHolder holder, T t);

    @Override
    public int getItemCount()
    {
        return mDatas.size();
    }

     public static class ViewHolder extends RecyclerView.ViewHolder
    {
        private SparseArray<View> mViews;
        private View mConvertView;
        private Context mContext;

        public ViewHolder(Context context, View itemView, ViewGroup parent)
        {
            super(itemView);
            mContext = context;
            mConvertView = itemView;
            mViews = new SparseArray<View>();
        }


        public static ViewHolder get(Context context, ViewGroup parent, int layoutId)
        {

            View itemView = LayoutInflater.from(context).inflate(layoutId, parent,
                    false);
            ViewHolder holder = new ViewHolder(context, itemView, parent);
            return holder;
        }


        /**
         * 通过viewId获取控件
         *
         * @param viewId
         * @return
         */
        public <T extends View> T getView(int viewId)
        {
            View view = mViews.get(viewId);
            if (view == null)
            {
                view = mConvertView.findViewById(viewId);
                mViews.put(viewId, view);
            }
            return (T) view;
        }
        public ViewHolder setText(int viewId, String text)
        {
            TextView tv = getView(viewId);
            tv.setText(text);
            return this;
        }

        public ViewHolder setImageResource(int viewId, int resId)
        {
            ImageView view = getView(viewId);
            view.setImageResource(resId);
            return this;
        }

        public ViewHolder setOnClickListener(int viewId,
                                             View.OnClickListener listener)
        {
            View view = getView(viewId);
            view.setOnClickListener(listener);
            return this;
        }
    }
}
