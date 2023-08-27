package com.codandotv.streamplayerapp.feature_list_streams.search.presentation.widgets


import androidx.annotation.StringRes
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun SearchableTopBar(
    isShowingSearchField: Boolean,
    currentSearchText: String,
    onSearchTextChanged: (String) -> Unit,
    onSearchDeactivated: () -> Unit,
    onSearchDispatched: (String) -> Unit,
    onSearchIconClicked: () -> Unit
) {
    when (isShowingSearchField) {
        true -> SearchTopBar(
            currentSearchText = currentSearchText,
            onSearchTextChanged = onSearchTextChanged,
            onSearchDeactivated = onSearchDeactivated,
            onSearchDispatched = onSearchDispatched
        )
        false -> HomeTopBar(topBarNameId = com.codandotv.streamplayerapp.feature.list.streams.R.string.detail_watch_primary_button, onSearchIconClicked = onSearchIconClicked)
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchTopBar(
    currentSearchText: String,
    onSearchTextChanged: (String) -> Unit,
    onSearchDeactivated: () -> Unit,
    onSearchDispatched: (String) -> Unit
) {
    Surface(
        modifier = Modifier.fillMaxWidth().height(56.dp),
    ) {
        TextField(modifier = Modifier.fillMaxWidth(),
            value = currentSearchText,
            onValueChange = { onSearchTextChanged(it) },
            placeholder = {
                Text(
                    text = "Digite aqui...",
                    color = Color.White
                )
            },
            singleLine = true,
            leadingIcon = { SearchLeadingIcon() },
            trailingIcon = { SearchTrailingIcon {
                if (currentSearchText.isNotEmpty()) onSearchTextChanged("") else onSearchDeactivated() }
            },
            keyboardOptions = KeyboardOptions(imeAction = ImeAction.Search),
            keyboardActions = KeyboardActions(onSearch = { onSearchDispatched(currentSearchText) } ),
            colors = TextFieldDefaults.textFieldColors(
                cursorColor = Color.White
            )
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeTopBar(@StringRes topBarNameId: Int = 0, onSearchIconClicked: () -> Unit) {
    TopAppBar(
        title = { Text(text = stringResource(id = topBarNameId) ) },
        actions = { SearchIcon(action = onSearchIconClicked) }
    )
}

@Composable
fun SearchIcon(action: () -> Unit = {}) {
    DefaultIcon(
        searchIcon = Icons.Filled.Search,
        contentDescription = "Search Icon",
        onIconClickAction = action
    )
}

@Composable
fun SearchLeadingIcon(action: () -> Unit = {}) {
    DefaultIcon(
        onIconClickAction = action
    )
}

@Composable
fun SearchTrailingIcon(action: () -> Unit = {}) {
    DefaultIcon(
        searchIcon = Icons.Default.Close,
        contentDescription = "Deactivate Search Icon",
        onIconClickAction = action
    )
}

@Composable
fun DefaultIcon (
    modifier: Modifier = Modifier,
    searchIcon: ImageVector = Icons.Default.Search,
    iconColor: Color = Color.White,
    contentDescription: String = "Magnifier Search Icon",
    onIconClickAction: () -> Unit = {}
) {
    IconButton(
        modifier = modifier,
        onClick = onIconClickAction
    ) {
        Icon(
            imageVector = searchIcon,
            contentDescription = contentDescription,
            tint = iconColor
        )
    }
}

@Composable
@Preview
fun HomeTopBarPreview() {
    HomeTopBar(onSearchIconClicked = {})
}

@Composable
@Preview
fun SearchTopBarPreview() {
    SearchTopBar(
        currentSearchText = "Texto de busca",
        onSearchTextChanged = {},
        onSearchDeactivated = {},
        onSearchDispatched = {}
    )
}