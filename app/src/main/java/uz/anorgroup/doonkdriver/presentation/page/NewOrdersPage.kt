package uz.anorgroup.doonkdriver.presentation.page

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.ConcatAdapter
import androidx.recyclerview.widget.LinearLayoutManager
import by.kirich1409.viewbindingdelegate.viewBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import uz.anorgroup.doonkdriver.R
import uz.anorgroup.doonkdriver.data.responce.car.Parcel
import uz.anorgroup.doonkdriver.data.responce.car.Passanger
import uz.anorgroup.doonkdriver.databinding.PageNewOrdersBinding
import uz.anorgroup.doonkdriver.presentation.adapters.GetAllParcelAdapter
import uz.anorgroup.doonkdriver.presentation.adapters.GetAllPassengerAdapter
import uz.anorgroup.doonkdriver.presentation.viewmodel.car.GetAllOrdersViewModel
import uz.anorgroup.doonkdriver.presentation.viewmodel.impl.car.GetAllOrdersViewModelImpl
import uz.anorgroup.doonkdriver.utils.scope
import uz.anorgroup.doonkdriver.utils.showToast
import uz.anorgroup.doonkdriver.utils.timber

@AndroidEntryPoint
class NewOrdersPage : Fragment(R.layout.page_new_orders) {
    private val binding by viewBinding(PageNewOrdersBinding::bind)
    private val listParcel = ArrayList<Parcel>()
    private val listPassenger = ArrayList<Passanger>()
    private val adapterPassenger = GetAllPassengerAdapter(listPassenger)
    private val adapterParcel = GetAllParcelAdapter(listParcel)
    private val viewModel: GetAllOrdersViewModel by viewModels<GetAllOrdersViewModelImpl>()

    @SuppressLint("FragmentLiveDataObserve")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) = binding.scope {

        viewModel.getAllOrders()

        val concatAdapter = ConcatAdapter()
        concatAdapter.addAdapter(adapterPassenger)
        concatAdapter.addAdapter(adapterParcel)
        listView.adapter = concatAdapter
        listView.layoutManager = LinearLayoutManager(requireContext())
        viewModel.errorFlow.onEach {
            showToast(it)
        }.launchIn(lifecycleScope)
        viewModel.progressFlow.onEach {
            if (it) progress.show()
            else progress.hide()
        }.launchIn(lifecycleScope)

        adapterParcel.setListener { parcel ->
            val bundle = Bundle()
            bundle.putSerializable("parcel", parcel)
            findNavController().navigate(R.id.action_mainScreen_to_parcelInfoScreen, bundle)
        }

        adapterPassenger.setListener { passenger ->
            val bundle = Bundle()
            bundle.putSerializable("passenger", passenger)
            findNavController().navigate(R.id.action_mainScreen_to_passengerScreenInfo2, bundle)
        }

        viewModel.getAllOrdersFlow.onEach {
            val parcelList = it.parcel
            val passengerList = it.passanger
            listParcel.clear()
            listPassenger.clear()
            for (i in parcelList.indices) {
                if (parcelList[i].status == 0) {
                    listParcel.add(parcelList[i])
                }
            }
            for (i in passengerList.indices) {
                if (passengerList[i].status == 0) {
                    listPassenger.add(passengerList[i])
                }
            }
            adapterPassenger.notifyDataSetChanged()
            adapterParcel.notifyDataSetChanged()

        }.launchIn(lifecycleScope)
    }

}