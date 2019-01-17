package com.example.amichais.bhs;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import java.util.Map;

public class Logs extends AppCompatActivity {

    String logs;
    TextView log;
    Button send;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_logs);

        log = (TextView)findViewById(R.id.textView19) ;
        send = (Button)findViewById(R.id.button29);

        logs = "";

        final SharedPreferences sp = getSharedPreferences("LogPref", MODE_PRIVATE);
        SharedPreferences.Editor editor;
        editor = sp.edit();

        try {
            Map<String, ?> allEntries = sp.getAll();
            String[] entries = new String[allEntries.size()-1];
            int index = 0;
            for (Map.Entry<String, ?> entry : allEntries.entrySet()) {
                if (!entry.getKey().equals("index")) {
                    entries[index] = entry.getKey() + entry.getValue();
                    index++;
                }
            }


            for (int i=0 ; i<entries.length ; i++){
                for (int j=0 ; j<entries.length ; j++){
                    if (Integer.parseInt(entries[j].substring(0,entries[j].indexOf("-")).replaceAll("[^0-9]", "")) == i+1) {
                        logs += entries[j];
                        logs += "\n";
                        break;
                    }
                }
            }
        }catch (Exception e){}



        log.setText(logs);
        log.setMovementMethod(new ScrollingMovementMethod());

        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String a = logs;
                String[] to = new String[]{"amichaisteiner@gmail.com"};
                String subject = ("דוח שליחות");
                Intent emailIntent = new Intent(Intent.ACTION_SEND);
                emailIntent.putExtra(Intent.EXTRA_EMAIL, to);
                emailIntent.putExtra(Intent.EXTRA_SUBJECT, subject);
                emailIntent.putExtra(Intent.EXTRA_TEXT, a);
                emailIntent.setType("message/rfc822");
                startActivity(Intent.createChooser(emailIntent, "Email"));


                sp.edit().clear().commit();
            }
        });
    }
}
