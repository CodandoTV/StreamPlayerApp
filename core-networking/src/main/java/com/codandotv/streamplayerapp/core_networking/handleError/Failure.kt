package com.codandotv.streamplayerapp.core_networking.handleError

import com.codandotv.streamplayerapp.core.networking.R
import org.koin.core.component.KoinComponent

/**
 * Base Class for handling errors/failures/exceptions.
 */
sealed class Failure(
    val code: Int? = -1,
    val errorMessage: String? = null,
    val errorMessageRes: Int = R.string.core_networking_msg_default_error
) : Exception(), KoinComponent {
    data class NoDataContent(val codeStatus: Int? = null) :
        Failure(codeStatus, errorMessageRes = R.string.core_networking_no_data_content)

    data class ServerError(val codeStatus: Int? = null) :
        Failure(codeStatus, errorMessageRes = R.string.core_networking_no_server_error)

    data class GenericError(
        val codeStatus: Int? = -12, private val msg: String? = null
    ) : Failure(
        codeStatus
    )

    data class NetworkError(
        val codeStatus: Int? = -13, private val throwable: Throwable
    ) : Failure(
        codeStatus, errorMessageRes = R.string.core_networking_networking_error
    )

    data class UnknownError(
        val codeStatus: Int? = null, private val throwable: Throwable? = Exception()
    ) : Failure(
        codeStatus, throwable?.message
    )

    data class UnexpectedApiException(
        val codeStatus: Int? = -14, private val throwable: Throwable? = Exception()
    ) : Failure(
        codeStatus, throwable?.message
    )

    data class ClientException(
        val codeStatus: Int? = -15, private val throwable: Throwable? = Exception()
    ) : Failure(
        codeStatus, throwable?.message
    )

    data class UnparsableResponseException(
        val codeStatus: Int? = -16, private val throwable: Throwable? = Exception()
    ) : Failure(
        codeStatus, throwable?.message
    )
}