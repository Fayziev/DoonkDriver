package uz.anorgroup.doonkdriver.presentation.page

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import by.kirich1409.viewbindingdelegate.viewBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import uz.anorgroup.doonkdriver.R
import uz.anorgroup.doonkdriver.data.responce.car.Parcel
import uz.anorgroup.doonkdriver.data.responce.car.Passanger
import uz.anorgroup.doonkdriver.databinding.PageNewOrdersBinding
import uz.anorgroup.doonkdriver.presentation.adapters.GetAllOrdersAdapter2
import uz.anorgroup.doonkdriver.presentation.viewmodel.car.GetAllOrdersViewModel
import uz.anorgroup.doonkdriver.presentation.viewmodel.impl.car.GetAllOrdersViewModelImpl
import uz.anorgroup.doonkdriver.utils.scope
import uz.anorgroup.doonkdriver.utils.showToast
@AndroidEntryPoint
class NewOrdersPage : Fragment(R.layout.page_new_orders) {
    private val binding by viewBinding(PageNewOrdersBinding::bind)
    private val listParcel = ArrayList<Parcel>()
    private val listPassenger = ArrayList<Passanger>()
    private val adapter = GetAllOrdersAdapter2(listParcel, listPassenger)
    private val viewModel: GetAllOrdersViewModel by viewModels<GetAllOrdersViewModelImpl>()
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) = binding.scope {

        listView.adapter = adapter
        listView.layoutManager = LinearLayoutManager(requireContext())
        viewModel.getAllOrders()
        viewModel.successFlow.onEach {
            showToast(it)
        }.launchIn(lifecycleScope)
        viewModel.errorFlow.onEach {
            showToast(it)
        }.launchIn(lifecycleScope)
        viewModel.progressFlow.onEach {

        }.launchIn(lifecycleScope)
        viewModel.getAllOrdersFlow.onEach {
            listParcel.addAll(it.parcel)
            listPassenger.addAll(it.passanger)
            adapter.notifyDataSetChanged()
        }.launchIn(lifecycleScope)
    }
}