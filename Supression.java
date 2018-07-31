package com.example.delyou.mesrecettes;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

public class Supression extends AppCompatActivity {

    Button btnSupp;
    Spinner sp;
    DatabaseRecettes db;
    ArrayAdapter da;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.supprimer_mes_recette);

        db= new DatabaseRecettes(getApplicationContext());
        sp=(Spinner)findViewById(R.id.SpinRece);

        da=new ArrayAdapter(getApplicationContext(),android.R.layout.simple_spinner_dropdown_item,db.tous_recetteID());
        sp.setAdapter(da);
        da.setNotifyOnChange(true);

        btnSupp=(Button)findViewById(R.id.btnSupp);
        btnSupp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(db.DeleteRecette(String.valueOf(sp.getSelectedItem()))==true)
                {
                    afficher("la recette est supprimé");
                    da.notifyDataSetChanged();
                    sp.setSelection(0);

                }
                else
                {
                    afficher("Suppresion est arreté");
                }
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
