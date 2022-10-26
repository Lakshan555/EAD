package com.example.ead.Login;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.ead.Home.HomeStationOwner;
import com.example.ead.Home.HomeVehicleOwner;
import com.example.ead.R;

import org.json.JSONObject;

import java.util.HashMap;

public class SignInVehicleOwner extends AppCompatActivity {

    private TextView signUp,name,password;
    private String userType = "VEHICLE_OWNER";
    private Button btn;
    private RequestQueue queue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sing_in_vehicle_owner);

        name = (TextView) findViewById(R.id.signInDVNo);
        password = (TextView) findViewById(R.id.signInDPass);
        signUp = (TextView) findViewById(R.id.noAcRegText);
        btn = findViewById(R.id.SignInBtn);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                LoginDriver();
            }
        });

        signUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SignInVehicleOwner.this, SignUpVehicleOwner.class);
                startActivity(intent);
            }
        });
    }

    private void LoginDriver(){

        HashMap<String,String> params = new HashMap<String,String>();

        params.put("uniqueIdentifier",name.getText().toString());
        params.put("password",password.getText().toString());
        params.put("userType",userType);

        queue = Volley.newRequestQueue(this);
        String url = "https://pasindu-fuelapi.herokuapp.com/users/login";
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest
                (Request.Method.POST, url, new JSONObject(params), new Response.Listener<JSONObject>() {

                    @Override
                    public void onResponse(JSONObject response) {
                        Log.e("Success ",response.toString());
                        Intent intent = new Intent(SignInVehicleOwner.this, HomeVehicleOwner.class);
                        startActivity(intent);

                    }
                }, new Response.ErrorListener(){

                    @Override
                    public void onErrorResponse(VolleyError error) {
                        // TODO: Handle error
                        Log.e("Error",error.toString());
                    }
                });
        queue.add(jsonObjectRequest);

    }
}