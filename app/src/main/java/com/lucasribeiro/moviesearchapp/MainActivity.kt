package com.lucasribeiro.moviesearchapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.TextView
import com.android.volley.Request
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


    }

    fun requestMovie(view: View){
        val resultsView = findViewById<TextView>(R.id.ResultsText)
        val queue = Volley.newRequestQueue(this)
        val searchTerm = findViewById<EditText>(R.id.MovieNameForm).text
        val url = "https://www.omdbapi.com/?apikey=${BuildConfig.OMBD_KEY}&t=${searchTerm}"


        val stringRequest = JsonObjectRequest(Request.Method.GET, url, null,
            { res ->
                resultsView.text = "The response was ${res["Title"]}" },
            { resultsView.text = "Error, Request failed" })
        queue.add(stringRequest)
    }


}