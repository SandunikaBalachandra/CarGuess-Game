package com.example.cars;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.*;

@SuppressLint("UseCompatLoadingForDrawables")
public class CarMakeActivity extends AppCompatActivity {

    final Random rnd = new Random();
    String str;
    private ArrayList cars = new ArrayList(30);
    TextView timer1, label, correct_car_name;
    Spinner spinner;
    Button identify;
    MainActivity main = new MainActivity();
    Boolean switched = false;

    //hashmap stores value for image keys
    public static HashMap<String, String> carnamesmap = new HashMap<>();

    private void setCarnamesmap() {
        carnamesmap.put("img_0", "ferrari");
        carnamesmap.put("img_5", "ferrari");
        carnamesmap.put("img_6", "ferrari");
        carnamesmap.put("img_9", "audi");
        carnamesmap.put("img_10", "audi");
        carnamesmap.put("img_11", "audi");
        carnamesmap.put("img_1", "bmw");
        carnamesmap.put("img_7", "bmw");
        carnamesmap.put("img_8", "bmw");
        carnamesmap.put("img_12", "ford");
        carnamesmap.put("img_13", "ford");
        carnamesmap.put("img_14", "ford");
        carnamesmap.put("img_2", "honda");
        carnamesmap.put("img_3", "honda");
        carnamesmap.put("img_4", "honda");
        carnamesmap.put("img_15", "mercedes");
        carnamesmap.put("img_16", "mercedes");
        carnamesmap.put("img_17", "mercedes");
        carnamesmap.put("img_18", "nissan");
        carnamesmap.put("img_19", "nissan");
        carnamesmap.put("img_20", "nissan");
        carnamesmap.put("img_21", "rolls-royce");
        carnamesmap.put("img_22", "rolls-royce");
        carnamesmap.put("img_23", "rolls-royce");
        carnamesmap.put("img_24", "toyota");
        carnamesmap.put("img_25", "toyota");
        carnamesmap.put("img_26", "toyota");
        carnamesmap.put("img_27", "volkswagen");
        carnamesmap.put("img_28", "volkswagen");
        carnamesmap.put("img_29", "volkswagen");
    }

    public HashMap<String, String> getCarNamesmap() {
        setCarnamesmap();
        return carnamesmap;
    }

    @SuppressLint("UseCompatLoadingForDrawables")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_car_make);


        spinner = findViewById(R.id.car_names);
        identify = findViewById(R.id.identify);
        label = findViewById(R.id.label);
        correct_car_name = findViewById(R.id.correct_car_name);
        timer1 = findViewById(R.id.timer1);
        String[] carnames = getResources().getStringArray(R.array.car_names);

        //I refered this youtube video for the spinner dropdown menu https://www.youtube.com/watch?v=X7Xz5ixKVhs
        // Creating adapter for spinner
        ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_spinner_item, carnames);
        // Drop down layout style
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setPrompt("Select the Car Name");
        spinner.setAdapter(adapter);

        //set default text to the spinner
        // attaching data adapter to spinner
//        spinner.setAdapter(new NothingSelectedSpinnerAdapter(
//                adapter,
//                R.layout.carname_spinner_row_nothing_selected, this));

        getCarNamesmap();
        setImage();
        //get passes switch status from main activity
        switched = getIntent().getBooleanExtra("SWITCH", false);
        if (switched == true) {
            countdown();
        }
        submitButton();


    }

    //finish function
    public void gameover() {
        Intent intent4 = new Intent(this, GameOver.class);
        startActivity(intent4);
    }


    //countdown timer
    CountDownTimer CountDownTimer;

    public final void countdown() {
        switched = getIntent().getBooleanExtra("SWITCH", false);
        if (switched == true) {
            //Set a countdown timer for 20 seconds
            CountDownTimer = new CountDownTimer(20000, 1000) {

                public void onTick(long millisUntilFinished) {
                    timer1.setText("seconds remaining: " + millisUntilFinished / 1000);
                }

                public void onFinish() {
                    timer1.setText("Time over!");
                    checkValue();
                }

            }.start();

        } else {
            timer1.setText("No Timer");
        }
    }


    //submit button
    private void submitButton() {
        switched = getIntent().getBooleanExtra("SWITCH", false);
        identify.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (switched == true) {
                    CountDownTimer.cancel();
                }
                getCarNamesmap();

                if (cars.size() == 30) {
                    checkValue();
                    finish();
                    gameover();


                } else {
                    checkValue();
                }

            }
        });
    }

    //check values
    private void checkValue() {
        switched = getIntent().getBooleanExtra("SWITCH", false);
        //check whether image value is equel to spinner value
        if (carnamesmap.get(str).equals(spinner.getSelectedItem().toString())) {
            //print 'correct!'
            label.setText(R.string.label_correct);
            label.setTextColor(Color.GREEN);
            identify.setText(R.string.next_button);
            identify.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    setImage();
                    submitButton();
                    if (switched == true) {
                        countdown();
                    }

                    identify.setText("Identify");
                    label.setText("");

                }
            });


        } else {
            //print 'wrong!'
            label.setText(R.string.label_wrong);
            label.setTextColor(Color.RED);
            correct_car_name.setText(carnamesmap.get(str));
            identify.setText(R.string.next_button);
            identify.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    setImage();
                    submitButton();
                    if (switched == true) {
                        countdown();
                    }

                    identify.setText("Identify");
                    label.setText("");
                    correct_car_name.setText("");
                }
            });
        }


    }

    //set images to imageview
    private void setImage() {
        //random images
        final ImageView img = findViewById(R.id.car_make_images);

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