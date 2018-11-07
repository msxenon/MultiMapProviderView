package com.msa.xtech.mapstestapp.utils.tileProvidersUtils

import org.osmdroid.tileprovider.tilesource.ITileSource

abstract class MapProvider {
    abstract fun getName(): String
    abstract fun getTileSource(): ITileSource

}