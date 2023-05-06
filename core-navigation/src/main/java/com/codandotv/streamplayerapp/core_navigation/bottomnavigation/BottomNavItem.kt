package com.codandotv.streamplayerapp.core_navigation.bottomnavigation

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import com.codandotv.streamplayerapp.core_navigation.R
import com.codandotv.streamplayerapp.core_navigation.routes.BottomNavRoutes

sealed class BottomNavItem(
    @StringRes var title: Int,
    @DrawableRes var iconUnselected: Int,
    @DrawableRes var iconSelected: Int,
    var screenRoute: String
) {
    object Home :
        BottomNavItem(
            R.string.bottom_nav_home,
            R.drawable.ic_home_unselected,
            R.drawable.ic_home_selected,
            BottomNavRoutes.HOME
        )

    object Games :
        BottomNavItem(
            R.string.bottom_nav_games,
            R.drawable.ic_games_unselected,
            R.drawable.ic_games_selected,
            BottomNavRoutes.GAMES
        )

    object News :
        BottomNavItem(
            R.string.bottom_nav_news,
            R.drawable.ic_news_unselected,
            R.drawable.ic_news_selected,
            BottomNavRoutes.NEWS
        )

    object Downloads : BottomNavItem(
        R.string.bottom_nav_downloads,
        R.drawable.ic_downloads_unselected,
        R.drawable.ic_downloads_selected,
        BottomNavRoutes.DOWNLOADS
    )
}