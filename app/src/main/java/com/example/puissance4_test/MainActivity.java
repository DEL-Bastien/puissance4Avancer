package com.example.puissance4_test;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.InputFilter;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Switch;

import com.example.puissance4_test.models.Param;

public class MainActivity extends Activity implements View.OnClickListener {

    private Button btnJeu;
    EditText editTextH;
    EditText editTextW;
    Switch[] btnsNbJ = new Switch[4];
    int width = 5;
    int height = 5;
    int nbJoueur = 0;

    /**
     * Called when the activity is first created.
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        btnsNbJ[0] = findViewById(R.id.btnNbJ2);
        btnsNbJ[1] = findViewById(R.id.btnNbJ3);
        btnsNbJ[2] = findViewById(R.id.btnNbJ4);
        btnsNbJ[3] = findViewById(R.id.btnNbJ5);

        for (int i = 0; i < btnsNbJ.length; i++) {
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

        editTextH = findViewById(R.id.editTxtHauteur);
        editTextW = findViewById(R.id.editTxtLargeur);

        editTextH.setFilters(new InputFilter[]{
                new MinMaxFilter("4", "9")
        });
        editTextW.setFilters(new InputFilter[]{
                new MinMaxFilter("4", "9")

        });

        editTextW.setOnClickListener(this);
        editTextH.setOnClickListener(this);

        btnJeu = findViewById(R.id.btnJeu);
        btnJeu.setOnClickListener(this);

    }

    public void onClick(View v) {
        if (v.getId() == R.id.btnJeu) {
            if (btnsNbJ[0].isChecked()) {
                nbJoueur = 2;
            } else if (btnsNbJ[1].isChecked()) {
                nbJoueur = 3;
            } else if (btnsNbJ[2].isChecked()) {
                nbJoueur = 4;
            } else if (btnsNbJ[3].isChecked()) {
                nbJoueur = 5;
            } else {
                nbJoueur = 2;
            }
            if (editTextH.getText().toString().isEmpty() && editTextH.getText().toString().isEmpty()) {
                width = 5;
                height = 5;
            } else {
                width = Integer.parseInt(editTextW.getText().toString());
                height = Integer.parseInt(editTextH.getText().toString());
            }
            Param param = new Param();
            param.setHeight(height);
            param.setWidth(width);
            param.setNbJoueur(nbJoueur);
            Intent intentMulti = new Intent(this, JeuActivity.class);
            startActivity(intentMulti);
        }
    }
}
