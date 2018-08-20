package prithvi.io.mvvmstarter.di.module

import android.arch.lifecycle.ViewModelProvider
import dagger.Binds
import dagger.Module
import prithvi.io.mvvmstarter.viewmodel.ViewModelFactory

@Module
abstract class ViewModelModule {

    @Binds
    internal abstract fun bindViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory
}