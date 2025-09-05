package com.example.barclaystest.presentation

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.barclaystest.R
import com.example.barclaystest.model.TransferType

@Composable
fun PaymentOptionScreen(navController: NavHostController) {
    var selectedOption by remember { mutableStateOf<TransferType?>(null) }
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
    ) {
        Text(stringResource(R.string.payment_option), style = MaterialTheme.typography.titleMedium)

        Row(verticalAlignment = Alignment.CenterVertically) {
            RadioButton(
                selected = selectedOption == TransferType.DOMESTIC,
                onClick = {
                    selectedOption = TransferType.DOMESTIC
                    navController.navigate("payment_screen/${selectedOption}")
                }
            )
            Text(stringResource(R.string.domestic), modifier = Modifier.padding(start = 8.dp))
        }

        Row(verticalAlignment = Alignment.CenterVertically) {
            RadioButton(
                selected = selectedOption == TransferType.INTERNATIONAL,
                onClick = {
                    selectedOption = TransferType.INTERNATIONAL
                    navController.navigate("payment_screen/${selectedOption}")
                }
            )
            Text(stringResource(R.string.internation), modifier = Modifier.padding(start = 8.dp))
        }
    }
}