package com.example.fly;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.fly.Common.Common;
import com.example.fly.Model.DataW;
import com.example.fly.Model.WeatherForecastResult;
import com.example.fly.Model.WeatherResult;
import com.example.fly.Model.locatie;
import com.example.fly.Retrofit.IOpenWeatherMap;
import com.example.fly.Retrofit.RetrofitClient;
import com.squareup.picasso.Picasso;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;

import static java.lang.Math.round;


public class Forecast extends AppCompatActivity {


    LinearLayout Layoutora1, Layoutora2, Layoutora3, Layoutora4, Layoutora5, Layoutora6, Layoutora7, Layoutora8;
    LinearLayout day1, day2, day3, day4, day5;
    TextView interval1, interval2, interval3, interval4, interval5;
    TextView dayText1, dayText2, dayText3, dayText4, dayText5;
    TextView textora1, textora2, textora3, textora4, textora5, textora6, textora7, textora8;
    TextView tempora1, tempora2, tempora3, tempora4, tempora5, tempora6, tempora7, tempora8;
    TextView conditiedec, conditieate, warning;
    ImageView imgora1, imgora2, imgora3, imgora4, imgora5, imgora6, imgora7, imgora8;
    ImageView zborora1, zborora2, zborora3, zborora4, zborora5, zborora6, zborora7, zborora8;
    ImageView weatherIcon, warningicon;
    TextView location, day, date, grade, descriere, vitezavant, directievant, umiditate, favorabil;
    Drawable draw1, draw2, draw3, draw4;
    String NumeLocatie;
    double lat, lon;
    WeatherForecastResult weatherForecastResult;
    CompositeDisposable compositeDisposable;
    IOpenWeatherMap mService;
    Retrofit retrofit;
    DataW[] dataW = new DataW[40];
    locatie data;
    String conv;

    int dayNr = 0, hour = 0;


    public int getItemCount() {
        return weatherForecastResult.getList().size();
    }


