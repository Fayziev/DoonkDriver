package uz.anorgroup.doonkdriver.presentation

import android.content.IntentFilter
import android.net.wifi.WifiManager
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.NavHostFragment
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import uz.anorgroup.doonkdriver.R
import uz.anorgroup.doonkdriver.data.reciver.InternetBroadCast
import uz.anorgroup.doonkdriver.presentation.viewmodel.StartScreenViewModel
import uz.anorgroup.doonkdriver.presentation.viewmodel.impl.StartScreenViewModelImpl
import uz.anorgroup.doonkdriver.utils.timber

@AndroidEntryPoint
class MainActivity : AppCompatActivity(R.layout.activity_main) {
    private val receiver = InternetBroadCast()
    private val viewModel: StartScreenViewModel by viewModels<StartScreenViewModelImpl>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        this.registerReceiver(receiver, IntentFilter(WifiManager.WIFI_STATE_CHANGED_ACTION))
        val navHost = supportFragmentManager.findFragmentById(R.id.container) as NavHostFragment
        val navController = navHost.navController.navInflater.inflate(R.navigation.nav_graph)
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)

//        receiver.setListener {
//            timber(it.toString())
//            if (it) navController.startDestination = R.id.mainScreen
//            else navController.startDestination = R.id.internetConnectionScreen
//            navHost.navController.graph = navController
//        }
    }
}


