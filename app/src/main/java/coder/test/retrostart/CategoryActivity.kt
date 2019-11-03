package coder.test.retrostart

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.GridLayoutManager
import coder.test.retrostart.libby.H
import coder.test.retrostart.models.Category
import coder.test.retrostart.services.ServiceBuilder
import coder.test.retrostart.services.WebService
import kotlinx.android.synthetic.main.activity_category.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class CategoryActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_category)

        supportActionBar?.hide()
        catRecycler.layoutManager = GridLayoutManager(this,2)

        loadAllCategory()

    }
    private fun loadAllCategory() {
        val service: WebService = ServiceBuilder.createService(WebService::class.java)
        val postRequest: Call<List<Category>> = service.getAllCategory("Bearer ${H.token?.token}")

        postRequest.enqueue(object : Callback<List<Category>> {
            override fun onFailure(call: Call<List<Category>>, t: Throwable) {
                Log.d("msg", t.message!!)
            }

            override fun onResponse(
                call: Call<List<Category>>,
                response: Response<List<Category>>
            ) {
                val cats = response.body()!!

                val adapter = CategoryAdapter(this@CategoryActivity,cats)

                catRecycler.adapter = adapter
            }

        })

    }
}
