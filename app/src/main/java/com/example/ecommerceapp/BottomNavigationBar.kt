package com.example.ecommerceapp

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.foundation.Image
import androidx.compose.material.BottomNavigation
import androidx.compose.material.Text


@Composable
fun BottomNavigationBar(
    modifier: Modifier = Modifier,
    onItemClick: (BottomNavItem) -> Unit
    , bottomItems: List<BottomNavItem>
) {
    BottomNavigation(
        modifier = modifier.height(50.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .background(
                    colorResource(R.color.purple),
                    shape = RoundedCornerShape(topStart = 12.dp, topEnd = 12.dp)
                )
                .padding(vertical = 8.dp),
            horizontalArrangement = Arrangement.SpaceAround
        ) {
            bottomItems.forEach { bottomNavItem ->
                Column(
                    modifier = Modifier
                        .clickable { onItemClick(bottomNavItem) }
                        .padding(8.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Image(
                        painter = painterResource(id = bottomNavItem.icon),
                        contentDescription = bottomNavItem.title,
                        modifier = Modifier.size(24.dp)
                    )
                    Text(
                        text = bottomNavItem.title,
                        fontSize = 12.sp,
                        color = Color.White
                    )
                }
            }
        }
    }
}
