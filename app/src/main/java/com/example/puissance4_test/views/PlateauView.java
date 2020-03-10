package com.example.puissance4_test.views;

import android.app.Activity;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.view.MotionEvent;
import android.view.View;

import com.example.puissance4_test.GameActivity;
import com.example.puissance4_test.models.Joueur;

import com.example.puissance4_test.models.Space;
import com.example.puissance4_test.models.Plateau;

import java.util.LinkedList;

public class PlateauView extends View {
    Paint paintGrid = new Paint();
    DisplayMetrics metrics = this.getContext().getResources().getDisplayMetrics();
    int largeurScreen = metrics.widthPixels;
    int hauteurScreen =metrics.heightPixels;
    int ligneNb = 5;
    int coloneNb = 5;
    float longCell = largeurScreen / coloneNb;
    float hauteurJeu = longCell * ligneNb;
    LinkedList<PionView> pion;
    private Activity activity;

    public PlateauView(Context context, AttributeSet attrs) {

        super(context, attrs);
        this.initGUI(context);
        pion = new LinkedList<PionView>();

    }

    public Activity getActivity() {
        return activity;
    }

    public void setActivity(Activity activity) {
        this.activity = activity;
    }


    private void initGUI(Context context){
        paintGrid.setColor(Color.BLUE);
        paintGrid.setTextSize(20);
        paintGrid.setStrokeWidth(12);

        this.setOnTouchListener(new View.OnTouchListener() {
            public synchronized boolean onTouch(View v, MotionEvent event) {

                int x = (int) event.getX();
                int y = (int) event.getY();
                int col = getColumnFromPosition(x);
                int lig = getRowFromPosition(y);

                String strColor;
                Joueur p;
                if (pion.size() % 2 == 0) {
                    p = Joueur.joueurs.get(0);
                    strColor = p.getColor().name();
                } else {
                    p = Joueur.joueurs.get(1);
                    strColor = p.getColor().name();
                }
                if (event.getAction() == MotionEvent.ACTION_DOWN) {
                    Space s;
                    s = p.placePion(col);
                    if(s != null){
                        pion.add(new PionView(getCellCenterX(s.getX()), getCellCenterY(s.getY()), (int) longCell / 3, strColor));
                        if(Plateau.getInstance().checkVictory(s)){
                            ((GameActivity) activity).popupwin(p);
                        }
                    }
                }
                return true;
            }
        });
    }

    @Override protected void onDraw(Canvas canvas) {
        drawGrid(canvas);
        for(PionView c : pion){
            c.draw(canvas);}
        invalidate();
    }

    private void drawGrid(Canvas canvas){
        //draw verticals lines
        for(int i = 1; i < coloneNb; i++ ){
            canvas.drawLine(longCell *i,0, longCell *i, hauteurJeu,paintGrid);
        }
        //draw horizontals lines
        for(int j = ligneNb; j >=0; j-- ){
            canvas.drawLine(0, hauteurJeu / ligneNb *j, largeurScreen, hauteurJeu / ligneNb *j,paintGrid);
        }
    }

    private int getCellCenterX(int cellNumX){
        int cx = (int) (cellNumX* longCell + longCell /2);
        return cx;
    }

    private int getCellCenterY(int cellNumY){
        int cy = (int) ((hauteurJeu -(cellNumY* longCell)- longCell) + longCell /2);
        return cy;
    }

    private int getColumnFromPosition(int posX){
        int colNum = (int) (posX/ longCell);
        return colNum;
    }
    private int getRowFromPosition(int posY){
        int rowNum = (int) ((hauteurScreen - hauteurJeu -posY)/ longCell);
        return rowNum;
    }
}
