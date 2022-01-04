package uz.anorgroup.doonkdriver.presentation.viewmodel.impl.car

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import uz.anorgroup.doonkdriver.data.responce.car.DataX
import uz.anorgroup.doonkdriver.domain.usecase.car.GetAllOrdersUseCase
import uz.anorgroup.doonkdriver.presentation.viewmodel.car.GetAllOrdersViewModel
import uz.anorgroup.doonkdriver.utils.eventValueFlow
import uz.anorgroup.doonkdriver.utils.isConnected
import javax.inject.Inject

@HiltViewModel
class GetAllOrdersViewModelImpl @Inject constructor(
    private val useCase: GetAllOrdersUseCase
) : ViewModel(), GetAllOrdersViewModel {

    override val errorFlow = eventValueFlow<String>()
    override val progressFlow = eventValueFlow<Boolean>()
    override val successFlow = eventValueFlow<String>()
    override val getAllOrdersFlow = eventValueFlow<DataX>()
    override fun getAllOrders() {
        if (!isConnected()) {
            viewModelScope.launch {
                errorFlow.emit("Internet bilan muammo bo'ldi")
            }
            return
        }
        viewModelScope.launch {
            progressFlow.emit(true)
        }
        useCase.getAllOrders().onEach {
            it.onSuccess { value ->
                progressFlow.emit(false)
                successFlow.emit("Success")
                getAllOrdersFlow.emit(value.data)
            }
            it.onFailure { throwable ->
                progressFlow.emit(false)
                errorFlow.emit(throwable.message.toString())
            }
        }.launchIn(viewModelScope)
    }
}