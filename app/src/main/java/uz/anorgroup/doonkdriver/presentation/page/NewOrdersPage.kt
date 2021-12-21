package uz.anorgroup.doonkdriver.presentation.page

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import by.kirich1409.viewbindingdelegate.viewBinding
import uz.anorgroup.doonkdriver.R
import uz.anorgroup.doonkdriver.databinding.PageNewOrdersBinding
import uz.anorgroup.doonkdriver.utils.scope

class NewOrdersPage:Fragment(R.layout.page_new_orders) {
    private val binding by viewBinding(PageNewOrdersBinding::bind)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) =binding.scope{

    }
}