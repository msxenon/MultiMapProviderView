package com.msa.xtech.mapstestapp.utils.revGeoCodingUtils

import org.json.JSONObject

class GoogleGeoCoding : RevGeocodingProviderBase() {


    override fun generateLink(lat: String, lng: String): String {
        return "https://maps.googleapis.com/maps/api/geocode/json?latlng=" + lat + "," + lng + "&key=AIzaSyD_VbEbxy2UC7AEE1kdqeIcEK47VX1PkCc"
    }

    override fun getName(): String {
        return "Google"
    }

    override fun getResult(json: JSONObject): String {
        return json.getJSONObject("result").getString("formatted_address")
    }


}