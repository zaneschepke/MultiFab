package com.iamageo.multifablibrary

import androidx.compose.foundation.layout.*
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp

@Composable
fun MiniFabItem(
    item: MultiFabItem,
    miniFabColor: Color,
    miniFabBackgroundColor: Color,
    onFabItemClicked: (item: MultiFabItem) -> Unit
) {
    Row(
        horizontalArrangement = Arrangement.spacedBy(10.dp, Alignment.CenterHorizontally),
        verticalAlignment = Alignment.CenterVertically
    ) {
        item.label?.let {
            it()
        }
        FloatingActionButton(
            modifier = item.modifier,
            onClick = { onFabItemClicked(item) },
            shape = item.shape,
            containerColor = miniFabBackgroundColor
        ) {
            Icon(
                painter = painterResource(item.icon),
                contentDescription = "multifab ${item.label}",
                tint = miniFabColor
            )
        }
    }
}