package com.example.amichais.bhs;

import android.app.Dialog;
import android.app.TimePickerDialog;
import android.graphics.Color;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;

public class Shift_Type extends AppCompatActivity {

    protected final int TIME_SRIKA = 10 * 1000;
    protected final int TIME_MATZLEMA = 15 * 1000;

    protected int numOfSricot = 0;
    protected boolean isFinish1 = false;
    protected boolean isFinish2 = false;
    protected int counter = 0;
    protected Button sentEmail;
    protected int isA = 0;
    protected int isB = 0;
    protected int isC = 0;
    protected Button timeA;
    protected Button timeB;
    protected Button timeC;
    protected Button mazlema;
    protected Button srica;
    protected static final  int DIALOG_ID =0;
    protected int hour_x;
    protected int minute_x;
    protected String s;
    protected EditText EDA;
    protected EditText EDB;
    protected EditText EDC;
    protected String theEmail;
    protected String name;
    protected String date;
    protected String temp_mail;
    protected EditText ET;


    @Override
    protected Dialog onCreateDialog(int id){
        if(id == DIALOG_ID)
            return new TimePickerDialog(this,kTimePickerListner,hour_x,minute_x,true);
        return  null;
    }


    protected TimePickerDialog.OnTimeSetListener  kTimePickerListner = new TimePickerDialog.OnTimeSetListener() {
        @Override
        public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
            hour_x = hourOfDay;
            minute_x = minute;
            s = hour_x + " : " + minute_x + "  ";
            numOfSricot++;

            if(isA ==1) {
                EDA.setText(s);
                isA = 0;
            }
            if(isB ==1) {
                EDB.setText(s);
                isB =0;
            }
            if(isC ==1) {
                EDC.setText(s);
                isC = 0;
            }
        }
    };

    protected void time1(){
        final  TextView time = (TextView)findViewById(R.id.textView6);
        new CountDownTimer(TIME_MATZLEMA,1000){
            public void onTick(long milli_sec){
                time.setText("יציאה לסריקה" + "\n" + String.valueOf((int) ((milli_sec / (1000*60*60)) % 24)) +  ":" + String.valueOf((int) ((milli_sec / (1000*60)) % 60)) +  ":" + String.valueOf((int) (milli_sec / 1000) % 60));
            }

            public void onFinish(){
                time.setTextColor(Color.RED);
                time.setText("צא לסריקה!" + "\n" + "צא לסריקה!");
                isFinish1 = true;
            }
        }.start();
    }

    protected void time2() {
        final  TextView time2 = (TextView)findViewById(R.id.textView9);
        new CountDownTimer(TIME_SRIKA,1000){
            public void onTick(long milli_sec){
                time2.setText("יציאה לסריקה" + "\n" + String.valueOf((int) ((milli_sec / (1000*60*60)) % 24)) +  ":" + String.valueOf((int) ((milli_sec / (1000*60)) % 60)) +  ":" + String.valueOf((int) (milli_sec / 1000) % 60));
            }

            public void onFinish(){
                time2.setTextColor(Color.RED);
                time2.setText("צא לסריקה!" + "\n" + "צא לסריקה!");
                isFinish2 = true;
            }
        }.start();
    }

    protected void select(View view){
        boolean checked = ((CheckBox) view).isChecked();
        if(checked)
            counter++;
        else
            counter--;
    }

}
