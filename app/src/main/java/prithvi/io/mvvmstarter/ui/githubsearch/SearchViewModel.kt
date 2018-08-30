package prithvi.io.mvvmstarter.ui.githubsearch

import android.arch.lifecycle.MutableLiveData
import io.reactivex.Flowable
import io.reactivex.processors.PublishProcessor
import io.reactivex.rxkotlin.subscribeBy
import prithvi.io.mvvmstarter.data.models.EditTextFlow
import prithvi.io.mvvmstarter.data.models.GithubUser
import prithvi.io.mvvmstarter.data.models.Response
import prithvi.io.mvvmstarter.data.repository.Repository
import prithvi.io.mvvmstarter.ui.base.BaseViewModel
import prithvi.io.mvvmstarter.utility.extentions.addTo
import prithvi.io.mvvmstarter.utility.extentions.fromWorkerToMain
import prithvi.io.mvvmstarter.utility.rx.Scheduler
import timber.log.Timber
import java.util.concurrent.TimeUnit
import javax.inject.Inject

class SearchViewModel @Inject constructor(
        private val repository: Repository,
        private val scheduler: Scheduler
) : BaseViewModel() {

    private val searchProcessor = PublishProcessor.create<String>()

    var githubUser: MutableLiveData<Response<List<GithubUser>>> = MutableLiveData()

    fun getGithubUsers(name: String) {
        githubUser.value = Response.loading()
        if (!searchProcessor.hasSubscribers()) searchGithubUsers()
        searchProcessor.onNext(name)
    }

    private fun searchGithubUsers() {
        searchProcessor
                .debounce(500, TimeUnit.MILLISECONDS)
                .switchMap { repository.github.getGithubUsers(it) }
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

    /*
     * Alternative way to convert EditText text change into stream.
     * With text stream api call can be made.
     * UiModel is also required for this approach incase api return an error.
     * onError will never be called in this way.
     */
    fun getGithubUsers(queryFlow: Flowable<EditTextFlow>) {
        queryFlow.filter { it.type == EditTextFlow.Type.AFTER }
                .map { it.query }
                .filter { it.length > 1 }
                .distinctUntilChanged()
                .doOnEach { githubUser.postValue(Response.loading()) }
                .debounce(500, TimeUnit.MILLISECONDS)
                .switchMap { repository.github.getGithubUsers(it).onErrorResumeNext(Flowable.just(listOf())) }
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