package com.example.ead;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.ead.Login.SignInStationOwner;
import com.example.ead.Login.SignInVehicleOwner;
import com.example.ead.View.FuelDetailsStationOwnerView;


public class MainActivity extends AppCompatActivity {
    private Button voBtn;
    private Button sOBtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        voBtn =(Button) findViewById(R.id.voBtn);
        sOBtn =(Button) findViewById(R.id.sOBtn);

        voBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this,SignInVehicleOwner.class);
                startActivity(intent);
            }
        });

        sOBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, FuelDetailsStationOwnerView.class);
                startActivity(intent);
            }
        });

    }
}