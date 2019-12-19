package com.app.spotcheck.base.utils;

/**
 * @ClassName: MultiItemTypeSupport
 * @Description: java类作用描述
 * @Author: 作者名
 * @CreateDate: 2019/12/19 20:32
 */
public interface MultiItemTypeSupport<T> {
    int getLayoutId(int itemType);

    int getItemViewType(int position, T t);
}
