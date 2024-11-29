package com.femuniz.clientlist.services;

import com.femuniz.clientlist.models.Client;
import com.femuniz.clientlist.models.WebResponse;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Query;

public interface ClientService {
    @GET("GetAllClient")
    Call<List<Client>> GetAllClient(@Query("idUser") int idUser);

    @GET("GetClientById")
    Call<Client> GetClientById(@Query("idClient") int idClient);

    @POST("AddClient")
    Call<WebResponse<Client>> AddClient(@Body Client client);

    @PUT("UpdateClient")
    Call<WebResponse<Client>> UpdateClient(@Body Client client);

    @DELETE("DeleteClient")
    Call<WebResponse<Boolean>> DeleteClient(@Query("idClient") int idClient);
}
