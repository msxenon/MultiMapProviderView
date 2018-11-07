package com.msa.xtech.mapstestapp.utils.revGeoCodingUtils

import org.json.JSONObject

class OSMGeoCoding : RevGeocodingProviderBase() {


    override fun generateLink(lat: String, lng: String): String {
        return "https://nominatim.openstreetmap.org/reverse?format=json&lat=" + lat + "&lon=" + lng + "&zoom=18&addressdetails=1"
    }

    override fun getName(): String {
        return "Open Street Map"
    }

    override fun getResult(json: JSONObject): String {
        return json.getString("display_name")
    }

}