package com.halifax.weatherforecast

import com.google.gson.annotations.SerializedName


data class Main (

  @SerializedName("dt_txt"       ) var dt_txt      : String? = null,
  @SerializedName("temp"       ) var temp      : Int? = null,
  @SerializedName("feels_like" ) var feelsLike : Int? = null,
  @SerializedName("temp_min"   ) var tempMin   : Int? = null,
  @SerializedName("temp_max"   ) var tempMax   : Int? = null,
  @SerializedName("pressure"   ) var pressure  : Int?    = null,
  @SerializedName("sea_level"  ) var seaLevel  : Int?    = null,
  @SerializedName("grnd_level" ) var grndLevel : Int?    = null,
  @SerializedName("humidity"   ) var humidity  : Int?    = null,
  @SerializedName("temp_kf"    ) var tempKf    : Int? = null,
  @SerializedName("weather"    ) var weather    : ArrayList<Weather>? = null
)