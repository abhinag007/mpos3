package com.tech.mpos.apiServices

import com.tech.mpos.models.SignInBody
import com.tech.mpos.models.UserBody
import com.tech.mpos.loginResponse.LoginResponse
import com.tech.mpos.transactionResponse.TransactionResponse
import com.tech.mpos.userResponse.UserResponse
import okhttp3.RequestBody
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.http.*

interface ApiInterface {
    @Headers("Content-Type:application/json")
    @POST("users/login")
    fun signin(@Body info: SignInBody): Call<LoginResponse>

    @Headers("Content-Type:application/json")
    @POST("users/register")
    fun registerUser(
        @Body info: UserBody
    ): retrofit2.Call<ResponseBody>

    @Headers("Content-Type:application/json")
    @GET("transactions")
    fun transactionsData(@Header("Authorization") token: String): Call<TransactionResponse>

    @Headers("Content-Type:application/json")
    @PUT("users/me")
    fun updateUser(@Header("Authorization") token: String, @Body requestBody: RequestBody):Call<UserResponse>

}