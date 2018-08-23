package prithvi.io.mvvmstarter.di.module

import android.app.Application
import android.content.Context
import dagger.Binds
import dagger.Module
import dagger.Provides
import prithvi.io.mvvmstarter.utility.rx.AppScheduler
import javax.inject.Singleton

@Module
abstract class AppModule {

    @Binds
    @Provides
    abstract fun provideContext(application: Application): Context

    @Provides
    @Singleton
    fun provideScheduler() = AppScheduler()

}