package com.btinternet.jackbaxter007.hydra.Model.GW2;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.btinternet.jackbaxter007.hydra.R;

public class GW2Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setTheme(R.style.AppTheme);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.gw2_activity_layout);
        Button  button = (Button)findViewById(R.id.apiButton);

        button.setOnClickListener(
                new View.OnClickListener()
                {
                    public void onClick(View view)
                    {
                        EditText edit = (EditText)findViewById(R.id.DOTA2SearchInput);
                        String result = edit.getText().toString();
                        if (result.length() != 72){
                            Toast.makeText(getApplicationContext(),"API Key incorrect, make sure you've included the -'s",Toast.LENGTH_LONG).show();
                            return;
                        }
                        Intent intent = new Intent(GW2Activity.this, GW2Account.class);
                        intent.putExtra("API Key", result);
                        startActivity(intent);
                    }
                });
    }
}
