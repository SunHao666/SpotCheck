package com.app.spotcheck.network;

import android.text.TextUtils;
import android.util.Log;

import com.app.spotcheck.base.utils.LogUtils;
import com.app.spotcheck.base.utils.SPUtils;
import com.app.spotcheck.moudle.MApplication;

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
public class PathInterceptor implements Interceptor {
    private String newHost = "";
    @Override
    public Response intercept(Chain chain) throws IOException {

        String newHost = SPUtils.getInstance(MApplication.getIntance()).getString("path");

        Request request = chain.request();
        HttpUrl url = request.url();
        //http://127.0.0.1/test/upload/img?userName=xiaoming&userPassword=12345
        String scheme = url.scheme();//  http https
        String host = url.host();//   127.0.0.1
        String path = url.encodedPath();//  /test/upload/img
        String query = url.encodedQuery();//  userName=xiaoming&userPassword=12345
        if(TextUtils.isEmpty(newHost)){
            newHost = host;
        }
        StringBuffer sb = new StringBuffer();
        String newUrl = sb.append(scheme).append("://").append(newHost).append(path).append("?").append(query).toString();
        LogUtils.error("newUrl="+newUrl);
        Request.Builder builder = request.newBuilder()
                .url(newUrl);

        return chain.proceed(builder.build());
    }
}
