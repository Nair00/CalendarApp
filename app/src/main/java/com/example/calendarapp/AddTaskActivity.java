package com.example.calendarapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.res.Configuration;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.NumberPicker;
import android.widget.TimePicker;

import com.github.tlaabs.timetableview.Schedule;
import com.github.tlaabs.timetableview.Time;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.TimeZone;

public class AddTaskActivity extends AppCompatActivity {
    EditText className;
    EditText classroomName;
    EditText teacherName;
    NumberPicker date;
    TimePicker startTime;
    TimePicker endTime;
    Button addButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_task);
        Calendar calendar = Calendar.getInstance(TimeZone.getDefault());

        className = findViewById(R.id.task_name);
        classroomName = findViewById(R.id.room_name);
        teacherName = findViewById(R.id.teach_name);
        date = findViewById(R.id.week_day);
        startTime = findViewById(R.id.start_time);
        endTime = findViewById(R.id.end_time);
        addButton = findViewById(R.id.add_button);
        String[] pickerVals = new String[] {"Sunday", "Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday"};

        date.setMinValue(1);
        date.setMaxValue(7);
        date.setDisplayedValues(pickerVals);
        date.setValue(calendar.get(Calendar.DAY_OF_WEEK));

        startTime.setIs24HourView(true);
        endTime.setIs24HourView(true);
        startTime.setMinute(0);
        endTime.setMinute(0);

        addButton.setOnClickListener(v -> {
            ArrayList<Schedule> schedules = new ArrayList<>();

            Schedule schedule = new Schedule();
            schedule.setClassTitle(className.getText().toString());
            schedule.setClassPlace(classroomName.getText().toString());
            schedule.setProfessorName(classroomName.getText().toString());
            schedule.setDay(date.getValue() - 1);
            schedule.setStartTime(new Time(startTime.getHour(),startTime.getMinute()));
            schedule.setEndTime(new Time(endTime.getHour(),endTime.getMinute()));
            schedules.add(schedule);

            MainActivity.timetableView.add(schedules);
            finish();
        });
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