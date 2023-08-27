package com.codandotv.streamplayerapp.feature_list_streams.search.presentation.screens

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.DefaultLifecycleObserver
import androidx.lifecycle.ViewModel

class SearchViewModel: ViewModel(), DefaultLifecycleObserver {

    private val _isShowstateSearch: MutableState<Boolean> = mutableStateOf(false)
    val isShowstateSearch: MutableState<Boolean> = _isShowstateSearch

    private val _currentSearchText: MutableState<String> = mutableStateOf("")
    val currentSearchText: MutableState<String> = _currentSearchText

    fun showSearchField(show: Boolean){
        _isShowstateSearch.value = show
    }

    fun setCurrentSearchText(newText: String){
        _currentSearchText.value = newText
    }
}