package com.codandotv.streamplayerapp.feature_list_streams.search.presentation.screens

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import com.codandotv.streamplayerapp.core_navigation.extensions.goBack
import com.codandotv.streamplayerapp.feature_list_streams.search.presentation.widgets.SearchableTopBar
import org.koin.androidx.compose.koinViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchScreen(
    viewModel: SearchViewModel = koinViewModel(),
    navController: NavController,
    disposable: () -> Unit = {}
) {

    var text by remember { mutableStateOf("") }

    Scaffold(
        topBar = {
            SearchableTopBar(
                currentSearchText = text,
                onSearchTextChanged = { currentText ->
                    text = currentText
                },
                onSearchDeactivated = {},
                onSearchDispatched = {},
                onSearchIconPressed = {},
                onBackPressed = {
                    navController.goBack()
                },
                onCleanTextPressed = {
                    text = ""
                },
                isShowingSearchField = false
            )
        }
    ) { paddingValues ->
        Column(modifier = Modifier.padding(paddingValues)) {
            repeat(10) {
                Text(text = "Texto de Teste $it")
            }
        }
        BackHandler {
            navController.goBack()
        }
    }
}
