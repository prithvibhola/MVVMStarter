package prithvi.io.mvvmstarter.di.module

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap
import prithvi.io.mvvmstarter.di.ViewModelKey
import prithvi.io.mvvmstarter.ui.githubsearch.SearchViewModel
import prithvi.io.mvvmstarter.viewmodel.ViewModelFactory

@Module
abstract class ViewModelModule {

    @Binds
    internal abstract fun bindViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory

    @Binds
    @IntoMap
    @ViewModelKey(SearchViewModel::class)
    internal abstract fun searchViewModel(viewModel: SearchViewModel): ViewModel
}