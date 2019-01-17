package com.example.amichais.bhs;

import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

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
        for (Map.Entry<String, ?> entry : allEntries.entrySet()) {
            logs += entry.getKey() + ": " + entry.getValue().toString();
            logs += "\n";
            logs = logs.replace("null", "");
        }
        log.setText(logs);
    }
}
