package com.rabiakambur.cookly.home.ui.component

import android.annotation.SuppressLint
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.rabiakambur.cookly.R
import com.rabiakambur.cookly.main.theme.PrimaryColor

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun HeaderComponent() {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .height(220.dp),
        shape = RoundedCornerShape(
            topStart = 0.dp,
            topEnd = 0.dp,
            bottomStart = 14.dp,
            bottomEnd = 14.dp
        ),
        border = BorderStroke(2.dp, Color.Transparent),
        colors = CardDefaults.cardColors(
            containerColor = PrimaryColor
        )
    ) {
        Column(
            modifier = Modifier
                .padding(16.dp),
        ) {
            Text(
                text = stringResource(R.string.header_component),
                fontSize = 26.sp,
                color = Color.White,
                fontWeight = FontWeight.Bold,
                style = MaterialTheme.typography.headlineLarge,
                modifier = Modifier
                    .padding(top = 14.dp, bottom = 24.dp)
            )
            SearchComponent()
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
private fun HeaderComponentPreview() {
    HeaderComponent()
}