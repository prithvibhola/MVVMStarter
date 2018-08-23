package prithvi.io.mvvmstarter.data.api

import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface Api {

//    https://api.github.com/search/users?q="prithvi"

    @GET("/search/users")
    fun getGithubUsers(
            @Query("q") name: String
    ): Single<>
}