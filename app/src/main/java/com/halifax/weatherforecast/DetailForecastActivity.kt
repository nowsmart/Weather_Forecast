package com.halifax.weatherforecast

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_forecast_detail.*

class DetailForecastActivity : AppCompatActivity() {
    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_forecast_detail)
        val actionbar = supportActionBar
        actionbar?.setDisplayHomeAsUpEnabled(true)
        var extras = intent.extras
        this.title  = extras?.get("city").toString()
        tvdt_txt.text = extras?.get("dt_txt").toString()
        tvTemp.text = extras?.get("temp").toString() + resources.getString(R.string.deg)
        tvFeelLike.text =resources.getString(R.string.feels_like)+ extras?.get("feelsLike").toString()+ resources.getString(R.string.deg)
        tvClouds.text = extras?.get("main").toString()
        tvBrokenClouds.text = extras?.get("description").toString()
    }
    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }
}