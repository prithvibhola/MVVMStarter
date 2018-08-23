package prithvi.io.mvvmstarter.data.repository

import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class Repository @Inject constructor(
        val github: GithubRepository
)
