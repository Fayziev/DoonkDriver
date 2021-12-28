package uz.anorgroup.doonkdriver.presentation.viewmodel.car

import android.net.Uri
import kotlinx.coroutines.flow.Flow
import uz.anorgroup.doonkdriver.data.responce.car.ImageUploadResponse
import java.io.File

interface ImageUploadViewModel {

    val errorFlow: Flow<String>
    val progressFlow: Flow<Boolean>
    val successFlow: Flow<ImageUploadResponse>
    val setPhotoFlow: Flow<Uri>

    fun imageUpload(file:File)
    fun setPhoto(photo:Uri)
}