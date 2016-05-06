package com.example.map2;

import java.util.List;

import android.content.Context;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.os.Looper;
import android.support.v4.app.FragmentActivity;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.GoogleMap.OnMapClickListener;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;


        public class MainActivity extends FragmentActivity implements OnMapReadyCallback, OnMapClickListener {
            
        	//LatLng c = new LatLng(0, 0);
        	GoogleMap myMap;
        	
        	@Override
            protected void onCreate(Bundle savedInstanceState) {
                super.onCreate(savedInstanceState);
                setContentView(R.layout.activity_main);
                SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                        .findFragmentById(R.id.map);
                mapFragment.getMapAsync(this);
                //AddCurrentLocation();
            }

        	
        	
        	
        	
            @Override
            public void onMapReady(GoogleMap map) {
            	myMap = map;
                // Add a marker in Sydney, Australia, and move the camera.
                LatLng sydney = new LatLng(-34, 151);
                LatLng moscow = new LatLng(55.751244, 37.618423);
                map.addMarker(new MarkerOptions().position(moscow).title("My position"));
                map.moveCamera(CameraUpdateFactory.newLatLng(moscow));
                AddCurrentLocation();
                myMap.setOnMapClickListener(new OnMapClickListener() {

                    @Override
                    public void onMapClick(LatLng point) {
                    	myMap.addMarker(new MarkerOptions().position(point).title("Marker"));
                    }
                });
            }
            
            public void AddCurrentLocation(){
            	
            	final LocationManager locationService = 
            			(LocationManager) this.getSystemService(Context.LOCATION_SERVICE);
            	Criteria criteria = new Criteria();
    			criteria.setAccuracy(Criteria.ACCURACY_MEDIUM);
    			
    			//List<String> available = locationService
    			
    			LocationListener ll = new LocationListener() {

					@Override
					public void onLocationChanged(Location location) {
						//location.get
						//Toast.makeText(MainActivity.this, location.getLatitude() +";"+location.getLongitude(), Toast.LENGTH_LONG).show();
						myMap.addMarker(new MarkerOptions().position(new LatLng(location.getLatitude(), location.getLongitude())).title("Marker in Sydney"));
		                myMap.moveCamera(CameraUpdateFactory.newLatLng(new LatLng(location.getLatitude(), location.getLongitude())));
					}

					@Override
					public void onStatusChanged(String provider, int status,
							Bundle extras) {
					}

					@Override
					public void onProviderEnabled(String provider) {
					}

					@Override
					public void onProviderDisabled(String provider) {
					}
    			};
				locationService.requestSingleUpdate(criteria, ll, Looper.getMainLooper());
    			
            }
            
			@Override
			public void onMapClick(LatLng arg0) {
				
				 myMap.addMarker(new MarkerOptions().position(arg0).title("Marker"));
				
			} 
        }

        