    @SuppressLint("ResourceType")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forecast);

        compositeDisposable = new CompositeDisposable();
        retrofit = RetrofitClient.getInstance();
        mService = retrofit.create(IOpenWeatherMap.class);

        Layoutora1 = findViewById(R.id.Layoutora1);
        Layoutora2 = findViewById(R.id.Layoutora2);
        Layoutora3 = findViewById(R.id.Layoutora3);
        Layoutora4 = findViewById(R.id.Layoutora4);
        Layoutora5 = findViewById(R.id.Layoutora5);
        Layoutora6 = findViewById(R.id.Layoutora6);
        Layoutora7 = findViewById(R.id.Layoutora7);
        Layoutora8 = findViewById(R.id.Layoutora8);

        textora1 = (TextView) findViewById(R.id.textora1);
        textora2 = (TextView) findViewById(R.id.textora2);
        textora3 = (TextView) findViewById(R.id.textora3);
        textora4 = (TextView) findViewById(R.id.textora4);
        textora5 = (TextView) findViewById(R.id.textora5);
        textora6 = (TextView) findViewById(R.id.textora6);
        textora7 = (TextView) findViewById(R.id.textora7);
        textora8 = (TextView) findViewById(R.id.textora8);

        tempora1 = (TextView) findViewById(R.id.tempora1);
        tempora2 = (TextView) findViewById(R.id.tempora2);
        tempora3 = (TextView) findViewById(R.id.tempora3);
        tempora4 = (TextView) findViewById(R.id.tempora4);
        tempora5 = (TextView) findViewById(R.id.tempora5);
        tempora6 = (TextView) findViewById(R.id.tempora6);
        tempora7 = (TextView) findViewById(R.id.tempora7);
        tempora8 = (TextView) findViewById(R.id.tempora8);

        day1 = findViewById(R.id.day1);
        day2 = findViewById(R.id.day2);
        day3 = findViewById(R.id.day3);
        day4 = findViewById(R.id.day4);
        day5 = findViewById(R.id.day5);

        imgora1 = findViewById(R.id.imgora1);
        imgora2 = findViewById(R.id.imgora2);
        imgora3 = findViewById(R.id.imgora3);
        imgora4 = findViewById(R.id.imgora4);
        imgora5 = findViewById(R.id.imgora5);
        imgora6 = findViewById(R.id.imgora6);
        imgora7 = findViewById(R.id.imgora7);
        imgora8 = findViewById(R.id.imgora8);

        zborora1 = findViewById(R.id.zborora1);
        zborora2 = findViewById(R.id.zborora2);
        zborora3 = findViewById(R.id.zborora3);
        zborora4 = findViewById(R.id.zborora4);
        zborora5 = findViewById(R.id.zborora5);
        zborora6 = findViewById(R.id.zborora6);
        zborora7 = findViewById(R.id.zborora7);
        zborora8 = findViewById(R.id.zborora8);
        zborora1.setVisibility(View.INVISIBLE);
        zborora2.setVisibility(View.INVISIBLE);
        zborora3.setVisibility(View.INVISIBLE);
        zborora4.setVisibility(View.INVISIBLE);
        zborora5.setVisibility(View.INVISIBLE);
        zborora6.setVisibility(View.INVISIBLE);
        zborora7.setVisibility(View.INVISIBLE);
        zborora8.setVisibility(View.INVISIBLE);

        dayText1 = findViewById(R.id.dayText1);
        dayText2 = findViewById(R.id.dayText2);
        dayText3 = findViewById(R.id.dayText3);
        dayText4 = findViewById(R.id.dayText4);
        dayText5 = findViewById(R.id.dayText5);

        interval1 = findViewById(R.id.interval1);
        interval2 = findViewById(R.id.interval2);
        interval3 = findViewById(R.id.interval3);
        interval4 = findViewById(R.id.interval4);
        interval5 = findViewById(R.id.interval5);

        grade = findViewById(R.id.grade);
        weatherIcon = findViewById(R.id.weatherIcon);
        vitezavant = findViewById(R.id.vitezavant);
        descriere = findViewById(R.id.descriere);
        umiditate = findViewById(R.id.umiditate);
        directievant = findViewById(R.id.directievant);
        favorabil = findViewById(R.id.favorabil);

        location = findViewById(R.id.location);
        day = findViewById(R.id.day);
        date = findViewById(R.id.date);

        conditiedec = findViewById(R.id.conditiedec);
        conditieate = findViewById(R.id.conditieate);
        warning = findViewById(R.id.warning);
        warningicon = findViewById(R.id.warningicon);

        NumeLocatie = getIntent().getStringExtra("nume");
        lat = getIntent().getDoubleExtra("lat", 45.862343);
        lon = getIntent().getDoubleExtra("lon", 23.043992);

        data = (locatie) getIntent().getSerializableExtra("Data");

        conditiedec.setText(new StringBuilder(String.valueOf(data.getDecolare())).append("/10"));
        conditieate.setText(new StringBuilder(String.valueOf(data.getAterizare())).append("/10"));

        setDate();

        if (data.getAterizare() > 5 && data.getDecolare() > 5) {
            warning.setVisibility(View.INVISIBLE);
            warningicon.setVisibility(View.INVISIBLE);
        }


        location.setText(NumeLocatie);

        draw1 = getResources().getDrawable(R.drawable.hour_custom);
        draw2 = getResources().getDrawable(R.drawable.unselected_hour);
        draw3 = getResources().getDrawable(R.drawable.custom_day);
        draw4 = getResources().getDrawable(R.drawable.unselected_day);

        Layoutora1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                hour = 0;

                Layoutora1.setBackground(draw1);
                Layoutora2.setBackground(draw2);
                Layoutora3.setBackground(draw2);
                Layoutora4.setBackground(draw2);
                Layoutora5.setBackground(draw2);
                Layoutora6.setBackground(draw2);
                Layoutora7.setBackground(draw2);
                Layoutora8.setBackground(draw2);

                tempora1.setTextColor(Color.WHITE);
                tempora2.setTextColor(Color.BLACK);
                tempora3.setTextColor(Color.BLACK);
                tempora4.setTextColor(Color.BLACK);
                tempora5.setTextColor(Color.BLACK);
                tempora6.setTextColor(Color.BLACK);
                tempora7.setTextColor(Color.BLACK);
                tempora8.setTextColor(Color.BLACK);

                textora1.setTextColor(Color.WHITE);
                textora2.setTextColor(Color.BLACK);
                textora3.setTextColor(Color.BLACK);
                textora4.setTextColor(Color.BLACK);
                textora5.setTextColor(Color.BLACK);
                textora6.setTextColor(Color.BLACK);
                textora7.setTextColor(Color.BLACK);
                textora8.setTextColor(Color.BLACK);


                getForecastWeather();


            }
        });

        Layoutora2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                hour = 1;

                Layoutora1.setBackground(draw2);
                Layoutora2.setBackground(draw1);
                Layoutora3.setBackground(draw2);
                Layoutora4.setBackground(draw2);
                Layoutora5.setBackground(draw2);
                Layoutora6.setBackground(draw2);
                Layoutora7.setBackground(draw2);
                Layoutora8.setBackground(draw2);

                tempora1.setTextColor(Color.BLACK);
                tempora2.setTextColor(Color.WHITE);
                tempora3.setTextColor(Color.BLACK);
                tempora4.setTextColor(Color.BLACK);
                tempora5.setTextColor(Color.BLACK);
                tempora6.setTextColor(Color.BLACK);
                tempora7.setTextColor(Color.BLACK);
                tempora8.setTextColor(Color.BLACK);

                textora1.setTextColor(Color.BLACK);
                textora2.setTextColor(Color.WHITE);
                textora3.setTextColor(Color.BLACK);
                textora4.setTextColor(Color.BLACK);
                textora5.setTextColor(Color.BLACK);
                textora6.setTextColor(Color.BLACK);
                textora7.setTextColor(Color.BLACK);
                textora8.setTextColor(Color.BLACK);

                getForecastWeather();


            }
        });

        Layoutora3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                hour = 2;

                Layoutora1.setBackground(draw2);
                Layoutora2.setBackground(draw2);
                Layoutora3.setBackground(draw1);
                Layoutora4.setBackground(draw2);
                Layoutora5.setBackground(draw2);
                Layoutora6.setBackground(draw2);
                Layoutora7.setBackground(draw2);
                Layoutora8.setBackground(draw2);

                tempora1.setTextColor(Color.BLACK);
                tempora2.setTextColor(Color.BLACK);
                tempora3.setTextColor(Color.WHITE);
                tempora4.setTextColor(Color.BLACK);
                tempora5.setTextColor(Color.BLACK);
                tempora6.setTextColor(Color.BLACK);
                tempora7.setTextColor(Color.BLACK);
                tempora8.setTextColor(Color.BLACK);

                textora1.setTextColor(Color.BLACK);
                textora2.setTextColor(Color.BLACK);
                textora3.setTextColor(Color.WHITE);
                textora4.setTextColor(Color.BLACK);
                textora5.setTextColor(Color.BLACK);
                textora6.setTextColor(Color.BLACK);
                textora7.setTextColor(Color.BLACK);
                textora8.setTextColor(Color.BLACK);

                getForecastWeather();


            }
        });


        Layoutora4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                hour = 3;

                Layoutora1.setBackground(draw2);
                Layoutora2.setBackground(draw2);
                Layoutora3.setBackground(draw2);
                Layoutora4.setBackground(draw1);
                Layoutora5.setBackground(draw2);
                Layoutora6.setBackground(draw2);
                Layoutora7.setBackground(draw2);
                Layoutora8.setBackground(draw2);

                tempora1.setTextColor(Color.BLACK);
                tempora2.setTextColor(Color.BLACK);
                tempora3.setTextColor(Color.BLACK);
                tempora4.setTextColor(Color.WHITE);
                tempora5.setTextColor(Color.BLACK);
                tempora6.setTextColor(Color.BLACK);
                tempora7.setTextColor(Color.BLACK);
                tempora8.setTextColor(Color.BLACK);

                textora1.setTextColor(Color.BLACK);
                textora2.setTextColor(Color.BLACK);
                textora3.setTextColor(Color.BLACK);
                textora4.setTextColor(Color.WHITE);
                textora5.setTextColor(Color.BLACK);
                textora6.setTextColor(Color.BLACK);
                textora7.setTextColor(Color.BLACK);
                textora8.setTextColor(Color.BLACK);

                getForecastWeather();


            }
        });


        Layoutora5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                hour = 4;

                Layoutora1.setBackground(draw2);
                Layoutora2.setBackground(draw2);
                Layoutora3.setBackground(draw2);
                Layoutora4.setBackground(draw2);
                Layoutora5.setBackground(draw1);
                Layoutora6.setBackground(draw2);
                Layoutora7.setBackground(draw2);
                Layoutora8.setBackground(draw2);

                tempora1.setTextColor(Color.BLACK);
                tempora2.setTextColor(Color.BLACK);
                tempora3.setTextColor(Color.BLACK);
                tempora4.setTextColor(Color.BLACK);
                tempora5.setTextColor(Color.WHITE);
                tempora6.setTextColor(Color.BLACK);
                tempora7.setTextColor(Color.BLACK);
                tempora8.setTextColor(Color.BLACK);

                textora1.setTextColor(Color.BLACK);
                textora2.setTextColor(Color.BLACK);
                textora3.setTextColor(Color.BLACK);
                textora4.setTextColor(Color.BLACK);
                textora5.setTextColor(Color.WHITE);
                textora6.setTextColor(Color.BLACK);
                textora7.setTextColor(Color.BLACK);
                textora8.setTextColor(Color.BLACK);

                getForecastWeather();


            }
        });


        Layoutora6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                hour = 5;

                Layoutora1.setBackground(draw2);
                Layoutora2.setBackground(draw2);
                Layoutora3.setBackground(draw2);
                Layoutora4.setBackground(draw2);
                Layoutora5.setBackground(draw2);
                Layoutora6.setBackground(draw1);
                Layoutora7.setBackground(draw2);
                Layoutora8.setBackground(draw2);

                tempora1.setTextColor(Color.BLACK);
                tempora2.setTextColor(Color.BLACK);
                tempora3.setTextColor(Color.BLACK);
                tempora4.setTextColor(Color.BLACK);
                tempora5.setTextColor(Color.BLACK);
                tempora6.setTextColor(Color.WHITE);
                tempora7.setTextColor(Color.BLACK);
                tempora8.setTextColor(Color.BLACK);

                textora1.setTextColor(Color.BLACK);
                textora2.setTextColor(Color.BLACK);
                textora3.setTextColor(Color.BLACK);
                textora4.setTextColor(Color.BLACK);
                textora5.setTextColor(Color.BLACK);
                textora6.setTextColor(Color.WHITE);
                textora7.setTextColor(Color.BLACK);
                textora8.setTextColor(Color.BLACK);

                getForecastWeather();


            }
        });


        Layoutora7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                hour = 6;

                Layoutora1.setBackground(draw2);
                Layoutora2.setBackground(draw2);
                Layoutora3.setBackground(draw2);
                Layoutora4.setBackground(draw2);
                Layoutora5.setBackground(draw2);
                Layoutora6.setBackground(draw2);
                Layoutora7.setBackground(draw1);
                Layoutora8.setBackground(draw2);

                tempora1.setTextColor(Color.BLACK);
                tempora2.setTextColor(Color.BLACK);
                tempora3.setTextColor(Color.BLACK);
                tempora4.setTextColor(Color.BLACK);
                tempora5.setTextColor(Color.BLACK);
                tempora6.setTextColor(Color.BLACK);
                tempora7.setTextColor(Color.WHITE);
                tempora8.setTextColor(Color.BLACK);

                textora1.setTextColor(Color.BLACK);
                textora2.setTextColor(Color.BLACK);
                textora3.setTextColor(Color.BLACK);
                textora4.setTextColor(Color.BLACK);
                textora5.setTextColor(Color.BLACK);
                textora6.setTextColor(Color.BLACK);
                textora7.setTextColor(Color.WHITE);
                textora8.setTextColor(Color.BLACK);
                getForecastWeather();


            }
        });


        Layoutora8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                hour = 7;

                Layoutora1.setBackground(draw2);
                Layoutora2.setBackground(draw2);
                Layoutora3.setBackground(draw2);
                Layoutora4.setBackground(draw2);
                Layoutora5.setBackground(draw2);
                Layoutora6.setBackground(draw2);
                Layoutora7.setBackground(draw2);
                Layoutora8.setBackground(draw1);

                tempora1.setTextColor(Color.BLACK);
                tempora2.setTextColor(Color.BLACK);
                tempora3.setTextColor(Color.BLACK);
                tempora4.setTextColor(Color.BLACK);
                tempora5.setTextColor(Color.BLACK);
                tempora6.setTextColor(Color.BLACK);
                tempora7.setTextColor(Color.BLACK);
                tempora8.setTextColor(Color.WHITE);

                textora1.setTextColor(Color.BLACK);
                textora2.setTextColor(Color.BLACK);
                textora3.setTextColor(Color.BLACK);
                textora4.setTextColor(Color.BLACK);
                textora5.setTextColor(Color.BLACK);
                textora6.setTextColor(Color.BLACK);
                textora7.setTextColor(Color.BLACK);
                textora8.setTextColor(Color.WHITE);
                getForecastWeather();


            }
        });


        day1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                dayNr = 0;

                day1.setBackground(draw3);
                day2.setBackground(draw4);
                day3.setBackground(draw4);
                day4.setBackground(draw4);
                day5.setBackground(draw4);

                interval1.setTextColor(Color.WHITE);
                interval2.setTextColor(Color.BLACK);
                interval3.setTextColor(Color.BLACK);
                interval4.setTextColor(Color.BLACK);
                interval5.setTextColor(Color.BLACK);

                dayText1.setTextColor(Color.WHITE);
                dayText2.setTextColor(Color.BLACK);
                dayText3.setTextColor(Color.BLACK);
                dayText4.setTextColor(Color.BLACK);
                dayText5.setTextColor(Color.BLACK);

                getForecastWeather();

            }

        });

        day2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                dayNr = 8;

                day1.setBackground(draw4);
                day2.setBackground(draw3);
                day3.setBackground(draw4);
                day4.setBackground(draw4);
                day5.setBackground(draw4);

                interval1.setTextColor(Color.BLACK);
                interval2.setTextColor(Color.WHITE);
                interval3.setTextColor(Color.BLACK);
                interval4.setTextColor(Color.BLACK);
                interval5.setTextColor(Color.BLACK);

                dayText1.setTextColor(Color.BLACK);
                dayText2.setTextColor(Color.WHITE);
                dayText3.setTextColor(Color.BLACK);
                dayText4.setTextColor(Color.BLACK);
                dayText5.setTextColor(Color.BLACK);

                getForecastWeather();

            }

        });
        day3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                dayNr = 16;

                day1.setBackground(draw4);
                day2.setBackground(draw4);
                day3.setBackground(draw3);
                day4.setBackground(draw4);
                day5.setBackground(draw4);

                interval1.setTextColor(Color.BLACK);
                interval2.setTextColor(Color.BLACK);
                interval3.setTextColor(Color.WHITE);
                interval4.setTextColor(Color.BLACK);
                interval5.setTextColor(Color.BLACK);

                dayText1.setTextColor(Color.BLACK);
                dayText2.setTextColor(Color.BLACK);
                dayText3.setTextColor(Color.WHITE);
                dayText4.setTextColor(Color.BLACK);
                dayText5.setTextColor(Color.BLACK);

                getForecastWeather();

            }

        });
        day4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                dayNr = 24;

                day1.setBackground(draw4);
                day2.setBackground(draw4);
                day3.setBackground(draw4);
                day4.setBackground(draw3);
                day5.setBackground(draw4);

                interval1.setTextColor(Color.BLACK);
                interval2.setTextColor(Color.BLACK);
                interval3.setTextColor(Color.BLACK);
                interval4.setTextColor(Color.WHITE);
                interval5.setTextColor(Color.BLACK);

                dayText1.setTextColor(Color.BLACK);
                dayText2.setTextColor(Color.BLACK);
                dayText3.setTextColor(Color.BLACK);
                dayText4.setTextColor(Color.WHITE);
                dayText5.setTextColor(Color.BLACK);

                getForecastWeather();

            }

        });
        day5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                dayNr = 32;

                day1.setBackground(draw4);
                day2.setBackground(draw4);
                day3.setBackground(draw4);
                day4.setBackground(draw4);
                day5.setBackground(draw3);

                interval1.setTextColor(Color.BLACK);
                interval2.setTextColor(Color.BLACK);
                interval3.setTextColor(Color.BLACK);
                interval4.setTextColor(Color.BLACK);
                interval5.setTextColor(Color.WHITE);

                dayText1.setTextColor(Color.BLACK);
                dayText2.setTextColor(Color.BLACK);
                dayText3.setTextColor(Color.BLACK);
                dayText4.setTextColor(Color.BLACK);
                dayText5.setTextColor(Color.WHITE);

                getForecastWeather();

            }

        });

        getForecastWeather();


    }

    private void getForecastWeather() {
        compositeDisposable.add((Disposable) mService.getForecastWeatherByLatLng(String.valueOf(lat),
                String.valueOf(lon),
                Common.APP_ID,
                "metric")
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<WeatherForecastResult>() {

                               @Override
                               public void accept(WeatherForecastResult weatherForecastResult) throws Exception {

                                   for (int i = 0; i < 40; i++) {

                                       dataW[i] = new DataW(weatherForecastResult.getList().get(i).getMain().getTemp());
                                       dataW[i].setClouds(weatherForecastResult.getList().get(i).getClouds().getAll());
                                       dataW[i].setDate(weatherForecastResult.getList().get(i).getDt_txt());
                                       dataW[i].setDescription(weatherForecastResult.getList().get(i).getWeather().get(0).getDescription());
                                       dataW[i].setHumidity(weatherForecastResult.getList().get(i).getMain().getHumidity());
                                       dataW[i].setIcon(weatherForecastResult.getList().get(i).getWeather().get(0).getIcon());
                                       dataW[i].setTemp(weatherForecastResult.getList().get(i).getMain().getTemp());
                                       dataW[i].setWindDeg(weatherForecastResult.getList().get(i).getWind().getDeg());
                                       dataW[i].setWindSpeed(weatherForecastResult.getList().get(i).getWind().getSpeed());


                                   }
                                   setWeather();

                               }
                           }, new Consumer<Throwable>() {
                               @Override
                               public void accept(Throwable throwable) throws Exception {

                                   Toast.makeText(Forecast.this, "" + throwable.getMessage(), Toast.LENGTH_SHORT).show();

                               }
                           }

                ));
    }


    private void setWeather() {


        tempora1.setText(new StringBuilder(String.valueOf(round(dataW[0 + dayNr].getTemp()))).append("°C").toString());
        tempora2.setText(new StringBuilder(String.valueOf(round(dataW[1 + dayNr].getTemp()))).append("°C").toString());
        tempora3.setText(new StringBuilder(String.valueOf(round(dataW[2 + dayNr].getTemp()))).append("°C").toString());
        tempora4.setText(new StringBuilder(String.valueOf(round(dataW[3 + dayNr].getTemp()))).append("°C").toString());
        tempora5.setText(new StringBuilder(String.valueOf(round(dataW[4 + dayNr].getTemp()))).append("°C").toString());
        tempora6.setText(new StringBuilder(String.valueOf(round(dataW[5 + dayNr].getTemp()))).append("°C").toString());
        tempora7.setText(new StringBuilder(String.valueOf(round(dataW[6 + dayNr].getTemp()))).append("°C").toString());
        tempora8.setText(new StringBuilder(String.valueOf(round(dataW[7 + dayNr].getTemp()))).append("°C").toString());


        Picasso.get().load(new StringBuilder("https://openweathermap.org/img/w/").append(dataW[0 + dayNr].getIcon()).append(".png").toString()).into(imgora1);
        Picasso.get().load(new StringBuilder("https://openweathermap.org/img/w/").append(dataW[1 + dayNr].getIcon()).append(".png").toString()).into(imgora2);
        Picasso.get().load(new StringBuilder("https://openweathermap.org/img/w/").append(dataW[2 + dayNr].getIcon()).append(".png").toString()).into(imgora3);
        Picasso.get().load(new StringBuilder("https://openweathermap.org/img/w/").append(dataW[3 + dayNr].getIcon()).append(".png").toString()).into(imgora4);
        Picasso.get().load(new StringBuilder("https://openweathermap.org/img/w/").append(dataW[4 + dayNr].getIcon()).append(".png").toString()).into(imgora5);
        Picasso.get().load(new StringBuilder("https://openweathermap.org/img/w/").append(dataW[5 + dayNr].getIcon()).append(".png").toString()).into(imgora6);
        Picasso.get().load(new StringBuilder("https://openweathermap.org/img/w/").append(dataW[6 + dayNr].getIcon()).append(".png").toString()).into(imgora7);
        Picasso.get().load(new StringBuilder("https://openweathermap.org/img/w/").append(dataW[7 + dayNr].getIcon()).append(".png").toString()).into(imgora8);


        grade.setText(new StringBuilder(String.valueOf(round(dataW[0 + dayNr + hour].getTemp()))).append("°C").toString());
        vitezavant.setText(new StringBuilder(String.valueOf(dataW[0 + dayNr + hour].getWindSpeed())).append("m/s").toString());
        directievant.setText(new StringBuilder(String.valueOf(dataW[0 + dayNr + hour].getWindSpeed())).append("°").toString());
        descriere.setText(new StringBuilder(String.valueOf(dataW[0 + dayNr + hour].getDescription())).toString());
        umiditate.setText(new StringBuilder(String.valueOf(dataW[0 + dayNr + hour].getHumidity())).append("%").toString());
        Picasso.get().load(new StringBuilder("https://openweathermap.org/img/w/").append(dataW[0 + dayNr + hour].getIcon()).append(".png").toString()).into(weatherIcon);
        date.setText(new StringBuilder(dataW[0 + dayNr].getDate()).substring(0, 10));

        textora1.setText(new StringBuilder(dataW[0].getDate()).substring(11, 16));
        textora2.setText(new StringBuilder(dataW[1].getDate()).substring(11, 16));
        textora3.setText(new StringBuilder(dataW[2].getDate()).substring(11, 16));
        textora4.setText(new StringBuilder(dataW[3].getDate()).substring(11, 16));
        textora5.setText(new StringBuilder(dataW[4].getDate()).substring(11, 16));
        textora6.setText(new StringBuilder(dataW[5].getDate()).substring(11, 16));
        textora7.setText(new StringBuilder(dataW[6].getDate()).substring(11, 16));
        textora8.setText(new StringBuilder(dataW[7].getDate()).substring(11, 16));


        zborora1.setVisibility(View.INVISIBLE);
        zborora2.setVisibility(View.INVISIBLE);
        zborora3.setVisibility(View.INVISIBLE);
        zborora4.setVisibility(View.INVISIBLE);
        zborora5.setVisibility(View.INVISIBLE);
        zborora6.setVisibility(View.INVISIBLE);
        zborora7.setVisibility(View.INVISIBLE);
        zborora8.setVisibility(View.INVISIBLE);


        String vantDir = convertorV(dataW[0 + dayNr].getWindDeg());
        if (vantDir.equals(data.getVant()) && dataW[0 + dayNr].getWindSpeed() > 0.8 && !dataW[dayNr + 0].getDescription().equals("light rain") && !dataW[dayNr + 0].getDescription().equals("moderate rain")) {
            zborora1.setVisibility(View.VISIBLE);


        }
        vantDir = convertorV(dataW[1 + dayNr].getWindDeg());
        if (vantDir.equals(data.getVant()) && dataW[1 + dayNr].getWindSpeed() > 0.8 && !dataW[dayNr + 1].getDescription().equals("light rain") && !dataW[dayNr + 1].getDescription().equals("moderate rain")) {
            zborora2.setVisibility(View.VISIBLE);


        }
        vantDir = convertorV(dataW[2 + dayNr].getWindDeg());
        if (vantDir.equals(data.getVant()) && dataW[2 + dayNr].getWindSpeed() > 0.8 && !dataW[dayNr + 2].getDescription().equals("light rain") && !dataW[dayNr + 2].getDescription().equals("moderate rain")) {
            zborora3.setVisibility(View.VISIBLE);


        }
        vantDir = convertorV(dataW[3 + dayNr].getWindDeg());
        if (vantDir.equals(data.getVant()) && dataW[3 + dayNr].getWindSpeed() > 0.8 && !dataW[dayNr + 3].getDescription().equals("light rain") && !dataW[dayNr + 3].getDescription().equals("moderate rain")) {
            zborora4.setVisibility(View.VISIBLE);


        }
        vantDir = convertorV(dataW[4 + dayNr].getWindDeg());
        if (vantDir.equals(data.getVant()) && dataW[4 + dayNr].getWindSpeed() > 0.8 && !dataW[dayNr + 4].getDescription().equals("light rain") && !dataW[dayNr + 4].getDescription().equals("moderate rain")) {
            zborora5.setVisibility(View.VISIBLE);


        }
        vantDir = convertorV(dataW[5 + dayNr].getWindDeg());
        if (vantDir.equals(data.getVant()) && dataW[5 + dayNr].getWindSpeed() > 0.8 && !dataW[dayNr + 5].getDescription().equals("light rain") && !dataW[dayNr + 5].getDescription().equals("moderate rain")) {
            zborora6.setVisibility(View.VISIBLE);


        }
        vantDir = convertorV(dataW[6 + dayNr].getWindDeg());
        if (vantDir.equals(data.getVant()) && dataW[6 + dayNr].getWindSpeed() > 0.8 && !dataW[dayNr + 6].getDescription().equals("light rain") && !dataW[dayNr + 6].getDescription().equals("moderate rain")) {
            zborora7.setVisibility(View.VISIBLE);


        }
        vantDir = convertorV(dataW[7 + dayNr].getWindDeg());
        if (vantDir.equals(data.getVant()) && dataW[7 + dayNr].getWindSpeed() > 0.8 && !dataW[dayNr + 7].getDescription().equals("light rain") && !dataW[dayNr + 7].getDescription().equals("moderate rain")) {
            zborora8.setVisibility(View.VISIBLE);


        }


        if (round(dataW[0].getTemp()) < round(dataW[4].getTemp())) {
            interval1.setText(new StringBuilder(String.valueOf(round(dataW[0].getTemp()))).append("°-").append(round(dataW[4].getTemp())).append("°"));
        } else {
            if (round(dataW[0].getTemp()) > round(dataW[4].getTemp())) {
                interval1.setText(new StringBuilder(String.valueOf(round(dataW[4].getTemp()))).append("°-").append(round(dataW[0].getTemp())).append("°"));
            } else {

                if (round(dataW[1].getTemp()) < round(dataW[4].getTemp())) {
                    interval1.setText(new StringBuilder(String.valueOf(round(dataW[1].getTemp()))).append("°-").append(round(dataW[4].getTemp())).append("°"));
                } else {
                    if (round(dataW[1].getTemp()) > round(dataW[4].getTemp())) {
                        interval1.setText(new StringBuilder(String.valueOf(round(dataW[4].getTemp()))).append("°-").append(round(dataW[1].getTemp())).append("°"));
                    }
                }
            }

        }

        if (round(dataW[8].getTemp()) < round(dataW[12].getTemp())) {
            interval2.setText(new StringBuilder(String.valueOf(round(dataW[8].getTemp()))).append("°-").append(round(dataW[12].getTemp())).append("°"));
        } else {
            if (round(dataW[8].getTemp()) > round(dataW[12].getTemp())) {
                interval2.setText(new StringBuilder(String.valueOf(round(dataW[12].getTemp()))).append("°-").append(round(dataW[8].getTemp())).append("°"));
            } else {

                if (round(dataW[9].getTemp()) < round(dataW[12].getTemp())) {
                    interval2.setText(new StringBuilder(String.valueOf(round(dataW[9].getTemp()))).append("°-").append(round(dataW[12].getTemp())).append("°"));
                } else {
                    if (round(dataW[9].getTemp()) > round(dataW[12].getTemp())) {
                        interval2.setText(new StringBuilder(String.valueOf(round(dataW[12].getTemp()))).append("°-").append(round(dataW[9].getTemp())).append("°"));
                    }
                }
            }

        }

        if (round(dataW[16].getTemp()) < round(dataW[20].getTemp())) {
            interval3.setText(new StringBuilder(String.valueOf(round(dataW[16].getTemp()))).append("°-").append(round(dataW[20].getTemp())).append("°"));
        } else {
            if (round(dataW[16].getTemp()) > round(dataW[20].getTemp())) {
                interval3.setText(new StringBuilder(String.valueOf(round(dataW[20].getTemp()))).append("°-").append(round(dataW[16].getTemp())).append("°"));
            } else {

                if (round(dataW[17].getTemp()) < round(dataW[20].getTemp())) {
                    interval3.setText(new StringBuilder(String.valueOf(round(dataW[17].getTemp()))).append("°-").append(round(dataW[20].getTemp())).append("°"));
                } else {
                    if (round(dataW[17].getTemp()) > round(dataW[20].getTemp())) {
                        interval3.setText(new StringBuilder(String.valueOf(round(dataW[20].getTemp()))).append("°-").append(round(dataW[17].getTemp())).append("°"));
                    }
                }
            }

        }

        if (round(dataW[24].getTemp()) < round(dataW[28].getTemp())) {
            interval4.setText(new StringBuilder(String.valueOf(round(dataW[24].getTemp()))).append("°-").append(round(dataW[28].getTemp())).append("°"));
        } else {
            if (round(dataW[24].getTemp()) > round(dataW[28].getTemp())) {
                interval4.setText(new StringBuilder(String.valueOf(round(dataW[28].getTemp()))).append("°-").append(round(dataW[24].getTemp())).append("°"));
            } else {

                if (round(dataW[25].getTemp()) < round(dataW[28].getTemp())) {
                    interval4.setText(new StringBuilder(String.valueOf(round(dataW[25].getTemp()))).append("°-").append(round(dataW[28].getTemp())).append("°"));
                } else {
                    if (round(dataW[25].getTemp()) > round(dataW[28].getTemp())) {
                        interval4.setText(new StringBuilder(String.valueOf(round(dataW[28].getTemp()))).append("°-").append(round(dataW[25].getTemp())).append("°"));
                    }
                }
            }

        }

        if (round(dataW[32].getTemp()) < round(dataW[36].getTemp())) {
            interval5.setText(new StringBuilder(String.valueOf(round(dataW[32].getTemp()))).append("°-").append(round(dataW[36].getTemp())).append("°"));
        } else {
            if (round(dataW[32].getTemp()) > round(dataW[36].getTemp())) {
                interval5.setText(new StringBuilder(String.valueOf(round(dataW[36].getTemp()))).append("°-").append(round(dataW[32].getTemp())).append("°"));
            } else {

                if (round(dataW[33].getTemp()) < round(dataW[36].getTemp())) {
                    interval5.setText(new StringBuilder(String.valueOf(round(dataW[33].getTemp()))).append("°-").append(round(dataW[36].getTemp())).append("°"));
                } else {
                    if (round(dataW[33].getTemp()) > round(dataW[36].getTemp())) {
                        interval5.setText(new StringBuilder(String.valueOf(round(dataW[36].getTemp()))).append("°-").append(round(dataW[33].getTemp())).append("°"));
                    }
                }
            }

        }


        double vant = dataW[0 + dayNr + hour].getWindDeg();
        vantDir = convertorV(vant);
        directievant.setText(vantDir);
        if (dataW[dayNr + hour].getDescription().equals("light rain") || dataW[dayNr + hour].getDescription().equals("moderate rain")) {
            favorabil.setText(new StringBuilder("The conditions for the selected time don't allow paragliding: rain expected"));
        } else {
            if (!vantDir.equals(data.getVant()) && dataW[dayNr + hour].getWindSpeed() < 0.8) {
                favorabil.setText(new StringBuilder("The conditions for the selected time don't allow paragliding: wind should blow from ").append(data.getVant()).append(" and the wind speed is to low"));
            } else {
                if (!vantDir.equals(data.getVant())) {
                    favorabil.setText(new StringBuilder("The conditions for the selected time don't allow paragliding: wind should blow from ").append(data.getVant()));
                } else {
                    if (dataW[dayNr + hour].getWindSpeed() < 0.8) {
                        favorabil.setText(new StringBuilder("The conditions for the selected time don't allow paragliding: the wind speed is to low"));
                    } else {
                        favorabil.setText("The conditions for the selected time allow paragliding");
                    }
                }
            }
        }


    }


    public void setDate() {
        Calendar c = Calendar.getInstance();
        Date datet = c.getTime();
        dayText1.setText(String.valueOf(datet));

        c.add(Calendar.DATE, 1);
        datet = c.getTime();
        dayText2.setText(String.valueOf(datet));

        c.add(Calendar.DATE, 1);
        datet = c.getTime();
        dayText3.setText(String.valueOf(datet));

        c.add(Calendar.DATE, 1);
        datet = c.getTime();
        dayText4.setText(String.valueOf(datet));

        c.add(Calendar.DATE, 1);
        datet = c.getTime();
        dayText5.setText(String.valueOf(datet));

    }

    public String convertorV(double vant) {
        conv = "";
        if (vant >= 338 || vant < 23) {
            conv = "N";
        } else {
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

        return conv;
    }

}