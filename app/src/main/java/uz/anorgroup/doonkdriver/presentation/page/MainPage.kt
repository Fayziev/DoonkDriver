package uz.anorgroup.doonkdriver.presentation.page

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import by.kirich1409.viewbindingdelegate.viewBinding
import com.google.android.material.tabs.TabLayoutMediator
import dagger.hilt.android.AndroidEntryPoint
import uz.anorgroup.doonkdriver.R
import uz.anorgroup.doonkdriver.databinding.PageMainBinding
import uz.anorgroup.doonkdriver.presentation.adapters.PageAdapter
import uz.anorgroup.doonkdriver.utils.scope

@AndroidEntryPoint
class MainPage : Fragment(R.layout.page_main) {
    private val bind by viewBinding(PageMainBinding::bind)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) = bind.scope {

        val adapter = PageAdapter(childFragmentManager, lifecycle)
        pager.adapter = adapter
        TabLayoutMediator(tablayout, pager) { tab, position ->
            when (position) {
                0 -> tab.text = "New Orders"
                1 -> tab.text = "In Progress"
                else -> tab.text = "Finished"
            }
        }.attach()

        roadMap.setOnClickListener {
//            findNavController().navigate(R.id.action_mainScreen_to_roadMapScreen)
        }

    }

}