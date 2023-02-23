package com.kolanse.tiktaktoe.multiplayer

import android.content.Context
import android.net.nsd.NsdManager
import android.net.nsd.NsdServiceInfo
import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.MutableLiveData
import java.lang.Exception

object NsdHelper {
    var mNsdManager: NsdManager? = null
    var mResolveListener: NsdManager.ResolveListener? = null
    var mDiscoveryListener: NsdManager.DiscoveryListener? = null
    var mRegistrationListener: NsdManager.RegistrationListener? = null
    var mServiceName = "TicTacToeNsd"
    var DEFAULT_SERVICE_NAME = "TicTacToeNsd"
    var mServiceType = "_tictactoe._tcp"
    var chosenServiceInfo: NsdServiceInfo? = null
    const val SERVICE_TYPE = "_http._tcp."
    const val TAG = "NsdHelper"
    var ServerStatus = MutableLiveData<ServerState>(ServerState.OFF)


    fun initializeNsd(context: Context, aa: FragmentActivity?) {


        mNsdManager = aa?.getSystemService(Context.NSD_SERVICE) as NsdManager



    }




    private fun connectToServer(context: Context){

        initializeDiscoveryListener(context)
    }




    fun initializeDiscoveryListener(context: Context) {
        mDiscoveryListener = @RequiresApi(Build.VERSION_CODES.JELLY_BEAN)
        object : NsdManager.DiscoveryListener {
            override fun onDiscoveryStarted(regType: String) {
                Log.d(TAG, "Service discovery started")
                ServerStatus.postValue(ServerState.SEARCHING_FOR_SERVICE)
            }

            override fun onServiceFound(service: NsdServiceInfo) {

                if (service.serviceType != "_tictactoe._tcp.") {
                    Log.d(TAG, "Unknown Service Type: " + service.serviceType)
                }
                else if (service.serviceName.contains(mServiceName)) {

                    try {
                        initializeResolveListener(context)
                        ServerStatus.postValue(ServerState.FOUND)
                        mNsdManager?.resolveService(service, mResolveListener)

                    } catch (e: Exception){
                        Log.d(TAG, "exception in discovery listener $e")
                    }
                }
            }

            override fun onServiceLost(service: NsdServiceInfo) {
                Log.e(TAG, "service lost$service")


                ServerStatus.postValue(ServerState.SERVICE_LOST)
                if (chosenServiceInfo == service) {
                    chosenServiceInfo = null
                }
            }
            override fun onDiscoveryStopped(serviceType: String) {
                Log.i(TAG, "Discovery stopped: $serviceType")
                ServerStatus.postValue(ServerState.OFF)
            }

            override fun onStartDiscoveryFailed(serviceType: String, errorCode: Int) {
                Log.e(TAG, "Discovery failed: Error code:$errorCode")
                ServerStatus.postValue(ServerState.OFF)
                mNsdManager?.stopServiceDiscovery(this)

            }

            override fun onStopDiscoveryFailed(serviceType: String, errorCode: Int) {
                Log.e(TAG, "Discovery failed: Error code:$errorCode")

                mNsdManager?.stopServiceDiscovery(this)
                ServerStatus.postValue(ServerState.OFF)
            }
        }
    }

    fun initializeResolveListener(context: Context) {
        mResolveListener = @RequiresApi(Build.VERSION_CODES.JELLY_BEAN)
        object : NsdManager.ResolveListener {
            override fun onResolveFailed(serviceInfo: NsdServiceInfo, errorCode: Int) {
                Log.e(TAG, "Resolve failed$errorCode")

            }

            override fun onServiceResolved(serviceInfo: NsdServiceInfo) {
                Log.e(TAG, "Resolve Succeeded. $serviceInfo")


                if (serviceInfo.serviceName == mServiceName) {
                    Log.d(TAG, "Same IP.")
                    return
                }
                ServerStatus.postValue(ServerState.RESOLVED)

                WebSocketClient.createWebClient(serviceInfo!!.host.hostAddress, serviceInfo!!.port, context)
                chosenServiceInfo = serviceInfo
            }
        }
    }

    fun initializeRegistrationListener(context: Context) {
        mRegistrationListener = @RequiresApi(Build.VERSION_CODES.JELLY_BEAN)
        object : NsdManager.RegistrationListener {
            override fun onServiceRegistered(NsdServiceInfo: NsdServiceInfo) {
                Log.d(TAG, "Service registered $NsdServiceInfo")
                ServerStatus.postValue(ServerState.SERVICE_CREATED)
                mServiceName = NsdServiceInfo.serviceName
                WebsocketServer.createWebServer(7000, context)
            }

            override fun onRegistrationFailed(arg0: NsdServiceInfo, arg1: Int) {
                Log.d(TAG, "Service registration failed")

            }
            override fun onServiceUnregistered(arg0: NsdServiceInfo) {
                //  Toast.makeText(context, "Service Unregistered" , Toast.LENGTH_LONG ).show()
                mServiceName = DEFAULT_SERVICE_NAME
                Log.d(TAG, "Service Unregistered Successfully")
                ServerStatus.postValue(ServerState.OFF)
                WebsocketServer.stopServer()
            }
            override fun onUnregistrationFailed(serviceInfo: NsdServiceInfo, errorCode: Int) {
                Log.d(TAG, "Service Unregistered Successfully")
            }
        }
    }

    @RequiresApi(Build.VERSION_CODES.JELLY_BEAN)
    fun registerService(port: Int, context: Context) {
        stopService(context)
        initializeRegistrationListener(context)
        val serviceInfo = NsdServiceInfo().apply {
            // The name is subject to change based on conflicts
            // with other services advertised on the same network.
            serviceName = "${mServiceName}_"
            serviceType = mServiceType
            setPort(port)
        }

        mNsdManager  =( context?.getSystemService(Context.NSD_SERVICE) as NsdManager).apply {


            registerService(serviceInfo, NsdManager.PROTOCOL_DNS_SD, mRegistrationListener)
        }


    }

    @RequiresApi(Build.VERSION_CODES.JELLY_BEAN)
    fun discoverServices(context: Context) {
        connectToServer(context)
        mNsdManager?.discoverServices(
            mServiceType, NsdManager.PROTOCOL_DNS_SD, mDiscoveryListener
        )

    }

    @RequiresApi(Build.VERSION_CODES.JELLY_BEAN)
    fun stopDiscovery(context: Context) {

        try {
            ServerStatus.postValue(ServerState.OFF)
            mNsdManager?.stopServiceDiscovery(mDiscoveryListener)


        } catch (e: Exception){
            Log.d(TAG, "Stop Discovery failure")

        }



    }

    @RequiresApi(Build.VERSION_CODES.JELLY_BEAN)
    fun stopService(context: Context) {

        try {
            mNsdManager?.unregisterService(mRegistrationListener)
        } catch (e: Exception){

        }

    }


}

enum class ServerState {
    SERVER_CREATED,
    CLIENT_CREATED,
    OFF,
    RESOLVED,
    FOUND,
    SERVICE_CREATED,
    SEARCHING_FOR_SERVICE,
    SERVICE_LOST
}
const val DEFAULT_PORT = 5050
