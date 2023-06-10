object Config {
    const val applicationId = "com.codandotv.streamplayerapp"
    const val buildToolsVersion = "33.0.1"
    const val compileSdkVersion = 33
    const val minSdkVersion = 24
    const val targetSdkVersion = 33
    const val versionName = "1.0"
    const val versionCode = 1
    const val packageName = "com.codandotv.streamplayerapp."

    object BuildField {
        const val host_debug = "\"https://api.themoviedb.org/3/\""
        const val host_release = "\"https://api.themoviedb.org/3/\""

        private const val tmdb_env_name_debug = "TMDB_API_TOKEN_DEBUG"
        private const val tmdb_env_name_release = "TMDB_API_TOKEN_RELEASE"
        val api_auth_debug: String by lazy { "\"Bearer ${System.getenv(tmdb_env_name_debug) ?: ""}\"" }
        val api_auth_release: String by lazy { "\"Bearer ${System.getenv(tmdb_env_name_release) ?: ""}\"" }
    }
}