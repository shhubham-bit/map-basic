package com.trainee.appinventiv.mapapp1

import android.content.Context
import android.content.SyncRequest
import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.Color
import android.location.LocationRequest
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.content.ContextCompat
import com.google.android.gms.maps.*

import com.google.android.gms.maps.model.*
import com.trainee.appinventiv.mapapp1.databinding.ActivityMapsBinding

class MapsActivity : AppCompatActivity(), OnMapReadyCallback , GoogleMap.OnPolylineClickListener{

    private lateinit var mMap: GoogleMap
    private lateinit var binding: ActivityMapsBinding
//    private lateinit var locationRequest: LocationRequest

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMapsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        val mapFragment = supportFragmentManager
            .findFragmentById(R.id.map) as SupportMapFragment


        mapFragment.getMapAsync(this)

//        locationRequest = LocationRequest.cre
    }

    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap
        mMap.mapType = GoogleMap.MAP_TYPE_TERRAIN
//        mMap.isBuildingsEnabled = true

        // Add a marker in Sydney and move the camera
        val palam = LatLng(28.59028784620173, 77.08891343874303)
        mMap.addMarker(MarkerOptions()
            .position(palam).title("Palam")
            .icon(this.bitmapDescriptorFromVector(R.drawable.ic_baseline_flight_24)))

        val patparganj = LatLng(28.606678, 77.274881)
        mMap.addMarker(MarkerOptions()
            .position(patparganj).title("Patparganj")
            .icon(this.bitmapDescriptorFromVector(R.drawable.ic_baseline_flight_24))
        )

        //mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(sydney, 11f))
        mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(palam,10f))

        mMap.addPolyline(PolylineOptions()
//            .add(LatLng(28.59028784620173, 77.08891343874303))  // palam
//            .add( LatLng(28.668020423942604, 77.2418930525715))  // red fort
            .addAll(mutableListOf(
                LatLng(28.59028784620173, 77.08891343874303),
                LatLng(28.668020423942604, 77.2418930525715),
                LatLng(28.575930, 77.113519),
                LatLng(28.59190810213256, 77.15815089568838),
                LatLng(28.606678, 77.274881)
                )
            )
            .color(Color.MAGENTA)
            .clickable(true)
            .startCap(SquareCap())
            .endCap(SquareCap())
            .jointType(JointType.BEVEL)
            .width(2f)
        )
        mMap.setOnPolylineClickListener(this)
        mMap.addCircle(CircleOptions()
            .center(palam)
            .radius(50.0)
            .strokeColor(Color.RED)
            .strokeWidth(4f)
            .fillColor(Color.MAGENTA)
        )
        mMap.isBuildingsEnabled = true

        mMap.uiSettings.apply {
            isCompassEnabled = true
            isMapToolbarEnabled = true
            isRotateGesturesEnabled = true
            isScrollGesturesEnabled = true
            isZoomControlsEnabled = true
            isRotateGesturesEnabled = true
        }
    }

    override fun onPolylineClick(p0: Polyline) {
        // get callback when click on polyline
    }



}



// hello
fun Context.bitmapDescriptorFromVector(vectorResId:Int): BitmapDescriptor {
    val vectorDrawable = ContextCompat.getDrawable(this, vectorResId)
    vectorDrawable!!.setBounds(0, 0, vectorDrawable.intrinsicWidth, vectorDrawable.intrinsicHeight)
    val bitmap = Bitmap.createBitmap(vectorDrawable.intrinsicWidth, vectorDrawable.intrinsicHeight, Bitmap.Config.ARGB_8888)
    vectorDrawable.draw(Canvas(bitmap))
    return BitmapDescriptorFactory.fromBitmap(bitmap)
}