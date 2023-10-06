import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.statusBarsIgnoringVisibility
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@OptIn(ExperimentalMaterial3Api::class, ExperimentalLayoutApi::class)
@Composable
fun CustomTopBar(onClick: () -> Unit, text: String) {
    TopAppBar(
        modifier = Modifier.windowInsetsPadding(WindowInsets.statusBarsIgnoringVisibility),
        title = { Text(text = text) },
        navigationIcon = {
            IconButton(onClick = onClick) {
                Icon(Icons.Default.Menu, "Menu")
            }
        })
}
