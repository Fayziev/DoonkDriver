package uz.anorgroup.doonkdriver.presentation.dialogs

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import by.kirich1409.viewbindingdelegate.viewBinding
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import uz.anorgroup.doonkdriver.R
import uz.anorgroup.doonkdriver.databinding.BottomCitysBinding
import uz.anorgroup.doonkdriver.presentation.adapters.CitysAdapter
import uz.anorgroup.doonkdriver.presentation.viewmodel.impl.location.CitysViewModelImpl
import uz.anorgroup.doonkdriver.presentation.viewmodel.location.CitysViewModels
import uz.anorgroup.doonkdriver.utils.scope
import uz.anorgroup.doonkdriver.utils.showToast

@AndroidEntryPoint
class CitysBottomDialog : BottomSheetDialogFragment() {
    private val viewModel: CitysViewModels by viewModels<CitysViewModelImpl>()
    private var listener: ((String) -> Unit)? = null
    private val adapter = CitysAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setStyle(STYLE_NORMAL, R.style.CustomBottomSheetDialogTheme)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? =
        inflater.inflate(R.layout.bottom_citys, container, false)


    private val bind by viewBinding(BottomCitysBinding::bind)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) = bind.scope {
        listView.adapter = adapter
        listView.layoutManager = LinearLayoutManager(requireContext())
        viewModel.getCitys("")
        adapter.setListener {
            listener?.invoke(it.name)
        }
        viewModel.successFlow.onEach {
            adapter.submitList(it.data)
            adapter.notifyDataSetChanged()
        }.launchIn(lifecycleScope)
        viewModel.errorFlow.onEach {
            showToast("Error")
        }.launchIn(lifecycleScope)
    }

    fun setListener(f: (String) -> Unit) {
        listener = f
    }

}