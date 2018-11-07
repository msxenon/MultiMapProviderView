package com.msa.xtech.mapstestapp.fragments

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.msa.xtech.mapstestapp.R
import com.msa.xtech.mapstestapp.utils.revGeoCodingUtils.ReverseGeoCodingProviders
import com.msa.xtech.mapstestapp.utils.tileProvidersUtils.MapTileProviders
import okhttp3.*
import org.json.JSONObject
import org.osmdroid.events.MapEventsReceiver
import org.osmdroid.util.GeoPoint
import org.osmdroid.views.MapView
import org.osmdroid.views.overlay.MapEventsOverlay
import java.io.IOException


class OSM_Fragment : Fragment() {
    private lateinit var map: MapView
    private lateinit var client: OkHttpClient

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.osm_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        map = activity!!.findViewById(R.id.maposm)
        val mapController = map.controller
        mapController.setZoom(6.0)
        map.setTileSource(MapTileProviders(activity!!.applicationContext).getDefaultProvider().getTileSource())
        map.overlays.add(MapEventsOverlay(mReceive))
        map.setBuiltInZoomControls(true)
        map.setMultiTouchControls(true)

        client = OkHttpClient()


    }


    private var mReceive: MapEventsReceiver = object : MapEventsReceiver {
        override fun singleTapConfirmedHelper(p: GeoPoint): Boolean {
            Toast.makeText(context, "LatLng =>" + p.latitude.toString() + ", " + p.longitude + " Loading reverse Geocoding...", Toast.LENGTH_LONG).show()
            activity!!.runOnUiThread {
                run(ReverseGeoCodingProviders(context!!).getDefaultProvider().generateLink(p.latitude.toString(), p.longitude.toString()))


            }


            return false
        }

        override fun longPressHelper(p: GeoPoint): Boolean {
            return false
        }
    }

    fun run(url: String) {
        val request = Request.Builder()
                .url(url)
                .build()

        client.newCall(request).enqueue(object : Callback {
            override fun onFailure(call: Call, e: IOException) {
                activity!!.runOnUiThread { Toast.makeText(context, e.message, Toast.LENGTH_LONG).show() }
            }

            override fun onResponse(call: Call, response: Response) = activity!!.runOnUiThread {
                try {
                    val json = JSONObject(response.body()?.string())

                    Toast.makeText(context, "Address:- " + ReverseGeoCodingProviders(context!!).getDefaultProvider().getResult(json), Toast.LENGTH_LONG).show()

                } catch (e: Exception) {
                    e.printStackTrace()
                }
            }
        })
    }

    override fun onResume() {
        super.onResume()
        map.setTileSource(MapTileProviders(activity!!.applicationContext).getDefaultProvider().getTileSource())

        //this will refresh the osmdroid configuration on resuming.
        //if you make changes to the configuration, use
        //SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);
        //Configuration.getInstance().load(this, PreferenceManager.getDefaultSharedPreferences(this));
        map.onResume() //needed for compass, my location overlays, v6.0.0 and up
    }

    override fun onPause() {
        super.onPause()
        //this will refresh the osmdroid configuration on resuming.
        //if you make changes to the configuration, use
        //SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);
        //Configuration.getInstance().save(this, prefs);
        map.onPause()  //needed for compass, my location overlays, v6.0.0 and up
    }
}