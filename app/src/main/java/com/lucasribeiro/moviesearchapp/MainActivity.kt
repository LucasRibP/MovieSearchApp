package com.lucasribeiro.moviesearchapp

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.Call
import retrofit2.Response
import retrofit2.Retrofit


class MainActivity : AppCompatActivity() {
    private lateinit var resultsTitleText : TextView
    private lateinit var resultsYearText : TextView
    private lateinit var resultsPosterImg : ImageView
    private lateinit var movieNameForm : EditText
    val retrofitClient = Retrofit.Builder()
        .baseUrl("https://www.omdbapi.com")
        .build()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        resultsTitleText = findViewById<TextView>(R.id.resultsTitleText)
        resultsYearText = findViewById<TextView>(R.id.resultsYearText)
        resultsPosterImg = findViewById<ImageView>(R.id.resultsPosterImg)
        movieNameForm = findViewById<EditText>(R.id.MovieNameForm)
    }

    @SuppressLint("SetTextI18n")
    fun requestMovie(view: View) {
        val searchTerm = movieNameForm.text
        val service = retrofitClient.create(omdbAPI::class.java)

        CoroutineScope(Dispatchers.IO).launch {
            val response = service.getSearch(BuildConfig.OMBD_KEY, searchTerm.toString())

            withContext(Dispatchers.Main) {
                if (response.isSuccessful) {
                    val gson = Gson()
                    val movieResponses = gson.fromJson(response.body()?.string(), MovieResponses::class.java)
                    println(movieResponses)
                } else {
                    Log.e("RETROFIT_ERROR", response.code().toString())
                }
            }
        }
            //    if(res["Response"] == "False") {
          //          resultsTitleText.text = res["Error"].toString()
        //        } else {
      //              resultsTitleText.text = res["Title"].toString()
    //                resultsYearText.text = res["Year"].toString()
  //                  Picasso.get().load(res["Poster"].toString()).into(resultsPosterImg)
//                }
    }

}