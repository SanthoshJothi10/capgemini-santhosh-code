package com.example.barclaystest

import com.example.barclaystest.model.TransferModel

fun TransferModel.toTransferDetails(): TransferModel {
    return TransferModel(
        recepient = this.recepient,
        account = this.account,
        amount = this.amount,
        iBan = this.iBan,
        swiftCode = this.swiftCode
    )
}