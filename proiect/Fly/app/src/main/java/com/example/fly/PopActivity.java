package com.example.fly;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.fly.Common.Common;
import com.example.fly.Model.WeatherResult;
import com.example.fly.Model.locatie;
import com.example.fly.Retrofit.IOpenWeatherMap;
import com.example.fly.Retrofit.RetrofitClient;
import com.google.android.gms.maps.model.LatLng;
import com.squareup.picasso.Picasso;

import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;

import static java.lang.Math.round;

public class PopActivity extends Activity {

    double lat;
    double lon;
    double vant,vantSpeed;
    String NumeLocatie;
    Button button,expand;
    TextView nume,temperatura,vitezavant,directievant,favorabil,umiditate,descriere;
    ImageView imagine;
    String descriereVreme;
    CompositeDisposable compositeDisposable;
    IOpenWeatherMap mService;
    Retrofit retrofit;
    locatie data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pop);
        addListenerOnButton();


        compositeDisposable = new CompositeDisposable();
        retrofit= RetrofitClient.getInstance();
        mService = retrofit.create(IOpenWeatherMap.class);



        imagine=(ImageView) findViewById(R.id.imagine);
        nume = (TextView) findViewById(R.id.nume);
        temperatura = (TextView) findViewById(R.id.temperatura);
        vitezavant = (TextView) findViewById(R.id.vitezavant);
        directievant = (TextView) findViewById(R.id.directievant);
        favorabil = (TextView) findViewById(R.id.favorabil);
        umiditate= (TextView) findViewById(R.id.umiditate);
        descriere = findViewById(R.id.descriere);




        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getRealMetrics(dm);
        int width =dm.widthPixels;
        int height= dm.heightPixels;

        getWindow().setLayout((int)(width*.7),(int)(height*.5));

        WindowManager.LayoutParams params = getWindow().getAttributes();
        params.gravity = Gravity.CENTER;
        params.x = 0;
        params.y = -20;
        getWindow().setAttributes(params);
        lat = getIntent().getDoubleExtra("lat",45.862343);
        lon = getIntent().getDoubleExtra("lon",0);
        NumeLocatie = getIntent().getStringExtra("nume");
        getWeatherInformation();


        data = (locatie) getIntent().getSerializableExtra("Data");

        addListenerOnButton2();

    }

    private void getWeatherInformation()
    {

        compositeDisposable.add((Disposable) mService.getWeatherByLatLng(String.valueOf(lat),
                String.valueOf(lon),
                Common.APP_ID,
                "metric")
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<WeatherResult>() {

                               @Override
                               public void accept(WeatherResult weatherResult) throws Exception {

                                   Picasso.get().load(new StringBuilder("https://openweathermap.org/img/w/").append(weatherResult.getWeather().get(0).getIcon()).append(".png").toString()).into(imagine);

                                   temperatura.setText(new StringBuilder(String.valueOf(round(weatherResult.getMain().getTemp()))).append("°C").toString() );
                                   descriere.setText(weatherResult.getWeather().get(0).getDescription());
                                   vitezavant.setText(new StringBuilder(String.valueOf(weatherResult.getWind().getSpeed())).append("m/s"));
                                   umiditate.setText(new StringBuilder(String.valueOf(weatherResult.getMain().getHumidity())).append("%"));
                                   nume.setText(NumeLocatie);
                                   vant=weatherResult.getWind().getDeg();
                                   vantSpeed=weatherResult.getWind().getSpeed();
                                   descriereVreme=weatherResult.getWeather().get(0).getDescription();
                                   setWeather();



                               }
                               }, new Consumer<Throwable>() {
                               @Override
                               public void accept(Throwable throwable) throws Exception {

                                    Toast.makeText(PopActivity.this ,""+throwable.getMessage(),Toast.LENGTH_SHORT).show();

                               }
                           }


                ));


    }



    public void setWeather()
    {

        String conv="";
        if(vant>=338 || vant<23)
        {
            conv="N";
        }
        else {
            if (vant >= 23 && vant < 67) {
                conv = "NE";
            } else {
                if (vant >= 67 && vant < 112) {
                    conv = "E";
                } else {
                    if (vant >= 112 && vant < 157) {
                        conv = "SE";
                    } else {
                        if (vant >= 157 && vant < 203) {
                            conv = "S";
                        } else {
                            if (vant >= 203 && vant < 248) {
                                conv = "SV";
                            } else {
                                if (vant >= 248 && vant < 293) {
                                    conv = "V";
                                } else {
                                    if (vant >= 293 && vant < 338) {
                                        conv = "NV";
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }


        directievant.setText(conv);


        if(descriereVreme.equals("light rain") || descriereVreme.equals("moderate rain") )
        {
            favorabil.setText(new StringBuilder("The conditions for the selected time don't allow paragliding: rain expected"));
        }
        else {
            if (!conv.equals(data.getVant()) && vantSpeed < 0.8) {
                favorabil.setText(new StringBuilder("The current conditions don't allow paragliding: wind should blow from ").append(data.getVant()).append(" and the wind speed is to low"));
            } else {
                if (!conv.equals(data.getVant())) {
                    favorabil.setText(new StringBuilder("The current conditions don't allow paragliding: wind should blow from ").append(data.getVant()));
                } else {
                    if (vantSpeed < 0.8) {
                        favorabil.setText(new StringBuilder("The current conditions don't allow paragliding: the wind speed is to low"));
                    } else {
                        favorabil.setText("The current conditions allow paragliding");
                    }
                }
            }

        }



    }

    public void addListenerOnButton() {

        final Context context = this;

        button = (Button) findViewById(R.id.close);

        button.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {

                finish();

            }

        });
    }


    public void addListenerOnButton2() {

        final Context context = this;

        expand = (Button) findViewById(R.id.expand);

        expand.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {

                Intent intent = new Intent(PopActivity.this,Forecast.class);
                intent.putExtra("lat", lat);
                intent.putExtra("lon", lon);
                intent.putExtra("nume", NumeLocatie);
                intent.putExtra("Data",data);
                startActivity(intent);

            }

        });
    }

}