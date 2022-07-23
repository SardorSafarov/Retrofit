package com.example.retrofit

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.retrofit.adaptet.Adapter
import com.example.retrofit.databinding.ActivityMainBinding
import com.example.retrofit.retrofit.ApiClient
import com.example.retrofit.retrofit.interfase.ApiInterfase
import com.example.retrofit.retrofit.models.UserModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    private lateinit var apiClient:ApiClient
    private lateinit var adapter: Adapter
    private lateinit var apiInterface: ApiInterfase
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding  = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        apiClient = ApiClient()
        apiInterface =apiClient.getRetrofit().create(ApiInterfase::class.java)
        adapter = Adapter(this)
        loadData()
    }

    private fun loadData() {
        apiInterface.getUser().enqueue(object :Callback<List<UserModel>>{

            override fun onResponse(
                call: Call<List<UserModel>>,
                response: Response<List<UserModel>>
            ) {
                if (response.isSuccessful)
                {
                  binding.apply {
                      rv.adapter = adapter
                      rv.layoutManager = LinearLayoutManager(this@MainActivity)
                      adapter.setData(response.body()!!)
                  }
                }
            }

            override fun onFailure(call: Call<List<UserModel>>, t: Throwable) {
            }

        })
    }
}