package coder.test.retrostart

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import coder.test.retrostart.libby.H
import coder.test.retrostart.models.Category
import coder.test.retrostart.models.Token
import coder.test.retrostart.services.ServiceBuilder
import coder.test.retrostart.services.WebService
import kotlinx.android.synthetic.main.activity_main.*
import org.jetbrains.anko.toast
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btnLogin.setOnClickListener {

            val email = etEmail.text.toString()
            val pass = etPassword.text.toString()
            val apikey = etApikey.text.toString()

            if (email.isNotEmpty() && pass.isNotEmpty() && apikey.isNotEmpty()) {
                toast("Email is $email password is $pass and ApiKey is $apikey")
                loginUserNow(email, pass, apikey);
            }

        }
    }

    private fun loginUserNow(email: String, pass: String, apikey: String) {

        val service: WebService = ServiceBuilder.createService(WebService::class.java)
        val postRequest: Call<Token> = service.loginUser(email, pass, apikey)

        postRequest.enqueue(object : Callback<Token> {
            override fun onFailure(call: Call<Token>, t: Throwable) {
                Log.d("msg", t.message!!)
            }

            override fun onResponse(call: Call<Token>, response: Response<Token>) {
                val token = response.body()!!
                H.token = token

                startActivity(Intent(this@MainActivity, CategoryActivity::class.java))
            }
        })

    }


}

