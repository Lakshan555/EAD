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

//create login for signIn for Vehicle owner
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
        btn = findViewById(R.id.SignUpVOBtn);

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

    //This will create post request for login
    private void LoginDriver(){

        HashMap<String,String> params = new HashMap<String,String>();

        params.put("identifier",name.getText().toString());
        params.put("password",password.getText().toString());
        params.put("user_type",userType);

        queue = Volley.newRequestQueue(this);
        String url = "https://ishankafuel.herokuapp.com/users/login";
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest
                (Request.Method.POST, url, new JSONObject(params), new Response.Listener<JSONObject>() {

                    @Override
                    public void onResponse(JSONObject response) {
                        Log.e("Success ",response.toString());

                        try {
                            if(response.get("isSuccessful").equals(true)){
                                Log.e("Success ",response.get("isSuccessful").toString());
                                Intent intent = new Intent(SignInVehicleOwner.this, HomeVehicleOwner.class);
                                startActivity(intent);
                            }
                            else{
                                Toast.makeText(SignInVehicleOwner.this, "Wrong!", Toast.LENGTH_SHORT).show();
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
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