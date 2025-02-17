package com.example.ecommerceapp

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil3.compose.rememberAsyncImagePainter
import com.example.ecommerceapp.model.ItemModel

@Composable
fun ItemList(items: List<ItemModel>) {
    LazyVerticalGrid(
        columns = GridCells.Fixed(2),
        modifier = Modifier.height(500.dp).padding(start = 7.dp, end = 7.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp),
        horizontalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        items(items.size) { index ->
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                ItemCard(item = items[index], pos = index)
            }
        }
    }
}

@Composable
fun ItemCard(item: ItemModel, pos: Int) {

    Column(
        modifier = Modifier.padding(8.dp).height(255.dp)
    ) {
        Log.d("ImageURL", "Image URL: ${item.picUrl}")

        // Coil image loading
        Image(
            painter = rememberAsyncImagePainter(
                item.picUrl.firstOrNull(),
            ),
            contentDescription = "Item Image",
            modifier = Modifier
                .width(200.dp)
                .height(200.dp)
                .background(colorResource(R.color.lightgray), shape = RoundedCornerShape(10.dp))
                .padding(7.dp)
                .clickable { }
        )

        Text(
            text = item.title,
            color = Color.Black,
            fontSize = 16.sp,
            fontWeight = FontWeight.Bold,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
            modifier = Modifier.padding(top = 6.dp)
        )

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Row {
                Image(
                    painter = painterResource(R.drawable.star),
                    contentDescription = "Rating",
                    modifier = Modifier.align(Alignment.CenterVertically)
                )
                Spacer(modifier = Modifier.width(6.dp))
                Text(
                    text = item.rating.toString(),
                    color = Color.Black,
                    fontSize = 14.sp
                )
                Text(
                    text = "(${item.price})",
                    color = colorResource(R.color.purple),
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Bold
                        , textAlign = TextAlign.End
                    , modifier = Modifier.fillMaxWidth()

                )
            }
        }
    }
}
