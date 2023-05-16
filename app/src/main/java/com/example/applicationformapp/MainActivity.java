package com.example.applicationformapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.os.Bundle;
import android.util.Log;

import com.example.appformlib.ApplicationForm;
import com.example.appformlib.Callback_DetailsProtocol;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    Callback_DetailsProtocol callback_detailsProtocol = new Callback_DetailsProtocol() {
        @Override
        public void useDetails(ArrayList<String> details) {
            // use details. details[0] = name, details[1] = phone, details[2] = email, details[3] = address , details[4] = additional info
            Log.d("In callback", "ready to use details");
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ConstraintLayout mainLay = findViewById(R.id.main_LAY_main);

        ApplicationForm.get().setCallback_detailsProtocol(callback_detailsProtocol);
        ApplicationForm.get().generateForm(this, mainLay);
    }
}