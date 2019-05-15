package com.btinternet.jackbaxter007.hydra;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import com.btinternet.jackbaxter007.hydra.Model.DOTA2.DOTA2Activity;
import com.btinternet.jackbaxter007.hydra.Model.GW2.GW2Activity;


import java.util.ArrayList;
import java.util.HashMap;

public class GameSelection extends AppCompatActivity {
    ListView simpleListView;
    int[] gameImages={R.drawable.gw2title,R.drawable.dota2title};//game images array

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setTheme(R.style.AppTheme);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

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
                    if (gameImages[i] == R.drawable.gw2title){
                        Intent intent = new Intent(GameSelection.this, GW2Activity.class);
                        GameSelection.this.startActivity(intent);
                    }
                    if (gameImages[i] == R.drawable.dota2title){
                        Intent intent = new Intent(GameSelection.this, DOTA2Activity.class);
                        GameSelection.this.startActivity(intent);
                    }
                    return;
                }
            });
    }
}
