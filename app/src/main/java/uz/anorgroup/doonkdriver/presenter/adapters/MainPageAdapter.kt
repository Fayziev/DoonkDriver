package uz.anorgroup.doonkdriver.presenter.adapters

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter
import uz.anorgroup.doonkdriver.presenter.page.CreatePage
import uz.anorgroup.doonkdriver.presenter.page.MainPage
import uz.anorgroup.doonkdriver.presenter.page.ProfilePage

class MainPageAdapter(
    frm: FragmentManager, lifecycle: Lifecycle
) : FragmentStateAdapter(frm, lifecycle) {

    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> {
                val mainPage = MainPage()
                mainPage
            }
            1 -> {
                val createPage = CreatePage()
                createPage
            }
            else -> {
                val profilePage = ProfilePage()
                profilePage

            }
        }
    }


    override fun getItemCount(): Int = 5
}
