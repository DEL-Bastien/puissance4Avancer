package com.example.puissance4_test;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.puissance4_test.models.Color;
import com.example.puissance4_test.models.Joueur;
import com.example.puissance4_test.models.Param;
import com.example.puissance4_test.models.Plateau;
import com.example.puissance4_test.views.PlateauView;

public class JeuActivity extends Activity implements View.OnClickListener,GameActivity {
    private Button btnQuit;
    private PlateauView plateauView;
    private JeuActivity currentIntent = this;
    private EditText ordre;
    private Context context;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.jeu);
        plateauView = findViewById(R.id.gameView);
        plateauView.setActivity(this);
        btnQuit = findViewById(R.id.btnLeave);
        btnQuit.setOnClickListener(this);
        ordre = findViewById(R.id.joueurOrdre);
        context= JeuActivity.this;
        Plateau.renew();
        if (Param.getNbJoueur() == 2) {
            new Joueur(Color.RED);
            new Joueur(Color.YELLOW);
        }
        if (Param.getNbJoueur() == 3) {
            new Joueur(Color.RED);
            new Joueur(Color.YELLOW);
            new Joueur(Color.GREEN);
        }
        if (Param.getNbJoueur() == 4) {
            new Joueur(Color.RED);
            new Joueur(Color.YELLOW);
            new Joueur(Color.GREEN);
            new Joueur(Color.CYAN);
        }
        if (Param.getNbJoueur() == 5) {
            new Joueur(Color.RED);
            new Joueur(Color.YELLOW);
            new Joueur(Color.GREEN);
            new Joueur(Color.CYAN);
            new Joueur(Color.MAGENTA);
        }

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnLeave:
                AlertDialog.Builder builderLeave = new AlertDialog.Builder(context);
                builderLeave.setMessage("Tu veux quitter ?");
                builderLeave.setCancelable(true);
                builderLeave.setPositiveButton("Oui",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialogLeave, int id) {
                                Intent mainActivity = new Intent(currentIntent, MainActivity.class);
                                startActivity(mainActivity);
                                finish();
                            }
                        });
                builderLeave.setNegativeButton("Non",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                dialog.cancel();
                            }
                        });
                AlertDialog alert = builderLeave.create();
                alert.show();
                break;
        }

    }

    @Override
    public void popupwin(Joueur P) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setMessage("Bravo! "+P.getPseudo()+" Gagne, la revanche? ?");
        builder.setCancelable(true);
        builder.setPositiveButton("Oui",
                new DialogInterface.OnClickListener(){
                    public void onClick(DialogInterface dialog, int id) {
                        finish();
                        startActivity(getIntent());
                    }
                });
        builder.setNegativeButton("Non",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        Intent mainActivity = new Intent(currentIntent,MainActivity.class);
                        startActivity(mainActivity);
                        finish();
                    }
                });

        AlertDialog alert = builder.create();
        alert.show();
    }

    @Override
    public void ordreJoueur(Joueur P) {
        ordre.setText("C'est à Joueur " + P.getId() + " : Couleur " + P.getColor() + " de jouer");
    }
}
