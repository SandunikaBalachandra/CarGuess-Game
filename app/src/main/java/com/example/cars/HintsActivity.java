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

public class HintsActivity extends AppCompatActivity {

    final Random rnd = new Random();
    String str,wordDisplayed,attemptsLeft;
    char[] imageDsplayedCharArray;
    EditText text_car_name;
    TextView hint_dashes, timer2,attemptsLeftText;
    Button submit;
    Boolean switched;
    
    ArrayList cars = new ArrayList(30);

    //make instance of CarMakeActivity to get the hashmap created in that
    CarMakeActivity cc = new CarMakeActivity();
    HashMap<String, String> carnamesmap = cc.getCarNamesmap();

    void revealLetterInWord(char letter) {
        int indexOfLetter = carnamesmap.get(str).indexOf(letter);

        //loop if index is positive or 0
        while (indexOfLetter >= 0) {
            imageDsplayedCharArray[indexOfLetter] = carnamesmap.get(str).charAt(indexOfLetter);
            indexOfLetter = carnamesmap.get(str).indexOf(letter, indexOfLetter + 1);

        }
        //displayed name
        wordDisplayed = String.valueOf(imageDsplayedCharArray);
    }

    @SuppressLint("UseCompatLoadingForDrawables")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hints);
        submit = findViewById(R.id.submit);
        text_car_name = findViewById(R.id.text_car_name);
        hint_dashes = findViewById(R.id.hint_dashes);
        attemptsLeftText = findViewById(R.id.label_hints);
        timer2 = findViewById(R.id.timer2);

        switched = getIntent().getBooleanExtra("SWITCH", false);

        if (cars.size() == 30) {
            gameOver();
        }
        else {
            setImage();
            if (switched == true) {
            countdown();}
            getName();
        }

    }
    private void gameOver(){
        submit.setEnabled(false);
        Intent intent4 = new Intent(this, GameOver.class);
        startActivity(intent4);
    }

    private void getName(){
        //char array
        imageDsplayedCharArray = (carnamesmap.get(str)).toCharArray();

        //add underscored
        for (int i = 0; i < imageDsplayedCharArray.length - 1; i++) {
            imageDsplayedCharArray[i] = '_';
        }
        wordDisplayed = String.valueOf(imageDsplayedCharArray);
        displayeWord();

        text_car_name.setText("");

        attemptsLeft = " X X X";
        attemptsLeftText.setText(attemptsLeft);

        //submit button event
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String string = text_car_name.getText().toString();
                checkIfLetterIsInWord(string.charAt(0));
            }
        });
    }
    @SuppressLint("UseCompatLoadingForDrawables")
    void setImage() {
        //random images
        final ImageView img = (ImageView) findViewById(R.id.car_hints_images);

        do {
            str = "img_" + (rnd.nextInt(30));

        } while (cars.contains(str));

        cars.add(str);

        img.setImageDrawable
                (
                        getResources().getDrawable(getResourceID(str,
                                getApplicationContext()))

                );
    }

    private void displayeWord() {
        String formattedString = "";
        for (char character : imageDsplayedCharArray) {
            formattedString += character + " ";
        }
        hint_dashes.setText(formattedString);
    }

    void checkIfLetterIsInWord(char letter) {
        switched = getIntent().getBooleanExtra("SWITCH", false);

        TextView ansTxt = findViewById(R.id.correct_car_name_hints);

        if (carnamesmap.get(str).indexOf(letter) >= 0) {
            if (switched == true) {
            CountDownTimer.cancel();
            countdown();}
            if (wordDisplayed.indexOf(letter) < 0) {

                revealLetterInWord(letter);
                displayeWord();
                if (!wordDisplayed.contains("_")) {
                    ansTxt.setText("correct!");
                    ansTxt.setTextColor(Color.GREEN);
                    submit.setText("NEXT");
                    submit.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            setImage();
                            ansTxt.setText("");
                            submit.setText("Submit");
                            if (switched == true) {
                            countdown();}
                            getName();

                        }
                    });
                }
            }
        } else {
            decreaseAttemptsLeft();
            if (switched == true) {
            CountDownTimer.cancel();
            countdown();}
            if (attemptsLeft.isEmpty()) {
                if (switched == true) {
                CountDownTimer.cancel();}
                attemptsLeftText.setText("WRONG!");
                attemptsLeftText.setTextColor(Color.RED);
                ansTxt.setText(carnamesmap.get(str));
                ansTxt.setTextColor(Color.YELLOW);
                submit.setText("NEXT");
                submit.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        setImage();
                        ansTxt.setText("");
                        submit.setText("Submit");
                        attemptsLeftText.setText("");
                        attemptsLeftText.setTextColor(Color.GRAY);
                        if (switched == true) {
                        countdown();}
                        getName();
                    }
                });
            }
        }
    }


    void decreaseAttemptsLeft() {
        if (!attemptsLeft.isEmpty()) {
            attemptsLeft = attemptsLeft.substring(0, attemptsLeft.length() - 2);
            attemptsLeftText.setText(attemptsLeft);
        }
    }

    CountDownTimer CountDownTimer;

    //countdown timer
    public final void countdown() {
        switched = getIntent().getBooleanExtra("SWITCH", false);
        if (switched == true) {
            //Set a countdown timer for 20 seconds
            CountDownTimer = new CountDownTimer(20000, 1000) {

                public void onTick(long millisUntilFinished) {
                    timer2.setText("seconds remaining: " + millisUntilFinished / 1000);
                }

                public void onFinish() {
                    timer2.setText("Time over!");
                    String string = text_car_name.getText().toString();
                    checkIfLetterIsInWord(string.charAt(0));


                }

            }.start();
        }else{
            timer2.setText("No Timer!");
        }

    }

    //set image resource id
    protected final int getResourceID
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