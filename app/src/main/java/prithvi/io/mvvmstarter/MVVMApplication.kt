package prithvi.io.mvvmstarter

import dagger.android.AndroidInjector
import dagger.android.DaggerApplication
import prithvi.io.mvvmstarter.di.component.DaggerAppComponent

class MVVMApplication : DaggerApplication() {

    override fun applicationInjector(): AndroidInjector<out DaggerApplication> {
        val appComponent = DaggerAppComponent.builder().application(this).build()
        appComponent.inject(this)
        return appComponent
    }
}
