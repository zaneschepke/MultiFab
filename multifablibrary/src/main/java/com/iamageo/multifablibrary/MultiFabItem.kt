package com.iamageo.multifablibrary

import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.unit.dp

class MultiFabItem(
    val icon: Int,
    val label: (@Composable () -> Unit)? = {},
    val shape: Shape = RoundedCornerShape(16.dp),
    val modifier: Modifier = Modifier.size(40.dp),
)
