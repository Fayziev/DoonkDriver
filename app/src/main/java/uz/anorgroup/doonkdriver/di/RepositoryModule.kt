package uz.anorgroup.doonkdriver.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import uz.anorgroup.doonkdriver.domain.repository.AuthRepository
import uz.anorgroup.doonkdriver.domain.repository.CarRepository
import uz.anorgroup.doonkdriver.domain.repository.LocationRepository
import uz.anorgroup.doonkdriver.domain.repository.repositoryimpl.AuthRepositoryImpl
import uz.anorgroup.doonkdriver.domain.repository.repositoryimpl.CarRepositoryImpl
import uz.anorgroup.doonkdriver.domain.repository.repositoryimpl.LocationRepositoryImpl
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
interface RepositoryModule {

    @Binds
    @Singleton
    fun getAppRepository(impl: AuthRepositoryImpl): AuthRepository

    @Binds
    @Singleton
    fun getCarRepository(impl: CarRepositoryImpl): CarRepository

    @Binds
    @Singleton
    fun getLocationRepository(impl: LocationRepositoryImpl): LocationRepository

}