package com.example.cars;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

public class IdentifyTheCarImageActivity extends AppCompatActivity {
    final Random rnd = new Random();
    String str1;
    String str2;
    String str3;
    String str;
    TextView carname;
    TextView guess;
    Button next;
    //make instance of CarMakeActivity to get the hashmap created in that
    CarMakeActivity cc = new CarMakeActivity();
    HashMap<String, String> carnamesmap = cc.getCarNamesmap();
    ArrayList cars = new ArrayList(30);

    @SuppressLint("UseCompatLoadingForDrawables")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_identify_the_car_image);
        next = findViewById(R.id.next);
        carname = findViewById(R.id.carName);
        guess = findViewById(R.id.guess);

        if (cars.size()==30){
            next.setEnabled(false);
            finish();
        }
        setImages();


    }

    @SuppressLint("UseCompatLoadingForDrawables")
    void setImages(){
        //random images
        final ImageView img1 = findViewById(R.id.image1);
        final ImageView img2 = findViewById(R.id.image2);
        final ImageView img3 = findViewById(R.id.image3);

        do {
            str1 = "img_" + (rnd.nextInt(30));

        } while (cars.contains(str1));

        cars.add(str1);
        img1.setImageDrawable
                (
                        getResources().getDrawable(getResourceID(str1,
                                getApplicationContext()))

                );

        do {
            str2 = "img_" + (rnd.nextInt(30));

        } while (cars.contains(str2));

        cars.add(str2);
        img2.setImageDrawable
                (
                        getResources().getDrawable(getResourceID(str2,
                                getApplicationContext()))

                );

        do {
            str3 = "img_" + (rnd.nextInt(30));

        } while (cars.contains(str3));

        cars.add(str3);
        img3.setImageDrawable
                (
                        getResources().getDrawable(getResourceID(str3,
                                getApplicationContext()))

                );

        String imageGuess[] = {str1, str2, str3};
        str = imageGuess[rnd.nextInt(3)];


        carname.setText(carnamesmap.get(str));

    }

    //set image resource id
    protected final static int getResourceID
    (final String resName, final Context ctx) {
        final int ResourceID =
                ctx.getResources().getIdentifier(resName, "drawable",
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
        if (carnamesmap.get(str1).equals(carnamesmap.get(str))) {
            guess.setText("CORRECT!");
            guess.setTextColor(Color.GREEN);

        } else {
            guess.setText("WRONG!");
            guess.setTextColor(Color.RED);
        }
        //NEXT button event
        next.setEnabled(true);
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setImages();
                guess.setText("");
                next.setEnabled(false);

            }
        });
    }

    public void clickImage2(View view) {
        if (carnamesmap.get(str2).equals(carnamesmap.get(str))) {
            guess.setText("CORRECT!");
            guess.setTextColor(Color.GREEN);

        } else {
            guess.setText("WRONG!");
            guess.setTextColor(Color.RED);
        }
        //NEXT button event
        next.setEnabled(true);
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setImages();
                guess.setText("");
                next.setEnabled(false);
            }
        });

    }

    public void clickImage3(View view) {
        if (carnamesmap.get(str3).equals(carnamesmap.get(str))) {
            guess.setText("CORRECT!");
            guess.setTextColor(Color.GREEN);

        } else {
            guess.setText("WRONG!");
            guess.setTextColor(Color.RED);
        }
        //NEXT button event
        next.setEnabled(true);
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setImages();
                guess.setText("");
                next.setEnabled(false);
            }
        });
    }
}
