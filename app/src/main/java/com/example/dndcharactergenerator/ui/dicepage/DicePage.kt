package com.example.dndcharactergenerator.ui.dicepage

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
//https://stackoverflow.com/questions/68603329/segment-controls-jetpack-compose
@Composable
fun DicePage (navController: NavHostController) {
        Text("Quel est le comble du paladin ?\n" +
                "D'être de mauvaise foi...")
}

@Composable
private fun SegmentedControl() {

}