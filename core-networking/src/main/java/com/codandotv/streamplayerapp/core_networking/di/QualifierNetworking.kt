package com.codandotv.streamplayerapp.core_networking.di

import org.koin.core.qualifier.Qualifier
import org.koin.core.qualifier.QualifierValue

object QualifierHost : Qualifier {
    override val value: QualifierValue
        get() = "QualifierHost"
}

object QualifierProfile : Qualifier {
    override val value: QualifierValue
        get() = "QualifierProfile"
}

object QualifierProfileRetrofit : Qualifier {
    override val value: QualifierValue
        get() = "QualifierProfileRetrofit"
}


object QualifierLoggerInterceptor : Qualifier {
    override val value: QualifierValue
        get() = "QualifierLoggerInterceptor"
}

object QualifierAuthInterceptor : Qualifier {
    override val value: QualifierValue
        get() = "QualifierAuthInterceptor"
}

