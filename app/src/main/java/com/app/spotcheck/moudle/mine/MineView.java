package com.app.spotcheck.moudle.mine;

import com.app.spotcheck.base.BaseView;
import com.app.spotcheck.moudle.bean.MineBean;

/**
 * @ClassName: MineView
 * @Description: java类作用描述
 * @Author: 作者名
 * @CreateDate: 2019/12/18 20:32
 */
public interface MineView extends BaseView {

   void showSuccess(MineBean bean);

   void showError(String error);

}
