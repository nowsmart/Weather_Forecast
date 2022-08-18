package com.halifax.weatherforecast

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

    }

    fun onForcast(view: View) {
        var city: String = tvCity.text.toString()
        var intent = Intent(this, ForecastActivity::class.java)
        intent.putExtra("city", city)
        startActivity( intent)

        //https://api.openweathermap.org/data/2.5/forecast?q=Karachi&appid=65d00499677e59496ca2f318eb68c049
    }
}