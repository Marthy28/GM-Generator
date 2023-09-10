package com.example.dndcharactergenerator.ui.component.dices

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import com.example.dndcharactergenerator.R
import com.example.dndcharactergenerator.theme.Dimens

@Composable
fun D20Component(value: Int) {
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
fun D100Component(value: Int) {
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
fun D10Component(value: Int) {
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
fun D12Component(value: Int) {
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
fun D8Component(value: Int) {
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
fun D6Component(value: Int) {
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
fun D4Component(value: Int) {
    Box(modifier = Modifier.fillMaxWidth(), contentAlignment = Alignment.Center) {
        Image(painterResource(id = R.drawable.d4), contentDescription = "")
        Text(
            modifier = Modifier.padding(top = Dimens.halfPadding),
            text = value.toString(),
            fontWeight = FontWeight.Bold
        )
    }
}

@Composable
@Preview
fun ShowDices() {
    Column {
        D4Component(value = 4)
        D6Component(value = 6)
        D8Component(value = 8)
        D10Component(value = 10)
        D20Component(value = 20)
        D100Component(value = 100)
    }
}
