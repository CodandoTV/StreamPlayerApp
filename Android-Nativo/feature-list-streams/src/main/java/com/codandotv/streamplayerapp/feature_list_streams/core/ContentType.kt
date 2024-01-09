package com.codandotv.streamplayerapp.feature_list_streams.core

import androidx.annotation.StringRes
import com.codandotv.streamplayerapp.feature.list.streams.R

enum class ContentType(@StringRes val contentName: Int, @StringRes val contentNameAsPlural: Int) {
    SHOW(R.string.list_content_type_show, R.string.list_content_type_show_plural),
    FILM(R.string.list_content_type_film, R.string.list_content_type_film_plural);

    companion object {
        fun getContentName(contentType: ContentType) =
            values().first { contentType == it }.contentName

        fun getContentNameAsPlural(contentType: ContentType) =
            values().first { contentType == it }.contentNameAsPlural
    }
}