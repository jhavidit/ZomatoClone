package com.dsckiet.zomatoclone.view

import android.Manifest
import android.annotation.SuppressLint
import android.content.ActivityNotFoundException
import android.content.Context
import android.content.Intent
import android.location.Location
import android.location.LocationManager
import android.net.Uri
import android.os.Bundle
import android.os.Looper
import android.provider.Settings
import android.util.Log
import android.view.View.GONE
import android.view.View.VISIBLE
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.dsckiet.zomatoclone.R
import com.dsckiet.zomatoclone.adapter.HomeScreenAdapter
import com.dsckiet.zomatoclone.util.Constants
import com.dsckiet.zomatoclone.viewModel.CurrentLocationViewModel
import com.dsckiet.zomatoclone.viewModel.HomeScreenViewModel
import com.google.android.gms.location.*
import com.karumi.dexter.Dexter
import com.karumi.dexter.MultiplePermissionsReport
import com.karumi.dexter.PermissionToken
import com.karumi.dexter.listener.PermissionRequest
import com.karumi.dexter.listener.multi.MultiplePermissionsListener
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.location_cell.*
import okhttp3.internal.notifyAll

class MainActivity : AppCompatActivity() {
    private lateinit var mFusedLocationClient: FusedLocationProviderClient
    private lateinit var viewModel: CurrentLocationViewModel
    private lateinit var viewModelHome:HomeScreenViewModel
    private lateinit var adapter: HomeScreenAdapter
    private var latitude: Double = 0.0
    private var longitude: Double = 0.0
    private var entity_id:Int=0
    private  var enity_type:String="city"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        viewModel = ViewModelProvider(this).get(CurrentLocationViewModel::class.java)
        viewModelHome = ViewModelProvider(this).get(HomeScreenViewModel::class.java)

        mFusedLocationClient = LocationServices.getFusedLocationProviderClient(this)

        if (!isLocationEnabled()) {
            Toast.makeText(
                this,
                "Your location provider is turned off, please turn it on",
                Toast.LENGTH_SHORT
            ).show()

            val intent = Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS)
            startActivity(intent)
        } else {
            // TODO (STEP 1: Asking the location permission on runtime.)
            // START
            Dexter.withActivity(this)
                .withPermissions(
                    Manifest.permission.ACCESS_FINE_LOCATION,
                    Manifest.permission.ACCESS_COARSE_LOCATION
                )
                .withListener(object : MultiplePermissionsListener {
                    override fun onPermissionsChecked(report: MultiplePermissionsReport?) {
                        if (report!!.areAllPermissionsGranted()) {
                            // TODO (STEP 7: Call the location request function here.)
                            // START
                            requestLocationData()
                            // END
                        }

                        if (report.isAnyPermissionPermanentlyDenied) {
                            Toast.makeText(
                                this@MainActivity,
                                "You have denied location permission. Please allow it is mandatory.",
                                Toast.LENGTH_SHORT
                            ).show()
                        }
                    }

                    override fun onPermissionRationaleShouldBeShown(
                        permissions: MutableList<PermissionRequest>?,
                        token: PermissionToken?
                    ) {
                        showRationalDialogForPermissions()
                    }
                }).onSameThread()
                .check()
            // END
        }



//        adapter = HomeScreenAdapter(this)
//        rv_home.adapter = adapter
//
//        viewModelHome.showProgess.observe(this, Observer {
//            if (it)
//                progressBar.visibility = VISIBLE
//            else
//                progressBar.visibility = GONE
//        })
//        viewModelHome.getRestaurantDetails(23, entity_type = "city" )
//        viewModelHome.showRestaurant.observe(this, Observer {
//            adapter.getLocationData(it)
//        })
    }

    private fun isLocationEnabled(): Boolean {
        val locationManager: LocationManager =
            getSystemService(Context.LOCATION_SERVICE) as LocationManager
        return locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER) || locationManager.isProviderEnabled(
            LocationManager.NETWORK_PROVIDER
        )
    }

    private fun showRationalDialogForPermissions() {
        AlertDialog.Builder(this)
            .setMessage("It Looks like you have turned off permissions required for this feature. It can be enabled under Application Settings")
            .setPositiveButton(
                "GO TO SETTINGS"
            ) { _, _ ->
                try {
                    val intent = Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS)
                    val uri = Uri.fromParts("package", packageName, null)
                    intent.data = uri
                    startActivity(intent)
                } catch (e: ActivityNotFoundException) {
                    e.printStackTrace()
                }
            }
            .setNegativeButton("Cancel") { dialog,
                                           _ ->
                dialog.dismiss()
            }.show()
    }

    @SuppressLint("MissingPermission")
    private fun requestLocationData() {

        val mLocationRequest = LocationRequest()
        mLocationRequest.priority = LocationRequest.PRIORITY_HIGH_ACCURACY

        mFusedLocationClient.requestLocationUpdates(
            mLocationRequest, mLocationCallback,
            Looper.myLooper()
        )
    }

    private val mLocationCallback = object : LocationCallback() {
        override fun onLocationResult(locationResult: LocationResult) {
            val mLastLocation: Location = locationResult.lastLocation
            latitude = mLastLocation.latitude
            Log.i("Current Latitude", "$latitude")

             longitude = mLastLocation.longitude
            Log.i("Current Longitude", "$longitude")
           getCurrentLocation(latitude,longitude)
        }
    }

    private fun getCurrentLocation(latitude:Double,longitude:Double) {
        if (Constants.isNetworkAvailable(this@MainActivity)) {
            viewModel.getCurrentLocation(latitude,longitude)
            viewModel.currentLocation.observe(this, Observer {
                location_name.text=it.locationSuggestions[0].name
                entity_id=it.locationSuggestions[0].id
getRestaurantDetails(entity_id,enity_type)
            })
        } else
            Toast.makeText(this, "Internet Unavailable", Toast.LENGTH_SHORT).show()
    }
    fun getRestaurantDetails(entityId:Int,entityType:String)
    {

        adapter = HomeScreenAdapter(this)
        rv_home.adapter = adapter

        viewModelHome.showProgess.observe(this, Observer {
            if (it)
                progressBar.visibility = VISIBLE
            else
                progressBar.visibility = GONE
        })
        viewModelHome.getRestaurantDetails(entityId, entityType )
        viewModelHome.showRestaurant.observe(this, Observer {
            adapter.getLocationData(it)
        })
    }


}