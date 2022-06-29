package com.tech.mpos.apiServices

import android.database.Observable
import com.tech.mpos.models.SignInBody
import com.tech.mpos.models.UserBody
import com.tech.mpos.loginResponse.LoginResponse
import com.tech.mpos.models.EmailUpdate
import com.tech.mpos.models.ReceiptEmail
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

    @Headers("Content-Type:application/json", "Accept: 'application/json")
    @GET("transactions")
    fun transactionsData(@Header("Authorization") token: String): Call<TransactionResponse>

    @Headers("Content-Type:application/json")
    @PUT("users/me")
    fun updateReceiptEmail(@Header("Authorization") token: String, @Body requestBody: ReceiptEmail):Call<UserResponse>

    @Headers("Content-Type:application/json")
    @PUT("users/me")
    fun updateEmail(@Header("Authorization") token: String, @Body requestBody: EmailUpdate):Call<UserResponse>

}