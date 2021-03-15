package com.example.cars;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    Switch countdown;
    Boolean switchStatus = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button carmake = findViewById(R.id.car_make);
        Button hints = findViewById(R.id.hints);
        Button identify_the_car_image = findViewById(R.id.identify_the_car_image);
        Button advanced_level = findViewById(R.id.advanced_level);
        countdown = findViewById(R.id.countdown);

        //car make button
        carmake.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                carMake();
            }
        });

        //hints button
        hints.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                hints();
            }
        });
        //identify car image button
        identify_the_car_image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                identifyTheCarImage();
            }
        });
        //advanced level button
        advanced_level.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                advancedLevel();
            }
        });
    }

    //check switch status
    public void getSwitchStatus() {
        if (countdown.isChecked()) {
            switchStatus = true;
        } else {
            switchStatus = false;
        }
    }


    private void carMake() {
        getSwitchStatus();
        Intent intent1 = new Intent(this, CarMakeActivity.class);
        intent1.putExtra("SWITCH", switchStatus); //pass switch status
        startActivity(intent1);

    }


    private void hints() {
        getSwitchStatus();
        Intent intent2 = new Intent(this, HintsActivity.class);
        intent2.putExtra("SWITCH", switchStatus);//pass switch status
        startActivity(intent2);

    }

    private void identifyTheCarImage() {
        getSwitchStatus();
        Intent intent3 = new Intent(this, IdentifyTheCarImageActivity.class);
        intent3.putExtra("SWITCH", switchStatus);//pass switch status
        startActivity(intent3);
    }

    private void advancedLevel() {
        getSwitchStatus();
        Intent intent4 = new Intent(this, AdvancedLevelActivity.class);
        intent4.putExtra("SWITCH", switchStatus);//pass switch status
        startActivity(intent4);
    }

}