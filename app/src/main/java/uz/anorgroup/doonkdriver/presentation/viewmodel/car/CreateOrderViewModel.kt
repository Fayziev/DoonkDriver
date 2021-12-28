package uz.anorgroup.doonkdriver.presentation.viewmodel.car

import androidx.lifecycle.LiveData
import kotlinx.coroutines.flow.Flow

interface CreateOrderViewModel {
    val whereCityFlow: Flow<String>
    val whereStreetFlow: Flow<String>
    val directionCityFlow: Flow<String>
    val directionStreetFlow: Flow<String>
    val openScreenLiveData:LiveData<Unit>

    fun whereCity(city: String)
    fun whereStreet(street: String)
    fun directionCity(city: String)
    fun directionStreet(street: String)
    fun openScreen()
}