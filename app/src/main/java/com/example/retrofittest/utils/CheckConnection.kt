package com.example.retrofittest.utils

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build

class CheckConnection(val context: Context) {

    fun isConnect():Boolean{
        var result=false
        val cm:ConnectivityManager= context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        if (Build.VERSION.SDK_INT>=Build.VERSION_CODES.M) {
            cm.getNetworkCapabilities(cm.activeNetwork)?.run {
                result=when{
                    hasTransport(NetworkCapabilities.TRANSPORT_WIFI)->true
                    hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR)->true
                    hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET)->true
                    else->false
                }
            }
        }else{
            cm.run {
                if (activeNetworkInfo?.type==ConnectivityManager.TYPE_WIFI||
                        activeNetworkInfo?.type==ConnectivityManager.TYPE_MOBILE)
                    result=true
            }
        }
        return result
    }
}