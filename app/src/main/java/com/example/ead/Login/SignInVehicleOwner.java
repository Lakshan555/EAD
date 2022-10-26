package com.example.ead.Login;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.ead.R;

public class SignInVehicleOwner extends AppCompatActivity {

    private TextView signUp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sing_in_vehicle_owner);

        signUp = (TextView) findViewById(R.id.noAcRegText);

        signUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SignInVehicleOwner.this, SignUpVehicleOwner.class);
                startActivity(intent);
            }
        });
    }
}