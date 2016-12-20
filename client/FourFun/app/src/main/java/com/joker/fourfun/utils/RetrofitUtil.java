package com.joker.fourfun.utils;

import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import com.joker.fourfun.BuildConfig;
import com.joker.fourfun.FourFun;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.Cache;
import okhttp3.CacheControl;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by joker on 2016/11/27.
 */

public class RetrofitUtil {
    private static final int DEFAULT_TIMEOUT = 10;
    public static final String SERVER_IP_ADDRESS = "http://192.168.155.1";
    private final static String BASE_URL = SERVER_IP_ADDRESS +":8080/4Fun/";
    private static Retrofit mRetrofit;

    public RetrofitUtil() {
        mRetrofit = new Retrofit.Builder().baseUrl(BASE_URL)
                .client(initOkHttp())
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
    }

    private OkHttpClient initOkHttp() {
        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        Interceptor cacheInterceptor = new Interceptor() {
            @Override
            public Response intercept(Chain chain) throws IOException {
                Request request = chain.request();
                if (!SystemUtil.isNetworkConnected()) {
                    request = request.newBuilder()
                            .cacheControl(CacheControl.FORCE_CACHE)
                            .build();
                }
                Response response = chain.proceed(request);
                if (SystemUtil.isNetworkConnected()) {
                    int maxAge = 0;
                    // 有网络时, 不缓存, 最大保存时长为0
                    response.newBuilder()
                            .header("Cache-Control", "public, max-age=" + maxAge)
                            .removeHeader("Pragma")
                            .build();
                } else {
                    // 无网络时，设置超时为4周
                    int maxStale = 60 * 60 * 24 * 28;
                    response.newBuilder()
                            .header("Cache-Control", "public, only-if-cached, max-stale=" + maxStale)
                            .removeHeader("Pragma")
                            .build();
                }

                return response;
            }
        };
        if (BuildConfig.DEBUG) {
            HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
            interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
            builder.addInterceptor(interceptor);
        }
        File cacheFile = new File(FourFun.getInstance().getCacheDir().getPath());
        // 设置缓存文件 20MB
        Cache cache = new Cache(cacheFile, 1024 * 1024 * 20);
        return builder
                .addInterceptor(cacheInterceptor)
                .addNetworkInterceptor(cacheInterceptor)
                .cache(cache)
                .connectTimeout(DEFAULT_TIMEOUT, TimeUnit.SECONDS)
                .readTimeout(DEFAULT_TIMEOUT, TimeUnit.SECONDS)
                .writeTimeout(DEFAULT_TIMEOUT, TimeUnit.SECONDS)
                .retryOnConnectionFailure(true)
                .build();
    }

    public <T> T create(Class<T> service) {
        return mRetrofit.create(service);
    }
}
