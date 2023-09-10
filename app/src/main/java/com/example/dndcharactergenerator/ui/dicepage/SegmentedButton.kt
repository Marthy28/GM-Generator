package com.example.dndcharactergenerator.ui.dicepage

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.ripple.rememberRipple
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.dndcharactergenerator.theme.Dimens

enum class Extremity {
    RIGHT, LEFT, NONE
}

@Composable
fun SegmentedButton(
    buttonLabels: List<String>,
    modifier: Modifier = Modifier,
    onChanged: (Int) -> Unit
) {
    val selectedItem = remember { mutableStateOf(0) }
    Row(
        modifier = modifier
    ) {
        buttonLabels.forEachIndexed { index, item ->
            val extremity: Extremity = when (index) {
                0 -> Extremity.LEFT
                buttonLabels.size - 1 -> Extremity.RIGHT
                else -> Extremity.NONE
            }
            ButtonComponent(
                onClick = {
                    selectedItem.value = index
                    onChanged(selectedItem.value)
                },
                extremity = extremity,
                isSelected = selectedItem.value == index,
                label = item,
                modifier = Modifier.weight(1f)
            )
        }
    }
}

@Composable
fun ButtonComponent(
    onClick: () -> Unit,
    extremity: Extremity,
    isSelected: Boolean,
    label: String, modifier: Modifier = Modifier
) {

    val shape = RoundedCornerShape(
        topStart = if (extremity == Extremity.LEFT) 40.dp else 0.dp,
        bottomStart = if (extremity == Extremity.LEFT) 40.dp else 0.dp,
        topEnd = if (extremity == Extremity.RIGHT) 40.dp else 0.dp,
        bottomEnd = if (extremity == Extremity.RIGHT) 40.dp else 0.dp,
    )

    Box(
        modifier = modifier
            .height(Dimens.segmentedButtonHeight)
            .clip(shape = shape)
            .border(
                width = 1.dp, MaterialTheme.colorScheme.primary, shape = shape
            )
            .background(
                color = if (isSelected) MaterialTheme.colorScheme.primary.copy(alpha = 0.5f) else Color.Transparent
            )
            .clickable(
                interactionSource = remember { MutableInteractionSource() },
                indication = rememberRipple(color = MaterialTheme.colorScheme.primary),
            ) { onClick() }
    ) {
        Text(
            modifier = Modifier
                .padding(all = Dimens.halfPadding)
                .align(Alignment.Center),
            textAlign = TextAlign.Center,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
            text = label,
            fontSize = 15.sp
        )
    }
}

@Composable
@Preview
fun ShowSegmentedButton() {
    SegmentedButton(buttonLabels = listOf("1", "2", "3"), onChanged = {})
}