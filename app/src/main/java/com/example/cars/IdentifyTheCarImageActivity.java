package com.example.cars;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Random;

public class IdentifyTheCarImageActivity extends AppCompatActivity {
    final Random rnd= new Random();
    String str1;
    String str2;
    String str3;
    String str;
    TextView carname;
    TextView guess;
    Button next;
    //make instance of CarMakeActivity to get the hashmap created in that
    CarMakeActivity cc=new CarMakeActivity();
    HashMap<String, String> carnamesmap = cc.getCarNamesmap();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_identify_the_car_image);
        next = (Button) findViewById(R.id.next);
        carname = (TextView) findViewById(R.id.carName);
        guess = (TextView) findViewById(R.id.guess);

        ArrayList cars = new ArrayList(30);


        //random images
        final ImageView img1 = (ImageView) findViewById(R.id.image1);
        final ImageView img2 = (ImageView) findViewById(R.id.image2);
        final ImageView img3 = (ImageView) findViewById(R.id.image3);

        do {
            str1 = "img_" + (rnd.nextInt(30) - rnd.nextInt(5));

        } while (cars.contains(str1));
        cars.add(str1);


        img1.setImageDrawable
                (
                        getResources().getDrawable(getResourceID(str1, "drawable",
                                getApplicationContext()))

                );

        do {
            str2 = "img_" + (rnd.nextInt(30) - rnd.nextInt(5));

        } while (cars.contains(str2));
        cars.add(str2);


        img2.setImageDrawable
                (
                        getResources().getDrawable(getResourceID(str2, "drawable",
                                getApplicationContext()))

                );

        do {
            str3 = "img_" + (rnd.nextInt(30) - rnd.nextInt(5));

        } while (cars.contains(str3));
        cars.add(str3);


        img3.setImageDrawable
                (
                        getResources().getDrawable(getResourceID(str3, "drawable",
                                getApplicationContext()))

                );

        String imageGuess[]={str1,str2,str3};
        str=imageGuess[rnd.nextInt(3)];

        carname.setText(carnamesmap.get(str));

    }


    //set image resource id
    protected final static int getResourceID
    (final String resName, final String resType, final Context ctx) {
        final int ResourceID =
                ctx.getResources().getIdentifier(resName, resType,
                        ctx.getApplicationInfo().packageName);
        if (ResourceID == 0) {
            throw new IllegalArgumentException
                    (
                            "No resource string found with name " + resName
                    );
        } else {
            return ResourceID;
        }
    }


    public void clickImage1(View view) {
        if (carnamesmap.get(str1).equals(carnamesmap.get(str))){
            guess.setText("CORRECT!");
            guess.setTextColor(Color.GREEN);

        }else{
            guess.setText("WRONG!");
            guess.setTextColor(Color.RED);
        }
        //NEXT button event
        next.setEnabled(true);
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                startActivity(getIntent());
            }
        });
    }

    public void clickImage2(View view) {
        if (carnamesmap.get(str2).equals(carnamesmap.get(str))){
            guess.setText("CORRECT!");
            guess.setTextColor(Color.GREEN);

        }else{
            guess.setText("WRONG!");
            guess.setTextColor(Color.RED);
        }
        //NEXT button event
        next.setEnabled(true);
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                startActivity(getIntent());
            }
        });

    }

    public void clickImage3(View view) {
        if (carnamesmap.get(str3).equals(carnamesmap.get(str))){
            guess.setText("CORRECT!");
            guess.setTextColor(Color.GREEN);

        }else{
            guess.setText("WRONG!");
            guess.setTextColor(Color.RED);
        }
        //NEXT button event
        next.setEnabled(true);
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                startActivity(getIntent());
            }
        });
        }
    }
