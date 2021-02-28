package com.example.cars;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;
import java.util.Scanner;

public class HintsActivity extends AppCompatActivity {

    final Random rnd= new Random();
    String str;
    char[] imageDsplayedCharArray;
    String wordDisplayed;
    EditText text_car_name;
    TextView hint_dashes;
    String attemptsLeft;
    Button submit;
    TextView attemptsLeftText;

    //make instance of CarMakeActivity to get the hashmap created in that
    CarMakeActivity cc=new CarMakeActivity();
    HashMap<String, String> carnamesmap = cc.getCarNamesmap();

    void revealLetterInWord(char letter){
        int indexOfLetter=carnamesmap.get(str).indexOf(letter);

        //loop if index is positive or 0
        while (indexOfLetter>=0){
            imageDsplayedCharArray[indexOfLetter]=carnamesmap.get(str).charAt(indexOfLetter);
            indexOfLetter=carnamesmap.get(str).indexOf(letter,indexOfLetter+1);

            }
        //displayed name
        wordDisplayed=String.valueOf(imageDsplayedCharArray);
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hints);
         submit = (Button) findViewById(R.id.submit);
        text_car_name= findViewById(R.id.text_car_name);
        hint_dashes=findViewById(R.id.hint_dashes);
        attemptsLeftText=findViewById(R.id.label_hints);



    ArrayList cars = new ArrayList(30);


        //random images
    final ImageView img = (ImageView) findViewById(R.id.car_hints_images);

        do {
        str = "img_" + (rnd.nextInt(30) - rnd.nextInt(5));

    } while (cars.contains(str));

        cars.add(str);

        img.setImageDrawable
                (
    getResources().getDrawable(getResourceID(str, "drawable",
                               getApplicationContext()))

            );

        //char array
        imageDsplayedCharArray = (carnamesmap.get(str)).toCharArray();

        //add underscored
        for (int i=0;i<imageDsplayedCharArray.length-1;i++){
            imageDsplayedCharArray[i]='_';
        }
        wordDisplayed=String.valueOf(imageDsplayedCharArray);
        displayeWord();

        text_car_name.setText("");

        attemptsLeft = " X X X";
        attemptsLeftText.setText(attemptsLeft);

    //Identify button event
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String string = text_car_name.getText().toString();
                checkIfLetterIsInWord(string.charAt(0));
            }
        });
    }


            private void displayeWord() {
                String formattedString = "";
                for (char character : imageDsplayedCharArray) {
                    formattedString += character + " ";
                }
                hint_dashes.setText(formattedString);
            }

            void checkIfLetterIsInWord(char letter) {

                TextView ansTxt = findViewById(R.id.correct_car_name_hints);

                if (carnamesmap.get(str).indexOf(letter) >= 0) {
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
                                    finish();
                                    startActivity(getIntent());
                                }
                            });
                        }
                    }
                } else {
                    decreaseAttemptsLeft();
                    if (attemptsLeft.isEmpty()) {
                        attemptsLeftText.setText("WRONG!");
                        attemptsLeftText.setTextColor(Color.RED);
                        ansTxt.setText(carnamesmap.get(str));
                        ansTxt.setTextColor(Color.YELLOW);
                        submit.setText("NEXT");
                        submit.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                finish();
                                startActivity(getIntent());
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

            //set image resource id
            protected final int getResourceID
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


}