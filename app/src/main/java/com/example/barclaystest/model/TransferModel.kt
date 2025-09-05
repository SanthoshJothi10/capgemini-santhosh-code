package com.example.barclaystest.model

data class TransferModel(
    val recepient: String,
    val account: String,
    val amount: String,
    val iBan: String,
    val swiftCode: String,
)