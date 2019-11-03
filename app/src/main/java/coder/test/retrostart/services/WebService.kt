package coder.test.retrostart.services

import coder.test.retrostart.models.Category
import coder.test.retrostart.models.Token
import retrofit2.Call
import retrofit2.http.*

interface WebService {

    @FormUrlEncoded
    @POST("login")
    fun loginUser(
        @Field("email") email: String,
        @Field("password") password: String,
        @Field("apikey") apikey: String
    ): Call<Token>


    @GET("product_cats")
    fun getAllCategory(
        @Header("Authorization") token : String
    ): Call<List<Category>>

}