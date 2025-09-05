package com.example.barclaystest.presentation

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.barclaystest.R

@Composable
fun PaymentStatus(navController: NavHostController) {
    var showDialog by remember { mutableStateOf(true) }
    Column(modifier = Modifier.padding(16.dp)) {
        if (showDialog) {
            AlertDialog(
                onDismissRequest = { showDialog = false },
                title = {
                    Text(
                        text = stringResource(R.string.confirm),
                        style = MaterialTheme.typography.bodyLarge
                    )
                },
                text = {
                    Text(
                        stringResource(R.string.successful),
                        style = MaterialTheme.typography.bodyLarge
                    )
                },
                confirmButton = {
                    TextButton(onClick = {
                        showDialog = false
                        // Handle confirmation
                        navController.navigateUp()

                    }) {
                        Text(stringResource(R.string.back))
                    }
                }
            )
        }
    }
}
