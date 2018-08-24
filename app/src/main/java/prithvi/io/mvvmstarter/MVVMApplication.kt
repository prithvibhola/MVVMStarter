package prithvi.io.mvvmstarter

import dagger.android.AndroidInjector
import dagger.android.DaggerApplication
import prithvi.io.mvvmstarter.di.component.DaggerAppComponent
import prithvi.io.mvvmstarter.utility.logging.CrashReportingTree
import timber.log.Timber

class MVVMApplication : DaggerApplication() {

    override fun onCreate() {
        super.onCreate()

        Timber.plant(if (BuildConfig.DEBUG) Timber.DebugTree() else CrashReportingTree())
    }

    override fun applicationInjector(): AndroidInjector<out DaggerApplication> {
        val appComponent = DaggerAppComponent.builder().application(this).build()
        appComponent.inject(this)
        return appComponent
    }
}
