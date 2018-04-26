package com.example.davegilbier.codelablifecycle.step4

import android.annotation.SuppressLint
import android.arch.lifecycle.Lifecycle
import android.arch.lifecycle.LifecycleObserver
import android.arch.lifecycle.LifecycleOwner
import android.arch.lifecycle.OnLifecycleEvent
import android.content.Context
import android.location.LocationListener
import android.location.LocationManager
import android.util.Log


class BoundLocationManager {
    fun bindLocationListenerIn(lifecycleOwner: LifecycleOwner,
                               listener: LocationListener, context: Context) {
        BoundLocationListener(lifecycleOwner, listener, context)
    }

    internal class BoundLocationListener(lifecycleOwner: LifecycleOwner,
                                         private val mListener: LocationListener, private val mContext: Context) : LifecycleObserver {
        private var mLocationManager: LocationManager? = null

        init {
            lifecycleOwner.lifecycle.addObserver(this)
        }

        @SuppressLint("MissingPermission")
        @OnLifecycleEvent(Lifecycle.Event.ON_RESUME)
        fun addLocationListener() {

            mLocationManager = mContext.getSystemService(Context.LOCATION_SERVICE) as LocationManager
            mLocationManager!!.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0f, mListener)
            Log.d("BoundLocationMgr", "Listener added")

            val lastLocation = mLocationManager!!.getLastKnownLocation(
                    LocationManager.GPS_PROVIDER)
            if (lastLocation != null) {
                mListener.onLocationChanged(lastLocation)
            }
        }


        @OnLifecycleEvent(Lifecycle.Event.ON_PAUSE)
        fun removeLocationListener() {
            if (mLocationManager == null) {
                return
            }
            mLocationManager!!.removeUpdates(mListener)
            mLocationManager = null
            Log.d("BoundLocationMgr", "Listener removed")
        }
    }
}
