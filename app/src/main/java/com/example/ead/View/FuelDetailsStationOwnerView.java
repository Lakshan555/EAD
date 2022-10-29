package com.example.ead.View;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
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
import com.example.ead.Api.FuelDetail;
import com.example.ead.Home.HomeStationOwner;
import com.example.ead.Login.SignInVehicleOwner;
import com.example.ead.MainActivity;
import com.example.ead.R;
import com.example.ead.UpdateFuelStatus;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class FuelDetailsStationOwnerView extends AppCompatActivity {
    private TextView dSOValue,sdSOValue,p92SOValue,p95SOValue;
    private Button updateStatusSOBtn,closedStatusSOBtn;
    private RequestQueue queue;
    private String  d,sd,p92,p95;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fuel_details_station_owner_view);

        dSOValue = (TextView) findViewById(R.id.dSOValue);
        sdSOValue = (TextView) findViewById(R.id.sdSOValue);
        p92SOValue = (TextView) findViewById(R.id.p92SOValue);
        p95SOValue = (TextView) findViewById(R.id.p95SOValue);
        updateStatusSOBtn = findViewById(R.id.updateStatusSOBtn);
        closedStatusSOBtn = findViewById(R.id.closedStatusSOBtn);

        //call get Details function
        getDetails();

        updateStatusSOBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(FuelDetailsStationOwnerView.this, UpdateFuelStatus.class);

                //pass data to update screen
                intent.putExtra("d", d);
                intent.putExtra("sd", sd);
                intent.putExtra("p92", p92);
                intent.putExtra("p95", p95);

                startActivity(intent);


            }
        });

        closedStatusSOBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(FuelDetailsStationOwnerView.this, HomeStationOwner.class);
                startActivity(intent);
            }
        });
    }

    //get updated details
    private void getDetails() {

//        SharedPreferences sharedPref = FuelDetailsStationOwnerView.this.getPreferences(Context.MODE_PRIVATE);
//        String sid = sharedPref.getString("userId", null);
//
//        Log.e("test",sid);

        String id = "635ac78a23d441bc8ef83db2";
        queue = Volley.newRequestQueue(this);
        String url = "https://ishankafuel.herokuapp.com/fuel_stations/"+id;
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest
                (Request.Method.GET, url, null, new Response.Listener<JSONObject>() {

                    @Override
                    public void onResponse(JSONObject response) {

                        try {
                            if (response.get("isSuccessful").equals(true)) {


                                JSONObject stations = response.getJSONObject("fuelStations");
                                JSONArray fd = stations.getJSONArray("fuel_details");

                                d = fd.getJSONObject(0).getString("quantity");
                                sd = fd.getJSONObject(1).getString("quantity");
                                p92 = fd.getJSONObject(2).getString("quantity");
                                p95 = fd.getJSONObject(3).getString("quantity");

                                dSOValue.setText(d+"L");
                                sdSOValue.setText(sd+"L");
                                p92SOValue.setText(p92+"L");
                                p95SOValue.setText(p95+"L");

                                Log.e("Success", response.toString());
                                Log.e("Success", fd.getString(0) );

                            } else {
                                Toast.makeText(FuelDetailsStationOwnerView.this, "Wrong!", Toast.LENGTH_SHORT).show();
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                            Toast.makeText(FuelDetailsStationOwnerView.this, "Exception!", Toast.LENGTH_SHORT).show();
                        }


                    }
                }, new Response.ErrorListener() {

                    @Override
                    public void onErrorResponse(VolleyError error) {
                        // TODO: Handle error
                        Log.e("Error", error.toString());
                    }
                });
        queue.add(jsonObjectRequest);

    }

}