package com.lucasribeiro.moviesearchapp

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import com.android.volley.Request
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import com.squareup.picasso.Callback
import com.squareup.picasso.Picasso
import retrofit2.Call
import retrofit2.Response
import kotlin.reflect.typeOf


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


    }

    @SuppressLint("SetTextI18n")
    fun requestMovie(view: View) {
        val resultsTitleText = findViewById<TextView>(R.id.resultsTitleText)
        val resultsYearText = findViewById<TextView>(R.id.resultsYearText)
        val resultsPosterImg = findViewById<ImageView>(R.id.resultsPosterImg)
        val searchTerm = findViewById<EditText>(R.id.MovieNameForm).text
        val url = "https://www.omdbapi.com"

        val retrofitClient = NetworkUtils.getRetrofitInstance(url)
        val endpoint = retrofitClient.create(Endpoint::class.java)
        val callback = endpoint.getSearch(BuildConfig.OMBD_KEY, searchTerm.toString())

        callback.enqueue(object : retrofit2.Callback<MovieResponses> {
            override fun onFailure(call: Call<MovieResponses>, t: Throwable) {
                Toast.makeText(baseContext, t.message, Toast.LENGTH_SHORT).show()
            }

            override fun onResponse(call: Call<MovieResponses>, res: Response<MovieResponses>) {
                println(res.body())
            }
        })

            //    if(res["Response"] == "False") {
          //          resultsTitleText.text = res["Error"].toString()
        //        } else {
      //              resultsTitleText.text = res["Title"].toString()
    //                resultsYearText.text = res["Year"].toString()
  //                  Picasso.get().load(res["Poster"].toString()).into(resultsPosterImg)
//                }
    }


}