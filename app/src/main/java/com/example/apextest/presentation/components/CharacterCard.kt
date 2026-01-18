package com.example.apextest.presentation.components

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.apextest.config.theme.Pastel
import com.example.apextest.infrastructure.model.Character

@Composable
fun CharacterCard(
    character: Character,
    modifier: Modifier
) {
    Card(
        modifier = modifier
            .fillMaxWidth()
            .border(width = 1.dp, color = Pastel.Border, shape = CardDefaults.shape),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surface
        ),
        elevation = CardDefaults.cardElevation(defaultElevation = 0.dp)
    ) {
        Column(
            modifier = Modifier.padding(14.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            CharacterHeader(
                name = character.name,
                imageUrl = character.image
            )

            CharacterSection(title = "PROPERTIES") {
                CharacterPropertyRow(label = "GENDER", value = character.gender, chipStyle = ChipStyle.Mint)
                CharacterPropertyRow(label = "SPECIES", value = character.species, chipStyle = ChipStyle.Peach)
                CharacterPropertyRow(label = "STATUS", value = character.status, chipStyle = ChipStyle.Lavender)
                if (character.type.isNotBlank()) {
                    CharacterPropertyRow(label = "TYPE", value = character.type, chipStyle = ChipStyle.Mint)
                }
            }
        }
    }
}