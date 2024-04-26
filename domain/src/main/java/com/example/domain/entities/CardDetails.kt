package com.example.domain.entities

import androidx.camera.core.ImageProxy

data class CardDetails (
    var uid : String?,
    var number: String?,
    var imageProxyList: List<ImageProxy>?
)