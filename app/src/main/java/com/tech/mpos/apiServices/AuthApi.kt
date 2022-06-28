package com.tech.mpos.apiServices

import com.tech.mpos.models.SignInBody
import com.tech.mpos.models.UserBody
import com.tech.mpos.loginResponse.LoginResponse
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
}