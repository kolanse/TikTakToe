package com.kolanse.tiktaktoe.multiplayer

import android.content.Context
import android.util.Log
import com.koushikdutta.async.http.AsyncHttpClient
import com.koushikdutta.async.http.WebSocket


object WebSocketClient {

    private var mWebSocket: WebSocket? = null


    fun createWebClient(address: String, port: Int, context: Context?) {
        val hostAddress = "http://$address:$DEFAULT_PORT"
        Log.d("CLIENTTAG", "address is $hostAddress")
        val mWebSocketConnectCallback =
            AsyncHttpClient.WebSocketConnectCallback { ex, webSocket ->
                if (ex != null) {
                    ex.printStackTrace()
                    return@WebSocketConnectCallback
                }
                mWebSocket = webSocket
                // webSocket.send("hello server")
                Log.d("CLIENTTAG", "It sends the hello server")
                webSocket.stringCallback =
                    WebSocket.StringCallback { s ->


                    }
            }
        val mAsyncHttpClient = AsyncHttpClient.getDefaultInstance()
        mAsyncHttpClient.websocket(hostAddress, null, mWebSocketConnectCallback)
        NsdHelper.ServerStatus.postValue(ServerState.CLIENT_CREATED)


    }

    fun sendMessage(message: String?) {
        mWebSocket?.send(message)
    }



}
