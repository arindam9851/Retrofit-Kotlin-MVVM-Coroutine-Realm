package com.mvvm_kotlin_retrofit.ui.main.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.mvvm_kotlin_retrofit.R
import com.mvvm_kotlin_retrofit.data.api.ApiHelper
import com.mvvm_kotlin_retrofit.data.api.RetrofitBuilder
import com.mvvm_kotlin_retrofit.data.model.Data
import com.mvvm_kotlin_retrofit.data.model.LanguageListResponse
import com.mvvm_kotlin_retrofit.ui.base.ViewModelFactory
import com.mvvm_kotlin_retrofit.ui.main.adapter.MainAdapter
import com.mvvm_kotlin_retrofit.ui.main.viewmodel.MainViewModel
import com.mvvm_kotlin_retrofit.utils.Status
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    private lateinit var viewModel: MainViewModel
    private lateinit var adapter: MainAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setupUI()
        setupViewModel()
        setupObservers()
    }

    private fun setupViewModel() {
        viewModel = ViewModelProviders.of(
                this,
                ViewModelFactory(ApiHelper(RetrofitBuilder.apiService),this)
        ).get(MainViewModel::class.java)

    }
    private fun setupUI() {
        recyclerView.layoutManager = LinearLayoutManager(this)
        adapter = MainAdapter(arrayListOf())
        recyclerView.addItemDecoration(
            DividerItemDecoration(
                recyclerView.context,
                (recyclerView.layoutManager as LinearLayoutManager).orientation
            )
        )
        recyclerView.adapter = adapter
    }
    private fun setupObservers() {
        if(viewModel.languageResponse.value==null){
            viewModel.getLanguageList("com.twr").observe(this, Observer {
                it?.let { resource ->
                    when (resource.status) {
                        Status.SUCCESS -> {
                            recyclerView.visibility = View.VISIBLE
                            progressBar.visibility = View.GONE
                            recyclerView.layoutManager = LinearLayoutManager(this)
                            adapter = MainAdapter(arrayListOf())
                            recyclerView.addItemDecoration(
                                DividerItemDecoration(
                                    recyclerView.context,
                                    (recyclerView.layoutManager as LinearLayoutManager).orientation
                                )
                            )
                            recyclerView.adapter = adapter
                            retrieveList(resource.data!!.value!!.data)

                        }
                        Status.ERROR -> {
                            recyclerView.visibility = View.VISIBLE
                            progressBar.visibility = View.GONE
                            Toast.makeText(this, it.message, Toast.LENGTH_LONG).show()
                        }
                        Status.LOADING -> {
                            progressBar.visibility = View.VISIBLE
                            recyclerView.visibility = View.GONE
                        }
                    }
                }
            })
        }
        else{

            viewModel.languageResponse.observe(this, Observer<LanguageListResponse> {
                this@MainActivity.runOnUiThread(java.lang.Runnable {
                    retrieveList(viewModel.languageResponse!!.value!!.data)
                })

            })

            
        }


    }

    private fun retrieveList(users: List<Data>) {
        adapter.apply {
            addUsers(users)
            notifyDataSetChanged()
        }
    }
}