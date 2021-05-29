package com.omaradev.examplecoroutine.network

import com.omaradev.examplecoroutine.models.Posts.Posts
import retrofit2.Response
import retrofit2.http.GET

interface ApiServices {

    @GET("posts")
    public suspend fun getPosts() : Response<Posts>
}