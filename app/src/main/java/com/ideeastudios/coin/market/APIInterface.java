package com.ideeastudios.coin.market;

import com.ideeastudios.coin.market.retrofit.CryptoList;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Query;

interface APIInterface {

    //https://pro.coinmarketcap.com/api/v1#section/Quick-Start-Guide
    @Headers("X-CMC_PRO_API_KEY: 3069b872-06b0-40f1-bf44-5a3f7f73d796")
    @GET("/v1/cryptocurrency/listings/latest?")
    Call<CryptoList> doGetUserList(@Query("limit") String page);

}
