package uz.anorgroup.doonkdriver.presentation.viewmodel.impl

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import uz.anorgroup.doonkdriver.data.responce.car.AllCarsResponse
import uz.anorgroup.doonkdriver.domain.usecase.car.AllCarsUseCase
import uz.anorgroup.doonkdriver.presentation.viewmodel.AllCarsViewModel
import uz.anorgroup.doonkdriver.utils.eventFlow
import uz.anorgroup.doonkdriver.utils.eventValueFlow
import uz.anorgroup.doonkdriver.utils.isConnected
import javax.inject.Inject

@HiltViewModel
class AllCarsViewModelImpl @Inject constructor(private val useCase: AllCarsUseCase) : AllCarsViewModel, ViewModel() {

    override val successFlow = eventValueFlow<AllCarsResponse>()
    override val errorFlow = eventValueFlow<String>()
    override val progressFlow = eventValueFlow<Boolean>()
    override val openAddCarFlow = eventValueFlow<Unit>()
    override val openCreateOrderFlow = eventFlow()

    override fun getAllCars() {
        if (!isConnected()) {
            viewModelScope.launch {
                errorFlow.emit("Internet bilan muammo bo'ldi")
            }
            return
        }
        viewModelScope.launch {
            progressFlow.emit(true)
        }
        useCase.getAllCars().onEach {
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
            openAddCarFlow.emit(Unit)
        }
    }

    override fun openCreateOrder() {
        viewModelScope.launch {
            openCreateOrderFlow.emit(Unit)
        }
    }
}