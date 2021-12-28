package uz.anorgroup.doonkdriver.presentation.viewmodel.impl.car

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import uz.anorgroup.doonkdriver.presentation.viewmodel.car.CreateOrderViewModel
import uz.anorgroup.doonkdriver.utils.eventValueFlow
import javax.inject.Inject

@HiltViewModel
class CreateOrderViewModelImpl @Inject constructor() : ViewModel(), CreateOrderViewModel {
    override val openScreenLiveData = MutableLiveData<Unit>()
    override val whereCityFlow = eventValueFlow<String>()
    override val whereStreetFlow = eventValueFlow<String>()
    override val directionCityFlow = eventValueFlow<String>()
    override val directionStreetFlow = eventValueFlow<String>()
    override fun whereCity(city: String) {
        viewModelScope.launch {
            whereCityFlow.emit(city)
        }
    }

    override fun whereStreet(street: String) {
        viewModelScope.launch {
            whereStreetFlow.emit(street)
        }
    }

    override fun directionCity(city: String) {
        viewModelScope.launch {
            directionCityFlow.emit(city)
        }
    }

    override fun directionStreet(street: String) {
        viewModelScope.launch {
            directionStreetFlow.emit(street)
        }
    }

    override fun openScreen() {
        openScreenLiveData.value = Unit
    }
}