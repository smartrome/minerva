package com.minerva;

import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.AppCompatActivity;
import android.text.Html;
import android.view.Gravity;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class MapsActivity extends AppCompatActivity implements OnMapReadyCallback {

    /*** Google Map Activity attribute ***/

    private GoogleMap googleMap;

    /*** Landmark place coordinates ***/

    protected Double placeLatitude;

    protected Double placeLongitude;

    protected String placeName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);

        /*** Change background color and text color of the Action Bar ***/
        android.support.v7.app.ActionBar actionBar = getSupportActionBar();
        actionBar.setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.rome)));
        actionBar.setTitle(Html.fromHtml("<font color='#FFFFFF'>Minerva</font>"));
        actionBar.setDisplayHomeAsUpEnabled(false);
        actionBar.setHomeButtonEnabled(false);

        // *** Get coordinates from the previous activity ***
        Intent intent = getIntent();
        placeLatitude = intent.getDoubleExtra("PLACE_LATITUDE", 0.0);
        placeLongitude = intent.getDoubleExtra("PLACE_LONGITUDE", 0.0);
        placeName = intent.getStringExtra("PLACE_NAME");

        // *** Obtain the SupportMapFragment and get notified when the map is ready to be used. ***
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
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
    @Override
    public void onMapReady(GoogleMap googleMap) {
        this.googleMap = googleMap;

        // *** Add marker over the landmark coordinates ***
        LatLng landmarkCoordinates = new LatLng(placeLatitude, placeLongitude);
        this.googleMap.addMarker(new MarkerOptions().position(landmarkCoordinates).title(placeName));

        // *** Make automatic zoom in on the place having the coordinates ***
        float zoomLevel = (float) 16.0; // This value goes up to 21
        this.googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(landmarkCoordinates, zoomLevel));

        // *** Disable Google Maps toolbar ***
        googleMap.getUiSettings().setMapToolbarEnabled(true);

        // *** Enable Google Map zoom controls ***
        googleMap.getUiSettings().setZoomControlsEnabled(true);

        // *** Display welcome message routine, it is presented the map. ***
        Toast welcomeToastMessage = (Toast) Toast.makeText(this, getString(R.string.message_maps_indication), Toast.LENGTH_SHORT);
        welcomeToastMessage.setGravity(Gravity.CENTER_HORIZONTAL, 0, 0);
        welcomeToastMessage.show();
    }
}
