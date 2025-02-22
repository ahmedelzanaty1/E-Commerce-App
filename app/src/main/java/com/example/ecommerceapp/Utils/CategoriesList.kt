package com.example.ecommerceapp.utils

import android.content.Intent
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.widget.ImageView
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import com.example.ecommerceapp.ListItemsActivity
import com.example.ecommerceapp.R
import com.example.ecommerceapp.model.CategoriesModel
import com.squareup.picasso.Picasso

@Composable
fun CategoriesList(categories: SnapshotStateList<CategoriesModel>) {
    val selectedIndex = remember { mutableStateOf(-1) }
    val context = LocalContext.current

    LazyRow(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(16.dp),
        contentPadding = PaddingValues(start = 16.dp, end = 16.dp, top = 8.dp)
    ) {
        items(categories.size) { index ->
            CategoryItem(
                item = categories[index],
                isSelected = selectedIndex.value == index,
                onClick = { selectedIndex.value = index
                        Handler(Looper.getMainLooper()).postDelayed({
                            val intent = Intent(context, ListItemsActivity::class.java).apply {
                                putExtra("id", categories[index].id)
                                putExtra("title", categories[index].title)
                            }
                            context.startActivity(intent)


                        },1000)
                }
            )
        }
    }
}

@Composable
fun CategoryItem(
    item: CategoriesModel,
    isSelected: Boolean,
    onClick: () -> Unit
) {
    Log.d("CategoryDebug", "Rendering category: ${item.title}, Image URL: ${item.picUrl}")

    Column(
        modifier = Modifier
            .clickable { onClick() }
            .padding(4.dp)
            .background(
                color = if (isSelected) colorResource(R.color.purple) else Color.LightGray,
                shape = RoundedCornerShape(8.dp)
            )
            .padding(3.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        LoadImageWithPicasso(
            url = item.picUrl,
            isSelected = isSelected
        )
        Spacer(modifier = Modifier.height(2.dp))
        Text(
            text = item.title,
            color = Color.White
        )
    }
}

@Composable
fun LoadImageWithPicasso(url: String?, isSelected: Boolean) {
    AndroidView(
        factory = { context ->
            ImageView(context).apply {
                scaleType = ImageView.ScaleType.CENTER_CROP
            }
        },
        update = { imageView ->
            if (!url.isNullOrEmpty()) {
                Log.d("Picasso", "Loading image with URL: $url")
                Picasso.get()
                    .load(url)
                    .error(R.drawable.logo_tech)
                    .into(imageView)
            } else {
                imageView.setImageResource(R.drawable.logo_tech)
            }
        },
        modifier = Modifier
            .size(35.dp)
            .background(
                color = Color.Transparent,
                shape = RoundedCornerShape(8.dp)
            )
    )

}
