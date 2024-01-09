object Keys {
    private const val default_tmdb_token = "eyJhbGciOiJIUzI1NiJ9.eyJhdWQiOiJiNDg2NWM4YTAzNzhmM2I4NjI0OWU1ZjNiYWFiMjU2NyIsInN1YiI6IjY0Mjk4YTg5YTNlNGJhMWM0NDgzM2U4OCIsInNjb3BlcyI6WyJhcGlfcmVhZCJdLCJ2ZXJzaW9uIjoxfQ.9cIxv29vkaZ2yW88DIFRUFK_nXbK2b6KS8t96kA8WAE"
    private const val tmdb_token_name_debug = "TMDB_BEARER_TOKEN_DEBUG"
    private const val tmdb_token_name_release = "TMDB_BEARER_TOKEN_RELEASE"

    object BuildField {
        val api_bearer_debug =
            "\"Bearer ${System.getenv(tmdb_token_name_debug) ?: default_tmdb_token}\""
        val api_bearer_release =
            "\"Bearer ${System.getenv(tmdb_token_name_release) ?: default_tmdb_token}\""
    }
}