package com.example.delyou.mesrecettes;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Hashtable;

public class TousRecette extends AppCompatActivity {
    ListView liste;
    DatabaseRecettes db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tous_recette);


       db=new DatabaseRecettes(getApplicationContext());

        liste=(ListView)findViewById(R.id.lv);

        ArrayList<String> li=db.tous_Recettes();
        ArrayAdapter da=new ArrayAdapter(getApplicationContext(),android.R.layout.simple_list_item_1,li);
        liste.setAdapter(da);

        liste.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                 Intent i=new Intent();
                i.setClass(getApplicationContext(),LireRecette.class);
              //  afficher(String.valueOf(liste.getItemAtPosition(position)));
                String[] inf=String.valueOf(liste.getItemAtPosition(position)).split(":");
                Hashtable<String,String> l=db.recetteParID(inf[0]);

                i.putExtra("id",l.get("id"));
                i.putExtra("Titre",l.get("Titre"));

                i.putExtra("Comp",l.get("comp"));

                i.putExtra("Preparation",l.get("prep"));

                 startActivity(i);

            }
        });

    }

    public void afficher(String Text)
    {
        Toast.makeText(getApplicationContext(),Text,Toast.LENGTH_SHORT).show();
    }
    public void  returnback(View v)
    {
        Intent i=new Intent(getApplicationContext(),MainActivity.class);
        startActivity(i);
    }

}
