package com.example.cars;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.*;

public class CarMakeActivity extends AppCompatActivity {

    final Random rnd = new Random();
    String str;

    //hashmap stores value for image keys
   public static HashMap<String, String> carnamesmap = new HashMap<>();
   private void setCarnamesmap(){
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
    public HashMap<String, String> getCarNamesmap(){
        setCarnamesmap();
        return carnamesmap;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_car_make);


        Spinner spinner = (Spinner) findViewById(R.id.car_names);
        Button identify = (Button) findViewById(R.id.identify);
        TextView label = (TextView) findViewById(R.id.label);
        TextView correct_car_name = (TextView) findViewById(R.id.correct_car_name);
        String[] carnames = getResources().getStringArray(R.array.car_names);

        // Creating adapter for spinner
        ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_spinner_item, carnames);
        // Drop down layout style
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setPrompt("Select the Car Name");
        //spinner.setAdapter(adapter);

        //set default text to the spinner -->Textview is in carname_spinner_row_nothing_selected.xml and instance of NothingSelectedSpinnerAdapter.java has created here
        // attaching data adapter to spinner
        spinner.setAdapter(new NothingSelectedSpinnerAdapter(
                adapter,
                R.layout.carname_spinner_row_nothing_selected, this));

        getCarNamesmap();
//        //hashmap stores value for image keys
//        HashMap<String, String> carnamesmap = new HashMap<>();
//        carnamesmap.put("img_0", "ferrari");
//        carnamesmap.put("img_5", "ferrari");
//        carnamesmap.put("img_6", "ferrari");
//        carnamesmap.put("img_9", "audi");
//        carnamesmap.put("img_10", "audi");
//        carnamesmap.put("img_11", "audi");
//        carnamesmap.put("img_1", "bmw");
//        carnamesmap.put("img_7", "bmw");
//        carnamesmap.put("img_8", "bmw");
//        carnamesmap.put("img_12", "ford");
//        carnamesmap.put("img_13", "ford");
//        carnamesmap.put("img_14", "ford");
//        carnamesmap.put("img_2", "honda");
//        carnamesmap.put("img_3", "honda");
//        carnamesmap.put("img_4", "honda");
//        carnamesmap.put("img_15", "mercedes");
//        carnamesmap.put("img_16", "mercedes");
//        carnamesmap.put("img_17", "mercedes");
//        carnamesmap.put("img_18", "nissan");
//        carnamesmap.put("img_19", "nissan");
//        carnamesmap.put("img_20", "nissan");
//        carnamesmap.put("img_21", "rolls-royce");
//        carnamesmap.put("img_22", "rolls-royce");
//        carnamesmap.put("img_23", "rolls-royce");
//        carnamesmap.put("img_24", "toyota");
//        carnamesmap.put("img_25", "toyota");
//        carnamesmap.put("img_26", "toyota");
//        carnamesmap.put("img_27", "volkswagen");
//        carnamesmap.put("img_28", "volkswagen");
//        carnamesmap.put("img_29", "volkswagen");



        ArrayList cars = new ArrayList(30);

        //random images
        final ImageView img = (ImageView) findViewById(R.id.car_make_images);

        do {
            str = "img_" + (rnd.nextInt(30) - rnd.nextInt(5));

        } while (cars.contains(str));

        cars.add(str);

        img.setImageDrawable
                (
                        getResources().getDrawable(getResourceID(str, "drawable",
                                getApplicationContext()))

                );

        //Identify button event
        identify.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getCarNamesmap();
                //check whether image value is equel to spinner value
                if (carnamesmap.get(str).equals(spinner.getSelectedItem().toString())) {
                    //print 'correct!'
                    label.setText(R.string.label_correct);
                    label.setTextColor(Color.GREEN);
                    identify.setText(R.string.next_button);
                    identify.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            finish();
                            startActivity(getIntent());
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
                             finish();
                            startActivity(getIntent());
                        }
                    });
                }
            }
        });

//        if (cars.size()==30){
//            identify.setEnabled(false);
//            label.setText(R.string.end_text);
//        }

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

}