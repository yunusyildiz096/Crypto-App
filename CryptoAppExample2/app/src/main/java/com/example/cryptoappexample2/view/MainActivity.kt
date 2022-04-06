package com.example.cryptoappexample2.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.Menu
import android.view.View
import android.widget.SearchView
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.cryptoappexample.adapter.RecyclerAdapter
import com.example.cryptoappexample.viewmodel.FeedViewModel
import com.example.cryptoappexample2.R
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    private var viewModel : FeedViewModel? = null
    private val recyclerAdapter = RecyclerAdapter(arrayListOf())


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        viewModel = ViewModelProviders.of(this).get(FeedViewModel::class.java)
        viewModel!!.refreshData()

        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = recyclerAdapter


        observeLiveData()

    }



    fun observeLiveData(){
        viewModel!!.cryptos.observe(this, Observer { crypto ->
            crypto.let {
                recyclerAdapter.updateCountryList(it)
                recyclerView.visibility = View.VISIBLE
                errorText.visibility = View.GONE
                progressBar.visibility = View.GONE
            }
        })

        viewModel!!.loading.observe(this, Observer { loading ->
            loading?.let {
                if(it){
                    progressBar.visibility = View.VISIBLE
                    recyclerView.visibility = View.GONE
                    errorText.visibility = View.GONE
                }else{
                    progressBar.visibility = View.GONE

                }
            }
        })
        viewModel!!.error.observe(this, Observer { error ->
            error?.let {
                if (it){
                    errorText.visibility = View.VISIBLE
                    recyclerView.visibility = View.GONE
                    progressBar.visibility = View.GONE
                }else{
                    errorText.visibility = View.GONE
                }
            }
        })
    }


}