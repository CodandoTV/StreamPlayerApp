package com.codandotv.streamplayerapp.feature_list_streams.list.presentation.widgets

import androidx.annotation.StringRes
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.codandotv.streamplayerapp.core_shared_ui.theme.ThemePreviews
import com.codandotv.streamplayerapp.feature.list.streams.R
import com.codandotv.streamplayerapp.feature_list_streams.core.ContentType
import com.codandotv.streamplayerapp.feature_list_streams.list.domain.model.HighlightBanner
import com.codandotv.streamplayerapp.feature_list_streams.list.domain.model.IconAndTextInfo
import com.codandotv.streamplayerapp.core.shared.ui.R as RSharedUI

@Composable
fun HighlightBanner(modifier: Modifier = Modifier, data: HighlightBanner?) {
    data ?: return

    Box(
        modifier = modifier
            .fillMaxWidth()
            .height(500.dp)
    ) {
        ContentImage(imageUrl = data.imageUrl)
        BackgroundGradient()
        Column(
            modifier = Modifier
                .align(Alignment.BottomEnd)
                .padding(bottom = 16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(modifier = Modifier.weight(1F))
            ContentType(contentType = data.contentType)
            ContentName(name = data.name)
            ContentRanking(
                extraInfo = data.extraInfo,
                contentTypeAsPlural = data.contentTypeAsPlural
            )
            ActionButtons(Modifier.weight(0.3F), data)
        }
    }
}

@Composable
fun ContentImage(modifier: Modifier = Modifier, imageUrl: String) {
    AsyncImage(
        modifier = modifier.fillMaxSize(),
        model = imageUrl,
        contentScale = ContentScale.Crop,
        contentDescription = stringResource(id = R.string.list_highligh_banner_content)
    )
}

@Composable
fun BackgroundGradient(modifier: Modifier = Modifier) {
    Box(
        modifier = modifier
            .fillMaxSize()
            .background(
                brush = Brush.verticalGradient(
                    colors = listOf(
                        Color.Transparent,
                        Color.Black
                    ),
                )
            )
    )
}

@Composable
fun ContentName(modifier: Modifier = Modifier, name: String) {
    Text(
        text = name,
        fontSize = 24.sp,
        modifier = modifier
            .padding(horizontal = 50.dp)
            .padding(vertical = 4.dp),
        textAlign = TextAlign.Center,
        color = MaterialTheme.colorScheme.onBackground
    )
}

@Composable
fun ContentRanking(
    modifier: Modifier = Modifier,
    extraInfo: IconAndTextInfo,
    @StringRes contentTypeAsPlural: Int
) {
    Row(modifier = modifier, verticalAlignment = Alignment.CenterVertically) {
        Icon(
            painter = painterResource(id = extraInfo.icon),
            contentDescription = stringResource(id = R.string.list_icon_highligh_banner_ranking),
            modifier = Modifier.size(24.dp),
            tint = Color.Unspecified
        )
        Text(
            text = stringResource(
                id = extraInfo.text,
                stringResource(contentTypeAsPlural).lowercase()
            ),
            Modifier.padding(start = 4.dp),
            style = TextStyle(fontWeight = FontWeight.Bold),
            fontSize = 14.sp,
            color = MaterialTheme.colorScheme.onBackground
        )
    }
}

@Composable
fun ContentType(modifier: Modifier = Modifier, @StringRes contentType: Int) {
    Row(modifier = modifier, verticalAlignment = Alignment.CenterVertically) {
        Icon(
            painter = painterResource(id = RSharedUI.drawable.ic_netflix),
            contentDescription = stringResource(id = RSharedUI.string.icon_netflix),
            modifier = Modifier.size(16.dp),
            tint = Color.Unspecified
        )
        Text(
            text = stringResource(contentType).uppercase(),
            Modifier.padding(start = 4.dp),
            fontSize = 12.sp,
            color = MaterialTheme.colorScheme.onBackground,
            letterSpacing = 4.sp
        )
    }

}

@Composable
fun ActionButtons(modifier: Modifier, data: HighlightBanner) {
    Row(verticalAlignment = Alignment.CenterVertically, modifier = modifier) {
        AddToListButton(
            modifier = Modifier
                .weight(1F)
                .fillMaxSize(), data
        ) {
            /* todo */
        }
        PlayButton {
            /* todo */
        }
        InfoButton(
            modifier = Modifier
                .weight(1F)
                .fillMaxSize(), data
        ) {
            /* todo */
        }
    }
}

@Composable
fun AddToListButton(
    modifier: Modifier = Modifier,
    data: HighlightBanner,
    onClick: () -> Unit
) {
    IconButton(onClick = { onClick.invoke() }, modifier = modifier) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Icon(
                painter = painterResource(id = RSharedUI.drawable.ic_add),
                contentDescription = stringResource(id = R.string.list_icon_add),
                tint = Color.White,
            )
            Text(
                fontSize = 10.sp,
                text = stringResource(id = data.leftButton.text),
                color = Color.White,
            )
        }
    }
}

@Composable
fun InfoButton(
    modifier: Modifier = Modifier,
    data: HighlightBanner,
    onClick: () -> Unit
) {
    IconButton(onClick = { onClick.invoke() }, modifier = modifier) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Icon(
                painter = painterResource(id = RSharedUI.drawable.ic_info),
                contentDescription = stringResource(id = R.string.list_icon_info),
                tint = Color.White
            )
            Text(
                text = stringResource(id = data.rightButton.text),
                fontSize = 10.sp,
                color = Color.White
            )
        }
    }
}

@Composable
fun PlayButton(
    modifier: Modifier = Modifier,
    onClick: () -> Unit
) {
    Button(
        onClick = { onClick.invoke() },
        colors = ButtonDefaults.buttonColors(containerColor = Color.White),
        shape = RoundedCornerShape(4.dp),
        contentPadding = PaddingValues(horizontal = 8.dp),
        modifier = modifier
            .padding(16.dp)
            .defaultMinSize(
                minWidth = 28.dp,
                minHeight = 28.dp
            )
    ) {
        Icon(
            painter = painterResource(RSharedUI.drawable.ic_play),
            contentDescription = stringResource(id = R.string.list_icon_play),
            tint = Color.Black,
            modifier = Modifier.padding(vertical = 8.dp)
        )
        Spacer(modifier = Modifier.width(4.dp))
        Text(
            text = stringResource(id = R.string.list_highlight_banner_watch),
            color = Color.Black,
            fontSize = 14.sp,
            modifier = Modifier
                .padding(bottom = 2.dp)
                .padding(end = 8.dp)
        )
    }
}

@ThemePreviews
@Composable
fun HighlightBannerPreview() {
    HighlightBanner(
        data = HighlightBanner(
            name = stringResource(id = RSharedUI.string.app_name),
            imageUrl = String(),
            contentType = ContentType.getContentName(ContentType.SHOW),
            contentTypeAsPlural = ContentType.getContentNameAsPlural(ContentType.SHOW),
            extraInfo = IconAndTextInfo(
                R.drawable.ic_top_10,
                ContentType.getContentName(ContentType.SHOW)
            ),
            leftButton = IconAndTextInfo(RSharedUI.drawable.ic_add, R.string.list_highlight_banner_add),
            centralButton = IconAndTextInfo(RSharedUI.drawable.ic_play, R.string.list_highlight_banner_watch),
            rightButton = IconAndTextInfo(RSharedUI.drawable.ic_info, R.string.list_highlight_banner_info),
        )
    )
}
