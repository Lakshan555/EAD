package com.example.ead.Login;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.ead.Home.HomeStationOwner;
import com.example.ead.Home.HomeVehicleOwner;
import com.example.ead.R;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;

public class SignUpStaionOwner extends AppCompatActivity {

    private TextView name,nic,station_name,register_no,fuelType,password;
    private String userType = "STATION_OWNER";
    private Button btn;
    private RequestQueue queue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up_station_owner);

        name = (TextView) findViewById(R.id.singUpSOName);
        nic = (TextView) findViewById(R.id.singUpSONicNo);
        station_name = (TextView) findViewById(R.id.singUpSTNo);
        register_no = (TextView) findViewById(R.id.singUpSoReg);
        fuelType = (TextView) findViewById(R.id.singUpSoFtype);
        password = (TextView) findViewById(R.id.singUpSoPass);
        btn = findViewById(R.id.SignUpSOBtn);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                SignUPStationOwner();

            }
        });

    }
    //this will crate  Station owner
    private void SignUPStationOwner(){

        HashMap<String,String> params = new HashMap<String,String>();


        params.put("user_name",name.getText().toString());
        params.put("NIC",nic.getText().toString());
        params.put("station_name",station_name.getText().toString());
        params.put("register_no",register_no.getText().toString());
        params.put("fuel_type",fuelType.getText().toString());
        params.put("user_type",userType);
        params.put("password",password.getText().toString());

        queue = Volley.newRequestQueue(this);
        String url = "https://ishankafuel.herokuapp.com/users/register";
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest
                (Request.Method.POST, url, new JSONObject(params), new Response.Listener<JSONObject>() {

                    @Override
                    public void onResponse(JSONObject response) {

                        try {
                            if(response.get("isSuccessful").equals(true)){

                                JSONObject user = response.getJSONObject("user");
                                String id = user.getString("id");
                                AddFuelStation(id);

                            }
                            else{
                                Toast.makeText(SignUpStaionOwner.this, "Some ting went wrong!!", Toast.LENGTH_SHORT).show();
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                            Toast.makeText(SignUpStaionOwner.this, "Some ting went wrong!!", Toast.LENGTH_SHORT).show();
                        }


                    }
                }, new Response.ErrorListener(){

                    @Override
                    public void onErrorResponse(VolleyError error) {
                        // TODO: Handle error
                        Toast.makeText(SignUpStaionOwner.this, "Some ting went wrong!!", Toast.LENGTH_SHORT).show();
                    }
                });
        queue.add(jsonObjectRequest);




    }

   //Create new fuel Station
    private void AddFuelStation(String id){

        HashMap<String,String> params = new HashMap<String,String>();

        params.put("owner_id",id);
        params.put("fuel_station_name",station_name.getText().toString());
        params.put("register_no",register_no.getText().toString());

        queue = Volley.newRequestQueue(this);
        String url = "https://ishankafuel.herokuapp.com/fuel_stations/";
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest
                (Request.Method.POST, url, new JSONObject(params), new Response.Listener<JSONObject>() {

                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            if(response.get("isSuccessful").equals(true)){

                                JSONObject station = response.getJSONObject("fuelStation");
                                String sId = station.getString("_id");

                                Log.e("Success ",sId);

                                AddQueue(sId);

                            }
                            else{
                                Toast.makeText(SignUpStaionOwner.this, "Some ting went wrong!!", Toast.LENGTH_SHORT).show();
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                            Toast.makeText(SignUpStaionOwner.this, "Some ting went wrong!!", Toast.LENGTH_SHORT).show();
                        }


                    }
                }, new Response.ErrorListener(){

                    @Override
                    public void onErrorResponse(VolleyError error) {
                        // TODO: Handle error
                        Toast.makeText(SignUpStaionOwner.this, "Some ting went wrong!!", Toast.LENGTH_SHORT).show();
                    }
                });
        queue.add(jsonObjectRequest);

    }

    //add new queue
    private void AddQueue(String sId){
        HashMap<String,String> params = new HashMap<String,String>();

        params.put("station_Id",sId);

        queue = Volley.newRequestQueue(this);
        String url = "https://ishankafuel.herokuapp.com/fuel_queues/";
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest
                (Request.Method.POST, url, new JSONObject(params), new Response.Listener<JSONObject>() {

                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            if(response.get("isSuccessful").equals(true)){
                                Toast.makeText(SignUpStaionOwner.this, "Successfully registered", Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(SignUpStaionOwner.this, SignInStationOwner.class);
                                startActivity(intent);
                            }
                            else{
                                Toast.makeText(SignUpStaionOwner.this, "Some ting went wrong!!", Toast.LENGTH_SHORT).show();
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                            Toast.makeText(SignUpStaionOwner.this, "Some ting went wrong!!", Toast.LENGTH_SHORT).show();
                        }


                    }
                }, new Response.ErrorListener(){

                    @Override
                    public void onErrorResponse(VolleyError error) {
                        // TODO: Handle error
                        Toast.makeText(SignUpStaionOwner.this, "Some ting went wrong!!", Toast.LENGTH_SHORT).show();
                    }
                });
        queue.add(jsonObjectRequest);

    }


}