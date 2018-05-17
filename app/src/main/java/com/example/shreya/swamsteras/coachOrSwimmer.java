package com.example.shreya.swamsteras;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class coachOrSwimmer extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_coach_or_swimmer);

        /**
         * The swim button leads to the swim and coach ans page.
         */
        final Button swimmersButton = findViewById(R.id.swimButton);
        swimmersButton.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view) {
                Intent intent = new Intent(view.getContext(), SwimAndCoachAns.class);
                startActivity(intent);
            }
        });

        /**
         * The coach button leads to the coach login page.
         */
        final Button coachsButton = findViewById(R.id.coachButton);
        coachsButton.setOnClickListener(new View.OnClickListener(){

            public void onClick(View view) {
                Intent intent = new Intent(view.getContext(), coachLogin.class);
                startActivity(intent);
            }
        });
    }
}