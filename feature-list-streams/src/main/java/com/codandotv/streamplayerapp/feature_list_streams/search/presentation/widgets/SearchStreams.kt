package com.codandotv.streamplayerapp.feature_list_streams.search.presentation.widgets


import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Cast
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.MicNone
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.codandotv.streamplayerapp.core.shared.ui.R
import com.codandotv.streamplayerapp.core_shared_ui.resources.Colors

@Composable
fun SearchableTopBar(
    isShowingSearchField: Boolean,
    currentSearchText: String,
    onSearchTextChanged: (String) -> Unit,
    onSearchDeactivated: () -> Unit,
    onSearchDispatched: (String) -> Unit,
    onSearchIconPressed: () -> Unit,
    onBackPressed: () -> Unit,
    onCleanTextPressed: () -> Unit
) {
    Column {
        StreamPlayerTopBar(
            onBackPressed = onBackPressed
        )
        SearchTopBar(
            currentSearchText = currentSearchText,
            onSearchTextChanged = onSearchTextChanged,
            onSearchDeactivated = onSearchDeactivated,
            onSearchDispatched = onSearchDispatched,
            onCleanTextPressed = onCleanTextPressed
        )
    }
}

@Composable
private fun StreamPlayerTopBar(
    onBackPressed: () -> Unit
) {
    Row(
        modifier = Modifier
            .height(56.dp)
    ) {
        IconButton(
            modifier = Modifier.fillMaxHeight(),
            onClick = {
                onBackPressed.invoke()
            }
        ) {
            Icon(
                imageVector = Icons.Default.ArrowBack,
                contentDescription = "Back",
                tint = Color.White,
            )
        }
        Spacer(modifier = Modifier.weight(1f))

        IconButton(
            modifier = Modifier.fillMaxHeight(),
            onClick = { /* todo */ }
        ) {
            Icon(
                imageVector = Icons.Default.Cast,
                contentDescription = stringResource(id = R.string.icon_profile),
                tint = Color.White,
            )
        }

        IconButton(
            modifier = Modifier.fillMaxHeight(),
            onClick = { /* todo */ }
        ) {
            Icon(
                modifier = Modifier
                    .height(24.dp)
                    .clip(RoundedCornerShape(4.dp)),
                painter = painterResource(R.drawable.perfil_fake),
                contentDescription = stringResource(id = R.string.icon_profile),
                tint = Color.Unspecified,
            )
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchTopBar(
    currentSearchText: String,
    onSearchTextChanged: (String) -> Unit,
    onSearchDeactivated: () -> Unit,
    onSearchDispatched: (String) -> Unit,
    onCleanTextPressed: () -> Unit
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(56.dp)
            .background(Colors.Gray100)
    ) {
        TextField(
            modifier = Modifier.fillMaxWidth(),
            value = currentSearchText,
            colors = TextFieldDefaults.textFieldColors(
                containerColor = Colors.Gray100,
                focusedIndicatorColor = Color.Transparent,
                disabledIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent,
                cursorColor = Color.Gray
            ),
            onValueChange = {
                onSearchTextChanged(it)
            },
            placeholder = {
                Text(
                    text = "Digite aqui...",
                    color = Color.Gray
                )
            },
            textStyle = TextStyle(
                fontSize = MaterialTheme.typography.bodyMedium.fontSize,
                color = Color.White
            ),
            singleLine = true,
            maxLines = 1,
            leadingIcon = {
                SearchIcon()
            },
            trailingIcon = {
                if (currentSearchText.isEmpty()) MicButton() else CloseButton(
                   action = onCleanTextPressed
                )
            },
            keyboardOptions = KeyboardOptions(imeAction = ImeAction.Search),
            keyboardActions = KeyboardActions(onSearch = { onSearchDispatched(currentSearchText) }),
        )
    }
}

@Composable
fun DefaultIcon(
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
fun SearchIcon(action: () -> Unit = {}) {
    DefaultIcon(
        searchIcon = Icons.Filled.Search,
        contentDescription = "Search Icon",
        onIconClickAction = action,
        iconColor = Color.Gray
    )
}

@Composable
fun CloseButton(action: () -> Unit = {}) {
    DefaultIcon(
        searchIcon = Icons.Default.Close,
        contentDescription = "Deactivate Search Icon",
        onIconClickAction = action,
        iconColor = Color.Gray
    )
}

@Composable
private fun MicButton(action: () -> Unit = {}) {
    DefaultIcon(
        searchIcon = Icons.Default.MicNone,
        contentDescription = "Mic Icon",
        onIconClickAction = action,
        iconColor = Color.Gray
    )
}

@Composable
@Preview
fun SearchBarPreview() {
    StreamPlayerTopBar(
        onBackPressed = {}
    )
}

@Composable
@Preview
fun SearchTopBarEmptyPreview() {
    SearchTopBar(
        currentSearchText = "",
        onSearchTextChanged = {},
        onSearchDeactivated = {},
        onSearchDispatched = {},
        onCleanTextPressed = {}
    )
}

@Composable
@Preview
fun SearchTopBarPreview() {
    SearchTopBar(
        currentSearchText = "Texto de busca",
        onSearchTextChanged = {},
        onSearchDeactivated = {},
        onSearchDispatched = {},
        onCleanTextPressed = {}
    )
}
