package prithvi.io.mvvmstarter.data.models

import com.squareup.moshi.Json
import prithvi.io.mvvmstarter.utility.Identifiable

data class Response<out T>(
        val status: Status,
        val data: T? = null,
        val error: Throwable? = null
) {
    enum class Status { LOADING, SUCCESS, ERROR }

    companion object {
        fun <T> loading(): Response<T> = Response(Status.LOADING, null, null)
        fun <T> success(data: T? = null): Response<T> = Response(Status.SUCCESS, data, null)
        fun <T> error(error: Throwable): Response<T> = Response(Status.ERROR, null, error)
    }
}

data class GithubUser(
        @Json(name = "login") val login: String,
        @Json(name = "id") val id: Long,
        @Json(name = "node_id") val nodeId: String,
        @Json(name = "avatar_url") val avatarUrl: String,
        @Json(name = "url") val url: String,
        @Json(name = "html_url") val htmlUrl: String,
        @Json(name = "followers_url") val followersUrl: String,
        @Json(name = "following_url") val followingUrl: String,
        @Json(name = "gists_url") val gistsUrl: String,
        @Json(name = "starred_url") val starredUrl: String,
        @Json(name = "subscriptions_url") val subscriptionsUrl: String,
        @Json(name = "organizations_url") val organizationsUrl: String,
        @Json(name = "repos_url") val reposUrl: String,
        @Json(name = "events_url") val eventsUrl: String,
        @Json(name = "received_events_url") val receivedEventsUrl: String,
        @Json(name = "type") val type: String,
        @Json(name = "score") val score: Float
) : Identifiable {
    override val identifier: Long get() = id
}

data class EditTextFlow(
        val query: String,
        val type: Type
) {
    enum class Type { BEFORE, AFTER, ON }
}