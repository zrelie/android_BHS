package com.example.amichais.bhs;


import android.Manifest;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

public class Escort extends AppCompatActivity {

    private Button start;
    private Button end;
    private Button take_pic;
    private ImageView imageView;
    private Bitmap bitmap;
    private EditText the_order;
    private EditText contact;
    private EditText were;
    private EditText expenses;
    public String name;
    private String date;

    LowBatBroadcast batBroadcast;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_escort);
        name = getIntent().getExtras().getString("name");
        date = getIntent().getExtras().getString("date");

        start = (Button)findViewById(R.id.button15);
        end = (Button)findViewById(R.id.button17);
        take_pic = (Button)findViewById(R.id.button27);
        imageView = (ImageView)findViewById(R.id.imageView);
        the_order = (EditText)findViewById(R.id.editText11);
        contact = (EditText)findViewById(R.id.editText12);
        were = (EditText)findViewById(R.id.editText13);
        expenses = (EditText)findViewById(R.id.editText14);

        batBroadcast = new LowBatBroadcast(name);

        take_pic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(intent,0);
            }
        });


        start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("sms:" + "0525878674") );
                intent.putExtra( "sms_body", "ההליווי התחיל" );
                startActivity(intent);
                getBaseContext().registerReceiver(batBroadcast, new IntentFilter(Intent.ACTION_BATTERY_LOW));
            }
        });

        end.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (the_order.getText().toString().equals("")|| contact.getText().toString().equals("")||expenses.getText().toString().equals("")||were.getText().toString().equals("") ) {
                    Toast.makeText(getApplicationContext(), "לא כל השדות מלאים נא למלא את כולם", Toast.LENGTH_LONG).show();
                }
                else {
                    try {
                        getBaseContext().unregisterReceiver(batBroadcast);
                    }catch (Exception e){}
                    SharedPreferences sp = getSharedPreferences("LogPref", MODE_PRIVATE);
                    SharedPreferences.Editor editor;
                    editor = sp.edit();
                    String index = "1";
                    if (!sp.getString("index", "").equals("")) {
                        int temp = Integer.parseInt(sp.getString("index", "")) + 1;
                        index = String.valueOf(temp);
                        editor.putString("index", index);
                    } else
                        editor.putString("index", "1");


                    editor.putString(index + " - " + name, "  " + date + "  ליווי");
                    editor.commit();
                    Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("sms:" + "0525878674"));
                    intent.putExtra("sms_body", "הליווי הסתיים" + "\n\n" + "מזמין הליווי הוא:   " + the_order.getText().toString() + "\n"
                            + "איש הקשר הוא:   " + contact.getText().toString() + "\n" + "מקום הליווי: " + were.getText().toString() + "\n" + "סכום הוצאות: " + expenses.getText().toString());
                    imageView.setImageBitmap(null);
                    startActivity(Intent.createChooser(intent, "send"));
                }
            }
        });


        ActivityCompat.requestPermissions(Escort.this,
                new String[]{Manifest.permission.SEND_SMS},
                1);
    }


    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           String permissions[], int[] grantResults) {
        switch (requestCode) {
            case 1: {
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                } else {
                }
                return;
            }
        }
    }



    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        bitmap = (Bitmap)data.getExtras().get("data");
        imageView.setImageBitmap(bitmap);

    }

    public String getName(){
        return name;
    }
}
