package com.halifax.weatherforecast

import android.annotation.SuppressLint
import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView
import java.lang.Exception
import java.text.SimpleDateFormat
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.util.*
import kotlin.collections.ArrayList

class MyAdapter(
    private var activity: ForecastActivity,
    private var simpleListItem1: Int,
    private var mainList: ArrayList<Main>
) : BaseAdapter() {
    override fun getCount(): Int {
        return mainList.size
    }

    override fun getItem(i: Int): Any {
        return mainList[i]
    }

    override fun getItemId(i: Int): Long {
        return i.toLong()
    }

    private class ViewHolder(row: View?) {
        var tvdt_txt: TextView? = null
        var tvTemp: TextView? = null
        var tvWeatherDescription: TextView? = null

        init {
            this.tvdt_txt = row?.findViewById<TextView>(R.id.tvdt_txt)
            this.tvTemp = row?.findViewById<TextView>(R.id.tvTemp)
            this.tvWeatherDescription = row?.findViewById<TextView>(R.id.tvWeatherDescription)
        }
    }

    @SuppressLint("SetTextI18n")
    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val view: View
        val viewHolder: ViewHolder
        if (convertView == null) {
            val inflater =
                activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
            view = inflater.inflate(R.layout.listviewweather, null)
            viewHolder = ViewHolder(view)
            view.tag = viewHolder
        } else {
            view = convertView
            viewHolder = view.tag as ViewHolder
        }
        var main = mainList[position]
        var weatherDescription = main.weather?.get(0)?.description
        try {
            var dt = main.dt_txt.toString()
            dt = dt.substring(11,16)
            viewHolder.tvdt_txt?.text = dt
            viewHolder.tvTemp?.text = "Temp: " + main.temp.toString() + "º"
            viewHolder.tvWeatherDescription?.text = weatherDescription.toString()
        } catch (ex: Exception) {
            Log.i("Hi", ex.message.toString())
        }
        return view
    }

}
