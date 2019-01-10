package com.example.amichais.bhs;

import android.graphics.Color;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;


import android.app.Dialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

public class night_shift extends AppCompatActivity {
    Button sentEmail;


    int numOfSricot = 0;
    boolean isFinis1 = false;
    boolean isFinis2 = false;
    int counter_check = 0;

    MediaPlayer matslema;
    MediaPlayer elarm;
    boolean sricot = false;
    boolean sricot2 = false;

    int counter = 59;
    int counterOfMimForTimer1 = 44;
    int counterOfhForTimer2 = 1;
    int counterOfMimForTimer2 = 29;
    int counter2 = 59;

    Button mazlema;
    Button srica;

    int isA = 0;
    int isB = 0;
    int isC = 0;
    EditText ET;
    Button timeA;
    Button timeB;
    Button timeC;

    static final int DIALOG_ID = 0;
    int hour_x;
    int cunter = 0;
    int minute_x;
    String s;
    EditText EDA;
    EditText EDB;
    EditText EDC;
    EditText EDD;
    EditText EDE;

    String theEmail;
    String temp_mail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        theEmail = getIntent().getExtras().getString("email");
        temp_mail = theEmail;
        setContentView(R.layout.activity_night_shift);

        time1();
        time2();

        mazlema = (Button)findViewById(R.id.button25);
        srica = (Button)findViewById(R.id.button26);

        ET = (EditText) findViewById(R.id.editText15);

        EDA = (EditText) findViewById(R.id.editText3);
        EDB = (EditText) findViewById(R.id.editText4);
        EDC = (EditText) findViewById(R.id.editText5);
        EDD= (EditText)findViewById(R.id.editText5);
        EDE= (EditText)findViewById(R.id.editText6);

        sentEmail = (Button) findViewById(R.id.button8);

