package com.app.spotcheck.moudle.bean;

/**
 * @ClassName: LoginBean
 * @Description: java类作用描述
 * @Author: 作者名
 * @CreateDate: 2019/12/18 15:12
 */
public class LoginBean {

    public int result_code;
    public String result_message;

    public int getResult_code() {
        return result_code;
    }

    public void setResult_code(int result_code) {
        this.result_code = result_code;
    }

    public String getResult_message() {
        return result_message;
    }

    public void setResult_message(String result_message) {
        this.result_message = result_message;
    }
}
