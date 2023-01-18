package kr.kro.fatcats.allerview.di

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import kr.kro.fatcats.allerview.util.dataStore
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class ContextModule {

    @Singleton
    @Provides
    fun provideDataStore(@ApplicationContext context: Context): DataStore<Preferences> = context.dataStore

}