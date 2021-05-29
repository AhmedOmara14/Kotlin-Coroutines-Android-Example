package com.omaradev.examplecoroutine.Util

import com.omaradev.examplecoroutine.models.Posts.Posts
import retrofit2.Response

sealed class ApiState{
    object Loading : ApiState()
    class Failure(val msg: Int) : ApiState()
    class Success(val data: Response<Posts>) : ApiState()
    object Empty : ApiState()
}