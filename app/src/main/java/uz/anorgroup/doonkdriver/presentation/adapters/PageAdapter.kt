package uz.anorgroup.doonkdriver.presentation.adapters

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter
import uz.anorgroup.doonkdriver.presentation.page.FinishedPage
import uz.anorgroup.doonkdriver.presentation.page.InProgressPage
import uz.anorgroup.doonkdriver.presentation.page.NewOrdersPage

class PageAdapter(
    fm: FragmentManager, lifecycle: Lifecycle,
) : FragmentStateAdapter(fm, lifecycle) {
    override fun getItemCount(): Int = 3

    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> {
                val newOrdersPage = NewOrdersPage()
                newOrdersPage
            }
            1 -> {
                val inProgressPage = InProgressPage()
                inProgressPage
            }
            else -> {
                val finishedPage = FinishedPage()
                finishedPage
            }
        }
    }

}