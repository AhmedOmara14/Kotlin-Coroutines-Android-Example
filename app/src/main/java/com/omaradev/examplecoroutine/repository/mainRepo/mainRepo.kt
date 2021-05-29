package com.omaradev.examplecoroutine.repository.mainRepo

import androidx.lifecycle.MutableLiveData
import com.omaradev.examplecoroutine.models.Posts.Posts
import com.omaradev.examplecoroutine.network.ApiServices
import javax.inject.Inject

class mainRepo
@Inject constructor(private val apiServices: ApiServices) {

    suspend fun getPosts() = apiServices.getPosts()
}