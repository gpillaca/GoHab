package pe.geff.gohab.core.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp

@Composable
fun CustomButton(
    onclick: () -> Unit,
    text: String,
    modifier: Modifier,
    shape: RoundedCornerShape = RoundedCornerShape(6.dp),
    isEnabled: Boolean = true
) {
    Button(
        onClick = onclick,
        modifier = modifier.fillMaxWidth().height(60.dp),
        shape = shape,
        enabled = isEnabled
    ) {
        Text(
            text = text,
            style = MaterialTheme.typography.bodyMedium.copy(
                fontWeight = FontWeight.Bold
            ),
        )
    }
}
