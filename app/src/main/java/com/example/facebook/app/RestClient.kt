package com.example.facebook.app

import com.example.facebook.app.dto.ReqResData
import com.example.facebook.app.util.Logger.logger
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RestClient {
    private lateinit var retrofit: Retrofit
    private lateinit var okHttpClient: OkHttpClient

    fun initClient() {
        val interceptor = HttpLoggingInterceptor()
        val client = OkHttpClient.Builder().addInterceptor(interceptor).build()
        interceptor.apply { interceptor.level = HttpLoggingInterceptor.Level.BODY }

        okHttpClient = OkHttpClient.Builder().build()
        retrofit = Retrofit.Builder()
            .baseUrl("https://reqres.in/api/")
            .client(client)
            .addConverterFactory(
                GsonConverterFactory.create()
            )
            .build()

    }

    private fun <T> createService(serviceClass: Class<T>): T {
        return retrofit.create(serviceClass)
    }

    val reqResApi: ReqResAPI
        get() = createService(ReqResAPI::class.java)


    fun <R> Call<R>.apiCall(
        onSuccess: (R) -> Unit,
        onFailure: ((Throwable) -> Unit)? = null,
    ) {
        enqueue(
            object: Callback<R> {
                override fun onResponse(
                    call: Call<R>,
                    response: Response<R>
                ) {
                    if(response.isSuccessful && response.body() != null){
                        onSuccess.invoke(response.body()!!)
                    }
                }

                override fun onFailure(call: Call<R>, t: Throwable) {
                    onFailure?.invoke(t)
                }
            }
        )
    }

}