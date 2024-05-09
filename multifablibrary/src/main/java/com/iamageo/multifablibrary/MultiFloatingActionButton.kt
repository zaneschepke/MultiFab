package com.iamageo.multifablibrary


import androidx.compose.animation.*
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp

@Composable
fun MultiFloatingActionButton(
    fabIcon: FabIcon,
    modifier: Modifier = Modifier,
    shape: Shape = RoundedCornerShape(16.dp),
    itemsMultiFab: List<MultiFabItem>,
    fabState: MutableState<MultiFabState> = rememberMultiFabState(),
    fabOption: FabOption = FabOption(),
    miniFabOption: FabOption = fabOption,
    onFabItemClicked: (fabItem: MultiFabItem) -> Unit,
    stateChanged: (fabState: MultiFabState) -> Unit = {}
) {
    val rotation by animateFloatAsState(
        if (fabState.value == MultiFabState.Expanded) fabIcon.iconRotate ?: 0f else 0f, label = ""
    )

    Column(
        modifier = modifier.wrapContentSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.End,
    ) {
        AnimatedVisibility(
            visible = fabState.value == MultiFabState.Expanded,
            enter = fadeIn() + expandVertically(),
            exit = fadeOut()
        ) {
            LazyColumn(
                horizontalAlignment = Alignment.End,
                verticalArrangement = Arrangement.spacedBy(15.dp, Alignment.CenterVertically),
                modifier = Modifier.padding(8.5.dp)
            ) {
                items(itemsMultiFab) { item ->
                    MiniFabItem(
                        item = item,
                        miniFabColor = miniFabOption.iconTint,
                        miniFabBackgroundColor = miniFabOption.backgroundTint,
                        onFabItemClicked = { onFabItemClicked(item) })
                }
                item{}
            }
        }
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.End,
        ) {
            FloatingActionButton(
                onClick = {
                    fabState.value = fabState.value.toggleValue()
                    stateChanged(fabState.value)
                },
                shape = shape,
                containerColor = fabOption.backgroundTint,
                contentColor = fabOption.iconTint
            ) {
                Icon(
                    painter =
                    if (fabState.value.isExpanded()) painterResource(fabIcon.iconResAfterRotate)
                    else painterResource(fabIcon.iconRes),
                    modifier = Modifier.rotate(rotation),
                    contentDescription = null,
                    tint = fabOption.iconTint
                )
            }
        }
    }
}

