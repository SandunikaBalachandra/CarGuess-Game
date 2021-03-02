package com.example.cars;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

public class AdvancedLevelActivity extends AppCompatActivity {
    EditText img1text;
    EditText img2text;
    EditText img3text;
    Button submit;
    TextView advance_guess;
    final Random rnd = new Random();
    String str1,str2,str3;
    String attemptsLeft;
    TextView text1,text2,text3;

    //make instance of CarMakeActivity to get the hashmap created in that
    CarMakeActivity cc = new CarMakeActivity();
    HashMap<String, String> carnamesmap = cc.getCarNamesmap();
    ArrayList cars = new ArrayList(29);

    @SuppressLint("UseCompatLoadingForDrawables")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_advanced_level);

        submit = findViewById(R.id.submit_advanced);
        img1text=findViewById(R.id.img1text);
        img2text=findViewById(R.id.img2text);
        img3text=findViewById(R.id.img3text);
        advance_guess=findViewById(R.id.advance_guess);
        text1=findViewById(R.id.text1name);
        text2=findViewById(R.id.text2name);
        text3=findViewById(R.id.text3name);

        if (cars.size()==30){
            submit.setEnabled(false);
            finish();
        }
        setImages();
        attemptsLeft = " X X X";
        //submit button event
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cc.getCarNamesmap();
                //check whether image value is equel to spinner value
                if (img1text.toString().equals(carnamesmap.get(str1))&& (img2text.toString().equals(carnamesmap.get(str2))) && (img3text.toString().equals(carnamesmap.get(str3)))){
                    //print 'correct!'
                    advance_guess.setText(R.string.label_correct);
                    advance_guess.setTextColor(Color.GREEN);
                    img1text.setTextColor(Color.GREEN);
                    img2text.setTextColor(Color.GREEN);
                    img3text.setTextColor(Color.GREEN);
                    submit.setText(R.string.next_button);
                    submit.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            setImages();
                        }
                    });

                } else {
                    decreaseAttemptsLeft();
                    if (attemptsLeft.isEmpty()) {
                        advance_guess.setText("WRONG!");
                        advance_guess.setTextColor(Color.RED);
                        submit.setText(R.string.next_button);
                        if (!(img1text.getText().toString().equals(carnamesmap.get(str1)))){
                            text1.setText(carnamesmap.get(str1));
                            text1.setTextColor(Color.YELLOW);
                        }
                        if (!(img2text.getText().toString().equals(carnamesmap.get(str2)))){
                            text2.setText(carnamesmap.get(str1));
                            text2.setTextColor(Color.YELLOW);
                        }
                        if (!(img3text.getText().toString().equals(carnamesmap.get(str3)))){
                            text3.setText(carnamesmap.get(str1));
                            text3.setTextColor(Color.YELLOW);
                        }
                        submit.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                setImages();
                                advance_guess.setText("");
                                submit.setText("Submit");
                            }
                        });
                    }

                    if (img1text.getText().toString().equals(carnamesmap.get(str1))){
                        img1text.setEnabled(false);
                        img1text.setTextColor(Color.GREEN);
                    }else {img1text.setTextColor(Color.RED);}
                    if (img2text.getText().toString().equals(carnamesmap.get(str2))){
                        img2text.setEnabled(false);
                        img2text.setTextColor(Color.GREEN);
                    }else {img2text.setTextColor(Color.RED);}
                    if (img3text.getText().toString().equals(carnamesmap.get(str3))){
                        img3text.setEnabled(false);
                        img3text.setTextColor(Color.GREEN);
                    }else {img3text.setTextColor(Color.RED);}

                }
            }
        });

//        if (cars.size()==30){
//            identify.setEnabled(false);
//            label.setText(R.string.end_text);
//        }

    }
    @SuppressLint("UseCompatLoadingForDrawables")
    void setImages(){
        //random images
        final ImageView img1 = findViewById(R.id.img1);
        final ImageView img2 = findViewById(R.id.img2);
        final ImageView img3 = findViewById(R.id.img3);

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

    }
    void decreaseAttemptsLeft() {
        if (!attemptsLeft.isEmpty()) {
            attemptsLeft = attemptsLeft.substring(0, attemptsLeft.length() - 2);
//            attemptsLeftText.setText(attemptsLeft);
        }
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
}