package com.femuniz.clientlist.services;


import com.femuniz.clientlist.models.WebResponse;
import com.femuniz.clientlist.models.UserModel;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface UserService {
    @POST("LoginUser")
    Call<WebResponse<Object>> LoginUser(@Body UserModel.LoginUser loginUser);

    @POST("RegisterUser")
    Call<WebResponse<UserModel.User>> RegisterUser(@Body UserModel.User user);
}
