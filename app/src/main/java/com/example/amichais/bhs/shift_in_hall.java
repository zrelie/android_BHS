package com.example.amichais.bhs;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class shift_in_hall extends AppCompatActivity {
    private Intent I_A;
    private Intent I_B;
    private Intent I_C;

    private Button button_A;
    private Button button_B;
    private Button button_C;
    private String theEmail;
    private String name;
    private String date;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        I_A = new Intent(shift_in_hall.this, Morning_shift.class);
        I_B = new Intent(shift_in_hall.this, noon_shift.class);
        I_C = new Intent(shift_in_hall.this, night_shift.class);

        theEmail = getIntent().getExtras().getString("email");
        name = getIntent().getExtras().getString("name");
        date = getIntent().getExtras().getString("date");

        setContentView(R.layout.activity_shift_in_hall);

        button_A = (Button)findViewById(R.id.button5);
        button_B = (Button)findViewById(R.id.button6);
        button_C = (Button)findViewById(R.id.button7);

        button_A.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                I_A.putExtra("email", theEmail);
                I_A.putExtra("name", name);
                I_A.putExtra("date", date);
                startActivity(I_A);
            }
        });

        button_B.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                I_B.putExtra("email", theEmail);
                I_B.putExtra("name", name);
                I_B.putExtra("date", date);
                startActivity(I_B);
            }
        });

        button_C.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                I_C.putExtra("email", theEmail);
                I_C.putExtra("name", name);
                I_C.putExtra("date", date);
                startActivity(I_C);
            }
        });

    }

}

