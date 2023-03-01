package com.example.myapplication.api

import com.example.myapplication.models.CreateUserRequest
import com.example.myapplication.models.CreateUserResponse
import com.example.retrorfitexample.models.ListOfUser
import retrofit2.Call
import retrofit2.http.*

interface ApiInterface {

    @GET("/api/users")
    fun getUsersList(@Query("page") page:String?) : Call<ListOfUser>


//    fun getSingleUser(@Path(value = "id") userId: String?): Call<SingleUserResponse?>

//
//    @GET("/api/users/{id}")
//    fun getSingleUser(@Path(value = "id") userId: String?): Call<SingleUserResponse?>


    @POST("/api/users")
    fun createUser(@Body params: CreateUserRequest?): Call<CreateUserResponse>
}