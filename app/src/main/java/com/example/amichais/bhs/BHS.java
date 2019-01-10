package com.example.amichais.bhs;

import android.app.Dialog;
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
import java.util.logging.SimpleFormatter;
import android.app.DatePickerDialog;
import java.util.Calendar;
import android.widget.DatePicker;

public class BHS extends AppCompatActivity {
    Intent I;
    Button buttonLogin;
    Button buttonD;
    EditText textUsername;
    EditText password;
    EditText date;
    String dateOfTheTask;
    int year_x;
    int month_x;
    int day_x;
    String theEmail;
    static final int DIALOG_ID = 0;


    String[] pass = {"108","116","102","89","68","88","120","114","20","11","113","123","121","1","79","122","103","115"};



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        I = new Intent(BHS.this, Choose_a_shift.class);


        setContentView(R.layout.activity_bhs);

        buttonLogin = (Button) findViewById(R.id.button);
        textUsername = (EditText) findViewById(R.id.editText);
        password = (EditText) findViewById(R.id.editText2);
        date = (EditText)findViewById(R.id.editText7);

        buttonLogin.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {

                if (textUsername.getText().toString().equals(""))
                    Toast.makeText(getApplicationContext(), "הכנס שם מאבטח", Toast.LENGTH_LONG).show();

                else if (password.getText().toString().equals(""))
                    Toast.makeText(getApplicationContext(), "הכנס סיסמה", Toast.LENGTH_LONG).show();

                else if (date.getText().toString().equals(""))
                    Toast.makeText(getApplicationContext(), "הכנס תאריך", Toast.LENGTH_LONG).show();
                else if(!goodPss())
                    Toast.makeText(getApplicationContext(), "סיסמה שגויה", Toast.LENGTH_LONG).show();

                else {
                    theEmail = " שם מאבטח :    " + textUsername.getText().toString() + "\n" + "תאריך  :  "  + date.getText().toString();
                    I.putExtra("email",theEmail);
                    startActivity(I);
                }


            }
        });

        final Calendar cal = Calendar.getInstance();
        year_x = cal.get(Calendar.YEAR);
        month_x = cal.get(Calendar.MONTH);
        day_x = cal.get(Calendar.DAY_OF_MONTH);
        showDialogOn(); }

    private boolean goodPss() {
        for(int i = 0; i < pass.length ; i++)
            if(password.getText().toString().equals(pass[i]))
                return true;
        return false;
    }


    public void showDialogOn(){
        buttonD = (Button) findViewById(R.id.button10);
        buttonD.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDialog(DIALOG_ID );
            }
        });
    }
    @Override
    protected Dialog onCreateDialog(int id){
        if(id==DIALOG_ID)
            return new DatePickerDialog(this,dpickerListner,year_x,month_x,day_x);
        return null;
    }
    private DatePickerDialog.OnDateSetListener dpickerListner =
            new DatePickerDialog.OnDateSetListener() {
                @Override
                public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                    year_x = year;
                    month_x = monthOfYear +1 ;
                    day_x = dayOfMonth;
                    dateOfTheTask = day_x + "/" + month_x + "/" + year_x;
                    date.setText(dateOfTheTask);
                }
            };
}

