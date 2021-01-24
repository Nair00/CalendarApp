package com.example.calendarapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.github.tlaabs.timetableview.Schedule;
import com.github.tlaabs.timetableview.TimetableView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Calendar;
import java.util.ArrayList;
import java.util.TimeZone;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    static TimetableView timetableView;
    FloatingActionButton fab;
    Calendar calendar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
        addTasks();
    }

    private void init(){
        calendar = Calendar.getInstance(TimeZone.getDefault());
        timetableView = findViewById(R.id.timetable);
        fab = findViewById(R.id.add_task_button);
        fab.setOnClickListener(v -> {
            Intent intent = new Intent(getApplicationContext(), AddTaskActivity.class);
            startActivity(intent);
        });

        timetableView.setHeaderHighlight(calendar.get(Calendar.DAY_OF_WEEK));
        timetableView.setOnStickerSelectEventListener((idx, schedules) -> {
            timetableView.remove(idx);
            saveTimetable();
        });
    }

    @Override
    public void onClick(View v){
        Toast toast = Toast.makeText(getApplicationContext(), "CLICK!", Toast.LENGTH_LONG);
        toast.show();
    }

    private void addTasks(){
        loadTimetable();
    }

    private void saveTimetable(){
        String json = timetableView.createSaveData();

        try {
            File file = new File(getApplicationContext().getFilesDir(), "SaveData");
            FileWriter fileWriter = new FileWriter(file);
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
            bufferedWriter.write(json);
            bufferedWriter.close();
        }catch (IOException e){
            Toast toast = Toast.makeText(getApplicationContext(), "Save went wrong!", Toast.LENGTH_LONG);
            toast.show();
        }
    }

    private void loadTimetable(){
        File file = new File(getApplicationContext().getFilesDir(),"SaveData");
        FileReader fileReader;
        try {
            fileReader = new FileReader(file);
        }catch (FileNotFoundException e){
            Toast toast = Toast.makeText(getApplicationContext(), "Save data not found!", Toast.LENGTH_LONG);
            toast.show();
            return;
        }
        try {
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            StringBuilder stringBuilder = new StringBuilder();
            String line = bufferedReader.readLine();
            while (line != null) {
                stringBuilder.append(line).append("\n");
                line = bufferedReader.readLine();
            }
            bufferedReader.close();
            String json = stringBuilder.toString();
            timetableView.load(json);
        }catch (IOException e){
            Toast toast = Toast.makeText(getApplicationContext(), "Loading data went wrong!", Toast.LENGTH_LONG);
            toast.show();
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        saveTimetable();
    }
}