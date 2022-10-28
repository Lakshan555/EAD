package com.example.ead;

import androidx.appcompat.app.AppCompatActivity;

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
import com.example.ead.Login.SignUpStaionOwner;
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

        FABtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                try {
                    UpdateFuelS();
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        });

    }

    private void UpdateFuelS() throws JSONException {

        HashMap<String,String> params = new HashMap<String,String>();
        String id = "635ac78a23d441bc8ef83db2";

//        FuelDetail diesel = new FuelDetail("Diesel",iDiesel.toString());
//        FuelDetail superDiesel = new FuelDetail("Super diesel",iSDiesel.toString());
//        FuelDetail p92 = new FuelDetail("Petrol 92",iPetrol92.toString());
//        FuelDetail p95 = new FuelDetail("Petrol 95",iPetrol95.toString());

//        Object[] fd = {
//                {
//                    "d"
//                }
//        };

//        ArrayList<FuelDetail> fd = new ArrayList<>();

//        FuelDetail[] fd = new FuelDetail[4];

//        fd[0] = new FuelDetail("Diesel",iDiesel.toString());
//        fd[1] =new FuelDetail("Super diesel",iSDiesel.toString());
//        fd[2] = new FuelDetail("Petrol 92",iPetrol92.toString());
//        fd[3] = new FuelDetail("Petrol 95",iPetrol95.toString());
//        fd.add(diesel);
//        fd.add(superDiesel);
//        fd.add(p92);
//        fd.add(p95);



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


//        fuel_details: [
//        {
//            fuel_type: String,
//                    quantity: String,
//        }
//    ],
//        arrived_time: [
//        {
//            name: String,
//                    time: String,
//        }
//    ],


        queue = Volley.newRequestQueue(this);
        String url = "https://ishankafuel.herokuapp.com/fuel_stations/"+id;
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest
                (Request.Method.PUT, url, new JSONObject(params), new Response.Listener<JSONObject>() {

                    @Override
                    public void onResponse(JSONObject response) {

                        try {
                            if(response.get("isSuccessful").equals(true)){
                                Toast.makeText(UpdateFuelStatus.this, "updated", Toast.LENGTH_SHORT).show();
//                                JSONObject user = response.getJSONObject("user");
//                                String id = user.getString("id");
//                                AddFuelStation(id);

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