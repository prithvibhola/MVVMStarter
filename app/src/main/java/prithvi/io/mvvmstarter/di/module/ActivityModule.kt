package prithvi.io.mvvmstarter.di.module

import dagger.Module
import dagger.android.ContributesAndroidInjector
import prithvi.io.mvvmstarter.MainActivity
import prithvi.io.mvvmstarter.di.ActivityScoped

@Module
abstract class ActivityModule {

    @ActivityScoped
    @ContributesAndroidInjector(modules = [])
    abstract fun mainActivity(): MainActivity

}