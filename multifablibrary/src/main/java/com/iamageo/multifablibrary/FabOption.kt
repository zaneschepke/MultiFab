package com.iamageo.multifablibrary

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.contentColorFor
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Immutable
import androidx.compose.runtime.Stable
import androidx.compose.ui.graphics.Color

@Immutable
interface FabOption {
    @Stable
    val iconTint: Color

    @Stable
    val backgroundTint: Color

}

private class FabOptionImpl(
    override val iconTint: Color,
    override val backgroundTint: Color,
) : FabOption

@Composable
fun FabOption(
    backgroundTint: Color = MaterialTheme.colorScheme.primary,
    iconTint: Color = contentColorFor(backgroundTint),
): FabOption =
    FabOptionImpl(iconTint = iconTint, backgroundTint = backgroundTint)