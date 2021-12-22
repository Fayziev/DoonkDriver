package uz.anorgroup.doonkdriver.domain.usecase.location

import kotlinx.coroutines.flow.Flow
import uz.anorgroup.doonkdriver.data.responce.location.SearchCityResponce

interface CitysDialogUseCase {
    fun getCitys(query: String): Flow<Result<SearchCityResponce>>
}