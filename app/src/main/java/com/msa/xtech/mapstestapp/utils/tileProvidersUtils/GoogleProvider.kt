package com.msa.xtech.mapstestapp.utils.tileProvidersUtils

import org.osmdroid.tileprovider.tilesource.ITileSource
import org.osmdroid.tileprovider.tilesource.OnlineTileSourceBase
import org.osmdroid.tileprovider.tilesource.XYTileSource
import org.osmdroid.util.MapTileIndex

class GoogleProvider : MapProvider() {
    override fun getName(): String {
        return "Google"
    }

    override fun getTileSource(): ITileSource {
        val GoogleHybrid: OnlineTileSourceBase = object : XYTileSource("Google-Hybrid",
                0, 19, 256, ".png", arrayOf("http://mt0.google.com", "http://mt1.google.com", "http://mt2.google.com", "http://mt3.google.com")) {
            override fun getTileURLString(pTileIndex: Long): String {
                return baseUrl + "/vt/lyrs=s&x=" + MapTileIndex.getX(pTileIndex) + "&y=" + MapTileIndex.getY(pTileIndex) + "&z=" + MapTileIndex.getZoom(pTileIndex)
            }
        }
        return GoogleHybrid
    }


}