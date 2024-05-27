package com.example.appiness

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class MainActivity : ComponentActivity(), OnItemClickListener {


    private lateinit var productDao: ProductDao
    var dataList = ArrayList<Product>()


    val retrofit = Retrofit.Builder()
        .baseUrl("https://apps.clickastro.com/test/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    val apiService = retrofit.create(ApiService::class.java)

    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: ProductAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_mainactivity)

        productDao = MyAppDatabase.getDatabase(this).productDao()


        recyclerView = findViewById(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this)


        val call = apiService.getProducts()

        call.enqueue(object : Callback<ApiResponse> {

            override fun onResponse(call: Call<ApiResponse>, response: Response<ApiResponse>) {
                if (response.isSuccessful) {
                    val apiResponse = response.body()
                    println(apiResponse)
                    val a = response.body()?.products?.toProductList() ?: emptyList()

                    dataList.clear();
                    if (a != null) {
                        dataList.addAll(a)
                    }

                    dataList.forEach { c ->
                        val data_teml = Product(
                            name = c.name,
                            description = c.description,
                            availableLanguages = c.availableLanguages,
                            sampleReports = c.sampleReports,
                            pages = c.pages,
                            pagesintext = c.pagesintext,
                            report_type = c.report_type,
                            authentic = c.authentic,
                            remedies = c.remedies,
                            vedic = c.vedic,
                            price = c.price,
                            discount = c.discount,
                            appDiscount = c.appDiscount,
                            couponDiscount = c.couponDiscount,
                            imagePath = c.imagePath,
                            soldcount = c.soldcount,
                            avg = c.avg
                        )

                        GlobalScope.launch {
                            productDao.insert(data_teml)
                        }


                        GlobalScope.launch {
                            val producta = productDao.getAll()
                            for (p in producta) {
                                dataList.add(p);
                                Log.e("producta*** :", p.name);
                            }
                        }


                        recyclerView.layoutManager =
                            LinearLayoutManager(this@MainActivity)
                        val adapter =
                            ProductAdapter(
                                dataList,
                                this@MainActivity,
                                listener = this@MainActivity
                            )
                        recyclerView.adapter = adapter


                    }
                } else {
                    println("Error: ${response.errorBody()}")
                }
            }

            override fun onFailure(call: Call<ApiResponse>, t: Throwable) {
                println("Failure: ${t.message}")
            }
        })


    }

//    private fun ApiResponse.tolist: List<ProductsResponse> {
//        return listOf(products)
//    }

    private fun ProductsResponse.toProductList(): List<Product> {
        return listOf(YG, LI, WL, CP, DT, GM, MR, MP, ED, PPOL, PCOL, BP)
    }

    override fun onItemClick(name: String, position: Int) {


        Toast.makeText(
            applicationContext,
            "id or name or email cannot be blank",
            Toast.LENGTH_LONG
        ).show()
    }


}





