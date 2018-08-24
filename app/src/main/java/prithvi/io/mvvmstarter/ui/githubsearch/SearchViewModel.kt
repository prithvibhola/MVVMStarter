package prithvi.io.mvvmstarter.ui.githubsearch

import android.arch.lifecycle.MutableLiveData
import io.reactivex.rxkotlin.subscribeBy
import prithvi.io.mvvmstarter.data.models.GithubUser
import prithvi.io.mvvmstarter.data.models.Response
import prithvi.io.mvvmstarter.data.repository.Repository
import prithvi.io.mvvmstarter.ui.base.BaseViewModel
import prithvi.io.mvvmstarter.utility.extentions.addTo
import prithvi.io.mvvmstarter.utility.extentions.fromWorkerToMain
import prithvi.io.mvvmstarter.utility.rx.Scheduler
import timber.log.Timber
import javax.inject.Inject

class SearchViewModel @Inject constructor(
        private val repository: Repository,
        private val scheduler: Scheduler
) : BaseViewModel() {

    var githubUser: MutableLiveData<Response<List<GithubUser>>> = MutableLiveData()

    fun getGithubUsers(name: String) {
        githubUser.value = Response.loading()
        repository.github.getGithubUsers(name)
                .fromWorkerToMain(scheduler)
                .subscribeBy(
                        onNext = {
                            githubUser.value = Response.success(it)
                        },
                        onError = {
                            githubUser.value = Response.error(it)
                            Timber.e(it, "Error in getting Github users")
                        }
                )
                .addTo(getCompositeDisposable())
    }
}