        sentEmail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!theEmail.equals(temp_mail))
                    theEmail = temp_mail;
                if (counter_check < 10)
                    Toast.makeText(getApplicationContext(), "לא כל השדות מלאים נא למלא את כולם", Toast.LENGTH_LONG).show();
                else{
                    theEmail += "\n" +
                            "-------------------------------------------------------" + "\n" + "סוג משמרת :  משמרת עולמיא  ";
                    theEmail += "\n" + "משמרת : משמרת לילה";
                    theEmail += "\n" +
                            "-------------------------------------------------------" + "\n" + "המאבטח חתם על :" + "\n\n1)אקדח ומחסנית \n2)פנס\n3)צרור מפתחות\n4)גז פלפל\n5)קריאת נהלי פתיחה באש"
                            + "\n" +
                            "-------------------------------------------------------" + "\n" + "משימות הלילה שבוצעו הם :\n\n" + "1)וידוא דריכת אזעקה מלאה \n2)כיבוי אורות פנימיים והדלקת אורות חיצוניים\n3)דריכת אזעקה ב18:00 במידה ואין עובדים\n 4)בדיקה שאין ציוד מפוזר בחוץ" + "\n" +
                            "-------------------------------------------------------" + "\n"
                            + "סריקות המאבטח בוצעו בשעות הבאות :" + "\n\n" + EDA.getText().toString() + "\n" + EDB.getText().toString() + "\n" +
                            EDC.getText().toString() + "\n";


                    if (!ET.getText().toString().equals("")) {
                        theEmail += "\n הערות או אירועים חריגים  :" + ET.getText().toString();
                        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("sms:" + "0525878674"));
                        intent.putExtra("sms_body", "קרה אירוע חריג והוא " + "\n" + ET.getText().toString());
                        startActivity(intent);
                    }


                    String a = theEmail;
                    String[] to = new String[]{"amichaisteiner@gmail.com"};
                    String subject = ("דוח משמרת!");
                    Intent emailIntent = new Intent(Intent.ACTION_SEND);
                    emailIntent.putExtra(Intent.EXTRA_EMAIL, to);
                    emailIntent.putExtra(Intent.EXTRA_SUBJECT, subject);
                    emailIntent.putExtra(Intent.EXTRA_TEXT, a);
                    emailIntent.setType("message/rfc822");
                    startActivity(Intent.createChooser(emailIntent, "Email"));
                }
             }
        });
        showTimePickerDialog();





        mazlema.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isFinis1) {
                    final TextView time = (TextView) findViewById(R.id.textView6);
                    time.setTextColor(Color.GREEN);
                    matslema.stop();
                    isFinis1 = false;
                    time1();
                }
            }
        });






        srica.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(isFinis2) {
                    numOfSricot++;
                    if (numOfSricot == 1)
                        EDA.setText("08:30");
                    else if (numOfSricot == 2)
                        EDB.setText("10:00");
                    else if (numOfSricot == 3)
                        EDC.setText("11:30");
                    else if (numOfSricot == 4)
                        EDD.setText("13:00");
                    else if (numOfSricot == 5)
                        EDE.setText("14:30");

                    final TextView time2 = (TextView) findViewById(R.id.textView9);
                    time2.setTextColor(Color.GREEN);
                    elarm.stop();
                    isFinis2 = false;
                    time2();
                }
            }
        });







    }


    @Override
    protected Dialog onCreateDialog(int id) {
        if (id == DIALOG_ID)
            return new TimePickerDialog(night_shift.this, kTimePickerListner, hour_x, minute_x, true);
        return null;
    }

    protected TimePickerDialog.OnTimeSetListener kTimePickerListner = new TimePickerDialog.OnTimeSetListener() {
        @Override
        public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
            hour_x = hourOfDay;
            minute_x = minute;
            s = minute_x + " : " + hour_x + "  ";

            if (isA == 1) {
                EDA.setText(s);
                isA = 0;
            }
            if (isB == 1) {
                EDB.setText(s);
                isB = 0;
            }
            if (isC == 1) {
                EDC.setText(s);
                isC = 0;
            }
        }

    };


    public void showTimePickerDialog() {
        timeA = (Button) findViewById(R.id.button22);
        timeB = (Button) findViewById(R.id.button23);
        timeC = (Button) findViewById(R.id.button24);

        timeA.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                isA = 1;
                showDialog(DIALOG_ID);
            }
        });
        timeB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                isB = 1;
                showDialog(DIALOG_ID);
            }
        });
        timeC.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                isC = 1;
                showDialog(DIALOG_ID);
            }
        });

    }


    public void time1(){
        matslema = MediaPlayer.create(this,R.raw.matslema);
        counter=59;
        counterOfMimForTimer1 = 44;
        final  TextView time = (TextView)findViewById(R.id.textView6);
        new CountDownTimer(2652000  ,1000){
            public void onTick(long milli_sec){
                time.setText("סריקת מצלמות" + "\n" + String.valueOf(counterOfMimForTimer1) + ":" +String.valueOf(counter)+"\n");
                counter--;
                if(counter == 0) {
                    counterOfMimForTimer1--;
                    counter=59;
                }
                if(counterOfMimForTimer1<0)
                    time.setText("סריקת מצלמות" + "\n" + "00:00" +"\n");
            }
            public void onFinish(){
                time.setTextColor(Color.RED);
                time.setText("סרוק מצלמות!" + "\n" + "סרוק מצלמות!");
                matslema.start();
                isFinis1 = true;

            }
        }.start();
    }

    private void time2() {
        elarm = MediaPlayer.create(this,R.raw.elarm);
        counterOfhForTimer2 = 1;
        counterOfMimForTimer2 = 29;
        counter2=59;
        final  TextView time2 = (TextView)findViewById(R.id.textView9);
        new CountDownTimer(5244000   ,1000){
            public void onTick(long milli_sec){
                time2.setText("יציאה לסריקה" + "\n" + String.valueOf(counterOfhForTimer2) +  ":" + String.valueOf(counterOfMimForTimer2) +  ":" +String.valueOf(counter2));
                counter2--;
                if(counter2==0){
                    counterOfMimForTimer2--;
                    counter2 = 59;
                }
                if((counterOfMimForTimer2==0)&&(counterOfhForTimer2==1)) {
                    counterOfhForTimer2 --;
                    counterOfMimForTimer2 = 59;
                }
                if(counterOfMimForTimer1<0)
                    time2.setText("יציאה לסריקה" + "\n" + "00:00" +"\n");

            }
            public void onFinish(){
                time2.setTextColor(Color.RED);
                time2.setText("צא לסריקה!" + "\n" + "צא לסריקה!");
                elarm.start();
                isFinis2 = true;
            }
        }.start();
    }

    public void select(View view) {
        boolean checked = ((CheckBox) view).isChecked();
        if (checked)
            counter_check ++;
        else
            counter_check --;

    }
}
