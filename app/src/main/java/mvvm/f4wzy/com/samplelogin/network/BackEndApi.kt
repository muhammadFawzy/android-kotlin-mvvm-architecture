package mvvm.f4wzy.com.samplelogin.network

import mvvm.f4wzy.com.samplelogin.model.User
import retrofit2.Call
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

interface BackEndApi {
    @FormUrlEncoded
    @POST("auth/login")
    fun LOGIN(@Field("email") email: String, @Field("password") password: String): Call<User>


}

