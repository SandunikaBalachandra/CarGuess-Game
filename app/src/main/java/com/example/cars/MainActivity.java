package com.example.cars;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        Button carmake=findViewById(R.id.car_make);
        Button hints=findViewById(R.id.hints);
        Button identify_the_car_image=findViewById(R.id.identify_the_car_image);
        Button advanced_level=findViewById(R.id.advanced_level);

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




    private void carMake(){
        Intent intent1=new Intent(this, CarMakeActivity.class);
        startActivity(intent1);

    }


    private void hints(){
        Intent intent2=new Intent(this, HintsActivity.class);
        startActivity(intent2);

    }

    private void identifyTheCarImage() {
        Intent intent3=new Intent(this, IdentifyTheCarImageActivity.class);
        startActivity(intent3);
    }

    private void advancedLevel() {
        Intent intent4=new Intent(this, AdvancedLevelActivity.class);
        startActivity(intent4);
    }

}