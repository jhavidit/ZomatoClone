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
import kotlinx.android.synthetic.main.fragment_home_screen.*

class MainActivity : AppCompatActivity() {
    private lateinit var mFusedLocationClient: FusedLocationProviderClient
    private lateinit var viewModel: CurrentLocationViewModel
    private lateinit var viewModelHome: HomeScreenViewModel
    private lateinit var adapter: HomeScreenAdapter
     var latitude: Double = 0.0
     var longitude: Double = 0.0
    var entity_id: Int = -1

    var enity_type: String = "city"
    var homeScreen = HomeScreen()

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
//
//            val bundle = Bundle()
//            bundle.putDouble("latitude", latitude)
//            bundle.putDouble("longitude",longitude)
//            val fragobj = HomeScreen()
//            fragobj.arguments = bundle
//            val intent=Intent(this@MainActivity,HomeScreen::class.java)
//                    intent.putExtra("latitude",latitude)
//            getCurrentLocation(latitude, longitude)
        }
    }

    private fun getCurrentLocation(latitude: Double, longitude: Double) {
        if (Constants.isNetworkAvailable(this@MainActivity)) {
            viewModel.getCurrentLocation(latitude, longitude)
            viewModel.currentLocation.observe(this, Observer {
                location_name.text = it.locationSuggestions[0].name
                entity_id = it.locationSuggestions[0].id
                    Log.e("id",entity_id.toString())


            })
//            Log.e("entity", entity_id.toString())
//            val bundle = Bundle()
//            bundle.putInt("entityId", entity_id)
//            val fragobj = HomeScreen()
//            fragobj.setArguments(bundle)
//            Log.e("entityid",entity_id.toString())

        } else
            Toast.makeText(this, "Internet Unavailable", Toast.LENGTH_SHORT).show()
    }

   


}