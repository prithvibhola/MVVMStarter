package prithvi.io.mvvmstarter.di.module

import dagger.Module
import dagger.android.ContributesAndroidInjector
import prithvi.io.mvvmstarter.ui.githubsearch.SearchActivity
import prithvi.io.mvvmstarter.di.ActivityScoped

@Module
abstract class ActivityModule {

    @ActivityScoped
    @ContributesAndroidInjector(modules = [])
    abstract fun mainActivity(): SearchActivity

}