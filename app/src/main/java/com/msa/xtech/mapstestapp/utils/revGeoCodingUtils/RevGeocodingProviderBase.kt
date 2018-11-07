package com.msa.xtech.mapstestapp.utils.revGeoCodingUtils

import org.json.JSONObject


abstract class RevGeocodingProviderBase {
    abstract fun getName(): String
    abstract fun generateLink(lat: String, lng: String): String
    abstract fun getResult(json: JSONObject): String

}