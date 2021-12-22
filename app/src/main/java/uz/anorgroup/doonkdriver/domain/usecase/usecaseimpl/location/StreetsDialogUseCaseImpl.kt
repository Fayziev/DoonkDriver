package uz.anorgroup.doonkdriver.domain.usecase.usecaseimpl.location

import kotlinx.coroutines.flow.Flow
import uz.anorgroup.doonkdriver.data.responce.location.SearchStreetsResponce
import uz.anorgroup.doonkdriver.domain.repository.LocationRepository
import uz.anorgroup.doonkdriver.domain.usecase.location.StreetssDialogUseCase
import javax.inject.Inject

class StreetsDialogUseCaseImpl @Inject constructor(private val repository: LocationRepository) : StreetssDialogUseCase {
    override fun getStreets(cityName: String, query: String): Flow<Result<SearchStreetsResponce>> = repository.searchStreet(cityName, query)
}