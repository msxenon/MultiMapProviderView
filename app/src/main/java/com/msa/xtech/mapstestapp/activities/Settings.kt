package com.msa.xtech.mapstestapp.activities

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.RadioButton
import android.widget.RadioGroup
import com.msa.xtech.mapstestapp.R
import com.msa.xtech.mapstestapp.utils.revGeoCodingUtils.RevGeocodingProviderBase
import com.msa.xtech.mapstestapp.utils.revGeoCodingUtils.ReverseGeoCodingProviders
import com.msa.xtech.mapstestapp.utils.tileProvidersUtils.MapProvider
import com.msa.xtech.mapstestapp.utils.tileProvidersUtils.MapTileProviders

class Settings : AppCompatActivity() {
    private lateinit var tileProviders: RadioGroup
    private lateinit var reverseGeocoding: RadioGroup
    private lateinit var defaultTileProvider: MapProvider
    private lateinit var tileProvidersObject: MapTileProviders
    private lateinit var revGeoProviders: ReverseGeoCodingProviders
    private lateinit var defaultRevGeoProvider: RevGeocodingProviderBase

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.settings)

        this.tileProviders = findViewById(R.id.tile_providers)
        this.reverseGeocoding = findViewById(R.id.rev_geo_providers)
        tileProvidersObject = MapTileProviders(this)
        revGeoProviders = ReverseGeoCodingProviders(this)

        defaultTileProvider = tileProvidersObject.getDefaultProvider()
        defaultRevGeoProvider = revGeoProviders.getDefaultProvider()
        var c = 0
        tileProvidersObject.getList().forEach { tileProvider ->
            run {
                val radioButton = RadioButton(this)
                radioButton.text = tileProvider.getName()
                radioButton.isChecked = tileProvider.getName() == defaultTileProvider.getName()
                radioButton.id = c++
                tileProviders.addView(radioButton)
            }
        }
        //
        var x = 0
        revGeoProviders.getList().forEach { geoProvider ->
            run {
                val radioButton = RadioButton(this)
                radioButton.text = geoProvider.getName()
                radioButton.isChecked = geoProvider.getName() == defaultRevGeoProvider.getName()
                radioButton.id = x++
                reverseGeocoding.addView(radioButton)
            }
        }
    }

    override fun onBackPressed() {
        super.onBackPressed()
        //change detector for tiles provider
        val nowSelectedProvider = tileProvidersObject.getList()[tileProviders.checkedRadioButtonId]
        if (nowSelectedProvider.getName() != tileProvidersObject.getDefaultProvider().getName()) {
            tileProvidersObject.setDefaultProvider(nowSelectedProvider.getName())
        }
        //end

        //change detector for revGeo provider
        val nowGeoProvider = revGeoProviders.getList()[reverseGeocoding.checkedRadioButtonId]
        if (nowGeoProvider.getName() != revGeoProviders.getDefaultProvider().getName()) {
            revGeoProviders.setDefaultProvider(nowSelectedProvider.getName())
        }
        //end
    }
}