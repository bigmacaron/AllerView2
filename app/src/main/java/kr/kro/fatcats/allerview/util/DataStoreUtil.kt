package kr.kro.fatcats.allerview.util

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.emptyPreferences
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.CancellationException
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.firstOrNull
import java.io.IOException


val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "dataStore")

suspend fun <T> Preferences.Key<T>.findCurrentValue(
    dataStore: DataStore<Preferences>
) : T? {
    return dataStore.data.catch { e ->
        LogUtil.e(LogUtil.DEBUG_LEVEL_2, e.message ?: "")
        if (e is IOException) {
            emit(emptyPreferences())
        } else {
            throw CancellationException(e.message, e)
        }
    }.firstOrNull()?.get(this)
}

class DataStoreUtil {
    object Keys {
        val USER_PROFILE = stringPreferencesKey("sample")
    }
}