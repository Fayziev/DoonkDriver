package uz.anorgroup.doonkdriver.presentation.viewmodel.location

import kotlinx.coroutines.flow.Flow
import uz.anorgroup.doonkdriver.data.responce.location.SearchStreetsResponce

interface StreetsViewModels {
    val errorFlow: Flow<String>
    val progressFlow: Flow<Boolean>
    val successFlow: Flow<SearchStreetsResponce>
    val openVerifyFlow: Flow<Unit>

    fun getCitys(city: String, query: String)

}