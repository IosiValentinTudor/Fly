package com.example.fly;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Looper;
import android.util.JsonWriter;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.RotateAnimation;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.fly.Model.Dialog;
import com.example.fly.Model.SaveFlight;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationCallback;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationResult;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.gson.Gson;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import static java.lang.Math.ceil;

public class LocatieActivity extends AppCompatActivity implements SensorEventListener, Dialog.DialogListener {

    /**declare de variabile **/
    Button button,start;
    TextView lati, longi, alt,progres,name1,name2;
    double altv, latv, longiv,maxalt=0,startlat,startlon;
    float maxspeed;
    String startcity;
    Location location;
    String stringdouble;
    int ok=0,ok2=0;
    SaveFlight saveFlight = new SaveFlight();

    FusedLocationProviderClient fusedLocationProviderClient;
    Geocoder geocoder;

    private LocationCallback locationCallback;
    MediaPlayer ascending;
    MediaPlayer descending;
    ImageView compass;
    private float[] mGravity= new float[3];
    private float[] mGeomantic= new float[3];
    float azimuth=0f;
    float currectAzimuth=0f;
    private SensorManager mSensnorManager;
    Animation frombottom;
    List<Address> addresses;
    NumberFormat formatter = new DecimalFormat("#0.0000000");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_locatie);
        ascending = MediaPlayer.create(this, R.raw.ascending);
        descending = MediaPlayer.create(this, R.raw.descending);


        addListenerOnButton();
        addListenerOnButtonConvert();
        addListenerOnButtonstart();


        lati = (TextView) findViewById(R.id.latitudine);
        longi = (TextView) findViewById(R.id.longitudine);
        alt = (TextView) findViewById(R.id.altitudine);
        progres = (TextView) findViewById(R.id.progres);
        name1 = (TextView) findViewById(R.id.name1);
        name2 = (TextView) findViewById(R.id.name2);




        frombottom = AnimationUtils.loadAnimation(this, R.anim.frombottom);
        compass = (ImageView) findViewById(R.id.compass);
        mSensnorManager = (SensorManager)getSystemService(SENSOR_SERVICE);

        frombottom = AnimationUtils.loadAnimation(this, R.anim.frombottom);






        fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(this);


        if (ActivityCompat.checkSelfPermission(LocatieActivity.this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {

                   getLocation();
        } else {
            ActivityCompat.requestPermissions(LocatieActivity.this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 44);
        }

        locationCallback = new LocationCallback() {
            @Override
            public void onLocationResult(LocationResult locationResult) {
                if (locationResult == null) {
                    return;
                }
                for (Location location : locationResult.getLocations()) {

                }
            }
        };



    }

    public void addListenerOnButton() {

        final Context context = this;

        button = (Button) findViewById(R.id.back);

        button.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {


                ok = 0;
                finish();

            }

        });
    }

    public void addListenerOnButtonstart() {

        final Context context = this;

        start = (Button) findViewById(R.id.start);

        start.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                if(ok==0) {
                    ok = 1;
                    start.setText("Stop Flight");
                    progres.setVisibility(View.VISIBLE);

                    startlat=location.getLatitude();
                    startlon=location.getLongitude();
                    startcity=addresses.get(0).getLocality();
                    saveFlight.setNume("NULL");

                    maxalt=0;
                    maxspeed=0;

                    Calendar c = Calendar.getInstance();
                    Date datet = c.getTime();

                    saveFlight.setStartDate(datet);

                }
                else
                {

                    Calendar c = Calendar.getInstance();
                    Date datet = c.getTime();

                    saveFlight.setAlt(maxalt);
                    saveFlight.setSpeed(maxspeed);
                    saveFlight.setLat(startlat);
                    saveFlight.setLon(startlon);
                    saveFlight.setCity(startcity);
                    saveFlight.setEndDate(datet);

                    //openDialog();

                    ok=0;
                    start.setText("Start Flight");
                    progres.setVisibility(View.INVISIBLE);

                }

            }

        });
    }


    public void openDialog()
    {
        Dialog dialog = new Dialog();
        dialog.show(getSupportFragmentManager(), "Save");

    }

    @Override
    public void saveName(String name) {
        saveFlight.setNume(name);


        if(!saveFlight.getNume().equals(null))
        {

        }
        else
        {

        }


        try {
            writeFlight();
        } catch (IOException e) {
            e.printStackTrace();
        }


    }


    public void writeFlight() throws IOException {


        Gson gson = new Gson();
        String json = gson.toJson(saveFlight);


    }





    public void addListenerOnButtonConvert() {



        button = (Button) findViewById(R.id.convert);

        button.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {


                if (ok2==0) {
                    lati.setText(addresses.get(0).getAdminArea());
                    name1.setText("City");
                    longi.setText(addresses.get(0).getLocality());
                    name2.setText("State");
                    ok2=1;
                }
                else
                {
                    lati.setText(String.format("%.7f",location.getLatitude()));
                    name1.setText("Latitudine");
                    longi.setText(String.format("%.7f",location.getLongitude()));
                    name2.setText("Longitudine");
                    ok2=0;

                }

            }

        });
    }

    @SuppressLint("MissingPermission")


    private void getLocation() {
        fusedLocationProviderClient.getLastLocation().addOnCompleteListener(new OnCompleteListener<Location>() {
            @Override
            public void onComplete(@NonNull Task<Location> task) {
                location = task.getResult();


                if (location != null) {


                    try {

                        geocoder = new Geocoder(LocatieActivity.this, Locale.getDefault());


                        addresses = geocoder.getFromLocation(location.getLatitude(), location.getLongitude(), 1);

                        latv = addresses.get(0).getLatitude();
                        stringdouble = String.format("%.7f",latv);
                        lati.setText(stringdouble);

                        longiv = addresses.get(0).getLongitude();
                        stringdouble = String.format("%.7f",longiv);
                        longi.setText(stringdouble);

                        altv = ceil(location.getAltitude());
                        stringdouble = Double.toString(altv);
                        alt.setText(stringdouble);



                        requestNewLocationData();

                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        });

    }


    private LocationCallback mLocationCallback = new LocationCallback() {
        @Override
        public void onLocationResult(LocationResult locationResult) {
            Location mLastLocation = locationResult.getLastLocation();



            if(ok2==0) {
                latv = mLastLocation.getLatitude();
                stringdouble = String.format("%.7f",latv);
                lati.setText(stringdouble);

                longiv = mLastLocation.getLongitude();
                stringdouble = String.format("%.7f",longiv);
                longi.setText(stringdouble);
            }

            maxalt=altv;
            if(altv > mLastLocation.getAltitude() && ok==1 && altv-mLastLocation.getAltitude()>1)
            {
                descending.start();
                maxalt=altv;
            }
            else
            {
                if(altv < mLastLocation.getAltitude() && ok==1 && mLastLocation.getAltitude()-altv>1)
                {
                    ascending.start();
                    maxalt=mLastLocation.getAltitude();
                }
            }


            if(maxspeed < mLastLocation.getSpeed() && ok==1)
            {
                maxspeed=mLastLocation.getSpeed();
            }


            altv = ceil(mLastLocation.getAltitude());
            stringdouble = Double.toString(altv);
            alt.setText(stringdouble);


            try {
                addresses = geocoder.getFromLocation(location.getLatitude(), location.getLongitude(), 1);
                String cityName = addresses.get(0).getLocality();


                String stateName = addresses.get(0).getAdminArea();


                String countryName = addresses.get(0).getCountryName();



            } catch (IOException e) {
                e.printStackTrace();
            }

        }
    };

    @SuppressLint("MissingPermission")
    private void requestNewLocationData() {

        LocationRequest mLocationRequest = new LocationRequest();
        mLocationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);
        mLocationRequest.setInterval(2000);
        mLocationRequest.setFastestInterval(2000);

        fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(this);
        fusedLocationProviderClient.requestLocationUpdates(
                mLocationRequest, mLocationCallback,
                Looper.myLooper()
        );

    }

    @Override
    protected void onResume(){
        super.onResume();
        mSensnorManager.registerListener(this,mSensnorManager.getDefaultSensor(Sensor.TYPE_MAGNETIC_FIELD),SensorManager.SENSOR_DELAY_GAME);
        mSensnorManager.registerListener(this,mSensnorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER),SensorManager.SENSOR_DELAY_GAME);

    }

    @Override
    protected void onPause(){
        super.onPause();
        mSensnorManager.unregisterListener(this);

    }


    @Override
    public void onSensorChanged(SensorEvent event) {

        final float alpha=0.97f;
        synchronized (this){

            if(event.sensor.getType() == Sensor.TYPE_ACCELEROMETER)
            {
                mGravity[0] = alpha*mGravity[0]+(1-alpha)*event.values[0];
                mGravity[1] = alpha*mGravity[1]+(1-alpha)*event.values[1];
                mGravity[2] = alpha*mGravity[2]+(1-alpha)*event.values[2];
            }
            if(event.sensor.getType() == Sensor.TYPE_MAGNETIC_FIELD)
            {
                mGeomantic[0] = alpha*mGeomantic[0]+(1-alpha)*event.values[0];
                mGeomantic[1] = alpha*mGeomantic[1]+(1-alpha)*event.values[1];
                mGeomantic[2] = alpha*mGeomantic[2]+(1-alpha)*event.values[2];
            }

            float R[] = new float[9];
            float I[] = new float[9];
            boolean succes = SensorManager.getRotationMatrix(R,I,mGravity,mGeomantic);
            if(succes)
            {
                float orientation[] = new float[3];
                SensorManager.getOrientation(R,orientation);
                azimuth = (float)Math.toDegrees(orientation[0]);
                azimuth = (azimuth+360)%360;
                Animation anim = new RotateAnimation(-currectAzimuth,-azimuth,Animation.RELATIVE_TO_SELF,0.5f,Animation.RELATIVE_TO_SELF,0.5f);
                currectAzimuth=azimuth;
                anim.setDuration(100);
                anim.setRepeatCount(0);
                anim.setFillAfter(true);

                compass.startAnimation(anim);

            }

        }



    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }
}

