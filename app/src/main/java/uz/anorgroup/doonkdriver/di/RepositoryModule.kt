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

@Module
@InstallIn(SingletonComponent::class)
interface RepositoryModule {

    @Binds
    fun getAppRepository(impl: AuthRepositoryImpl): AuthRepository

    @Binds
    fun getCarRepository(impl: CarRepositoryImpl): CarRepository

    @Binds
    fun getLocationRepository(impl: LocationRepositoryImpl): LocationRepository

}