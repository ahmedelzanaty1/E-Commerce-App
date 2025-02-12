package com.example.ecommerceapp

import android.os.Bundle
import android.util.Log
import android.widget.ImageView
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.constraintlayout.compose.ConstraintLayout
import com.example.ecommerceapp.ViewModel.HomeViewModel
import com.example.ecommerceapp.model.SliderModel
import com.example.ecommerceapp.ui.theme.ECommerceAppTheme
import com.google.accompanist.pager.*
import com.squareup.picasso.Picasso
import kotlinx.coroutines.delay

class HomeActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ECommerceAppTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    HomeActivityScreen(modifier = Modifier.padding(innerPadding))
                }
            }
        }
    }
}

@Composable
fun HomeActivityScreen(modifier: Modifier = Modifier) {
    val viewModel = remember { HomeViewModel() }
    val banner by viewModel.banner.observeAsState(emptyList())
    val showBannerLoading = remember { mutableStateOf(true) }

    LaunchedEffect(Unit) {
        viewModel.loadbanner()
        showBannerLoading.value = false
    }

    ConstraintLayout(modifier = Modifier.fillMaxSize().background(Color.White)) {
        val (scrollList) = createRefs()

        LazyColumn(
            modifier = Modifier.fillMaxSize().constrainAs(scrollList) {
                top.linkTo(parent.top)
            }
                .padding(top = 16.dp)
        ) {
            item {
                Row(
                    modifier = Modifier.fillMaxWidth().padding(16.dp),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Column {
                        Text("Welcome Back", color = Color.Black)
                        Text("Ahmed", color = Color.Black, fontWeight = FontWeight.Bold, fontSize = 18.sp)
                    }
                    Row {
                        Image(painter = painterResource(R.drawable.fav_icon), contentDescription = "fav")
                        Spacer(modifier = Modifier.width(16.dp))
                        Image(painter = painterResource(R.drawable.search_icon), contentDescription = "search")
                    }
                }
            }
            item {
                if (showBannerLoading.value) {
                    Box(modifier = Modifier.fillMaxWidth().height(200.dp), contentAlignment = Alignment.Center) {
                        CircularProgressIndicator()
                    }
                } else {
                    BannerSlider(banner)
                }
            }
        }
    }
}

@OptIn(ExperimentalPagerApi::class)
@Composable
fun BannerSlider(banner: List<SliderModel>) {
    AutoSlidingCarousel(banner)
}

@OptIn(ExperimentalPagerApi::class)
@Composable
fun AutoSlidingCarousel(banner: List<SliderModel>) {
    val pagerState = rememberPagerState()
    LaunchedEffect(pagerState) {
        if (banner.isNotEmpty())
        while (true) {
            delay(3000)
            val nextPage = (pagerState.currentPage + 1) % banner.size
            pagerState.animateScrollToPage(nextPage)
        }
    }
    Column(modifier = Modifier.fillMaxSize().padding(12.dp)) {
        HorizontalPager(count = banner.size, state = pagerState) { page ->

            LoadImageWithPicasso(banner[page].url)
            Log.e("BannerSlider", "Loaded Image URL: ${banner[page].url}")

        }
        DotsIndicator(
            totalDots = banner.size,
            selectedIndex = pagerState.currentPage,
            selectedColor = colorResource(R.color.purple),
            unSelectedColor = Color.LightGray,
            dotSize = 10.dp,
            modifier = Modifier.align(Alignment.CenterHorizontally).padding(8.dp)
        )
    }
}

@Composable
fun DotsIndicator(
    totalDots: Int,
    selectedIndex: Int,
    selectedColor: Color,
    unSelectedColor: Color,
    dotSize: Dp,
    modifier: Modifier = Modifier
) {
    LazyRow(modifier = modifier.wrapContentWidth().wrapContentHeight()) {
        items(totalDots) { index ->
            IndicatorDot(
                color = if (index == selectedIndex) selectedColor else unSelectedColor,
                size = dotSize,
                modifier = Modifier.padding(2.dp)
            )
            if (index != totalDots - 1) {
                Spacer(modifier = Modifier.padding(horizontal = 2.dp))
            }
        }
    }
}

@Composable
fun IndicatorDot(modifier: Modifier = Modifier, size: Dp, color: Color) {
    Box(
        modifier = modifier.size(size).clip(CircleShape).background(color)
    )
}
@Composable
fun LoadImageWithPicasso(url: String) {
    AndroidView(
        factory = { context ->
            ImageView(context).apply {
                scaleType = ImageView.ScaleType.CENTER_CROP
            }
        },
        update = { imageView ->
            Picasso.get().load(url).into(imageView)
        },
        modifier = Modifier
            .fillMaxWidth()
            .height(150.dp)
    )
}
