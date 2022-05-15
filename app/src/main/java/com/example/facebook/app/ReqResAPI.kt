package com.example.facebook.app

import com.example.facebook.app.dto.*
import retrofit2.Call
import retrofit2.http.*


interface ReqResAPI {
    @GET("users")
    fun getUsers(@Query("page") page: Int): Call<ReqResData<List<User>>>

    @GET("users/{userId}")
    fun getUser(@Path("userId") userId: Long): Call<ReqResData<User>>

    @POST("users")
    fun postUser(@Body user: PutUser): Call<GetUser>

    @FormUrlEncoded
    @POST("register")
    fun registerUser(
        @Field("email") email: String,
        @Field("password") password: String,
    ): Call<RegisterUser>

    @DELETE("users/{userId}")
    fun deleteUser(@Path("userId") userId: Long): Call<Unit>

    @PUT("users/{userId}")
    fun putUser(
        @Path("userId") userId: Long,
        @Body user: PutUser)
    : Call<GetUser>

}