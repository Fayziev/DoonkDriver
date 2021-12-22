package uz.anorgroup.doonkdriver.presentation.dialogs

import android.annotation.SuppressLint
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SearchView
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import by.kirich1409.viewbindingdelegate.viewBinding
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import uz.anorgroup.doonkdriver.R
import uz.anorgroup.doonkdriver.data.responce.location.DataStreet
import uz.anorgroup.doonkdriver.databinding.BottomDialogStreetsBinding
import uz.anorgroup.doonkdriver.presentation.adapters.StreetsAdapter
import uz.anorgroup.doonkdriver.presentation.viewmodel.impl.location.StreetsViewModelImpl
import uz.anorgroup.doonkdriver.presentation.viewmodel.location.StreetsViewModels
import uz.anorgroup.doonkdriver.utils.scope
import uz.anorgroup.doonkdriver.utils.showToast

@AndroidEntryPoint
class StreetsBottomDialog : BottomSheetDialogFragment() {
    private val viewModel: StreetsViewModels by viewModels<StreetsViewModelImpl>()
    private val adapter = StreetsAdapter("")
    private lateinit var hendler: Handler
    private var querySt = ""
    private var cityQuery = ""
    private var listener: ((DataStreet) -> Unit)? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setStyle(STYLE_NORMAL, R.style.CustomBottomSheetDialogTheme)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? =
        inflater.inflate(R.layout.bottom_dialog_streets, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) = bind.scope {
        listView.adapter = adapter
        listView.layoutManager = LinearLayoutManager(requireContext())
        arguments?.getString("id")?.let {
            cityQuery = it
            viewModel.getStreets(cityQuery, querySt)
            showToast(it)
        }
        adapter.setListener {
            listener?.invoke(it)
        }
        viewModel.successFlow.onEach {
            adapter.submitList(it.data)
        }.launchIn(lifecycleScope)

        viewModel.errorFlow.onEach {
            showToast("Error")
        }.launchIn(lifecycleScope)


        hendler = Handler(Looper.getMainLooper())
        bind.searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener,
            androidx.appcompat.widget.SearchView.OnQueryTextListener {
            @SuppressLint("NotifyDataSetChanged")
            override fun onQueryTextSubmit(query: String?): Boolean {
//                hendler.removeCallbacksAndMessages(null)
//                query?.let {
//                    querySt = it.trim()
//                    adapter.query = querySt
//                    viewModel.getStreets(cityQuery, querySt)
//                    bind.searchView.setQuery(querySt, false)
//                }
                return true
            }

            @SuppressLint("NotifyDataSetChanged")
            override fun onQueryTextChange(newText: String?): Boolean {
                hendler.removeCallbacksAndMessages(null)
                hendler.postDelayed({
                    newText?.let {
                        querySt = it.trim()
                        viewModel.getStreets(cityQuery, querySt)
                        adapter.query = querySt
                        bind.searchView.setQuery(querySt, false)
                    }
                }, 500)
                return true
            }
        })

    }

    private val bind by viewBinding(BottomDialogStreetsBinding::bind)
    fun setListener(f: (DataStreet) -> Unit) {
        listener = f
    }

}