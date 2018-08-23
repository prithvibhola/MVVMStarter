package prithvi.io.mvvmstarter.data.models

import com.squareup.moshi.Json

data class GithubUserResponse(
        @Json(name = "total_count") val total_count: Int,
        @Json(name = "incomplete_results") val incomplete_results: Boolean,
        @Json(name = "items") val items: List<GithubUser>
)