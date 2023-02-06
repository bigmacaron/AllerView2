package kr.kro.fatcats.allerview.network

import android.content.Context
import android.net.ConnectivityManager
import android.net.Network
import android.net.NetworkCapabilities
import android.os.Build
import androidx.viewbinding.BuildConfig
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import kr.kro.fatcats.allerview.network.exception.AVNetworkException
import kr.kro.fatcats.allerview.util.LogUtil
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Response
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class AVServer(
    context: Context,
    baseUrl: String?
) {

    val serverApi: AVServerApi

    init {
        serverApi = Retrofit.Builder()
            .baseUrl(baseUrl)
            .client(provideOkHttpClient(context))
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(CoroutineCallAdapterFactory())
            .build()
            .create(AVServerApi::class.java)
    }

    private fun provideOkHttpClient(context: Context): OkHttpClient {
        val httpLoggingInterceptor = HttpLoggingInterceptor()
        httpLoggingInterceptor.level = if (BuildConfig.DEBUG)
            HttpLoggingInterceptor.Level.BASIC
        else
            HttpLoggingInterceptor.Level.NONE
        return OkHttpClient.Builder()
            .addInterceptor(ConnectivityInterceptor(context))
            .addInterceptor(httpLoggingInterceptor)
//            .addInterceptor(AVErrorInterceptor())
            .build()
    }

    /**
     * 네트워크 상태 확인
     */
    class ConnectivityInterceptor(
        context: Context
    ) : ConnectivityManager.NetworkCallback(), Interceptor {
        private var isOnline = true

        init {
            val connectivityManager = context.getSystemService(ConnectivityManager::class.java)
            val currentNetwork = connectivityManager.activeNetwork
            try {
                connectivityManager.getNetworkCapabilities(currentNetwork)?.let {
                    isOnline = isOnline(it)
                }
            } catch (e: Exception) {
                LogUtil.e(LogUtil.DEBUG_LEVEL_2, e.message!!)
            }
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                connectivityManager.registerDefaultNetworkCallback(this)
            }
        }

        override fun intercept(chain: Interceptor.Chain): Response {
            return if (isOnline) {
                chain.run {
                    proceed(request())
                }
            } else {
                throw AVNetworkException()
            }
        }

        override fun onCapabilitiesChanged(
            network: Network,
            networkCapabilities: NetworkCapabilities
        ) {
            super.onCapabilitiesChanged(network, networkCapabilities)
            isOnline = isOnline(networkCapabilities)
        }

        private fun isOnline(caps: NetworkCapabilities): Boolean {
            return caps.hasCapability(NetworkCapabilities.NET_CAPABILITY_VALIDATED)
                    && caps.hasCapability(NetworkCapabilities.NET_CAPABILITY_INTERNET)
        }
    }

}
