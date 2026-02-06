package com.example.businesscard

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Phone
import androidx.compose.material.icons.filled.Share
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            BusinessCard()
        }
    }
}

@Composable
fun BusinessCard() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFDDEFE0)),
        verticalArrangement = Arrangement.SpaceAround,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.height(60.dp))
        NameAndTitle()
        ContactInformation()
    }
}

@Composable
fun NameAndTitle() {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = painterResource(id = R.drawable.android_logo),
            contentDescription = "Android Logo",
            modifier = Modifier
                .size(120.dp)
                .background(Color(color = 0xFF002e40))
        )

        Spacer(modifier = Modifier.height(16.dp))

        Text(
            text = "Dhairya Rana",
            fontSize = 36.sp,
            fontWeight = FontWeight.Light,
            color = Color.Black
        )

        Text(
            text = "Android Developer Extraordinaire",
            fontSize = 16.sp,
            fontWeight = FontWeight.Bold,
            color = Color(0xFF1B5E20)
        )
    }
}

@Composable
fun ContactInformation() {
    Column(
        modifier = Modifier
            .padding(bottom = 32.dp)
    ) {
        ContactItem(icon = Icons.Default.Phone, text = "+1 368 886 3567")
        ContactItem(icon = Icons.Default.Share, text = "@theyrya")
        ContactItem(icon = Icons.Default.Email, text = "dhairya@gmail.com")
    }
}


@Composable
fun ContactItem(icon: ImageVector, text: String) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier.padding(vertical = 8.dp)
    ) {
        Icon(
            imageVector = icon,
            contentDescription = null,
            tint = Color(0xFF1B5E20),
            modifier = Modifier.size(24.dp)
        )

        Spacer(modifier = Modifier.width(16.dp))

        Text(
            text = text,
            fontSize = 14.sp,
            color = Color.Black
        )
    }
}

@Preview(showBackground = true)
@Composable
fun BusinessCardPreview() {
    BusinessCard()
}
