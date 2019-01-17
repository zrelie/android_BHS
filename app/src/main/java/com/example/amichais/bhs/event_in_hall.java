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
import android.widget.CheckBox;


public class event_in_hall extends AppCompatActivity {
    private RadioGroup radioGroup;
    private RadioButton A;
    private RadioButton B;
    private RadioButton C;
    private boolean isauser = false;
    private Button sentEmail;
    private static final  int DIALOG_ID =0;
    private String s;
    private String theEmail;
    private String name;
    private String date;
    private String temp_mail;
    private int cunter = 0;
    private EditText BT;
    private EditText CT;
    private EditText DT;
    private EditText ET;
    private String temp;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        theEmail = getIntent().getExtras().getString("email");
        temp_mail = theEmail;
        name = getIntent().getExtras().getString("name");
        date = getIntent().getExtras().getString("date");
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

                    editor.putString(index+ " - " +name, "  " + date + "  אירוע");
                    editor.commit();
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