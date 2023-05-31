package com.codandotv.streamplayerapp.core_shared.qualifier

import org.koin.core.qualifier.Qualifier
import org.koin.core.qualifier.QualifierValue

object QualifierDispatcherIO : Qualifier {
    override val value: QualifierValue
        get() = "dispatcherIO"
}
