package com.example.resttest.Common
import com.example.resttest.Interface.RetrofitServices
import com.example.resttest.Retrofit.RetrofitClient

object Common {
    private var BASE_URL = "https://www.simplifiedcoding.net/demos/"
    val retrofitService: RetrofitServices
        get() = RetrofitClient.getClient(BASE_URL).create(RetrofitServices::class.java)
}