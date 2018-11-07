package com.msa.xtech.mapstestapp.utils.tileProvidersUtils

import android.content.Context

import android.content.Context.MODE_PRIVATE
import com.msa.xtech.mapstestapp.utils.SharedPrefsKeys


class MapTileProviders(c: Context) {
    val prefsName = "com.msa.xtech.mapstestapp"
    val cc = c

    companion object Factory {
        var Arr3 = arrayOf<MapProvider>(GoogleProvider(), OsmProvider())

    }

    fun getDefaultProvider(): MapProvider {
        val preferences = cc.getSharedPreferences(prefsName, Context.MODE_PRIVATE)
        val nameProvider = preferences.getString(SharedPrefsKeys.mapProvider.name, GoogleProvider().getName())
        var m: MapProvider? = null
        Arr3.forEach { map ->
            if (map.getName().equals(nameProvider, true))
                m = map
        }
        return m!!
    }

    fun setDefaultProvider(name: String) {
        val editor = cc.getSharedPreferences(prefsName, MODE_PRIVATE).edit()
        editor.putString(SharedPrefsKeys.mapProvider.name, name)
        editor.apply()
    }


    fun getList(): Array<MapProvider> {
        return Arr3
    }
}