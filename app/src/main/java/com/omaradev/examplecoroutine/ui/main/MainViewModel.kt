package com.omaradev.examplecoroutine.ui.main

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.omaradev.examplecoroutine.Util.ApiState
import com.omaradev.examplecoroutine.repository.mainRepo.mainRepo
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class MainViewModel @ViewModelInject constructor(private val mainRepo: mainRepo) : ViewModel() {

    private val _response = MutableStateFlow<ApiState>(ApiState.Empty)
    public val getPosts: StateFlow<ApiState> = _response

    init {
        getAllPosts()
    }

    private fun getAllPosts() {
        viewModelScope.launch {
            mainRepo.getPosts().let { responce ->
                if (responce.isSuccessful) {
                      _response.value = ApiState.Success(responce);
                } else {
                    _response.value = ApiState.Failure(responce.code())
                }
            }
        }
    }

    companion object {
        private const val TAG = "MainViewModel"
    }
}
