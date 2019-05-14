package com.btinternet.jackbaxter007.hydra;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.SimpleAdapter;
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
import com.btinternet.jackbaxter007.hydra.Model.GW2.GW2Activity;
import com.btinternet.jackbaxter007.hydra.Model.GW2.GW2Data.Account;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class GameSelection extends AppCompatActivity {
    ListView simpleListView;
    int[] gameImages={R.drawable.gw2title,R.drawable.dota2title};//game images array

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setTheme(R.style.AppTheme);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//        List<HashMap<String, String>> aList = new ArrayList<HashMap<String, String>>();
//
//        for (int i = 0; i < listviewImage.length; i++) {
//            HashMap<String, String> hm = new HashMap<String, String>();
//            hm.put("listview_image", Integer.toString(listviewImage[i]));
//            aList.add(hm);
//        }
//
//        String[] from = {"listview_image"};
//        int[] to = {R.id.game_image};
//
//        SimpleAdapter simpleAdapter = new SimpleAdapter(getBaseContext(), aList, R.layout.game_list_row, from, to);
//        final ListView androidListView = (ListView) findViewById(R.id.game_list);
//        androidListView.setAdapter(simpleAdapter);
//        androidListView.setOnItemClickListener(new OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                Toast.makeText(getBaseContext(), listviewImage[i], Toast.LENGTH_SHORT.show());
//            }
//        });

            simpleListView=(ListView)findViewById(R.id.game_list);

            ArrayList<HashMap<String,String>> arrayList=new ArrayList<>();
            for (int i=0;i<gameImages.length;i++)
            {
                HashMap<String,String> hashMap=new HashMap<>();//create a hashmap to store the data in key value pair
                hashMap.put("image",Integer.toString(gameImages[i]));
                arrayList.add(hashMap);//add the hashmap into arrayList
            }
            String[] from={"image"};//string array
            int[] to={R.id.game_image};//int array of views id's
            SimpleAdapter simpleAdapter=new SimpleAdapter(this,arrayList,R.layout.game_list_row,from,to);//Create object and set the parameters for simpleAdapter
            simpleListView.setAdapter(simpleAdapter);//sets the adapter for listView

            //perform listView item click event
            simpleListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                    Toast.makeText(getApplicationContext(),gameImages[i],Toast.LENGTH_LONG).show();//show the selected image in toast according to position
                    Intent intent = new Intent(GameSelection.this, GW2Activity.class);
                    GameSelection.this.startActivity(intent);
                }
            });


        // Instantiate the RequestQueue.
        RequestQueue queue = Volley.newRequestQueue(this);
        String url ="https://api.guildwars2.com/v2/tokeninfo?access_token=D0DBBF8D-B1FE-564B-BE18-2557045FEC6EE0E463D5-F286-4E86-AE84-F8DDAFF454A2";

        // Request a string response from the provided URL.
        final Gson gson = new GsonBuilder().create();
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Account account = gson.fromJson(response, Account.class);
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                String message=null;
                if(error instanceof NetworkError)
                {
                    message = "Cannot connect to Internet...Please check your connection!";
                    Toast.makeText(GameSelection.this, message, Toast.LENGTH_SHORT).show();
                }
                else if(error instanceof ServerError)
                {
                    message = "The server could not be found. Please try again after some time!!";
                    Toast.makeText(GameSelection.this, message, Toast.LENGTH_SHORT).show();
                }
                else if (error instanceof ParseError) {
                    message = "Parsing error! Please try again after some time!!";
                    Toast.makeText(GameSelection.this, message, Toast.LENGTH_SHORT).show();
                }
            }
        });

// Add the request to the RequestQueue.
        queue.add(stringRequest);
    }
}
