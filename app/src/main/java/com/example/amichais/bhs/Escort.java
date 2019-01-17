package com.example.amichais.bhs;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class Escort extends AppCompatActivity {

    private Button start;
    private Button end;
    private Button take_pic;
    private ImageView imageView;
    private Bitmap bitmap;
    private String name;
    private String date;
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

            }
        });
        end.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
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

                editor.putString(index+ " - " +name, "  " + date + "  ליווי");
                editor.commit();
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("sms:" + "0525878674") );
                intent.putExtra( "sms_body", "הליווי הסתיים" );
                intent.putExtra(Intent.EXTRA_STREAM, bitmap);
                intent.setType("image/gif");
                startActivity(Intent.createChooser(intent, "send"));

            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        bitmap = (Bitmap)data.getExtras().get("data");
        imageView.setImageBitmap(bitmap);

    }
}
