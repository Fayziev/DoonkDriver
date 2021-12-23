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
import uz.anorgroup.doonkdriver.data.responce.car.CarData
import uz.anorgroup.doonkdriver.databinding.BottomDialogMarksBinding
import uz.anorgroup.doonkdriver.presentation.adapters.BrandsTypeAdapter
import uz.anorgroup.doonkdriver.presentation.viewmodel.car.BrandTypeDialogViewModel
import uz.anorgroup.doonkdriver.presentation.viewmodel.impl.car.BrandTypeDialogViewModelImpl
import uz.anorgroup.doonkdriver.utils.scope
import uz.anorgroup.doonkdriver.utils.showToast

@AndroidEntryPoint
class BrandsBottomDialog : BottomSheetDialogFragment() {
    private val viewModel: BrandTypeDialogViewModel by viewModels<BrandTypeDialogViewModelImpl>()
    private val adapter = BrandsTypeAdapter()
    private var listener: ((CarData) -> Unit)? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setStyle(STYLE_NORMAL, R.style.CustomBottomSheetDialogTheme)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? =
        inflater.inflate(R.layout.bottom_dialog_marks, container, false)

    private val bind by viewBinding(BottomDialogMarksBinding::bind)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) = bind.scope {
        listView.adapter = adapter
        listView.layoutManager = LinearLayoutManager(requireContext())
        viewModel.continueSignUpRequest()
        adapter.setListener {
            listener?.invoke(it)
        }
        viewModel.successFlow.onEach {
            adapter.submitList(it.data)
        }.launchIn(lifecycleScope)
        viewModel.errorFlow.onEach {
            showToast("Error")
        }.launchIn(lifecycleScope)
    }

    fun setListener(f: (CarData) -> Unit) {
        listener = f
    }

}