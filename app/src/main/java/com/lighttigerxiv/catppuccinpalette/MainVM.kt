package com.lighttigerxiv.catppuccinpalette

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.lighttigerxiv.catppuccin_kt.CatppuccinTheme
import com.lighttigerxiv.catppuccin_kt.getCatppuccinThemes
import com.lighttigerxiv.catppuccin_kt.getMochaColors
import com.lighttigerxiv.catppuccinpalette.getNavigationItems
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class MainVM(application: Application) : AndroidViewModel(application) {


    val themes = getCatppuccinThemes()
    val navigationItems = getNavigationItems()

    private val _currentTheme = MutableStateFlow(getMochaColors())
    val currentTheme = _currentTheme.asStateFlow()
    fun updateCurrentTheme(newValue: CatppuccinTheme) {
        _currentTheme.update { newValue }
    }

    private val _currentThemeName = MutableStateFlow("Mocha")
    val currentThemeName= _currentThemeName.asStateFlow()
    fun updateCurrentThemeName(newValue:String) {
        _currentThemeName.update { newValue }
    }

    private val _loading = MutableStateFlow(true)
    val loading = _loading.asStateFlow()
    fun updateLoading(newValue:Boolean) {
        _loading.update { newValue }
    }
}