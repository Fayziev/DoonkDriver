package uz.anorgroup.doonkdriver.presentation.viewmodel.impl.car

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import uz.anorgroup.doonkdriver.data.request.car.CreateCarRequest2
import uz.anorgroup.doonkdriver.data.responce.car.CreateCarResponce
import uz.anorgroup.doonkdriver.domain.usecase.car.CarCreateUseCase
import uz.anorgroup.doonkdriver.presentation.viewmodel.car.CarCreateViewModel
import uz.anorgroup.doonkdriver.utils.eventValueFlow
import uz.anorgroup.doonkdriver.utils.isConnected
import javax.inject.Inject

@HiltViewModel
class CarCreateViewModelImpl @Inject constructor(
    private val useCase: CarCreateUseCase
) : ViewModel(), CarCreateViewModel {

    override val deleteItemFlow = eventValueFlow<Int>()
    override val setYearOfIssueFlow = eventValueFlow<String>()
    override val setColorFlow = eventValueFlow<String>()
    override val setNumberFlow = eventValueFlow<String>()
    override val errorFlow = eventValueFlow<String>()
    override val progressFlow = eventValueFlow<Boolean>()
    override val successFlow = eventValueFlow<CreateCarResponce>()
    override val openTruckFlow = eventValueFlow<Unit>()
    override val setBrandFlow = eventValueFlow<String>()
    override val setModelFlow = eventValueFlow<String>()
    override fun setBrand(brand: String) {
        viewModelScope.launch {
            setBrandFlow.emit(brand)
        }
    }

    override fun deleteItem(pos: Int) {
        viewModelScope.launch {
            deleteItemFlow.emit(pos)
        }
    }

    override fun setColor(color: String) {
        viewModelScope.launch {
            setColorFlow.emit(color)
        }
    }

    override fun setYearOfIssue(yearOfIssue: String) {
        viewModelScope.launch {
            setYearOfIssueFlow.emit(yearOfIssue)
        }
    }

    override fun setNumber(number: String) {
        viewModelScope.launch {
            setNumberFlow.emit(number)
        }
    }


    override fun setModel(model: String) {
        viewModelScope.launch {
            setModelFlow.emit(model)
        }
    }

    override fun carCreate(request: CreateCarRequest2) {
        if (!isConnected()) {
            viewModelScope.launch {
                errorFlow.emit("Internet bilan muammo bo'ldi")
            }
            return
        }
        viewModelScope.launch {
            progressFlow.emit(true)
        }
        useCase.createCar(request).onEach {
            it.onSuccess { value ->
                progressFlow.emit(false)
                successFlow.emit(value)
            }
            it.onFailure { throwable ->
                progressFlow.emit(false)
                errorFlow.emit(throwable.message.toString())
            }
        }.launchIn(viewModelScope)
    }

    override fun openScreen() {
        viewModelScope.launch {
            openTruckFlow.emit(Unit)
        }
    }
}