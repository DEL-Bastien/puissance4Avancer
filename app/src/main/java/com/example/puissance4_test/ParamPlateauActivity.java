package com.example.puissance4_test;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.InputFilter;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Switch;

public class ParamPlateauActivity extends Activity implements View.OnClickListener {
    private Button btnQuit;
    private Button btnSave;
    EditText editTextH;
    EditText editTextW;
    Switch[] btnsNbJ = new Switch[4];

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.param);
        btnsNbJ[0] = findViewById(R.id.btnNbJ2);
        btnsNbJ[1] = findViewById(R.id.btnNbJ3);
        btnsNbJ[2] = findViewById(R.id.btnNbJ4);
        btnsNbJ[3] = findViewById(R.id.btnNbJ5);

        for(int i = 0; i < btnsNbJ.length; i++)
        {
            final int finalI = i;
            View.OnClickListener listener = new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    btnsNbJ[0].setChecked(btnsNbJ[0] == view);
                    btnsNbJ[1].setChecked(btnsNbJ[1] == view);
                    btnsNbJ[2].setChecked(btnsNbJ[2] == view);
                    btnsNbJ[3].setChecked(btnsNbJ[3] == view);
                }
            };
            btnsNbJ[finalI].setOnClickListener(listener);
        }

        btnQuit = findViewById(R.id.btnQuit);
        btnSave = findViewById(R.id.btnSave);
        btnQuit.setOnClickListener(this);
        btnSave.setOnClickListener(this);

        editTextH = findViewById(R.id.editTxtHauteur);
        editTextW = findViewById(R.id.editTxtLargeur);

        editTextH.setFilters( new InputFilter[]{
                new MinMaxFilter("4","9")
        });
        editTextW.setFilters( new InputFilter[]{
                new MinMaxFilter("4","9")

        });

        editTextW.setOnClickListener(this);
        editTextH.setOnClickListener(this);



    }

    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnSave:
                System.out.println("width : " + editTextW.getText().toString());
                System.out.println("height :  " + editTextH.getText().toString());
                Intent intentParam = new Intent(this, MainActivity.class);
                startActivity(intentParam);
                break;
            case R.id.btnQuit:
                Intent intentJeu = new Intent(this, MainActivity.class);
                startActivity(intentJeu);
                break;

        }
    }
}
