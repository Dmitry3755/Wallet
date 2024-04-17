package com.example.nfctagreader.data_classes

data class CardDetails (
    var owner: String?,
    var number: String?,
    var expirationMonth: String?,
    var expirationYear: String?
)