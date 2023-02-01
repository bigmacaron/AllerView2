package kr.kro.fatcats.allerview.di

import android.app.Application
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kr.kro.fatcats.allerview.BuildConfig
import kr.kro.fatcats.allerview.network.AVServer
import kr.kro.fatcats.allerview.network.AVServerApi
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class NetworkModule {

    companion object {
        const val BASE_URL = BuildConfig.BASE_URL

        // DataType
        const val C002 = "C002"
        const val C005 = "C005"
    }

    @Singleton
    @Provides
    fun provideAVServerApi(application: Application): AVServerApi {
        return AVServer(application, BASE_URL).serverApi
    }

}