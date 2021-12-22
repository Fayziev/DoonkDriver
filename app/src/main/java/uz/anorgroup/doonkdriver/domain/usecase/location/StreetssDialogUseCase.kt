package uz.anorgroup.doonkdriver.domain.usecase.location

import kotlinx.coroutines.flow.Flow
import uz.anorgroup.doonkdriver.data.responce.location.SearchStreetsResponce

interface StreetssDialogUseCase {
    fun getStreets(cityName: String, query: String): Flow<Result<SearchStreetsResponce>>
}