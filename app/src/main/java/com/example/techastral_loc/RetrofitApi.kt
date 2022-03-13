package com.example.techastral_loc

import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface RetrofitApi {

    @POST("auth/login/")
    fun login(
        @Body params: logData
    ): Call<logData>

    @POST("auth/register/")
    fun register(
        @Body params: registerData
    ): Call<registerData>

    //    http://shrutiprasad.pythonanywhere.com/auth/CrowdFundingDetails/
    @GET("auth/CrowdFundingDetails/")
    fun funding(
    ): Call<ArrayList<dataItem>>

    @GET("auth/EventDetails/")
    fun volunter(
    ): Call<ArrayList<dataItem2>>

    //    "http://shrutiprasad.pythonanywhere.com/auth/Funding/"
    @POST("auth/Funding/")
    fun fund(
        @Body params: dataFund
    ): Call<dataFund>

    @GET("auth/NGODetails")
    fun ngo(
    ): Call<ArrayList<dataNgo>>

}