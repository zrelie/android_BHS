package com.example.amichais.bhs;

import android.content.SharedPreferences;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class night_shift extends Shift_Type {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        theEmail = getIntent().getExtras().getString("email");
        temp_mail = theEmail;
        name = getIntent().getExtras().getString("name");
        date = getIntent().getExtras().getString("date");
        setContentView(R.layout.activity_night_shift);

        time1();
        time2();

        startService(new Intent(this, TimeServiceSrika.class));
        startService(new Intent(this, TimeServiceMatzlema.class));

        mazlema = (Button)findViewById(R.id.button25);
        srica = (Button)findViewById(R.id.button26);

        ET = (EditText) findViewById(R.id.editText15);

        EDA = (EditText) findViewById(R.id.editText3);
        EDB = (EditText) findViewById(R.id.editText4);
        EDC = (EditText) findViewById(R.id.editText5);

        sentEmail = (Button) findViewById(R.id.button8);

        sentEmail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!theEmail.equals(temp_mail))
                    theEmail = temp_mail;
                if (counter < 10)
                    Toast.makeText(getApplicationContext(), "לא כל השדות מלאים נא למלא את כולם", Toast.LENGTH_LONG).show();
                else{
                    SharedPreferences sp = getSharedPreferences("LogPref", MODE_PRIVATE);
                    SharedPreferences.Editor editor;
                    editor = sp.edit();
                    String index = "1";
                    if(!sp.getString("index","").equals("")) {
                        int temp = Integer.parseInt(sp.getString("index","")) + 1;
                        index = String.valueOf(temp);
                        editor.putString("index", index);
                    }else
                        editor.putString("index", "1");

                    editor.putString(index+ " - " +name, "  " + date + "  משמרת לילה");
                    editor.commit();
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
                stopService(new Intent(v.getContext(), TimeServiceMatzlema.class));
                startService(new Intent(v.getContext(), TimeServiceMatzlema.class));
                if (isFinish1) {
                    final TextView time = (TextView) findViewById(R.id.textView6);
                    time.setTextColor(Color.GREEN);
                    isFinish1 = false;
                    time1();
                }
            }
        });


        srica.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                stopService(new Intent(v.getContext(), TimeServiceSrika.class));
                startService(new Intent(v.getContext(), TimeServiceSrika.class));
                if(isFinish2) {
                    numOfSricot++;
                    if (numOfSricot == 1)
                        EDA.setText("23:00");
                    else if (numOfSricot == 2)
                        EDB.setText("03:00");
                    else if (numOfSricot == 3)
                        EDC.setText("06:15");

                    final TextView time2 = (TextView) findViewById(R.id.textView9);
                    time2.setTextColor(Color.GREEN);
                    isFinish2 = false;
                    time2();
                }
            }
        });
    }

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


    @Override
    public void onDestroy() {
        super.onDestroy();
        stopService(new Intent(this, TimeServiceSrika.class));
        stopService(new Intent(this, TimeServiceMatzlema.class));
    }
    public void select(View view) {
        super.select(view);
    }
}
