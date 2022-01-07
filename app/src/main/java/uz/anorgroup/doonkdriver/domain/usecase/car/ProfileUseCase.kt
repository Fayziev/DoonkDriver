package uz.anorgroup.doonkdriver.domain.usecase.car

interface ProfileUseCase {
    fun getName(): String
    fun getImage(): String
    fun setStartScreen(startScreen: Boolean)
}