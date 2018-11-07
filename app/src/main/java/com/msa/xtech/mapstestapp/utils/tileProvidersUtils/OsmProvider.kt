package com.msa.xtech.mapstestapp.utils.tileProvidersUtils

import org.osmdroid.tileprovider.tilesource.ITileSource
import org.osmdroid.tileprovider.tilesource.TileSourceFactory


class OsmProvider : MapProvider() {
    override fun getName(): String {
        return "Open Street map"
    }

    override fun getTileSource(): ITileSource {
        return TileSourceFactory.MAPNIK
    }


}