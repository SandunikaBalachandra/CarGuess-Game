package com.example.cars;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Objects;
import java.util.Random;

public class IdentifyTheCarImageActivity extends AppCompatActivity {
    final Random rnd = new Random();
    String str1,str2,str3,str;
    TextView carname,guess,timer3;
    Button next;
    ImageView img1,img2,img3;
    Boolean switched=false;

    //make instance of CarMakeActivity to get the hashmap created in that
    CarMakeActivity cc = new CarMakeActivity();
    HashMap<String, String> carnamesmap = cc.getCarNamesmap();

    ArrayList cars = new ArrayList(30);
    ArrayList carsnames = new ArrayList(10);

    //set fade in animation
    Animation fadein;

    @SuppressLint("UseCompatLoadingForDrawables")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_identify_the_car_image);
        next = findViewById(R.id.next);
        carname = findViewById(R.id.carName);
        guess = findViewById(R.id.guess);
        timer3=findViewById(R.id.timer3);

        fadein= AnimationUtils.loadAnimation(getApplicationContext(),R.anim.fade_in);
        if (cars.size()==10){
            next.setEnabled(false);
            finish();
            Intent intent4=new Intent(this, GameOver.class);
            startActivity(intent4);

        }
        switched = getIntent().getBooleanExtra("SWITCH", false);
        setImages();
        if (switched == true) {
        countdown();}

        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (switched == true) {
                countdown();}
                setImages();
                guess.setText("");
                next.setEnabled(false);
                fadein= AnimationUtils.loadAnimation(getApplicationContext(),R.anim.fade_in);
                if (carsnames.size()==10){
                    next.setEnabled(true);
                    next.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            gameover();
                        }});


                }

            }
        });

    }

    public void gameover(){
//        finish();
        Intent intent4=new Intent(this, GameOver.class);
        startActivity(intent4);
    }

    android.os.CountDownTimer CountDownTimer;
    //countdown timer
    public final void countdown(){
        switched = getIntent().getBooleanExtra("SWITCH", false);
        if (switched == true) {
            //Set a countdown timer for 20 seconds
            CountDownTimer = new CountDownTimer(20000, 1000) {

                public void onTick(long millisUntilFinished) {
                    timer3.setText("seconds remaining: " + millisUntilFinished / 1000);
                }

                public void onFinish() {
                    timer3.setText("Time over!");
                    fadeInCorrectImage();
                    guess.setText("WRONG!");
                    guess.setTextColor(Color.RED);
                    //NEXT button event
                    next.setEnabled(true);


                }

            }.start();
        }else{
            timer3.setText("No Timer!");
        }

    }



    @SuppressLint("UseCompatLoadingForDrawables")
    void setImages(){
        //random images
         img1 = findViewById(R.id.image1);
         img2 = findViewById(R.id.image2);
         img3 = findViewById(R.id.image3);

        do {
            str1 = "img_" + (rnd.nextInt(30));

        } while (cars.contains((str1)));

        cars.add(str1);
        img1.setImageDrawable
                (
                        getResources().getDrawable(getResourceID(str1,
                                getApplicationContext()))

                );

        do {
            str2 = "img_" + (rnd.nextInt(30));

        } while (cars.contains((str2))&&(carnamesmap.get(str2).equals(carnamesmap.get(str1))));

        cars.add(str2);
        img2.setImageDrawable
                (
                        getResources().getDrawable(getResourceID(str2,
                                getApplicationContext()))

                );

        do {
            str3 = "img_" + (rnd.nextInt(30));

        } while (cars.contains(str3)&&(carnamesmap.get(str3).equals(carnamesmap.get(str1)))&&(carnamesmap.get(str3).equals(carnamesmap.get(str2))));

        cars.add(str3);
        img3.setImageDrawable
                (
                        getResources().getDrawable(getResourceID(str3,
                                getApplicationContext()))

                );
        String imageGuess[] = {str1, str2, str3};
        do {
            str = imageGuess[rnd.nextInt(3)];
        } while ((carsnames.contains(str)));

        carsnames.add(str);
        carname.setText(carnamesmap.get(str));


    }

    //set image resource id
    protected final static int getResourceID
    (final String resName, final Context ctx) {
        final int ResourceID =
                ctx.getResources().getIdentifier(resName, "drawable",
                        ctx.getApplicationInfo().packageName);
//        if (ResourceID == 0) {
//            throw new IllegalArgumentException
//                    (
//                            "No resource string found with name " + resName
//                    );
//        } else {
            return ResourceID;
//        }
    }


    public void clickImage1(View view) {
        switched = getIntent().getBooleanExtra("SWITCH", false);
        if (switched == true) {
        CountDownTimer.cancel();}
        if (carnamesmap.get(str1).equals(carnamesmap.get(str))) {
            guess.setText("CORRECT!");
            guess.setTextColor(Color.GREEN);

        } else {
            guess.setText("WRONG!");
            guess.setTextColor(Color.RED);
            fadeInCorrectImage();
        }
        //NEXT button event
        next.setEnabled(true);

    }

    public void clickImage2(View view) {
        switched = getIntent().getBooleanExtra("SWITCH", false);
        if (switched == true) {
        CountDownTimer.cancel();}
        if (carnamesmap.get(str2).equals(carnamesmap.get(str))) {
            guess.setText("CORRECT!");
            guess.setTextColor(Color.GREEN);

        } else {
            guess.setText("WRONG!");
            guess.setTextColor(Color.RED);
            fadeInCorrectImage();
        }
        //NEXT button event
        next.setEnabled(true);


    }

    public void clickImage3(View view) {
        switched = getIntent().getBooleanExtra("SWITCH", false);
        if (switched == true) {
        CountDownTimer.cancel();}
        if (carnamesmap.get(str3).equals(carnamesmap.get(str))) {
            guess.setText("CORRECT!");
            guess.setTextColor(Color.GREEN);

        } else {
            guess.setText("WRONG!");
            guess.setTextColor(Color.RED);
            fadeInCorrectImage();
        }
        //NEXT button event
        next.setEnabled(true);

    }

    //fade in the correct image
    public void fadeInCorrectImage(){
        if (Objects.equals(carnamesmap.get(str1), carnamesmap.get(str))){
            img1.setAnimation(fadein);
        }
        if (Objects.equals(carnamesmap.get(str2), carnamesmap.get(str))){
            img2.setAnimation(fadein);
        }
        if (Objects.equals(carnamesmap.get(str3), carnamesmap.get(str))){
            img3.setAnimation(fadein);
        }
    }
}
