package com.example.delyou.mesrecettes;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    CardView choixRecette,choixFavorite,choixMesRecette,choixQuitter,choixSuprm;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Selcionne des elements
        choixRecette=(CardView)findViewById(R.id.TousRecette);
        choixFavorite=(CardView)findViewById(R.id.Favorite);
        choixMesRecette=(CardView)findViewById(R.id.MesRecette);
        choixQuitter=(CardView)findViewById(R.id.Quitter);
        choixSuprm=(CardView)findViewById(R.id.SuppmierRecette);


        //l'ajoute du evenement

        choixRecette.setOnClickListener(this);
        choixFavorite.setOnClickListener(this);
        choixMesRecette.setOnClickListener(this);
        choixQuitter.setOnClickListener(this);
        choixSuprm.setOnClickListener(this);




    }

    @Override
    public void onClick(View v) {
        Intent i;
        switch (v.getId())
        {
            case R.id.TousRecette:
                afficher("Vous avez choisi Tous les Recette");
                i=new Intent(getApplicationContext(),TousRecette.class);
                startActivity(i);

                break;
            case R.id.Favorite:
                afficher("vous avez choisi la Favorite");
                i=new Intent(getApplicationContext(),favoriteRecette.class);
                startActivity(i);
                break;

            case R.id.MesRecette:
                afficher("vous avez choisi les recette personelle");
                i=new Intent(getApplicationContext(),MesRecette.class);
                startActivity(i);
                break;

            case R.id.Quitter:

                afficher("vous avez choisi modifier des recettes ");
                i=new Intent(getApplicationContext(),modification.class);
                startActivity(i);
                break;
            case R.id.SuppmierRecette:

                afficher("vous avez choisi supprimer des recettes ");
                i=new Intent(getApplicationContext(),Supression.class);
                startActivity(i);

                break;
                default:break;
        }
    }
    public  void afficher(String Text)
    {
        Toast.makeText(getApplicationContext(),Text,Toast.LENGTH_SHORT).show();
    }
}
