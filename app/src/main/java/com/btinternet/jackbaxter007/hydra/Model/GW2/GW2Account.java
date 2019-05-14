package com.btinternet.jackbaxter007.hydra.Model.GW2;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class GW2Account extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle bundle = getIntent().getExtras();
        String message = bundle.getString("API Key");
    }
}
