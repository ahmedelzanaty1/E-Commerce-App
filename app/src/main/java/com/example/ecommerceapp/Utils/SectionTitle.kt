package com.example.ecommerceapp.Utils

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.ecommerceapp.R

@Composable
fun SectionTitle(title : String , actionTitle : String) {
    Row(
        modifier = Modifier.fillMaxWidth()
            .padding(start = 16.dp, end = 16.dp, top = 16.dp)
        , horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(text = title
        , color = Color.Black
        , fontSize = 16.sp
        , fontWeight = FontWeight.Bold
        )
        Text(
            text = actionTitle
            , color = colorResource(R.color.purple)

        )
    }
}