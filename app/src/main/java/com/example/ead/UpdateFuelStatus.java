package com.example.ead;

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
import com.example.ead.Api.FuelDetail;
import com.example.ead.Login.SignInVehicleOwner;
import com.example.ead.Login.SignUpStaionOwner;
import com.example.ead.View.FuelDetailsStationOwnerView;
import com.google.gson.JsonObject;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;

public class UpdateFuelStatus extends AppCompatActivity {

    private TextView iDiesel,iSDiesel,iPetrol92,iPetrol95,fiADiesel,fiASDiesel,fiAPetrol92,fiAPetrol95,
            fAiAStockDiesel,fSiAStockSDiesel,PiAStockPetrol92,FAiAStockPetrol95;

    private Button FABtn;
    private RequestQueue queue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_fuel_status);

        iDiesel = (TextView) findViewById(R.id.iDiesel);
        iSDiesel = (TextView) findViewById(R.id.iSDiesel);
        iPetrol92 = (TextView) findViewById(R.id.iPetrol92);
        iPetrol95 = (TextView) findViewById(R.id.iPetrol95);

        fiADiesel = (TextView) findViewById(R.id.fiADiesel);
        fiASDiesel = (TextView) findViewById(R.id.fiASDiesel);
        fiAPetrol92 = (TextView) findViewById(R.id.fiAPetrol92);
        fiAPetrol95 = (TextView) findViewById(R.id.fiAPetrol95);

        fAiAStockDiesel = (TextView) findViewById(R.id.fAiAStockDiesel);
        fSiAStockSDiesel = (TextView) findViewById(R.id.fSiAStockSDiesel);
        PiAStockPetrol92 = (TextView) findViewById(R.id.PiAStockPetrol92);
        FAiAStockPetrol95 = (TextView) findViewById(R.id.FAiAStockPetrol95);
        FABtn = findViewById(R.id.FABtn);

        Intent intent = getIntent();

        String d = intent.getStringExtra("d");
        String sd = intent.getStringExtra("sd");
        String p92 = intent.getStringExtra("p92");
        String p95 = intent.getStringExtra("p95");

        //set values to input field
        iDiesel.setText(d);
        iSDiesel.setText(sd);
        iPetrol92.setText(p92);
        iPetrol95.setText(p95);


        FABtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                try {
                    UpdateFuelS();
                    Intent intent = new Intent(UpdateFuelStatus.this, FuelDetailsStationOwnerView.class);
                    startActivity(intent);
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        });

    }

    private void UpdateFuelS() throws JSONException {

        HashMap<String,String> params = new HashMap<String,String>();
        String id = "635ac78a23d441bc8ef83db2";




        JSONObject d = new JSONObject();
        JSONObject sd = new JSONObject();
        JSONObject p92 = new JSONObject();
        JSONObject p95 = new JSONObject();
        d.put("fuel_type","Diesel");
        d.put("quantity",iDiesel.getText().toString());

        sd.put("fuel_type","Super diesel");
        sd.put("quantity",iSDiesel.getText().toString());

        p92.put("fuel_type","Petrol 92");
        p92.put("quantity",iPetrol92.getText().toString());

        p95.put("fuel_type","Petrol 95");
        p95.put("quantity",iPetrol95.getText().toString());
        JSONArray fd = new JSONArray();

        fd.put(d);
        fd.put(sd);
        fd.put(p92);
        fd.put(p95);


        Log.e("array",fd.toString());

        params.put("fuel_details",fd.toString());


        queue = Volley.newRequestQueue(this);
        String url = "https://ishankafuel.herokuapp.com/fuel_stations/"+id;
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest
                (Request.Method.PUT, url, new JSONObject(params), new Response.Listener<JSONObject>() {

                    @Override
                    public void onResponse(JSONObject response) {

                        try {
                            if(response.get("isSuccessful").equals(true)){
                                Toast.makeText(UpdateFuelStatus.this, "Updated", Toast.LENGTH_SHORT).show();

                            }
                            else{
                                Toast.makeText(UpdateFuelStatus.this, "Some ting went wrong!!", Toast.LENGTH_SHORT).show();
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                            Toast.makeText(UpdateFuelStatus.this, "Some ting went wrong!!", Toast.LENGTH_SHORT).show();
                        }


                    }
                }, new Response.ErrorListener(){

                    @Override
                    public void onErrorResponse(VolleyError error) {
                        // TODO: Handle error
                        Toast.makeText(UpdateFuelStatus.this, "Some ting went wrong!!", Toast.LENGTH_SHORT).show();
                    }
                });
        queue.add(jsonObjectRequest);




    }
}


