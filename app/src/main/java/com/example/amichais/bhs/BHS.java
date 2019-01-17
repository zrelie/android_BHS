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
import android.app.DatePickerDialog;
import java.util.Calendar;
import android.widget.DatePicker;

public class BHS extends AppCompatActivity {
    private Intent I;
    private Intent logs;
    private Button buttonLogin;
    private Button buttonD;
    private Button log;
    private EditText textUsername;
    private EditText password;
    private EditText date;
    private String dateOfTheTask;
    private int year_x;
    private int month_x;
    private int day_x;
    private String theEmail;
    private static final int DIALOG_ID = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        I = new Intent(BHS.this, Choose_a_shift.class);
        logs = new Intent(this, Logs.class);
        setContentView(R.layout.activity_bhs);

        buttonLogin = (Button) findViewById(R.id.button);
        textUsername = (EditText) findViewById(R.id.editText);
        password = (EditText) findViewById(R.id.editText2);
        date = (EditText)findViewById(R.id.editText7);
        log = (Button) findViewById(R.id.button28);

        log.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(textUsername.getText().toString().equals("בוס") && password.getText().toString().equals("111"))
                    startActivity(logs);
                else
                    Toast.makeText(getApplicationContext(), "אינך מורשה באזור זה", Toast.LENGTH_LONG).show();

            }
        });

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
                    I.putExtra("name",textUsername.getText().toString());
                    I.putExtra("date",date.getText().toString());
                    startActivity(I);
                }


            }
        });

        final Calendar cal = Calendar.getInstance();
        year_x = cal.get(Calendar.YEAR);
        month_x = cal.get(Calendar.MONTH);
        day_x = cal.get(Calendar.DAY_OF_MONTH);
        showDialogOn();
    }

    private boolean goodPss() {

        SharedPreferences sp = getSharedPreferences("MyPref", MODE_PRIVATE);
        if(!sp.getString(textUsername.getText().toString(),"").equals("")) {
            if ((sp.getString(textUsername.getText().toString(), "").equals(password.getText().toString())))
                return true;
        }
        else if((password.getText().toString().length() >=5) && (password.getText().toString().substring(0,5).equals("abcde"))){
            SharedPreferences.Editor editor;
            editor = sp.edit();
            editor.putString(textUsername.getText().toString(), password.getText().toString().substring(5));
            editor.commit();
            return true;
        }
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

