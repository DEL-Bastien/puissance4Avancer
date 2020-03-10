package com.example.puissance4_test;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends Activity implements View.OnClickListener {

    private Button btnJeu;
    private Button btnParam;

    /**
     * Called when the activity is first created.
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        btnParam = findViewById(R.id.btnParam);
        btnParam.setOnClickListener(this);

        btnJeu = findViewById(R.id.btnJeu);
        btnJeu.setOnClickListener(this);

    }

    public void onClick(View v) {

        switch(v.getId()){
            case R.id.btnParam:
                Intent intentSettings = new Intent(this, ParamPlateauActivity.class);
                startActivity(intentSettings);
                break;
            case R.id.btnJeu:
                Intent intentMulti = new Intent(this, JeuActivity.class);
                startActivity(intentMulti);
                break;
        }
    }
}
