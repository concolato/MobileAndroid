package com.example.geolocate;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import android.app.Activity;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.widget.Toast;

public class MainActivity extends Activity { 
    // Google Map
    private GoogleMap googleMap;
    private LocationManager locationmanager;
 
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
 
        try {
            // Loading map
        	googleMap = ((MapFragment) getFragmentManager().findFragmentById(R.id.map)).getMap();      	
        	
    		googleMap.setMyLocationEnabled(true); // false to disable
    		googleMap.getUiSettings().setZoomControlsEnabled(false); // true to enable
    		googleMap.getUiSettings().setMyLocationButtonEnabled(true);
    		googleMap.getUiSettings().setRotateGesturesEnabled(true);
    		googleMap.getUiSettings().setCompassEnabled(true);
    		googleMap.setMapType(GoogleMap.MAP_TYPE_SATELLITE);
            
    		Criteria cri= new Criteria();
    		String bbb = locationmanager.getBestProvider(cri, true);
    		Location myLocation = locationmanager.getLastKnownLocation(bbb);
    		
    		
    		double latitude= myLocation.getLatitude();
    		double longitude = myLocation.getLongitude();
    		LatLng coords = new LatLng(latitude, longitude);
    		
    		if(latitude == 0){
    			Toast.makeText(getApplicationContext(),
                        "Sorry! unable to get location", Toast.LENGTH_SHORT)
                        .show();
    		}
    		 		
    		googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(coords, 20));
 
        } catch (Exception e) {
            e.printStackTrace();
        }
 
    }
 
    /**
     * function to load map. If map is not created it will create it for you
     * */
	public void initilizeMap() {		
		// latitude and longitude
		Criteria cri= new Criteria();
		String bbb = locationmanager.getBestProvider(cri, true);
		Location myLocation = locationmanager.getLastKnownLocation(bbb);

		double latitude= myLocation.getLatitude();
		double longitude = myLocation.getLongitude();
		LatLng ll = new LatLng(latitude, longitude);
		
		// create marker
		MarkerOptions marker = new MarkerOptions().position(new LatLng(latitude, longitude)).title("Hello Maps");	 
		// Changing marker icon and adding marker
		//marker.icon(BitmapDescriptorFactory.fromResource(R.drawable.my_marker_icon)));
		marker.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_ROSE));
		
		googleMap.setMyLocationEnabled(true); // false to disable
		googleMap.getUiSettings().setZoomControlsEnabled(false); // true to enable
		googleMap.getUiSettings().setMyLocationButtonEnabled(true);
		googleMap.getUiSettings().setRotateGesturesEnabled(true);
		googleMap.getUiSettings().setCompassEnabled(true);
		googleMap.setMapType(GoogleMap.MAP_TYPE_SATELLITE);
		googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(ll, 20));
		googleMap.addMarker(marker);
	
        if (googleMap == null) {
            googleMap = ((MapFragment) getFragmentManager()
            		.findFragmentById(R.id.map)).getMap();
 
            // check if map is created successfully or not
            if (googleMap == null) {
                Toast.makeText(getApplicationContext(),
                        "Sorry! unable to create maps", Toast.LENGTH_SHORT)
                        .show();
            }
        }
        
    }
 
    /*@Override
    protected void onResume() {
        super.onResume();
        this.initilizeMap();
    }
 	*/
}
