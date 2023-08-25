package com.example.dndcharactergenerator.ui.component.dices

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import com.example.dndcharactergenerator.R
import com.example.dndcharactergenerator.theme.Dimens

@Composable
fun D20(value: Int) {
    Box(modifier = Modifier.fillMaxWidth(), contentAlignment = Alignment.Center) {
        Image(painterResource(id = R.drawable.d20), contentDescription = "")
        Text(
            modifier = Modifier.padding(top = Dimens.halfPadding),
            text = value.toString(),
            fontWeight = FontWeight.Bold
        )
    }
}

@Composable
fun D100(value: Int) {
    Box(modifier = Modifier.fillMaxWidth(), contentAlignment = Alignment.Center) {
        Image(painterResource(id = R.drawable.d20), contentDescription = "")
        Text(
            modifier = Modifier.padding(top = Dimens.halfPadding),
            text = value.toString(),
            fontWeight = FontWeight.Bold
        )
    }
}

@Composable
fun D10(value: Int) {
    Box(modifier = Modifier.fillMaxWidth(), contentAlignment = Alignment.Center) {
        Image(painterResource(id = R.drawable.d10), contentDescription = "")
        Text(
            modifier = Modifier.padding(top = Dimens.halfPadding),
            text = value.toString(),
            fontWeight = FontWeight.Bold
        )
    }
}

@Composable
fun D12(value: Int) {
    Box(modifier = Modifier.fillMaxWidth(), contentAlignment = Alignment.Center) {
        Image(painterResource(id = R.drawable.d20), contentDescription = "")
        Text(
            modifier = Modifier.padding(top = Dimens.halfPadding),
            text = value.toString(),
            fontWeight = FontWeight.Bold
        )
    }
}

@Composable
fun D8(value: Int) {
    Box(modifier = Modifier.fillMaxWidth(), contentAlignment = Alignment.Center) {
        Image(painterResource(id = R.drawable.d8), contentDescription = "")
        Text(
            modifier = Modifier.padding(top = Dimens.halfPadding),
            text = value.toString(),
            fontWeight = FontWeight.Bold
        )
    }
}

@Composable
fun D6(value: Int) {
    Box(modifier = Modifier.fillMaxWidth(), contentAlignment = Alignment.Center) {
        Image(painterResource(id = R.drawable.d6), contentDescription = "")
        Text(
            modifier = Modifier.padding(top = Dimens.halfPadding),
            text = value.toString(),
            fontWeight = FontWeight.Bold
        )
    }
}

@Composable
fun D4(value: Int) {
    Box(modifier = Modifier.fillMaxWidth(), contentAlignment = Alignment.Center) {
        Image(painterResource(id = R.drawable.d4), contentDescription = "")
        Text(
            modifier = Modifier.padding(top = Dimens.halfPadding),
            text = value.toString(),
            fontWeight = FontWeight.Bold
        )
    }
}
