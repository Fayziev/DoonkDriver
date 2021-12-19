package uz.anorgroup.doonkdriver.presentation.screens

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import by.kirich1409.viewbindingdelegate.viewBinding
import dagger.hilt.android.AndroidEntryPoint
import uz.anorgroup.doonkdriver.R
import uz.anorgroup.doonkdriver.databinding.ScreenMainBinding
import uz.anorgroup.doonkdriver.presentation.adapters.MainPageAdapter
import uz.anorgroup.doonkdriver.utils.scope

@AndroidEntryPoint
class MainScreen : Fragment(R.layout.screen_main) {
    private val bind by viewBinding(ScreenMainBinding::bind)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) = bind.scope {
        super.onViewCreated(view, savedInstanceState)
        val adapter = MainPageAdapter(childFragmentManager, lifecycle)
        pager.adapter = adapter

        bind.pager.isUserInputEnabled = false
        bottomNav.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.home -> {
                    bind.pager.setCurrentItem(0, true)
                }
                R.id.create -> {
                    bind.pager.setCurrentItem(1, true)
                }
                R.id.profile -> {
                    bind.pager.setCurrentItem(2, true)
                }
            }
            return@setOnItemSelectedListener true
        }
    }
}