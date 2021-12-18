package uz.anorgroup.doonkdriver.di

import android.content.Context
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import uz.anorgroup.doonkdriver.BuildConfig.BASE_URL
import uz.anorgroup.doonkdriver.data.pref.MyPref
import uz.anorgroup.doonkdriver.utils.addHeaderInterceptor
import uz.anorgroup.doonkdriver.utils.addLoggingInterceptor
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class NetworkModule {

    @[Provides Singleton]
    fun getRetrofit(client: OkHttpClient): Retrofit =
        Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .client(client)
            .baseUrl(BASE_URL)
            .build()


    @[Provides Singleton]
    fun getOkHttpClient(pref: MyPref, @ApplicationContext context: Context): OkHttpClient =
        OkHttpClient.Builder()
            .addLoggingInterceptor(context)
            .readTimeout(60, TimeUnit.SECONDS)
            .writeTimeout(60, TimeUnit.SECONDS)
            .addInterceptor(addHeaderInterceptor(pref))
            .build()
}