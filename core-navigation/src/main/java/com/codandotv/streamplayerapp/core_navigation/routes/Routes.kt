package com.codandotv.streamplayerapp.core_navigation.routes

import com.codandotv.streamplayerapp.core_navigation.routes.Routes.PARAM.ID

enum class Routes(
    val route: String,
    val hasBottomBar: Boolean = true,
) {
    Detail(route = "detailsList/", hasBottomBar = false),
    DetailComplete(route = "${Detail.route}{${ID}}", hasBottomBar = false),
    Splash(route = "splash", hasBottomBar = false),
    Home(route = "home"),
    Games(route = "games"),
    News(route = "news"),
    Scenes(route = "scenes"),
    Downloads(route = "downloads");

    companion object {
        fun hasBottomBar(route: String): Boolean {
            return Routes.values().find {
                it.route == route && it.hasBottomBar
            } != null
        }
    }

    object PARAM {
        const val ID = "id"
    }
}
