package com.miaopu.shop.web;


import android.text.TextUtils;

import com.alibaba.fastjson.JSONObject;
import com.miaopu.shop.ShopApplication;
import com.miaopu.shop.utils.Constants;
import com.miaopu.shop.utils.l;
import com.miaopu.shop.web.fastjson.FastJsonConverterFactory;

import java.util.Map;
import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

/**
 * Created by user on 2017/6/23.
 *
 * @Date: 2017/6/23
 * @Email: zzh_hz@126.com
 * @QQ: 1299234582
 * @author: zzh
 * @Description: 网络访问框架封装
 */
public class RetrofitUtils {
    public static final String TAG = "---result---";
    /**
     * "http://192.168.200.15:8088/cyye/"
     * http://124.128.34.243:8011
     */
    public static final String BASE_URL = Constants.BASE_URL + "/";
    private static Retrofit retrofit;
    private static OkHttpClient okHttpClient = new OkHttpClient.Builder().addInterceptor((chain) -> {
        Request.Builder builder = chain.request()
                .newBuilder()
                .addHeader("Accept", "application/json")
                .addHeader("Content-Type", "application/json");
        if (!TextUtils.isEmpty(ShopApplication.getToken())) {
            builder.header("Authorization", "Bearer " + ShopApplication.getToken());
        }
        Request request = builder.build();
        return chain.proceed(request);
    }).writeTimeout(60, TimeUnit.SECONDS).build();


    public static ApiService Api() {
        if (retrofit == null) {
            retrofit = new Retrofit.Builder().baseUrl(BASE_URL)
                    .addConverterFactory(FastJsonConverterFactory.create())
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create()).client(okHttpClient)
                    .build();
        }
        return retrofit.create(ApiService.class);
    }

    public static RequestBody map2Params(Map<String, String> map) {
        if (map != null && !map.isEmpty()) {
            String json = JSONObject.toJSONString(map);
            l.d(json);
            return RequestBody.create(okhttp3.MediaType.parse("application/json; charset=utf-8"), json);
        } else {
            return RequestBody.create(okhttp3.MediaType.parse("application/json; charset=utf-8"), "{}");
        }
    }

}
