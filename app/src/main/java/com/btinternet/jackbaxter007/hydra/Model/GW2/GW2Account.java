package com.btinternet.jackbaxter007.hydra.Model.GW2;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
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
import com.btinternet.jackbaxter007.hydra.R;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class GW2Account extends AppCompatActivity {
    ListView simpleListView;
    String[] characterName = {};//Character Name Array
    private static GW2Account tinstance = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setTheme(R.style.AppTheme);
        super.onCreate(savedInstanceState);
        Bundle bundle = getIntent().getExtras();
        final String gw2APIKey = bundle.getString("API Key");
        setContentView(R.layout.gw2_account);

        RequestQueue queue = Volley.newRequestQueue(this);
        String gw2Url = "https://api.guildwars2.com/v2/";
        String accUrl = gw2Url+"account?access_token="+gw2APIKey;
        String charUrl = gw2Url+"characters?access_token="+gw2APIKey;

        // Request a string response from the provided URL.
        final Gson gson = new GsonBuilder().create();
        StringRequest accRequest = new StringRequest(Request.Method.GET, accUrl,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Account account = gson.fromJson(response, Account.class);
                        TextView name = (TextView)findViewById(R.id.gw2AccountName);
                        TextView age = (TextView)findViewById(R.id.gw2AccountAge);
                        TextView expansions = (TextView)findViewById(R.id.gw2OwnedExpansions);
                        TextView fractal = (TextView)findViewById(R.id.gw2FractalLevel);
                        TextView daily = (TextView)findViewById(R.id.gw2DailyAP);
                        TextView monthly = (TextView)findViewById(R.id.gw2MonthlyAP);
                        TextView wvw = (TextView)findViewById(R.id.gw2WvWRank);

                        String str = account.getAccess().toString();
                        str.replace("[", "");
                        str.replace("]", "");

                        name.setText(account.getName());
                        age.setText(account.getAge());
                        expansions.setText(str);
                        fractal.setText(account.getFractal_level());
                        daily.setText(account.getDaily_ap());
                        monthly.setText(account.getMonthly_ap());
                        wvw.setText(account.getWvw_rank());
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                String message=null;
                if(error instanceof NetworkError)
                {
                    message = "Cannot connect to Internet...Please check your connection!";
                    Toast.makeText(GW2Account.this, message, Toast.LENGTH_SHORT).show();
                }
                else if(error instanceof ServerError)
                {
                    message = "The server could not be found. Please try again after some time!!";
                    Toast.makeText(GW2Account.this, message, Toast.LENGTH_SHORT).show();
                }
                else if (error instanceof ParseError) {
                    message = "Parsing error! Please try again after some time!!";
                    Toast.makeText(GW2Account.this, message, Toast.LENGTH_SHORT).show();
                }
            }
        });

        tinstance = this;
        StringRequest charRequest = new StringRequest(Request.Method.GET, charUrl,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        List<String> re = gson.fromJson(response, new TypeToken<List<String>>(){}.getType());
                        characterName = re.toArray(new String[0]);

                        simpleListView=(ListView)findViewById(R.id.gw2CharacterList);

                        ArrayList<HashMap<String,String>> arrayList=new ArrayList<>();
                        for (int i=0;i<characterName.length;i++)
                        {
                            HashMap<String,String> hashMap=new HashMap<>();//create a hashmap to store the data in key value pair
                            hashMap.put("name",characterName[i]);
                            arrayList.add(hashMap);//add the hashmap into arrayList
                        }
                        String[] from={"name"};//string array
                        int[] to={R.id.characterListText};//int array of views id's
                        SimpleAdapter simpleAdapter=new SimpleAdapter(tinstance,arrayList,R.layout.character_list_row,from,to);//Create object and set the parameters for simpleAdapter
                        simpleListView.setAdapter(simpleAdapter);//sets the adapter for listView

                        //perform listView item click event
                        simpleListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                            @Override
                            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                                Intent intent = new Intent(GW2Account.this, GW2Character.class);
                                intent.putExtra("characterName", characterName[i]);
                                intent.putExtra("APIkey", gw2APIKey);
                                GW2Account.this.startActivity(intent);
                            }
                        });
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                String message=null;
                if(error instanceof NetworkError)
                {
                    message = "Cannot connect to Internet...Please check your connection!";
                    Toast.makeText(GW2Account.this, message, Toast.LENGTH_SHORT).show();
                }
                else if(error instanceof ServerError)
                {
                    message = "The server could not be found. Please try again after some time!!";
                    Toast.makeText(GW2Account.this, message, Toast.LENGTH_SHORT).show();
                }
                else if (error instanceof ParseError) {
                    message = "Parsing error! Please try again after some time!!";
                    Toast.makeText(GW2Account.this, message, Toast.LENGTH_SHORT).show();
                }
            }
        });
        // Add the request to the RequestQueue.
        queue.add(accRequest);
        queue.add(charRequest);
    }
}
