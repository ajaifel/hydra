package com.btinternet.jackbaxter007.hydra.Model.DOTA2;

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
import com.btinternet.jackbaxter007.hydra.Model.DOTA2.DOTA2Data.AccountDetails;
import com.btinternet.jackbaxter007.hydra.Model.DOTA2.DOTA2Data.WinLoss;
import com.btinternet.jackbaxter007.hydra.Model.GW2.GW2Character;
import com.btinternet.jackbaxter007.hydra.Model.GW2.GW2Data.CharacterDetail;
import com.btinternet.jackbaxter007.hydra.R;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class DOTA2Account extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setTheme(R.style.AppTheme);
        super.onCreate(savedInstanceState);
        Bundle bundle = getIntent().getExtras();
        String accID = bundle.getString("accID");
        final String accName = bundle.getString("accName");
        setContentView(R.layout.dota2_account_layout);
        String dota2Url = "https://api.opendota.com/api/players/";
        String url = dota2Url+accID;
        String wlUrl = url+"/wl";
        String cwlUrl = wlUrl+"?limit=20";


        RequestQueue queue = Volley.newRequestQueue(this);

        // Request a string response from the provided URL.
        final Gson gson = new GsonBuilder().create();
        StringRequest accRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        AccountDetails accountDetails = gson.fromJson(response, AccountDetails.class);
                        TextView name = (TextView)findViewById(R.id.dota2Name);
                        TextView rank = (TextView)findViewById(R.id.dota2Rank);
                        TextView srank = (TextView)findViewById(R.id.dota2SoloCompRank);
                        TextView crank = (TextView)findViewById(R.id.dota2CompRank);
                        TextView mmr = (TextView)findViewById(R.id.dota2MMR);

                        name.setText(accName);
                        rank.setText(accountDetails.getRank_tier());
                        srank.setText(accountDetails.getSolo_competitive_rank());
                        crank.setText(accountDetails.getCompetitive_rank());
                        mmr.setText(accountDetails.getMmr_estimate());
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                String message=null;
                if(error instanceof NetworkError)
                {
                    message = "Cannot connect to Internet...Please check your connection!";
                    Toast.makeText(DOTA2Account.this, message, Toast.LENGTH_SHORT).show();
                }
                else if(error instanceof ServerError)
                {
                    message = "The server could not be found. Please try again after some time!!";
                    Toast.makeText(DOTA2Account.this, message, Toast.LENGTH_SHORT).show();
                }
                else if (error instanceof ParseError) {
                    message = "Parsing error! Please try again after some time!!";
                    Toast.makeText(DOTA2Account.this, message, Toast.LENGTH_SHORT).show();
                }
            }
        });
        StringRequest wlRequest = new StringRequest(Request.Method.GET, wlUrl,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        WinLoss winLoss = gson.fromJson(response, WinLoss.class);
                        TextView wL = (TextView)findViewById(R.id.dota2WL);
                        TextView wR = (TextView)findViewById(R.id.dota2WR);
                        wL.setText("Wins: "+winLoss.getWin()+" Losses: "+winLoss.getLose());
                        wR.setText(winLoss.getWL());
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                String message=null;
                if(error instanceof NetworkError)
                {
                    message = "Cannot connect to Internet...Please check your connection!";
                    Toast.makeText(DOTA2Account.this, message, Toast.LENGTH_SHORT).show();
                }
                else if(error instanceof ServerError)
                {
                    message = "The server could not be found. Please try again after some time!!";
                    Toast.makeText(DOTA2Account.this, message, Toast.LENGTH_SHORT).show();
                }
                else if (error instanceof ParseError) {
                    message = "Parsing error! Please try again after some time!!";
                    Toast.makeText(DOTA2Account.this, message, Toast.LENGTH_SHORT).show();
                }
            }
        });
        StringRequest cwlRequest = new StringRequest(Request.Method.GET, cwlUrl,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        WinLoss winLoss = gson.fromJson(response, WinLoss.class);
                        TextView cwR = (TextView)findViewById(R.id.dota2WRCurrent);
                        cwR.setText(winLoss.getWL());
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                String message=null;
                if(error instanceof NetworkError)
                {
                    message = "Cannot connect to Internet...Please check your connection!";
                    Toast.makeText(DOTA2Account.this, message, Toast.LENGTH_SHORT).show();
                }
                else if(error instanceof ServerError)
                {
                    message = "The server could not be found. Please try again after some time!!";
                    Toast.makeText(DOTA2Account.this, message, Toast.LENGTH_SHORT).show();
                }
                else if (error instanceof ParseError) {
                    message = "Parsing error! Please try again after some time!!";
                    Toast.makeText(DOTA2Account.this, message, Toast.LENGTH_SHORT).show();
                }
            }
        });
        // Add the request to the RequestQueue.
        queue.add(accRequest);
        queue.add(wlRequest);
        queue.add(cwlRequest);
    }
}
