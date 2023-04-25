package com.codandotv.streamplayerapp.core_networking.handleError

/**
 * Base Class for handling errors/failures/exceptions.
 */
sealed class Failure(val code: Int? = -1, val errorMessage: String) : java.lang.Exception() {
    data class NoDataContent(val codeStatus: Int? = null) : Failure(codeStatus, "No data content")
    data class ServerError(val codeStatus: Int? = null) : Failure(codeStatus, "Server error!")
    data class GenericError(val codeStatus: Int? = -1100,
                            private val msg: String? = null) : Failure(codeStatus, msg
            ?: MSG_DEFAULT)

    data class NetworkError(val codeStatus: Int? = -1200,
                            private val throwable: Throwable) : Failure(codeStatus, "Sem conexão. Verifique o wifi ou dados móveis e tente novamente em alguns instantes.")

    data class UnknownError(val codeStatus: Int? = null,
                            private val throwable: Throwable? = Exception()) : Failure(codeStatus, throwable?.message
            ?: MSG_DEFAULT)

    data class UnexpectedApiException(val codeStatus: Int? = -1011,
                                      private val throwable: Throwable? = Exception()) : Failure(codeStatus, throwable?.message
            ?: MSG_DEFAULT)

    data class ClientException(val codeStatus: Int? = -1011,
                               private val throwable: Throwable? = Exception()) : Failure(codeStatus, throwable?.message
            ?: MSG_DEFAULT)

    data class UnparsableResponseException(val codeStatus: Int? = -1012,
                                           private val throwable: Throwable? = Exception()) : Failure(codeStatus, throwable?.message
            ?: MSG_DEFAULT)

    companion object {
        private const val MSG_DEFAULT = "Não foi possível concluir. Estamos trabalhando para resolver. Tente novamente em alguns instantes."
    }
}