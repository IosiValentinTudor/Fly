package com.example.fly;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;

import com.example.fly.Model.locatie;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.database.collection.LLRBBlackValueNode;
import com.google.firebase.ktx.Firebase;


import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import static java.lang.Math.ceil;

public class Harta extends FragmentActivity implements OnMapReadyCallback {

    GoogleMap map;
    Button button;
    LinearLayout satelit, teren;
    Location location;
    FusedLocationProviderClient fusedLocationProviderClient;
    Geocoder geocoder;
    List<Address> addresses;
    List<locatie> locatieList = new ArrayList<locatie>();
    FirebaseDatabase database = FirebaseDatabase.getInstance("https://flymaster-default-rtdb.europe-west1.firebasedatabase.app/");
    DatabaseReference myRef = database.getReference("locatie");
    locatie Loc;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_harta);
        addListenerOnButton();

        satelit = findViewById(R.id.satelit);
        teren = findViewById(R.id.teren);

        fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(this);

        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);


    }
    @SuppressLint("MissingPermission")


    public void onMapReady(GoogleMap googleMap) {
        map = googleMap;
        map.setMapType(GoogleMap.MAP_TYPE_HYBRID);

        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NotNull DataSnapshot dataSnapshot) {


                Iterable<DataSnapshot> children = dataSnapshot.getChildren();
                for (DataSnapshot child : children) {

                    Loc = child.getValue(com.example.fly.Model.locatie.class);

                    LatLng latLng = new LatLng(Loc.getLatitudine(), Loc.getLongitudine());
                    map.addMarker(new MarkerOptions().position(latLng).title(Loc.getNume())).setTag(Loc);
                    locatieList.add(Loc);


                }

            }

            @Override
            public void onCancelled(DatabaseError error) {

            }
        });


        satelit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                satelit.setBackgroundColor(Color.BLUE);
                teren.setBackgroundColor(Color.WHITE);
                map.setMapType(GoogleMap.MAP_TYPE_HYBRID);


            }
        });

        teren.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                teren.setBackgroundColor(Color.BLUE);
                satelit.setBackgroundColor(Color.WHITE);
                map.setMapType(GoogleMap.MAP_TYPE_TERRAIN);


            }
        });



        map.setOnMarkerClickListener(new GoogleMap.OnMarkerClickListener() {
            @Override
            public boolean onMarkerClick(Marker marker) {

                if(!marker.getTitle().equals("ME")) {
                    Intent i = new Intent(Harta.this, PopActivity.class);
                    LatLng position = marker.getPosition();
                    i.putExtra("lat", position.latitude);
                    i.putExtra("lon", position.longitude);
                    i.putExtra("nume", marker.getTitle());
                    locatie data = (locatie) marker.getTag();
                    i.putExtra("Data", data);

                    startActivity(i);
                }
                return false;
            }
        });

        fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(this);

        fusedLocationProviderClient.getLastLocation().addOnCompleteListener(new OnCompleteListener<Location>() {
            @Override
            public void onComplete(@NonNull Task<Location> task) {
                location = task.getResult();


                if (location != null) {


                    try {

                        geocoder = new Geocoder(Harta.this, Locale.getDefault());


                        addresses = geocoder.getFromLocation(location.getLatitude(), location.getLongitude(), 1);

                        LatLng latLng = new LatLng(location.getLatitude(), location.getLongitude());
                        map.addMarker(new MarkerOptions().position(latLng).title("ME").icon(BitmapDescriptorFactory.fromResource(R.drawable.human)));


                        CameraPosition cameraPosition = new CameraPosition.Builder()
                                .target(latLng)
                                .zoom(15)
                                .build();

                        map.moveCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));




                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        });



    }


    public void addListenerOnButton() {

        final Context context = this;

        button = (Button) findViewById(R.id.back);

        button.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {

                Log.d("CASABLANCA",String.valueOf(FirebaseDatabase.getInstance()));

                finish();

            }

        });


    }

    @SuppressLint("MissingPermission")
    private void getLocation() {


    }

}