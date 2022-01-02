package uz.anorgroup.doonkdriver.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import uz.anorgroup.doonkdriver.data.api.AuthApi
import uz.anorgroup.doonkdriver.data.api.CarApi
import uz.anorgroup.doonkdriver.data.api.LocationApi
import uz.anorgroup.doonkdriver.data.api.MapApi
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class ApiModule {

    @[Provides Singleton]
    fun getAuthApi(retrofit: Retrofit): AuthApi = retrofit.create(AuthApi::class.java)

    @[Provides Singleton]
    fun getCarApi(retrofit: Retrofit): CarApi = retrofit.create(CarApi::class.java)

    @[Provides Singleton]
    fun getLocationApi(retrofit: Retrofit): LocationApi = retrofit.create(LocationApi::class.java)

    @[Provides Singleton]
    fun getMapApi(retrofit: Retrofit): MapApi = retrofit.create(MapApi::class.java)

}