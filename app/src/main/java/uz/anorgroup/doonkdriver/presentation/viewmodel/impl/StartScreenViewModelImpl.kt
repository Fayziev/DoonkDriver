package uz.anorgroup.doonkdriver.presentation.viewmodel.impl

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import uz.anorgroup.doonkdriver.domain.usecase.auth.StartScreenUseCase
import uz.anorgroup.doonkdriver.presentation.viewmodel.StartScreenViewModel
import uz.anorgroup.doonkdriver.utils.eventValueFlow
import javax.inject.Inject

@HiltViewModel
class StartScreenViewModelImpl @Inject constructor(private val useCase: StartScreenUseCase) : ViewModel(), StartScreenViewModel {
    override val startScreenFlow = eventValueFlow<Boolean>()

    override fun getStartScreen() {
        viewModelScope.launch {
            startScreenFlow.emit(useCase.getStartScreen())
        }
    }


}