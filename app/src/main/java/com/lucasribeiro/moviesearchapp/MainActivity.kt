package com.lucasribeiro.moviesearchapp

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import com.android.volley.Request
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import com.squareup.picasso.Picasso


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
        val queue = Volley.newRequestQueue(this)
        val searchTerm = findViewById<EditText>(R.id.MovieNameForm).text
        val url = "https://www.omdbapi.com/?apikey=${BuildConfig.OMBD_KEY}&t=${searchTerm}"


        val stringRequest = JsonObjectRequest(Request.Method.GET, url, null,
            { res ->
                resultsTitleText.text = "${res["Title"]}"
                resultsYearText.text = "${res["Year"]}"
                Picasso.get().load("${res[" Poster "]}").into(resultsPosterImg)
            },
            { resultsTitleText.text = "Error, Request failed" })
        queue.add(stringRequest)
    }


}