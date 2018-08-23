package prithvi.io.mvvmstarter.data.api

import io.reactivex.Single
import prithvi.io.mvvmstarter.data.models.GithubUserResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface Api {

    @GET("/search/users")
    fun getGithubUsers(
            @Query("q") name: String
    ): Single<GithubUserResponse>
}