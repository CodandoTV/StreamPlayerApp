package com.codandotv.streamplayerapp.feature_list_streams.search.presentation.screens

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.DefaultLifecycleObserver
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.codandotv.streamplayerapp.core_networking.handleError.catchFailure
import com.codandotv.streamplayerapp.feature_list_streams.search.domain.usecase.SearchUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.onCompletion
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch

class SearchViewModel(
    private val searchUseCase: SearchUseCase
): ViewModel(), DefaultLifecycleObserver {

    private val _isShowstateSearch: MutableState<Boolean> = mutableStateOf(false)
    val isShowstateSearch: MutableState<Boolean> = _isShowstateSearch

    private val _currentSearchText = MutableStateFlow("")
    val currentSearchText = _currentSearchText.asStateFlow()

    init {
        viewModelScope.launch {
            /*
            Substituir esse chamada ao endpoint de pesquisa pelo o de principais buscas,
            Devemos substituir o parametro query com o texto que usuário está digitando
             */
            searchUseCase(query = "i").onStart {
                //começar load
            }.onCompletion {
                //parar load
            }.catchFailure {
                //erro
            }.collect{
                //atualizar _uiState
                println(it)
            }
        }
    }
    fun showSearchField(show: Boolean){
        _isShowstateSearch.value = show
    }

    fun setCurrentSearchText(newText: String){
        _currentSearchText.value = newText
    }

    fun onCleanText(){
        _currentSearchText.value = ""
    }
}
