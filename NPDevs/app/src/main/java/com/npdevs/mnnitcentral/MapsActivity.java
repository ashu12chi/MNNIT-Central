package com.npdevs.mnnitcentral;

import android.Manifest;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.LocationManager;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.CheckBox;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Objects;

public class MapsActivity extends AppCompatActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    private LatLng latLng;
    static private final int REQUEST_LOCATION_PERMISSION = 500;
    private String s,regno;
    private CheckBox riding;

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.maps_options, menu);
        return true;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        s="";
        Intent k=getIntent();
        regno= Objects.requireNonNull(k.getExtras()).getString("regno");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        assert mapFragment != null;
        mapFragment.getMapAsync(this);
        final LocationManager manager = (LocationManager) getSystemService( Context.LOCATION_SERVICE );
        riding=findViewById(R.id.get_ride);

        if ( !manager.isProviderEnabled( LocationManager.GPS_PROVIDER ) ) {
            buildAlertMessageNoGps();
        }
    }

    protected void onDestroy() {
        super.onDestroy();
        riding.setChecked(false);
        updateStatus();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Change the map type based on the user's selection.
        switch (item.getItemId()) {
            case R.id.normal_map:
                mMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);
                return true;
            case R.id.satellite_map:
                mMap.setMapType(GoogleMap.MAP_TYPE_HYBRID);
                return true;
            case R.id.terrain_map:
                mMap.setMapType(GoogleMap.MAP_TYPE_TERRAIN);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        //Add a marker in Prayagraj and move the camera
        latLng = new LatLng(25.490575, 81.866328);
        mMap.getUiSettings().setAllGesturesEnabled(true);
        mMap.getUiSettings().setScrollGesturesEnabledDuringRotateOrZoom(true);
        mMap.addMarker(new MarkerOptions().position(latLng).title("Drag to adjust...").draggable(true));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(latLng,15.5f));
        enableMyLocation();
        mMap.setOnMapLongClickListener(new GoogleMap.OnMapLongClickListener() {
            @Override
            public void onMapLongClick(LatLng latLng2) {
                latLng=latLng2;
                mMap.clear();
                mMap.addMarker(new MarkerOptions().position(latLng2).draggable(true));
                updateStatus();
                if(riding.isChecked())
                    checkCabs();
            }
        });
        mMap.setOnMarkerDragListener(new GoogleMap.OnMarkerDragListener() {
            @Override
            public void onMarkerDragStart(Marker marker) {
            }
            @Override
            public void onMarkerDrag(Marker marker) {
            }
            @Override
            public void onMarkerDragEnd(Marker marker) {
                latLng=new LatLng(marker.getPosition().latitude,marker.getPosition().longitude);
                updateStatus();
            }
        });
        riding.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (riding.isChecked()) {
                    updateStatus();
                    checkCabs();
                }
                else {
                    updateStatus();
                    mMap.clear();
                    mMap.addMarker(new MarkerOptions().position(latLng).draggable(true));
                }
            }
        });
    }

    private void enableMyLocation() {
        if (ContextCompat.checkSelfPermission(this,
                Manifest.permission.ACCESS_FINE_LOCATION)
                == PackageManager.PERMISSION_GRANTED) {
            mMap.setMyLocationEnabled(true);
        } else {
            ActivityCompat.requestPermissions(this, new String[]
                            {Manifest.permission.ACCESS_FINE_LOCATION},
                    REQUEST_LOCATION_PERMISSION);
        }
    }
    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           @NonNull String[] permissions,
                                           @NonNull int[] grantResults) {
        // Check if location permissions are granted and if so enable the location data layer.
        if (requestCode == REQUEST_LOCATION_PERMISSION) {
            if (grantResults.length > 0
                    && grantResults[0]
                    == PackageManager.PERMISSION_GRANTED) {
                enableMyLocation();
            }
        }
    }
    private void buildAlertMessageNoGps() {
        final AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("Your GPS seems to be disabled, do you want to enable it?")
                .setCancelable(false)
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    public void onClick(@SuppressWarnings("unused") final DialogInterface dialog, @SuppressWarnings("unused") final int id) {
                        startActivity(new Intent(android.provider.Settings.ACTION_LOCATION_SOURCE_SETTINGS));
                    }
                })
                .setNegativeButton("Exit", new DialogInterface.OnClickListener() {
                    public void onClick(final DialogInterface dialog, @SuppressWarnings("unused") final int id) {
                        finish();
                    }
                });
        final AlertDialog alert = builder.create();
        alert.show();
    }
    private void checkCabs()
    {
        final DatabaseReference myRef= FirebaseDatabase.getInstance().getReference("drivers");
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                mMap.clear();
                mMap.addMarker(new MarkerOptions().position(latLng).title("Drag to adjust...").draggable(true));

                Log.e("Count ", "" + dataSnapshot.getChildrenCount());
                for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {
                    if(Boolean.parseBoolean(postSnapshot.child("isRiding").getValue().toString())) {
                        String name=postSnapshot.child("name").getValue().toString();
                        String mob=postSnapshot.child("mob").getValue().toString();
                        String cab=postSnapshot.child("cab").getValue().toString();
                        double lat=Double.parseDouble(postSnapshot.child("lat").getValue().toString());
                        double lng=Double.parseDouble(postSnapshot.child("lng").getValue().toString());
                        s=name + " " + "" + mob + " " + cab;
                        LatLng cabLoc=new LatLng(lat,lng);
                        mMap.addMarker(new MarkerOptions().position(cabLoc).title(s).icon(BitmapDescriptorFactory
                                .defaultMarker(BitmapDescriptorFactory.HUE_CYAN)));
                    }
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
    private void updateStatus() {
        final DatabaseReference myRef = FirebaseDatabase.getInstance().getReference("users/"+regno);
        myRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull final DataSnapshot dataSnapshot) {
                myRef.child("isRiding").setValue(riding.isChecked()+"");
                myRef.child("lat").setValue(latLng.latitude+"");
                myRef.child("lng").setValue(latLng.longitude+"");
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
}


