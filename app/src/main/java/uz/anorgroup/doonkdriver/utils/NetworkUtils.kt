package uz.anorgroup.doonkdriver.utils

import android.content.Context
import com.readystatesoftware.chuck.ChuckInterceptor
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import timber.log.Timber
import uz.anorgroup.doonkdriver.BuildConfig
import uz.anorgroup.doonkdriver.data.pref.MyPref

fun OkHttpClient.Builder.addLoggingInterceptor(context: Context): OkHttpClient.Builder {
    HttpLoggingInterceptor.Level.HEADERS
    val logging = HttpLoggingInterceptor.Logger { message -> Timber.tag("HTTP").d(message) }
    if (BuildConfig.LOGGING) {
        addInterceptor(ChuckInterceptor(context))
            .addInterceptor(HttpLoggingInterceptor(logging))
    }
    return this
}

fun addHeaderInterceptor(pref: MyPref) = Interceptor { chain ->
    val request = chain.request()
    val newRequest = request.newBuilder().removeHeader("token").addHeader("token", pref.accessToken).build()
    val response = chain.proceed(newRequest)
    response
}

