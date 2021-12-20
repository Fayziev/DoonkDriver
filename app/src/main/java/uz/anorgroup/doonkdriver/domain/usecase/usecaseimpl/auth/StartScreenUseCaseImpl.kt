package uz.anorgroup.doonkdriver.domain.usecase.usecaseimpl.auth

import uz.anorgroup.doonkdriver.domain.repository.AuthRepository
import uz.anorgroup.doonkdriver.domain.usecase.auth.StartScreenUseCase
import javax.inject.Inject

class StartScreenUseCaseImpl @Inject constructor(private val repository: AuthRepository) : StartScreenUseCase {

    override fun getStartScreen(): Boolean = repository.getStartScreen()

}