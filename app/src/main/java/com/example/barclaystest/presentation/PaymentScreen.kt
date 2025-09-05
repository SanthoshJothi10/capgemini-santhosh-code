package com.example.barclaystest.presentation

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.barclaystest.R
import com.example.barclaystest.model.TransferType
import com.example.barclaystest.navigation.Route.PAYMENT_STATUS


@Composable
fun PaymentScreen(navController: NavController, selectedOptionValue: String?) {

    var recipientName by remember { mutableStateOf("") }
    var recipientError by remember { mutableStateOf(false) }
    var accountNumber by remember { mutableStateOf("") }
    var accountError by remember { mutableStateOf(false) }
    var amount by remember { mutableStateOf("") }
    var amountError by remember { mutableStateOf(false) }
    var iban by remember { mutableStateOf("") }
    var iBanError by remember { mutableStateOf(false) }
    var swiftCode by remember { mutableStateOf("") }
    var swiftCodeError by remember { mutableStateOf(false) }
    val selectedOption by remember { mutableStateOf(selectedOptionValue) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
    ) {
        val selectedValue = if (selectedOptionValue == TransferType.DOMESTIC.toString()) {
            stringResource(R.string.domestic)
        } else {
            stringResource(R.string.internation)
        }
        Text(
            text = selectedValue,
            style = MaterialTheme.typography.titleMedium
        )

        Spacer(modifier = Modifier.padding(8.dp))

        // recepient name OutlinedTextField
        OutlinedTextField(
            value = recipientName,
            onValueChange = {
                recipientName = it
                recipientError = it.isBlank()
            },
            label = { Text(text = stringResource(R.string.recepient_name)) },
            placeholder = { Text(text = stringResource(R.string.recepient_name)) },
            singleLine = true,
            isError = recipientError,
            modifier = Modifier
                .fillMaxWidth().testTag("RecepientField")
        )
        Spacer(modifier = Modifier.padding(2.dp))
        if (recipientError) Text(
            stringResource(R.string.recepient_name_error),
            color = Color.Red,
            style = MaterialTheme.typography.bodySmall
        )

        // account number OutlinedTextField
        OutlinedTextField(
            value = accountNumber,
            onValueChange = {
                accountNumber = it
                accountError = it.isBlank()
            },
            label = { Text(text = stringResource(R.string.account_number)) },
            placeholder = { Text(text = stringResource(R.string.account_number)) },
            singleLine = true,
            isError = accountError,
            modifier = Modifier.fillMaxWidth().testTag("AccountField"),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password)
        )
        if (accountError) {
            Text(
                text = stringResource(R.string.account_number_error),
                color = Color.Red,
                style = MaterialTheme.typography.bodySmall
            )
        }
        // amount OutlinedTextField
        OutlinedTextField(
            value = amount,
            onValueChange = {
                amount = it
                amountError = it.isBlank()
            },
            label = { Text(text = stringResource(R.string.amount)) },
            placeholder = { Text(text = stringResource(R.string.amount)) },
            singleLine = true,
            isError = amountError,
            modifier = Modifier.fillMaxWidth().testTag("AmountField"),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Decimal)

        )
        if (amountError) {
            Text(
                text = stringResource(R.string.amount_error),
                color = Color.Red,
                style = MaterialTheme.typography.bodySmall
            )
        }
        Spacer(modifier = Modifier.padding(2.dp))

        // this validated is check the payment option whether domestic or international
        // it show the iban and swift code text field if international option
        if (selectedOption == TransferType.INTERNATIONAL.toString()) {
            OutlinedTextField(
                value = iban,
                onValueChange = {
                    iban = it
                    iBanError = it.length != 34
                },
                label = { Text(stringResource(R.string.iban)) },
                placeholder = { Text(text = stringResource(R.string.iban)) },
                modifier = Modifier.fillMaxWidth().testTag("ibanField"),
                singleLine = true,
                isError = iBanError,
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Ascii)
            )
            if (iBanError) {
                Text(
                    text = stringResource(R.string.iban_error),
                    color = Color.Red,
                    style = MaterialTheme.typography.bodySmall
                )
            }
            Spacer(modifier = Modifier.padding(2.dp))
            // swiftCode OutlinedTextField
            OutlinedTextField(
                value = swiftCode,
                onValueChange = {
                    swiftCode = it
                    swiftCodeError = it.isBlank()
                },
                placeholder = { Text(text = stringResource(R.string.swift_code)) },
                label = { Text(stringResource(R.string.swift_code)) },
                modifier = Modifier.fillMaxWidth().testTag("swiftCodeField"),
                singleLine = true,
                isError = swiftCodeError
            )
            Spacer(modifier = Modifier.padding(2.dp))
            if (swiftCodeError) {
                Text(
                    text = stringResource(R.string.swift_code_error),
                    color = Color.Red,
                    style = MaterialTheme.typography.bodySmall
                )
            }
        }
        // this row is show of cancel and send button
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
                .align(alignment = Alignment.End)
        ) {
            Button(
                onClick = {
                    navController.navigateUp()
                }
            ) { Text(stringResource(R.string.cancel)) }
            Spacer(modifier = Modifier.padding(16.dp))
            Button(
                onClick = {
                    recipientError = recipientName.isBlank()
                    accountError = accountNumber.isBlank()
                    amountError = amount.isBlank()
                    if (selectedOption == TransferType.INTERNATIONAL.toString()) {
                        iBanError = iban.length != 34
                        swiftCodeError = swiftCode.isBlank()
                        if (!recipientError && !accountError && !amountError && !iBanError && !swiftCodeError) {
                            navController.navigate(PAYMENT_STATUS)
                        }
                    } else {
                        if (!recipientError && !accountError && !amountError) {
                            navController.navigate(PAYMENT_STATUS)
                        }
                    }
                }
            ) { Text(text = stringResource(R.string.transfer)) }
        }
    }
}

