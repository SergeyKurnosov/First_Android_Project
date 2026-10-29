package com.example.first_android_project;

import android.graphics.Color;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.SeekBar;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.List;


public class MainActivity extends AppCompatActivity {
    SeekBar R_,G_,B_;


    int back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        R_ = findViewById(R.id.seekBarR);
        G_ = findViewById(R.id.seekBarG);
        B_ = findViewById(R.id.seekBarB);

        R_.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                setBackColor(progress, G_.getProgress(), B_.getProgress());
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
            }
            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        G_.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                setBackColor(R_.getProgress(), progress, B_.getProgress());
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
            }
            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        B_.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                setBackColor(R_.getProgress(), G_.getProgress(), progress);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
            }
            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

    }

    void setBackColor(int red, int green, int blue){
        int r = red;
        int g = green;
        int b = blue;
        back = rgbToInt(r,g,b);

        findViewById(R.id.main).setBackgroundColor(Color.rgb( r, g, b));
    }

    int clamp(int value, int min, int max) {
        return Math.max(min, Math.min(max, value));
    }

    int rgbToInt(int red, int green, int blue) {
        red = clamp(red, 0, 255);
        green = clamp(green, 0, 255);
        blue = clamp(blue, 0, 255);
        return (red << 16) | (green << 8) | blue;
    }



}