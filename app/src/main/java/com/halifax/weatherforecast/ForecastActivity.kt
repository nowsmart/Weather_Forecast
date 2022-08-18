package com.halifax.weatherforecast


import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.android.volley.Request
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import kotlinx.android.synthetic.main.activity_forecastactivity.*
import org.json.JSONArray
import org.json.JSONObject
import kotlin.math.roundToInt


class ForecastActivity : AppCompatActivity() {
    private lateinit var city: String
    private lateinit var mainList: ArrayList<Main>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_forecastactivity)

        city = intent.getStringExtra("city").toString()
        title = city

        listview.setOnItemClickListener { parent, _, position, _ ->
            val intent = Intent(this, DetailForecastActivity::class.java)
            val weather = mainList[position]
            intent.putExtra("city", city)
            intent.putExtra("dt_txt", weather.dt_txt)
            intent.putExtra("temp", weather.temp)
            intent.putExtra("feelsLike", weather.feelsLike)
            intent.putExtra("main", weather.weather?.get(0)?.main)
            intent.putExtra("description", weather.weather?.get(0)?.description)
            startActivity( intent)
        }
        populateListView()

        val actionbar = supportActionBar
        actionbar?.setDisplayHomeAsUpEnabled(true)
    }

    private fun populateListView() {
        mainList = arrayListOf()
        val apiKey="b432d0fa85926802ca312c7c5d5f12d8"//Freekey
        //apikey ="65d00499677e59496ca2f318eb68c049"//Freekey
        val apiUrl =
            "https://api.openweathermap.org/data/2.5/forecast?q=${city}&appid=${apiKey}"
        val queue = Volley.newRequestQueue(this)

        val stringReq = StringRequest(Request.Method.GET, apiUrl,
            { response ->
                val strResp = response.toString()
                val obj = JSONObject(strResp)
                val listObj = obj.getJSONArray("list")

                for (i in 0 until listObj.length()) {
                    val list: JSONObject = listObj.getJSONObject(i)
                    val dt_txt = list.getString("dt_txt")
                    val main: JSONObject = list.getJSONObject("main")
                    val temp: Int = main.getDouble("temp").toFahrenheit()
                    val feels_like: Int = main.getDouble("feels_like").toFahrenheit()
                    val temp_min: Int = main.getDouble("temp_min").toFahrenheit()
                    val temp_max: Int = main.getDouble("temp_max").toFahrenheit()
                    val pressure: Int = main.getInt("pressure")
                    val sea_level: Int = main.getInt("sea_level")
                    val grnd_level: Int = main.getInt("grnd_level")
                    val humidity: Int = main.getInt("humidity")
                    val temp_kf: Int = main.getDouble("temp_kf").toFahrenheit()

                    val weatherA: JSONArray = list.get("weather") as JSONArray
                    val weatherList = arrayListOf<Weather>()
                    for (i in 0 until weatherA.length()) {
                        val weather = weatherA.getJSONObject(i)
                        val wId: Int = weather.getInt("id")
                        val wmain: String = weather.getString("main")
                        val wdescription: String = weather.getString("description")
                        val wicon: String = weather.getString("icon")
                        weatherList.add(
                            Weather(
                                wId, wmain, wdescription, wicon
                            )
                        )
                    }
                    mainList.add(
                        Main(
                            dt_txt,
                            temp,
                            feels_like,
                            temp_min,
                            temp_max,
                            pressure,
                            sea_level,
                            grnd_level,
                            humidity,
                            temp_kf,
                            weatherList
                        )
                    )
                }
                listview.adapter = MyAdapter(
                    this,
                    android.R.layout.simple_list_item_1,
                    mainList
                )
            },
            { error -> Log.i("Error", error.toString()) }
        )
        queue.add(stringReq)
    }

    override fun onResume() {
        super.onResume()
        Log.d("lifecycle", "onResume invoked")
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }
}

private fun Double.toFahrenheit(): Int {
    return ((this - 273.15) * 1.8 + 32.0).roundToInt()
}

