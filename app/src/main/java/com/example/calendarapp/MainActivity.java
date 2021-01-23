package com.example.calendarapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;

import com.github.tlaabs.timetableview.Schedule;
import com.github.tlaabs.timetableview.Time;
import com.github.tlaabs.timetableview.TimetableView;

import java.util.Calendar;
import java.util.ArrayList;
import java.util.TimeZone;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    TimetableView timetableView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
    }

    private void init(){
        timetableView = findViewById(R.id.timetable);
        timetableView.setHeaderHighlight(Calendar.DAY_OF_WEEK - 1);

        timetableView.setOnStickerSelectEventListener(new TimetableView.OnStickerSelectedListener() {
            @Override
            public void OnStickerSelected(int idx, ArrayList<Schedule> schedules) {

            }
        });
        addTask();
    }

    @Override
    public void onClick(View v){

    }

    private void addTask(){
        ArrayList<Schedule> schedules = new ArrayList<Schedule>();

        Schedule schedule = new Schedule();
        schedule.setClassTitle("Data Structure"); // sets subject
        schedule.setClassPlace("IT-601"); // sets place
        schedule.setProfessorName("Won Kim"); // sets professor
        schedule.setStartTime(new Time(10,0)); // sets the beginning of class time (hour,minute)
        schedule.setEndTime(new Time(13,30)); // sets the end of class time (hour,minute)
        schedules.add(schedule);

        timetableView.add(schedules);

        Schedule schedule2 = new Schedule();
        schedule2.setClassTitle("Data Structure"); // sets subject
        schedule2.setClassPlace("IT-601"); // sets place
        schedule2.setProfessorName("Won Kim"); // sets professor
        schedule2.setDay(2);
        schedule2.setStartTime(new Time(14,0)); // sets the beginning of class time (hour,minute)
        schedule2.setEndTime(new Time(15,30)); // sets the end of class time (hour,minute)
        ArrayList<Schedule> scheduless = new ArrayList<>();
        scheduless.add(schedule2);

        timetableView.add(scheduless);
//.. add one or more schedules
    }
}