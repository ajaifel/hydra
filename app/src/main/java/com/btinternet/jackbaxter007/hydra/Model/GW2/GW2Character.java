package com.btinternet.jackbaxter007.hydra.Model.GW2;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.NetworkError;
import com.android.volley.ParseError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.ServerError;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.btinternet.jackbaxter007.hydra.Model.GW2.GW2Data.Account;
import com.btinternet.jackbaxter007.hydra.Model.GW2.GW2Data.CharacterDetail;
import com.btinternet.jackbaxter007.hydra.R;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class GW2Character extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setTheme(R.style.AppTheme);
        super.onCreate(savedInstanceState);
        Bundle bundle = getIntent().getExtras();
        String gw2CharName = bundle.getString("characterName");
        String gw2APIKey = bundle.getString("APIkey");
        setContentView(R.layout.gw2_character);
        String url = "https://api.guildwars2.com/v2/characters?access_token="+gw2APIKey+"&ids="+gw2CharName;

        RequestQueue queue = Volley.newRequestQueue(this);

        // Request a string response from the provided URL.
        final Gson gson = new GsonBuilder().create();
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        String re = response.substring(1, response.length()-2);
                        CharacterDetail characterDetail = gson.fromJson(re, CharacterDetail.class);
                        TextView name = (TextView)findViewById(R.id.gw2Name);
                        TextView race = (TextView)findViewById(R.id.gw2Race);
                        TextView gender = (TextView)findViewById(R.id.gw2Gender);
                        TextView prof = (TextView)findViewById(R.id.gw2Profession);
                        TextView lev = (TextView)findViewById(R.id.gw2Level);
                        TextView age = (TextView)findViewById(R.id.gw2Age);
                        name.setText(characterDetail.getName());
                        race.setText(characterDetail.getRace());
                        gender.setText(characterDetail.getGender());
                        prof.setText(characterDetail.getProfession());
                        lev.setText(characterDetail.getLevel());
                        age.setText(characterDetail.getAge());
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                String message=null;
                if(error instanceof NetworkError)
                {
                    message = "Cannot connect to Internet...Please check your connection!";
                    Toast.makeText(GW2Character.this, message, Toast.LENGTH_SHORT).show();
                }
                else if(error instanceof ServerError)
                {
                    message = "The server could not be found. Please try again after some time!!";
                    Toast.makeText(GW2Character.this, message, Toast.LENGTH_SHORT).show();
                }
                else if (error instanceof ParseError) {
                    message = "Parsing error! Please try again after some time!!";
                    Toast.makeText(GW2Character.this, message, Toast.LENGTH_SHORT).show();
                }
            }
        });
        // Add the request to the RequestQueue.
        queue.add(stringRequest);
    }
}
