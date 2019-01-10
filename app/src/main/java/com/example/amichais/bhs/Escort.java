package com.example.amichais.bhs;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
import java.io.File;

public class Escort extends AppCompatActivity {

    Button start;
    Button end;
    String phoneNumber = "0525878674";
    String message = "aaaa";
    private File imageFile;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_escort);

        start = (Button)findViewById(R.id.button15);
        end = (Button)findViewById(R.id.button17);


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

                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("sms:" + "0525878674") );
                intent.putExtra( "sms_body", "הליווי הסתיים" );
                startActivity(intent);

            }
        });


    }
    public void process(View view){
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        imageFile =  new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES),"test.jpg");
        Uri tempuri =  Uri.fromFile(imageFile);
        intent.putExtra(MediaStore.EXTRA_OUTPUT, tempuri);
        intent.putExtra(MediaStore.EXTRA_VIDEO_QUALITY,1);
        startActivityForResult(intent,0);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(requestCode == 0){
            switch (requestCode)   {
                case Activity.RESULT_OK:
                    if(imageFile.exists()){
                        Toast.makeText(getApplicationContext(),"the file was saved at" + imageFile.getAbsolutePath(), Toast.LENGTH_LONG).show();
                    }
                    else {
                        Toast.makeText(getApplicationContext(),"the was an err", Toast.LENGTH_LONG).show();
                    }

                    break;
                case Activity.RESULT_CANCELED:
                    break;
                default:
                    break;
            }
        }
    }
}
