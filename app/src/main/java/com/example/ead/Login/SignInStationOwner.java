package com.example.ead.Login;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.ead.R;

public class SignInStationOwner extends AppCompatActivity {

    private TextView signUp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sing_in_station_owner);

        signUp = (TextView) findViewById(R.id.noAcStationRegText);

        signUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SignInStationOwner.this, SignUpStaionOwner.class);
                startActivity(intent);
            }
        });
    }
}