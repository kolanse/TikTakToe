package com.kolanse.tiktaktoe.multiplayer

import android.content.Context
import android.util.Log
import com.koushikdutta.async.http.WebSocket
import com.koushikdutta.async.http.server.AsyncHttpServer
import java.util.ArrayList

object WebsocketServer  {

    private val mWebSocket = ArrayList<WebSocket>()
    var mAsyncHttpServer: AsyncHttpServer? = null

    fun createWebServer(port: Int , context: Context?) {


        mAsyncHttpServer = AsyncHttpServer()
        val mWebSocketCallback =
            AsyncHttpServer.WebSocketRequestCallback { webSocket, request ->
                Log.d("WEBSOCKETSERVER", "connected port$port")
                mWebSocket.add(webSocket)
                webSocket.stringCallback =
                    WebSocket.StringCallback { s ->
                        Log.d("WEBSOCKETSERVER", "Got a message from client$s")
                        sendMessage(s, context)


                    }
            }
        mAsyncHttpServer?.websocket("/", mWebSocketCallback)
        mAsyncHttpServer?.listen(DEFAULT_PORT)
        NsdHelper.ServerStatus.postValue(ServerState.SERVER_CREATED)
        Log.d("WEBSOCKETSERVER", "Server Created")
    }


    fun stopServer(){
        try {
            NsdHelper.ServerStatus.postValue(ServerState.OFF)
            mAsyncHttpServer?.stop()
        } catch (e:Exception){

            Log.d("SERVERSTATUS", "THE EXCEPTION: $e")
        }

    }

    fun sendMessage(message: String?, context: Context?) {
        Log.d("BROADCASTKDS", "THE kds: $message the socket $mWebSocket")
        for (webSocket in mWebSocket) {
            webSocket.send(message)
        }
    }
}