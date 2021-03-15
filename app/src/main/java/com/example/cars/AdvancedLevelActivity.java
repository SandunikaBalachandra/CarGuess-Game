package com.example.cars;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.CountDownTimer;
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
    int scores=0;
    TextView score,timer4;
    Boolean switched=false;

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
        score=findViewById(R.id.score);
        timer4=findViewById(R.id.timer4);

        setImages();
        if (switched == true) {
        countdown();}

        attemptsLeft = " X X X";

        submitButton();

        score.setText(String.format("score%d", scores));

    }

    private void submitButton(){
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (cars.size()==30){
//            submit.setEnabled(false);
                    finish();
                    gameover();
                }
                cc.getCarNamesmap();
                if (switched == true) {
                CountDownTimer.cancel();}
                checkValue();

            }
        });
    }

    private void checkValue(){
        switched = getIntent().getBooleanExtra("SWITCH", false);

        //check whether image value is equel to spinner value
        if (img1text.getText().toString().toUpperCase().equals(carnamesmap.get(str1).toUpperCase())&& (img2text.getText().toString().toUpperCase().equals(carnamesmap.get(str2).toUpperCase())) && (img3text.getText().toString().toUpperCase().equals(carnamesmap.get(str3).toUpperCase()))){
            //print 'correct!'
            advance_guess.setText(R.string.label_correct);
            advance_guess.setTextColor(Color.GREEN);
            img1text.setTextColor(Color.GREEN);
            img2text.setTextColor(Color.GREEN);
            img3text.setTextColor(Color.GREEN);
            scores=scores+3;
            score.setText(String.format("score%d", scores));
            submit.setText(R.string.next_button);

            submit.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (switched == true) {
                    CountDownTimer.cancel();
                    countdown();}
                    setImages();
                    advance_guess.setText("");
                    text1.setText("");
                    text2.setText("");
                    text3.setText("");
                    img1text.setText("");
                    img2text.setText("");
                    img3text.setText("");
                    submit.setText("Submit");
                    submitButton();
                    img1text.setEnabled(true);
                    img2text.setEnabled(true);
                    img3text.setEnabled(true);
                    attemptsLeft = " X X X";
                    img1text.setTextColor(Color.BLACK);
                    img2text.setTextColor(Color.BLACK);
                    img3text.setTextColor(Color.BLACK);
                }
            });

        } else {
            decreaseAttemptsLeft();
            if (switched == true) {
            countdown();}
            if (attemptsLeft.isEmpty()) {
                if (switched == true) {
                CountDownTimer.cancel();}
                if (!(img1text.isEnabled())){
                    scores=scores+1;
                }
                if (!(img2text.isEnabled())){
                    scores=scores+1;
                }
                if (!(img3text.isEnabled())){
                    scores=scores+1;
                }
                score.setText(String.format("score%d", scores));
                advance_guess.setText("WRONG!");
                advance_guess.setTextColor(Color.RED);
                submit.setText(R.string.next_button);
                if (!(img1text.getText().toString().toUpperCase().equals(carnamesmap.get(str1).toUpperCase()))){
                    text1.setText(carnamesmap.get(str1).toUpperCase());
                    text1.setTextColor(Color.YELLOW);
                }
                if (!(img2text.getText().toString().toUpperCase().equals(carnamesmap.get(str2).toUpperCase()))){
                    text2.setText(carnamesmap.get(str2).toUpperCase());
                    text2.setTextColor(Color.YELLOW);
                }
                if (!(img3text.getText().toString().toUpperCase().equals(carnamesmap.get(str3).toUpperCase()))){
                    text3.setText(carnamesmap.get(str3).toUpperCase());
                    text3.setTextColor(Color.YELLOW);
                }
                submit.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        setImages();
                        if (switched == true) {
                        CountDownTimer.cancel();
                        countdown();}
                        advance_guess.setText("");
                        text1.setText("");
                        text2.setText("");
                        text3.setText("");
                        img1text.setText("");
                        img2text.setText("");
                        img3text.setText("");
                        submit.setText("Submit");
                        attemptsLeft = " X X X";
                        submitButton();
                        img1text.setEnabled(true);
                        img2text.setEnabled(true);
                        img3text.setEnabled(true);
                        img1text.setTextColor(Color.BLACK);
                        img2text.setTextColor(Color.BLACK);
                        img3text.setTextColor(Color.BLACK);
                    }
                });

            }

            if (img1text.getText().toString().toUpperCase().equals(carnamesmap.get(str1).toUpperCase())){
                img1text.setEnabled(false);
                img1text.setTextColor(Color.GREEN);

            }else {img1text.setTextColor(Color.RED);}
            if (img2text.getText().toString().toUpperCase().equals(carnamesmap.get(str2).toUpperCase())){
                img2text.setEnabled(false);
                img2text.setTextColor(Color.GREEN);

            }else {img2text.setTextColor(Color.RED);}
            if (img3text.getText().toString().toUpperCase().equals(carnamesmap.get(str3).toUpperCase())){
                img3text.setEnabled(false);
                img3text.setTextColor(Color.GREEN);

            }else {img3text.setTextColor(Color.RED);}



        }
    }

    android.os.CountDownTimer CountDownTimer;
    //countdown timer
    public final void countdown(){
        switched = getIntent().getBooleanExtra("SWITCH", false);
        if (switched == true) {
            //Set a countdown timer for 20 seconds
            CountDownTimer = new CountDownTimer(20000, 1000) {

                public void onTick(long millisUntilFinished) {
                    timer4.setText("seconds remaining: " + millisUntilFinished / 1000);
                }

                public void onFinish() {
                    timer4.setText("Time over!");
                    checkValue();
                }

            }.start();
        }else{
            timer4.setText("No Timer!");
        }

    }
    public void gameover() {
//        finish();
        Intent intent4 = new Intent(this, GameOver.class);
        startActivity(intent4);
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
//        if (ResourceID == 0) {
//            throw new IllegalArgumentException
//                    (
//                            "No resource string found with name " + resName
//                    );
//        } else {
            return ResourceID;
//        }
    }
}