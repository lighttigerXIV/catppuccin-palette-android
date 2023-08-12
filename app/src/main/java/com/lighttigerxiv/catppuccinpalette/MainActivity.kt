package com.lighttigerxiv.catppuccinpalette

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.ViewModelProvider
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import com.lighttigerxiv.catppuccin_kt.getMochaColors
import com.lighttigerxiv.catppuccinpalette.backend.constants.INTER_MEDIUM
import com.lighttigerxiv.catppuccinpalette.backend.constants.MEDIUM
import com.lighttigerxiv.catppuccinpalette.backend.constants.SMALL
import com.lighttigerxiv.catppuccinpalette.screens.preview.PreviewScreen
import com.lighttigerxiv.catppuccinpalette.ui.theme.CatppuccinPaletteTheme
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        val vm = ViewModelProvider(this)[MainVM::class.java]

        setContent {
            CatppuccinPaletteTheme {

                val scope = rememberCoroutineScope()
                val uiController = rememberSystemUiController()

                val themes = vm.themes
                val currentTheme = vm.currentTheme.collectAsState().value
                val currentThemeName = vm.currentThemeName.collectAsState().value
                val loading = vm.loading.collectAsState().value

                LaunchedEffect(loading) {
                    launch {
                        uiController.setStatusBarColor(Color(getMochaColors().base.hex.asLong()))
                        vm.updateLoading(false)
                    }
                }

                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(Color(currentTheme.base.hex.asLong()))
                        .padding(MEDIUM)
                ) {

                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .weight(1f, fill = true)
                    ) {
                        PreviewScreen(
                            name = currentThemeName,
                            theme = currentTheme
                        )
                    }

                    Spacer(modifier = Modifier.height(MEDIUM))

                    Row(
                        modifier = Modifier
                            .height(70.dp)
                            .fillMaxWidth(),
                        horizontalArrangement = Arrangement.Center
                    ) {

                        LazyRow(
                            modifier = Modifier
                                .clip(CircleShape)
                                .background(Color(currentTheme.crust.hex.asLong()))
                                .padding(
                                    start = MEDIUM,
                                    end = MEDIUM,
                                    top = INTER_MEDIUM,
                                    bottom = INTER_MEDIUM
                                )
                        ) {
                            itemsIndexed(
                                items = vm.navigationItems,
                                key = { _, item -> item.route }
                            ) { index, navigationItem ->

                                val selected =
                                    currentTheme == themes.mocha && index == 0 || currentTheme == themes.macchiato && index == 1 || currentTheme == themes.frappe && index == 2 || currentTheme == themes.latte && index == 3

                                Row {
                                    Spacer(modifier = Modifier.width(SMALL))

                                    Column(
                                        modifier = Modifier.clickable {

                                            when (index) {
                                                0 -> {
                                                    vm.updateCurrentTheme(themes.mocha)
                                                    vm.updateCurrentThemeName("Mocha")
                                                    scope.launch {
                                                        uiController.setStatusBarColor(Color(themes.mocha.base.hex.asLong()))
                                                    }
                                                }

                                                1 -> {
                                                    vm.updateCurrentTheme(themes.macchiato)
                                                    vm.updateCurrentThemeName("Macchiato")
                                                    scope.launch {
                                                        uiController.setStatusBarColor(Color(themes.macchiato.base.hex.asLong()))
                                                    }
                                                }

                                                2 -> {
                                                    vm.updateCurrentTheme(themes.frappe)
                                                    vm.updateCurrentThemeName("Frappe")
                                                    scope.launch {
                                                        uiController.setStatusBarColor(Color(themes.frappe.base.hex.asLong()))
                                                    }
                                                }

                                                else -> {
                                                    vm.updateCurrentTheme(themes.latte)
                                                    vm.updateCurrentThemeName("Latte")
                                                    scope.launch {
                                                        uiController.setStatusBarColor(Color(themes.latte.base.hex.asLong()))
                                                    }
                                                }
                                            }
                                        },
                                        verticalArrangement = Arrangement.Center,
                                        horizontalAlignment = Alignment.CenterHorizontally
                                    ) {

                                        Icon(
                                            modifier = Modifier
                                                .height(24.dp)
                                                .width(24.dp),
                                            painter = painterResource(id = if (selected) navigationItem.activeIcon else navigationItem.inactiveIcon),
                                            contentDescription = null,
                                            tint = Color(currentTheme.text.hex.asLong())
                                        )

                                        Text(
                                            text = navigationItem.name,
                                            color = Color(currentTheme.text.hex.asLong()),
                                            fontSize = 12.sp
                                        )
                                    }
                                    Spacer(modifier = Modifier.width(SMALL))
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}