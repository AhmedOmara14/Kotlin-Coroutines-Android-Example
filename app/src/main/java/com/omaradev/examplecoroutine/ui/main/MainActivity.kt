package com.omaradev.examplecoroutine.ui.main

import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.omaradev.examplecoroutine.R
import com.omaradev.examplecoroutine.Util.ApiState
import com.omaradev.examplecoroutine.adapter.AdapterOfPosts
import com.omaradev.examplecoroutine.models.Posts.Posts
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect

/**
Created By Ahmed Omara
 **/

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private val mainViewModel :MainViewModel by viewModels()
    private lateinit var recyclerView :RecyclerView
    private lateinit var adapterOfPosts: AdapterOfPosts
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initComponent();
        lifecycleScope.launchWhenStarted  {
            mainViewModel.getPosts.collect { it->
                when(it){
                    is ApiState.Loading -> {

                    }
                    is ApiState.Failure -> {
                        Log.d("main", "onCreate: ${it.msg}")
                    }
                    is ApiState.Success -> {
                        Log.d(TAG, "onCreate: " + it.data.body()?.get(0))
                        initRecyclerView(it.data.body())
                    }
                    is ApiState.Empty -> {

                    }
                }
            }

        }

    }

    private fun initRecyclerView(body: Posts?) {
        recyclerView.setHasFixedSize(true)
        val layoutManager2: RecyclerView.LayoutManager =
            LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        recyclerView.setLayoutManager(layoutManager2)
        adapterOfPosts= body?.let { AdapterOfPosts( it) }!!
        recyclerView.setAdapter(adapterOfPosts)
        adapterOfPosts.notifyDataSetChanged()
    }

    private fun initComponent() {
        recyclerView =findViewById(R.id.rvOfAllPosts);
    }



    companion object {
        private const val TAG = "MainActivity"
    }
}