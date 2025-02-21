package com.example.ecommerceapp

import android.os.Bundle
import android.util.Log
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.ecommerceapp.ViewModel.HomeViewModel
import com.google.accompanist.systemuicontroller.rememberSystemUiController

class ListItemsActivity : AppCompatActivity() {
    private val viewModel = HomeViewModel()
    private var id : String = ""
    private var title : String = ""
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        id = intent.getStringExtra("id")?:""
        title = intent.getStringExtra("title")?:""

        setContent {
            AppBar(color = MaterialTheme.colorScheme.background)
            ListItemScreen(
                title = title,
                onbackclick = {
                    finish()
                },
                viewModel = viewModel,
                id = id
            )
        }
    }
    @Composable
    private fun AppBar(color : Color){
        val appBar = rememberSystemUiController()
        appBar.setStatusBarColor(color)
    }
}

@Composable
fun ListItemScreen(
    title: String,
    onbackclick: () -> Unit,
    viewModel: HomeViewModel,
    id: String
) {
    val items by viewModel.recommended.observeAsState(emptyList())
    val isLoading = remember { mutableStateOf(true) }

    LaunchedEffect(id) {
        viewModel.loadfiltered(id)
    }

    LaunchedEffect(items) {
            isLoading.value = false

    }
    if (isLoading.value || items.isEmpty()) {
        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            CircularProgressIndicator()
        }
    } else {
        ItemListfull(items = items)
    }

    Column(modifier = Modifier.fillMaxSize()) {
        ConstraintLayout(modifier = Modifier.padding(top = 36.dp, end = 16.dp, start = 16.dp)) {
            val (backBtn, cartTxt) = createRefs()
            Text(
                modifier = Modifier.fillMaxWidth()
                    .constrainAs(cartTxt) { centerTo(parent) },
                textAlign = TextAlign.Center,
                fontSize = 25.sp,
                fontWeight = FontWeight.Bold,
                text = title
            )
            Image(
                painter = painterResource(R.drawable.back),
                contentDescription = "Back",
                modifier = Modifier.clickable {
                    onbackclick()
                }.constrainAs(backBtn) {
                    top.linkTo(parent.top)
                    bottom.linkTo(parent.bottom)
                    start.linkTo(parent.start)
                }
            )
        }
    }

}
