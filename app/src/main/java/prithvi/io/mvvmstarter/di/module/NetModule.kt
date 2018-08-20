package prithvi.io.mvvmstarter.di.module

import android.app.Application
import com.squareup.moshi.KotlinJsonAdapterFactory
import com.squareup.moshi.Moshi
import dagger.Module
import dagger.Provides
import okhttp3.Cache
import okhttp3.OkHttpClient
import prithvi.io.mvvmstarter.data.api.Api
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
abstract class NetModule {

    @Provides
    @Singleton
    fun provideOkHttpCache(application: Application): Cache = Cache(application.cacheDir, (10 * 1024 * 1024).toLong())

    @Provides
    @Singleton
    fun provideMoshi(): Moshi = Moshi.Builder().add(KotlinJsonAdapterFactory()).build()

    @Provides
    @Singleton
    fun provideNoAuthOkHttpClient(okHttpClientBuilder: OkHttpClient.Builder): OkHttpClient = okHttpClientBuilder.build()

    @Provides
    @Singleton
    fun provideApi(retrofit: Retrofit) = retrofit.create(Api::class.java)

}