package uz.anorgroup.doonkdriver.presentation.viewmodel.car

import kotlinx.coroutines.flow.Flow
import uz.anorgroup.doonkdriver.data.responce.car.ModelResponce

interface ModelsDialogViewModel {
    val errorFlow: Flow<String>
    val progressFlow: Flow<Boolean>
    val successFlow: Flow<ModelResponce>

    fun getModels()

}