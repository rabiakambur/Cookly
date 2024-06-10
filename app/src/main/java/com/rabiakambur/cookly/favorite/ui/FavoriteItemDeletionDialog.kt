package com.rabiakambur.cookly.favorite.ui

import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.sp
import com.rabiakambur.cookly.R
import com.rabiakambur.cookly.main.theme.PrimaryColor

@Composable
fun FavoriteItemDeletionDialog(
    shouldShowItemDeletionDialog: (Boolean) -> Unit,
    onDeleteAction: () -> Unit
    ) {

    AlertDialog(
        onDismissRequest = {
            shouldShowItemDeletionDialog(false)
        },
        containerColor = Color.White,
        title = {
            Text(
                text = stringResource(R.string.favorite_item_deletion_dialog_text),
                fontSize = 20.sp,
                color = Color.Black
            )
        },
        confirmButton = {
            Button(
                onClick = {
                    shouldShowItemDeletionDialog(false)
                    onDeleteAction()
                },
                colors = ButtonDefaults.buttonColors(
                    containerColor = PrimaryColor,
                    contentColor = Color.White
                )
            ) {
                Text(
                    text = stringResource(R.string.favorite_item_deletion_dialog_positive_button)
                )
            }
        },
        dismissButton = {
            Button(
                onClick = {
                    shouldShowItemDeletionDialog(false)
                },
                colors = ButtonDefaults.buttonColors(
                    containerColor = PrimaryColor,
                    contentColor = Color.White
                )
            ) {
                Text(
                    text = stringResource(R.string.favorite_item_deletion_dialog_negative_button)
                )
            }
        }
    )
}