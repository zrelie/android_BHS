package com.example.amichais.bhs;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class shift_in_hall extends AppCompatActivity {
    Intent I_A;
    Intent I_B;
    Intent I_C;

    Button button_A;
    Button button_B;
    Button button_C;
    String theEmail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        I_A = new Intent(shift_in_hall.this, Morning_shift.class);
        I_B = new Intent(shift_in_hall.this, noon_shift.class);
        I_C = new Intent(shift_in_hall.this, night_shift.class);

        theEmail = getIntent().getExtras().getString("email");

        setContentView(R.layout.activity_shift_in_hall);

        button_A = (Button)findViewById(R.id.button5);
        button_B = (Button)findViewById(R.id.button6);
        button_C = (Button)findViewById(R.id.button7);

        button_A.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                I_A.putExtra("email", theEmail);
                startActivity(I_A);
            }
        });

        button_B.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                I_B.putExtra("email", theEmail);
                startActivity(I_B);
            }
        });

        button_C.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                I_C.putExtra("email", theEmail);
                startActivity(I_C);
            }
        });

    }

}

