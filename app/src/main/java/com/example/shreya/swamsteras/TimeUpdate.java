package com.example.shreya.swamsteras;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class TimeUpdate extends AppCompatActivity
{

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_time_update);
        Log.d("TimeUpdate", "Entered TimeUpdate");
        final Button timeButton = findViewById(R.id.updateButton);
        Log.d("TimeUpdate", "Completed button creation.");

        // Creates a OnClickListener.
        timeButton.setOnClickListener(new View.OnClickListener()
        {
            public void onClick(View view)
            {
                EditText name = findViewById(R.id.eventNames);
                EditText start = findViewById(R.id.startTime);
                EditText mins = findViewById(R.id.minutesPerRace);
                EditText secs = findViewById(R.id.secondsPerRace);

                String eventName = name.getText().toString();
                String[] time = start.getText().toString().split(":");

                // Gets the start hour and minute as well as the minutes and seconds it takes per race.
                int startHour = Integer.parseInt(time[0]);
                int startMinute = Integer.parseInt(time[1]);
                int raceMinutes = Integer.parseInt(mins.getText().toString());
                int raceSeconds = Integer.parseInt(secs.getText().toString());

                // Packages all this data to be sent into another activity.
                Intent intent = new Intent(view.getContext(), UpdateTime.class);
                intent.putExtra("eventName", eventName);
                intent.putExtra("startHour", startHour);
                intent.putExtra("startMinute", startMinute);
                intent.putExtra("raceMinutes", raceMinutes);
                intent.putExtra("raceSeconds", raceSeconds);
                startActivity(intent);
            }
        });
    }
}
