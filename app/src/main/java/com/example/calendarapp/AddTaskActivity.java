package com.example.calendarapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.res.Configuration;
import android.os.Bundle;

public class AddTaskActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_task);
    }

    @Override
    public void onAttachedToWindow() {
        super.onAttachedToWindow();

        int screenSize = getResources().getConfiguration().screenLayout & Configuration.SCREENLAYOUT_SIZE_MASK;

        switch (screenSize) {
            case Configuration.SCREENLAYOUT_SIZE_LARGE:
                this.getWindow().setLayout(900, 755);
                break;
            case Configuration.SCREENLAYOUT_SIZE_XLARGE:
                this.getWindow().setLayout(1080, 1000); //width x height
                break;
        }
    }
}