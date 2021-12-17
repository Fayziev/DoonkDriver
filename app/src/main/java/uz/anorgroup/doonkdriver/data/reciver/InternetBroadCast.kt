package uz.anorgroup.doonkdriver.data.reciver

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.net.ConnectivityManager

class InternetBroadCast : BroadcastReceiver() {
    private var listener: ((Boolean) -> Unit)? = null
    override fun onReceive(context: Context?, intent: Intent?) {
        listener?.invoke(isConnectedOrConnecting(context!!))
    }

    fun setListener(f: (Boolean) -> Unit) {
        listener = f
    }

    private fun isConnectedOrConnecting(context: Context): Boolean {
        val connMgr = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val networkInfo = connMgr.activeNetworkInfo
        return networkInfo != null && networkInfo.isConnectedOrConnecting
    }

}


