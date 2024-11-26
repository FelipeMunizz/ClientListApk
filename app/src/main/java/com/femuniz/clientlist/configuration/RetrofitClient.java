package com.femuniz.clientlist.configuration;

import androidx.annotation.NonNull;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import java.io.IOException;

public class RetrofitClient {
    private static final String BaseUrlUser = "http://10.0.2.2:5000/api/User/";
    private static final String BaseUrlClient = "http://10.0.2.2:5000/api/Client/";

    private static OkHttpClient createOkHttpClient(String token) {
        return new OkHttpClient.Builder()
                .addInterceptor(new Interceptor() {
                    @NonNull
                    @Override
                    public Response intercept(@NonNull Chain chain) throws IOException {
                        Request originalRequest = chain.request();

                        Request newRequest = originalRequest.newBuilder()
                                .header("Authorization", "Bearer " + token)
                                .build();

                        return chain.proceed(newRequest);
                    }
                })
                .build();
    }

    public static Retrofit GetClientUser() {

        return new Retrofit.Builder()
                .baseUrl(BaseUrlUser)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    public static Retrofit GetClientClient(String token) {
        OkHttpClient okHttpClient = createOkHttpClient(token);

        return new Retrofit.Builder()
                .baseUrl(BaseUrlClient)
                .addConverterFactory(GsonConverterFactory.create())
                .client(okHttpClient)
                .build();
    }
}
