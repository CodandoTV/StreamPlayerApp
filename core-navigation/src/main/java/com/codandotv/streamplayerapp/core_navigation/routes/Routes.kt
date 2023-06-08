package com.codandotv.streamplayerapp.core_navigation.routes

import com.codandotv.streamplayerapp.core_navigation.routes.Routes.PARAM.ID

object Routes {
    const val DETAIL = "DetailList/"
    const val DETAIL_COMPLETE = "${DETAIL}{${ID}}"
    const val Splash = "splash"
    const val SHARING = "sharing"

    object PARAM {
        const val ID = "id"
    }
}