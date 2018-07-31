package com.example.delyou.mesrecettes;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class LireRecette extends AppCompatActivity {

    TextView titre,composants,preparation;
    String id;
    Button btnFav;
    DatabaseRecettes db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lire_recette);
        titre=(TextView) findViewById(R.id.titreRecette);
        composants=(TextView) findViewById(R.id.Composant);
        preparation=(TextView) findViewById(R.id.bodyRecette);
            db=new DatabaseRecettes(getApplicationContext());
        Intent i=getIntent();
        if(i!=null)
        {
            Bundle extra=i.getExtras();
            if(extra!=null)
            {
                id=extra.getString("id");
                titre.setText(extra.getString("Titre"));
                composants.setText(extra.getString("Comp"));
                preparation.setText(extra.getString("Preparation"));
            }
        }


        btnFav=(Button)findViewById(R.id.fav);




        if(db.parmisFavorite(id)==true)
        {
            btnFav.setText("Non Favorite");
        }
        else
        {
            btnFav.setText("Marquer dans Favorite");
        }


        btnFav.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(btnFav.getText().toString().equals("Marquer dans Favorite")==true)
                {
                    if(db.MarquerRecette(id)==true)
                    {
                        afficher("la Recette parmis les favorites");
                        btnFav.setText("Non Favorite");
                    }
                }
                else
                {
                    if(db.NonMarquerRecette(id)==true)
                    {
                        afficher("la Recette n'est pas parmis les favorites");
                        btnFav.setText("Marquer dans Favorite");
                    }
                }
            }
        });




    }
    public void  returnback(View v)
    {
        Intent i=new Intent(getApplicationContext(),MainActivity.class);
        startActivity(i);
    }

    public void afficher(String Text)
    {
        Toast.makeText(getApplicationContext(),Text,Toast.LENGTH_SHORT).show();
    }
}
