package com.iamageo.multifab

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.iamageo.multifab.ui.theme.MultiFabTheme
import com.iamageo.multifablibrary.FabIcon
import com.iamageo.multifablibrary.FabOption
import com.iamageo.multifablibrary.MultiFabItem
import com.iamageo.multifablibrary.MultiFloatingActionButton

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MultiFabTheme {
                Scaffold(
                    containerColor = Color.Black,
                    floatingActionButton = {
                        MultiFloatingActionButton(
                            fabIcon = FabIcon(
                                iconRes = R.drawable.ic_baseline_add_24,
                                iconResAfterRotate = R.drawable.ic_baseline_remove_24,
                                iconRotate = 180f
                            ),
                            fabOption = FabOption(
                                iconTint = Color.Blue,
                                backgroundTint = Color.White,
                            ),
                            itemsMultiFab = listOf(
                                MultiFabItem(
                                    value = "label1",
                                    label = {
                                            Text("label1", color = Color.White, textAlign = TextAlign.Center, modifier = Modifier.padding(end = 10.dp))
                                    },
                                    icon = R.drawable.ic_baseline_add_24,
                                    miniFabOption = FabOption()
                                ),
                                MultiFabItem(
                                    value = "label1",
                                    label = {
                                            Text("label2", color = Color.White, textAlign = TextAlign.Center, modifier = Modifier.padding(end = 10.dp))
                                    },
                                    icon = R.drawable.ic_baseline_add_24,
                                    miniFabOption = FabOption()
                                ),
                            ),
                            onFabItemClicked = { println(it.value) },
                            shape = RoundedCornerShape(16.dp),
                        )
                    }
                ) { paddingValues ->
                    Column(modifier = Modifier
                        .fillMaxSize().padding(paddingValues),) {}
                }
            }
        }
    }
}