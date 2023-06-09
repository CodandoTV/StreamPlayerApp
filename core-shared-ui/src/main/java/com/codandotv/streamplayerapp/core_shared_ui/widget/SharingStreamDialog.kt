package com.codandotv.streamplayerapp.core_shared_ui.widget

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.sp

@Composable
fun SharingStreamDialog(
    onConfirmButton: () -> Unit = {},
    onDismissButton: () -> Unit = {},
    setShowDialog: (Boolean) -> Unit = {}
) {
    println(">>>> entra no dialog")
    AlertDialog(
        onDismissRequest = { setShowDialog(false) },
        confirmButton = {
            TextButton(onClick = {
                onConfirmButton()
                setShowDialog(false)
            }) {
                Text("Apagar")
            }
        },
        dismissButton = {
            TextButton(onClick = {
                onDismissButton()
                setShowDialog(false)
            }) {
                Text("Cancelar")
            }
        },
        icon = { Icon(Icons.Filled.Delete, null) },
        title = { Text(text = "Apagar dados de navegação?", fontSize = 20.sp) },
        text = { Text(text = "Isso vai fazer com que seu histórico de navegação seja completamente apagado.") },
        modifier = Modifier.fillMaxWidth()
    )
}
