package com.example.ecommerceapp

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.ecommerceapp.ui.theme.ECommerceAppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ECommerceAppTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    IntroActivity(onClick = {
                        startActivity(Intent(this,HomeActivity::class.java))
                    } , modifier = Modifier.padding(innerPadding))

                }
            }
        }
    }
}
@Composable
@Preview
fun IntroActivity(onClick : () -> Unit ={} , modifier: Modifier = Modifier){
    Column (
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
            .verticalScroll(rememberScrollState())
            .padding(16.dp)
            , horizontalAlignment = Alignment.CenterHorizontally
    ){
        Image(painter = painterResource(id = R.drawable.intro_logo) ,
            contentDescription = "Logo",
            contentScale = ContentScale.Fit,
            modifier = Modifier.padding(top = 15.dp).fillMaxWidth())
        Spacer(modifier = Modifier.height(20.dp))

        Text(text = stringResource(R.string.intro_title)
            , fontSize = 26.sp
        , fontWeight = FontWeight.Bold
        ,color = Color.Black
        , textAlign = TextAlign.Center
        )
        Spacer(modifier = Modifier.height(20.dp))
        Text(text = stringResource(R.string.intro_subtitle)
            ,modifier = Modifier.padding(top = 15.dp)
            , fontSize = 18.sp
        , fontWeight = FontWeight.Normal
        ,color = Color.DarkGray
        ,textAlign = TextAlign.Center
        , lineHeight = 24.sp)
        Spacer(modifier = Modifier.height(20.dp))
        Button(onClick = { onClick()}
        , modifier = Modifier.padding(horizontal = 32.dp
                , vertical = 16.dp).fillMaxWidth()
                .height(50.dp)
                , colors = ButtonDefaults.buttonColors(
                    containerColor = colorResource(id = R.color.purple)
                )
                , shape = RoundedCornerShape(10.dp)
                ) {
            Text(stringResource(id = R.string.letgo)
            ,color = Color.White
            , fontSize = 18.sp)
        }
        Text(stringResource(id = R.string.sign)
            , modifier = Modifier.padding(top = 16.dp)
            , textAlign = TextAlign.Center
            , fontSize = 18.sp)

    }
}