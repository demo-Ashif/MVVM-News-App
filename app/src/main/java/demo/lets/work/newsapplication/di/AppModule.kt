package demo.lets.work.newsapplication.di

import android.content.Context
import demo.lets.work.newsapplication.domain.repository.NewsRepository
import demo.lets.work.newsapplication.data.repository.NewsRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import demo.lets.work.newsapplication.core.common.DispatcherProvider
import demo.lets.work.newsapplication.core.utils.ConnectivityObserver
import demo.lets.work.newsapplication.core.utils.NetworkConnectivityObserver
import demo.lets.work.newsapplication.data.local.NewsDao
import demo.lets.work.newsapplication.data.remote.api.NewsApi
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideNewsRepository(api: NewsApi, newsDao: NewsDao): NewsRepository =
        NewsRepositoryImpl(api, newsDao)

    @Provides
    @Singleton
    fun provideDispatchers(): DispatcherProvider = object : DispatcherProvider {
        override val main: CoroutineDispatcher
            get() = Dispatchers.Main
        override val io: CoroutineDispatcher
            get() = Dispatchers.IO
    }

    @Provides
    @Singleton
    fun provideNetworkObserver(@ApplicationContext context: Context): ConnectivityObserver =
        NetworkConnectivityObserver(context)
}