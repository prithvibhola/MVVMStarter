package prithvi.io.mvvmstarter.ui.base

import android.arch.lifecycle.ViewModel
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import prithvi.io.mvvmstarter.utility.extentions.addTo

abstract class BaseViewModel : ViewModel(), HasDisposableManager {

    private var compositeDisposable = CompositeDisposable()

    override fun getCompositeDisposable(): CompositeDisposable {
        if (compositeDisposable.isDisposed)
            compositeDisposable = CompositeDisposable()
        return compositeDisposable
    }

    override fun addDisposable(disposable: Disposable) {
        disposable.addTo(compositeDisposable)
    }

    override fun dispose() {
        getCompositeDisposable().dispose()
    }

    override fun onCleared() {
        dispose()
        super.onCleared()
    }
}