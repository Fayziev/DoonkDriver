package uz.anorgroup.doonkdriver.presentation.viewmodel.impl.car

import android.net.Uri
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import uz.anorgroup.doonkdriver.data.responce.car.ImageUploadResponse
import uz.anorgroup.doonkdriver.domain.usecase.car.ImageUploadUseCase
import uz.anorgroup.doonkdriver.presentation.viewmodel.car.ImageUploadViewModel
import uz.anorgroup.doonkdriver.utils.eventValueFlow
import uz.anorgroup.doonkdriver.utils.isConnected
import java.io.File
import javax.inject.Inject

@HiltViewModel
class ImageUploadViewModelImpl @Inject constructor(private val useCase: ImageUploadUseCase) : ViewModel(), ImageUploadViewModel {

    override val errorFlow = eventValueFlow<String>()
    override val progressFlow = eventValueFlow<Boolean>()
    override val successFlow = eventValueFlow<ImageUploadResponse>()
    override val setPhotoFlow = eventValueFlow<Uri>()


    override fun setPhoto(photo: Uri) {
        viewModelScope.launch {
            setPhotoFlow.emit(photo)
        }
    }

    override fun imageUpload(file: File) {
        if (!isConnected()) {
            viewModelScope.launch {
                errorFlow.emit("Internet bilan muammo bo'ldi")
            }
            return
        }
        viewModelScope.launch {
            progressFlow.emit(true)
        }
        useCase.imageUpload(file).onEach {
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
}