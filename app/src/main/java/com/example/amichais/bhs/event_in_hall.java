package com.example.amichais.bhs;

import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.CheckBox;
import android.widget.RadioGroup;
import android.widget.RadioButton;
import android.widget.EditText;
import android.widget.TimePicker;
import android.widget.Toast;

public class event_in_hall extends AppCompatActivity {
    RadioGroup radioGroup;
    RadioButton A;
    RadioButton B;
    RadioButton C;
    boolean isauser = false;
    Button sentEmail;
    static final  int DIALOG_ID =0;
    String s;
    String theEmail;
    String temp_mail;
    int cunter = 0;
    EditText BT;
    EditText CT;
    EditText DT;
    EditText ET;
    String temp;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        theEmail = getIntent().getExtras().getString("email");
        temp_mail = theEmail;
        setContentView(R.layout.activity_event_in_hall);

        radioGroup = (RadioGroup) findViewById(R.id.radioGroup);
        A = (RadioButton) findViewById(R.id.radioButton);
        B = (RadioButton) findViewById(R.id.radioButton2);
        C = (RadioButton) findViewById(R.id.radioButton3);

        BT = (EditText) findViewById(R.id.editText3);
        CT = (EditText) findViewById(R.id.editText4);
        DT = (EditText) findViewById(R.id.editText5);
        ET = (EditText) findViewById(R.id.editText9);


        sentEmail = (Button) findViewById(R.id.button9);

        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener(){
            @Override
            public void onCheckedChanged(RadioGroup group, int i) {
                RadioButton rb = (RadioButton) radioGroup.findViewById(i);
                switch (i) {
                    case R.id.radioButton:
                        temp = "סוג האירוע הוא : "  + A.getText().toString();
                        isauser =false;
                        break;
                    case R.id.radioButton2:
                        temp = "סוג האירוע הוא : "  + B.getText().toString();
                        isauser =false;
                        break;
                    case R.id.radioButton3:
                        temp ="סוג האירוע הוא : "  + C.getText().toString();
                        isauser =false;
                        break;

                }
            }
        });
        sentEmail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!theEmail.equals(temp_mail))
                    theEmail=temp_mail;
                if (temp == null || cunter <6 || (BT.getText().toString().equals("") || CT.getText().toString().equals("") || DT.getText().toString().equals(""))) {
                    Toast.makeText(getApplicationContext(), "לא כל השדות מלאים נא למלא את כולם", Toast.LENGTH_LONG).show();
                } else {
                        theEmail += "\n" + "-------------------------------------------------------" + "\n" + "סוג משמרת  :  אירוע ";
                        if (!isauser)
                            theEmail += "\n" + temp;
                        theEmail += "\n" + "-------------------------------------------------------" + "\n" +  "המאבטח חתם על :" + "\n\n1)אקדח ומחסנית \n2)פנס\n3)קשר\n4)גז פלפל\n5)כובע אבטחה\n6)רשיונות"
                                + "\n" + "-------------------------------------------------------" + "\n" + "אחראי אירוע :  " + BT.getText().toString() + " \n" + "-------------------------------------------------------" + "\n" +  "מאבטחים באירוע : " + CT.getText().toString() + " \nמספר שעות : " + DT.getText().toString();



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

    }

    public void select(View view){
        boolean checked = ((CheckBox) view).isChecked();
        if(checked)
            cunter ++;
        else
            cunter--;
    }
}