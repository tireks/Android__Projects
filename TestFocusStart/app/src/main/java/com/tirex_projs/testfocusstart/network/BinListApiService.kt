package com.tirex_projs.testfocusstart.network

import com.tirex_projs.testfocusstart.model.CardModel
import io.reactivex.Observable
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query
import retrofit2.http.Url

interface BinListApiService {
 @GET
 fun getCard(@Url url:String): Observable<CardModel.Card>
 companion object {
  fun create(): BinListApiService {

   val retrofit = Retrofit.Builder()
    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
    .addConverterFactory(GsonConverterFactory.create())
    .baseUrl("https://lookup.binlist.net/")
    .build()

   return retrofit.create(BinListApiService::class.java)
  }
 }
}