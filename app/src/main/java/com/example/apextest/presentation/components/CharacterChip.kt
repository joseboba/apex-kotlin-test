package com.example.apextest.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.apextest.config.theme.Pastel

@Composable
fun CharacterChip(
    text: String,
    style: ChipStyle,
    modifier: Modifier = Modifier
) {
    val (bg, fg) = when (style) {
        ChipStyle.Mint -> MaterialTheme.colorScheme.secondary to MaterialTheme.colorScheme.onSecondary
        ChipStyle.Peach -> MaterialTheme.colorScheme.tertiary to MaterialTheme.colorScheme.onTertiary
        ChipStyle.Lavender -> MaterialTheme.colorScheme.primary to MaterialTheme.colorScheme.onPrimary
    }

    Text(
        text = text,
        fontSize = 12.sp,
        color = fg,
        modifier = modifier
            .background(bg, RoundedCornerShape(999.dp))
            .padding(horizontal = 10.dp, vertical = 6.dp)
    )
}