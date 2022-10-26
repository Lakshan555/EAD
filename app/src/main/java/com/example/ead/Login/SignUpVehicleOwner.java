package com.example.ead.Login;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.ead.Home.HomeVehicleOwner;
import com.example.ead.R;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;

public class SignUpVehicleOwner extends AppCompatActivity {

    private TextView name,nic,VehicleNo,chNo,fuelType,vehicleType,password;
    private String userType = "VEHICLE_OWNER";
    private Button btn;
    private RequestQueue queue;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up_vehicle_owner);

        name = (TextView) findViewById(R.id.singUpVOName);
        nic = (TextView) findViewById(R.id.singUpVONicNo);
        chNo = (TextView) findViewById(R.id.singUpChNo);
        VehicleNo = (TextView) findViewById(R.id.singUpVhNo);
        fuelType = (TextView) findViewById(R.id.singUpVoFtype);
        vehicleType=(TextView) findViewById(R.id.singUpVoVehiType);
        password = (TextView) findViewById(R.id.singUpVoPass);
        btn = findViewById(R.id.SignUpVOBtn);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SignUPDriver();
            }
        });
    }

    //Register vehicle owner details
    private void SignUPDriver(){

        HashMap<String,String> params = new HashMap<String,String>();

        params.put("name",name.getText().toString());
        params.put("NIC",nic.getText().toString());
        params.put("chassis_number",chNo.getText().toString());
        params.put("vehicle_number",VehicleNo.getText().toString());
        params.put("fuel_type",fuelType.getText().toString());
        params.put("vehicle_type",vehicleType.getText().toString());
        params.put("user_type",userType);
        params.put("password",password.getText().toString());



        queue = Volley.newRequestQueue(this);
        String url = "https://ishankafuel.herokuapp.com/users/register";
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest
                (Request.Method.POST, url, new JSONObject(params), new Response.Listener<JSONObject>() {

                    @Override
                    public void onResponse(JSONObject response) {
                        Log.e("Success ",response.toString());

                        try {
                            if(response.get("isSuccessful").equals(true)){
                                Log.e("Success ",response.get("user").toString());
                                Intent intent = new Intent(SignUpVehicleOwner.this, SignInVehicleOwner.class);
                                startActivity(intent);
                            }
                            else{
                                Toast.makeText(SignUpVehicleOwner.this, "Wrong!", Toast.LENGTH_SHORT).show();
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                            Toast.makeText(SignUpVehicleOwner.this, "Exception!", Toast.LENGTH_SHORT).show();
                        }


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