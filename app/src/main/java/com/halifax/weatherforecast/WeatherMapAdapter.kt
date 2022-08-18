package com.halifax.weatherforecast

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView
import kotlinx.android.synthetic.main.activity_forecastactivity.*

class WeatherMapAdapter(private var context: Context, private val dataSource: ArrayList<Main>) :
    BaseAdapter() {

    private class ViewHolder(row: View?) {
        var dt_txt: TextView? = null
        var tvTemp: TextView? = null
        var tvWeatherDescription: TextView? = null

        init {
            this.dt_txt = row?.findViewById<TextView>(R.id.tvdt_txt)
            this.tvTemp = row?.findViewById<TextView>(R.id.tvTemp)
            this.tvWeatherDescription = row?.findViewById<TextView>(R.id.tvWeatherDescription)
        }
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val view: View
        val viewHolder: ViewHolder
        if (convertView == null) {

            val inflater =
                context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
            view = inflater.inflate(R.layout.listviewweather, null)

            viewHolder = ViewHolder(view)
            view.tag = viewHolder
        } else {
            view = convertView
            viewHolder = view.tag as ViewHolder
        }

        viewHolder.dt_txt?.text = dataSource[position].toString()
        viewHolder.tvTemp?.text = dataSource[position].toString()
        viewHolder.tvWeatherDescription?.text = dataSource[position].toString()
        return view
    }

    override fun getItem(position: Int): Any {
        return dataSource[position]
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getCount(): Int {
        return dataSource.size
    }

    private val inflater: LayoutInflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
}