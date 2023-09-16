package com.example.dndcharactergenerator.ui.component.dices

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.dndcharactergenerator.R
import com.example.dndcharactergenerator.theme.Dimens

@Composable
fun D20Component(value: Int) {
    Box(modifier = Modifier.fillMaxWidth(), contentAlignment = Alignment.Center) {
        Image(painterResource(id = R.drawable.d20), contentDescription = "", modifier = Modifier.fillMaxSize())
        DiceText(value.toString(), colored = true)
    }
}

@Composable
fun D100Component(value: Int) {
    Box(modifier = Modifier.fillMaxWidth(), contentAlignment = Alignment.Center) {
        Image(painterResource(id = R.drawable.d20), contentDescription = "", modifier = Modifier.fillMaxSize())
        DiceText(value.toString(), fontSize = 25.sp)
    }
}

@Composable
fun D10Component(value: Int) {
    Box(modifier = Modifier.fillMaxWidth(), contentAlignment = Alignment.Center) {
        Image(painterResource(id = R.drawable.d10), contentDescription = "", modifier = Modifier.fillMaxSize())
        DiceText(value.toString(), Dimens.mediumPadding)
    }
}

@Composable
fun D12Component(value: Int) {
    Box(modifier = Modifier.fillMaxWidth(), contentAlignment = Alignment.Center) {
        Image(painterResource(id = R.drawable.d12), contentDescription = "", modifier = Modifier.fillMaxSize())
        DiceText(value.toString(), Dimens.mediumPadding)
    }
}

@Composable
fun D8Component(value: Int) {
    Box(modifier = Modifier.fillMaxWidth(), contentAlignment = Alignment.Center) {
        Image(painterResource(id = R.drawable.d8), contentDescription = "", modifier = Modifier.fillMaxSize())
        DiceText(value.toString(), 0.dp)
    }
}

@Composable
fun D6Component(value: Int) {
    Box(modifier = Modifier.fillMaxWidth(), contentAlignment = Alignment.Center) {
        Image(painterResource(id = R.drawable.d6), contentDescription = "", modifier = Modifier.fillMaxSize())
        DiceText(value.toString(), 0.dp)
    }
}

@Composable
fun D4Component(value: Int) {
    Box(modifier = Modifier.fillMaxWidth(), contentAlignment = Alignment.Center) {
        Image(painterResource(id = R.drawable.d4), contentDescription = "", modifier = Modifier.fillMaxSize())
        DiceText(value.toString())
    }
}

@Composable
fun DiceText(
    text: String,
    topPadding: Dp = Dimens.standardPadding,
    fontSize: TextUnit = 30.sp,
    colored: Boolean = false
) {
    Text(
        modifier = Modifier.padding(top = Dimens.standardPadding),
        text = text,
        fontWeight = FontWeight.Bold,
        fontSize = 30.sp,
        color = if (colored) when (text) {
            "1" -> Color.Red
            "20" -> Color.Green
            else -> Color.Black
        } else Color.Black
    )
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
