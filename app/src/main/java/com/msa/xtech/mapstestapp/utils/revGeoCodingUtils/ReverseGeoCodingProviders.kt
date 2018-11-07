package com.msa.xtech.mapstestapp.utils.revGeoCodingUtils

import android.content.Context
import android.content.Context.MODE_PRIVATE
import com.msa.xtech.mapstestapp.utils.SharedPrefsKeys


class ReverseGeoCodingProviders(c: Context) {
    val prefsName = "com.msa.xtech.mapstestapp"
    val cc = c

    companion object Factory {
        var Arr = arrayOf<RevGeocodingProviderBase>(GoogleGeoCoding(), OSMGeoCoding())
    }

    fun getDefaultProvider(): RevGeocodingProviderBase {

        val preferences = cc.getSharedPreferences(prefsName, Context.MODE_PRIVATE)
        val nameProvider = preferences.getString(SharedPrefsKeys.geoProvider.name, GoogleGeoCoding().getName())
        var m: RevGeocodingProviderBase? = null
        Arr.forEach { map ->

            if (map.getName().equals(nameProvider, true))
                m = map


        }
        return m!!
    }

    fun setDefaultProvider(name: String) {
        val editor = cc.getSharedPreferences(prefsName, MODE_PRIVATE).edit()
        editor.putString(SharedPrefsKeys.geoProvider.name, name)
        editor.apply()
    }


    fun getList(): Array<RevGeocodingProviderBase> {
        return Arr
    }
}