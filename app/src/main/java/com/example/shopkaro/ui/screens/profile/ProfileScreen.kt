package com.example.shopkaro.ui.screens.profile

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Face
import androidx.compose.material3.Divider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.shopkaro.R
import com.example.shopkaro.ui.theme.BoxColor
import com.example.shopkaro.ui.theme.Purple80

@Composable
fun ProfileScreen(
    profileUiState: ProfileUiState,
    signOut: () -> Unit,
    navigateToOrders: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.height(20.dp))
        Image(
            Icons.Filled.Face, contentDescription = null,
            modifier = Modifier
                .width(80.dp)
                .height(80.dp)
                .clip(CircleShape)
                .background(Purple80)
                .padding(16.dp)
        )
        Spacer(modifier = Modifier.height(10.dp))
        Text(text = profileUiState.name, fontSize = 18.sp)
        Spacer(modifier = Modifier.height(4.dp))
        Text(text = profileUiState.email)

        Spacer(modifier = Modifier.height(40.dp))
        Text(
            text = "Actions",color = Color.Black,  modifier = Modifier
                .padding(horizontal = 16.dp)
                .fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(10.dp))
        ProfileItem(R.drawable.orders, "My orders", true, RoundedClip.Top) {
            navigateToOrders()
        }
        ProfileItem(
            R.drawable.support,
            "Support",
            roundedClip = RoundedClip.Bottom
        )
        Spacer(modifier = Modifier.height(10.dp))
        Text(
            text = "Actions", color = Color.Black, modifier = Modifier
                .padding(horizontal = 16.dp)
                .fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(10.dp))
        ProfileItem(R.drawable.app_version, "App Version", true, RoundedClip.Top)
        ProfileItem(R.drawable.rate, "Rate us", true, RoundedClip.Not)
        ProfileItem(
            R.drawable.log_out,
            "Log out",
            roundedClip = RoundedClip.Bottom
        ) {
            signOut()
        }


    }
}

@Composable
fun ProfileItem(
    iconId: Int,
    title: String,
    isDivider: Boolean = false,
    roundedClip: RoundedClip,
    click: () -> Unit = {}
) {
    val roundedCornerShape = when (roundedClip) {
        RoundedClip.Top -> {
            RoundedCornerShape(topStart = 16.dp, topEnd = 16.dp)
        }

        RoundedClip.Bottom -> {
            RoundedCornerShape(bottomStart = 16.dp, bottomEnd = 16.dp)
        }

        else -> {
            RoundedCornerShape(0.dp)
        }
    }
    Column(modifier = Modifier
        .padding(horizontal = 16.dp)
        .clip(roundedCornerShape)
        .background(BoxColor)
        .clickable { click() }) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                painter = painterResource(id = iconId), contentDescription = null,
                modifier = Modifier
                    .width(24.dp)
                    .height(24.dp)
            )
            Spacer(modifier = Modifier.width(12.dp))
            Text(text = title, fontSize = 16.sp, color = Color.Black)

        }
        if (isDivider) {
            Divider(modifier = Modifier.padding(horizontal = 16.dp))
        }

    }

}

enum class RoundedClip {
    Top, Bottom, Not
}