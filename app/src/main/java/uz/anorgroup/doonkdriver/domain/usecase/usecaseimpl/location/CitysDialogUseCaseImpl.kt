package uz.anorgroup.doonkdriver.domain.usecase.usecaseimpl.location

import kotlinx.coroutines.flow.Flow
import uz.anorgroup.doonkdriver.data.responce.location.SearchCityResponce
import uz.anorgroup.doonkdriver.domain.repository.LocationRepository
import uz.anorgroup.doonkdriver.domain.usecase.location.CitysDialogUseCase
import javax.inject.Inject

class CitysDialogUseCaseImpl @Inject constructor(private val repository: LocationRepository) : CitysDialogUseCase {
    override fun getCitys(query: String): Flow<Result<SearchCityResponce>> = repository.searchCity(query)
}