package com.example.cars;

import androidx.appcompat.app.AppCompatActivity;

import android.animation.ValueAnimator;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class GameOver extends AppCompatActivity {

    TextView gameover;
    Button backhome;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_over);

        gameover=findViewById(R.id.gameover);
        backhome=findViewById(R.id.backhome);

        final float startSize = 12; // Size in pixels
        final float endSize = 42;
        long animationDuration = 5000; // Animation duration in ms

        ValueAnimator animator = ValueAnimator.ofFloat(startSize, endSize);
        animator.setDuration(animationDuration);


        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                float animatedValue = (float) valueAnimator.getAnimatedValue();
                gameover.setTextSize(animatedValue);

            }
        });

//back home button
        backhome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                backHome();
            }
        });

    }

    private void backHome() {
        Intent intent=new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}