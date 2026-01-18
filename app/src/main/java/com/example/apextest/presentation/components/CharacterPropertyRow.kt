package com.example.apextest.presentation.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.sp
import com.example.apextest.config.theme.Pastel

enum class ChipStyle { Mint, Peach, Lavender }

@Composable
fun CharacterPropertyRow(
    label: String,
    value: String,
    chipStyle: ChipStyle,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(text = label, fontSize = 12.sp, color = Pastel.TextSoft)
        CharacterChip(text = value.ifBlank { "-" }, style = chipStyle)
    }
}