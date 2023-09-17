package com.codandotv.streamplayerapp.core_navigation.extensions

import androidx.navigation.NavController

fun NavController.goBack() = this.navigateUp()
