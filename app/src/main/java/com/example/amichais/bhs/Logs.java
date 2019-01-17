package com.example.amichais.bhs;

import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;
import java.util.Map;

public class Logs extends AppCompatActivity {

    String logs;
    TextView log;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_logs);
        log = (TextView)findViewById(R.id.textView19) ;
        SharedPreferences sp = getSharedPreferences("LogPref", MODE_PRIVATE);
        Map<String, ?> allEntries = sp.getAll();
        String[] entries = new String[allEntries.size()];
        int index = 0;
        for (Map.Entry<String, ?> entry : allEntries.entrySet()) {
            if (!entry.getKey().equals("index")) {
                entries[index] = entry.getKey() + entry.getValue();
                index++;
            }
//            if (!entry.getKey().equals("index")) {
//                logs += entry.getKey() + ": " + entry.getValue().toString();
//                logs += "\n";
//                logs = logs.replace("null", "");
//            }
        }

        for (int i=0 ; i<entries.length ; i++){
            for (int j=0 ; j<entries.length ; i++){
                if (Integer.parseInt(entries[j].substring(0,entries[j].indexOf("-")).replaceAll("[^0-9]", "")) == i+1)
                    logs+=entries[j];
            }
        }


        log.setText(logs);
    }
}
