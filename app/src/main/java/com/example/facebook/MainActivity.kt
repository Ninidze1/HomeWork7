package com.example.facebook

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.facebook.app.RestClient
import com.example.facebook.app.RestClient.apiCall
import com.example.facebook.app.dto.PutUser
import com.example.facebook.app.util.Logger.LOG_TAG
import com.example.facebook.app.util.Logger.logger

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        RestClient.initClient()

        RestClient.reqResApi.getUsers(1).apiCall(
            onSuccess = { response ->
                response.data?.forEach { user -> logger("getUsers\n$user") }
            },
            onFailure = {
                logger(it.stackTraceToString())
            }
        )

        RestClient.reqResApi.getUser(1).apiCall(
            onSuccess = { response ->
                logger("getUser\n${response}")
            },
            onFailure = {
                logger(it.stackTraceToString())
            }
        )

        val user = PutUser(
            name = "Giorgi",
            job = "Svarschik"
        )
        RestClient.reqResApi.postUser(user).apiCall(
            onSuccess = { response ->
                logger("postUser\n${response}")
            },
            onFailure = {
                logger(it.stackTraceToString())
            }
        )

        val putUser = PutUser(
            name = "Giorgi",
            job = "Parikmaxer"
        )
        RestClient.reqResApi.putUser(1, putUser).apiCall(
            onSuccess = { response ->
                logger("putUser\n${response}")
            },
            onFailure = {
                logger(it.stackTraceToString())
            }
        )

        RestClient.reqResApi.deleteUser(2).apiCall(
            onSuccess = {
                logger("putUser -> Deleted Successfully")
            },
            onFailure = {
                logger(it.stackTraceToString())
            }
        )

        RestClient.reqResApi.registerUser(
            email = "ninidze@reqres.in",
            password = "123123123"
        ).apiCall(
            onSuccess = { response ->
                logger("registerUser -> $response")
            },
            onFailure = {
                logger(it.stackTraceToString())
            }
        )
    }
}