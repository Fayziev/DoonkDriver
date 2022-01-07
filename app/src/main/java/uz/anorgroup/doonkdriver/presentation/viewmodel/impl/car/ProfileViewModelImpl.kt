package uz.anorgroup.doonkdriver.presentation.viewmodel.impl.car

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import uz.anorgroup.doonkdriver.domain.usecase.car.ProfileUseCase
import uz.anorgroup.doonkdriver.presentation.viewmodel.car.ProfileViewModel
import uz.anorgroup.doonkdriver.utils.eventValueFlow
import javax.inject.Inject

@HiltViewModel
class ProfileViewModelImpl @Inject constructor(
    private val useCase: ProfileUseCase,
) : ViewModel(), ProfileViewModel {

    override val openScreenFlow = eventValueFlow<Unit>()
    override val getNameFlow = eventValueFlow<String>()
    override val getImageFlow = eventValueFlow<String>()

    override fun getName() {
        viewModelScope.launch {
            getNameFlow.emit(useCase.getName())
        }
    }

    override fun getImage() {
        viewModelScope.launch {
            getImageFlow.emit(useCase.getImage())
        }
    }

    override fun openScreen() {
        viewModelScope.launch {
            openScreenFlow.emit(Unit)
        }
    }

    override fun setStartScreen(startScreen: Boolean) {
        useCase.setStartScreen(startScreen)
    }
}