package com.app.spotcheck.network;

import com.app.spotcheck.base.utils.SPUtils;
import com.app.spotcheck.moudle.MApplication;
import com.app.spotcheck.utils.GlobalKey;

import java.io.IOException;

import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 * @ClassName: HeadInterceptor
 * @Description: java类作用描述
 * @Author: 作者名
 * @CreateDate: 2019/12/24 14:46
 */
public class HeadInterceptor implements Interceptor {
    @Override
    public Response intercept(Chain chain) throws IOException {
        Request request = chain.request();
        String path = request.url().url().toString();

        StringBuffer sb = new StringBuffer();
        sb.append(path).append("?").append("logtime="+Contant.LOGTIME);
        MApplication intance = MApplication.getIntance();
        String uuAdminUser = SPUtils.getInstance(intance).getString(GlobalKey.KEY_UUADMINUSER);
        Request.Builder builder = request.newBuilder()
                .url(sb.toString())
                .addHeader("uuAdminUser",uuAdminUser == null ? "":uuAdminUser)
                .addHeader("Content-Type","application/json")
                .addHeader("Accept","application/json");

        return chain.proceed(builder.build());
    }
}
