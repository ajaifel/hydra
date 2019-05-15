package com.btinternet.jackbaxter007.hydra.Model.DOTA2;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
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
import com.btinternet.jackbaxter007.hydra.Model.DOTA2.DOTA2Data.AccountSearch;
import com.btinternet.jackbaxter007.hydra.R;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.ArrayList;
import java.util.HashMap;

public class DOTA2Activity extends AppCompatActivity {
    private String curSearch;
    AccountSearch[] accountSearch;
    ListView simpleListView;
    private static DOTA2Activity tinstance = null;
    public void setCurSearch(String curSearch) {
        this.curSearch = curSearch;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setTheme(R.style.AppTheme);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dota2_activity);
        Button button = (Button)findViewById(R.id.dota2SearchButton);
        final String searchUrl = "https://api.opendota.com/api/search?q=";


        final Gson gson = new GsonBuilder().create();
        tinstance = this;
        button.setOnClickListener(
                new View.OnClickListener()
                {
                    public void onClick(View view)
                    {
                        EditText edit = (EditText)findViewById(R.id.DOTA2SearchInput);
                        String result = edit.getText().toString();
                        result.replaceAll(" ", "%20");
                        curSearch = searchUrl+result;
                        StringRequest accRequest = new StringRequest(Request.Method.GET, curSearch,
                                new Response.Listener<String>() {
                                    @Override
                                    public void onResponse(String response) {
                                        accountSearch = gson.fromJson(response, AccountSearch[].class);
                                        simpleListView=(ListView)findViewById(R.id.Dota2AccList);

                                        ArrayList<HashMap<String,String>> arrayList=new ArrayList<>();
                                        for (int i=0;i<accountSearch.length;i++)
                                        {
                                            HashMap<String,String> hashMap=new HashMap<>();//create a hashmap to store the data in key value pair
                                            hashMap.put("name",accountSearch[i].getPersonaname());
                                            hashMap.put("id",accountSearch[i].getAccount_id());
                                            arrayList.add(hashMap);//add the hashmap into arrayList
                                        }
                                        String[] from={"name", "id"};//string array
                                        int[] to={R.id.dota2AccountListName, R.id.dota2AccountListId};//int array of views id's
                                        SimpleAdapter simpleAdapter=new SimpleAdapter(tinstance,arrayList,R.layout.dota2_account_list_row,from,to);//Create object and set the parameters for simpleAdapter
                                        simpleListView.setAdapter(simpleAdapter);//sets the adapter for listView

                                        //perform listView item click event
                                        simpleListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                                            @Override
                                            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                                                Intent intent = new Intent(DOTA2Activity.this, DOTA2Account.class);
                                                intent.putExtra("accID", accountSearch[i].getAccount_id());
                                                intent.putExtra("accName", accountSearch[i].getPersonaname());
                                                DOTA2Activity.this.startActivity(intent);
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
                                    Toast.makeText(DOTA2Activity.this, message, Toast.LENGTH_SHORT).show();
                                }
                                else if(error instanceof ServerError)
                                {
                                    message = "The server could not be found. Please try again after some time!!";
                                    Toast.makeText(DOTA2Activity.this, message, Toast.LENGTH_SHORT).show();
                                }
                                else if (error instanceof ParseError) {
                                    message = "Parsing error! Please try again after some time!!";
                                    Toast.makeText(DOTA2Activity.this, message, Toast.LENGTH_SHORT).show();
                                }
                            }
                        });
                        RequestQueue queue = Volley.newRequestQueue(tinstance);
                        // Add the request to the RequestQueue.
                        queue.add(accRequest);
                    }
                });

    }
}
