package com.example.beerlover;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private TextView VtxtWelcome;
    private Button btnList, btnScan;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        VtxtWelcome = findViewById(R.id.VtxtWelcome);
        btnList = findViewById(R.id.btnList);
        btnScan = findViewById(R.id.btnScan);
    }
}