package uz.anorgroup.doonkdriver.presentation.viewmodel.location

import kotlinx.coroutines.flow.Flow
import uz.anorgroup.doonkdriver.data.responce.location.SearchCityResponce

interface CitysViewModels {
    val errorFlow: Flow<String>
    val progressFlow: Flow<Boolean>
    val successFlow: Flow<SearchCityResponce>
    val openVerifyFlow: Flow<Unit>

    fun getCitys()

}