package com.example.delyou.mesrecettes;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;
import android.view.View;

import java.util.ArrayList;
import java.util.Hashtable;

public class modification extends AppCompatActivity {

    Spinner sp;
    Button btnmdf;
    ArrayAdapter da;
    DatabaseRecettes db;
    EditText titre,composants,preparation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.modifer_mes_recette);

        sp=(Spinner)findViewById(R.id.SpinnerRecette);
        db  =new DatabaseRecettes(getApplicationContext());

        titre=(EditText)findViewById(R.id.titreRModi);
        composants=(EditText)findViewById(R.id.CompRModi);
        preparation=(EditText)findViewById(R.id.bodyRModi);

        da=new ArrayAdapter(getApplicationContext(),android.R.layout.simple_spinner_dropdown_item,db.tous_recetteID());
        sp.setAdapter(da);
        da.setNotifyOnChange(true);

        titre.setMovementMethod(new ScrollingMovementMethod());
        composants.setMovementMethod(new ScrollingMovementMethod());
        preparation.setMovementMethod(new ScrollingMovementMethod());

        sp.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                Hashtable<String,String> l = db.recetteParID(String.valueOf(sp.getSelectedItem()));

                        titre.setText(l.get("Titre"));
                        composants.setText(l.get("comp"));
                        preparation.setText(l.get("prep"));


            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }

        });

        btnmdf=(Button)findViewById(R.id.btnModi);
        btnmdf.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                titre=(EditText)findViewById(R.id.titreRModi);
                composants=(EditText)findViewById(R.id.CompRModi);
                preparation=(EditText)findViewById(R.id.bodyRModi);

                if(db.updateRecette(titre.getText().toString(),composants.getText().toString(),preparation.getText().toString(),String.valueOf(sp.getSelectedItem()))==true)
                {
                    afficher("votre Recette est modifié");
                    da.notifyDataSetChanged();
                    titre.setText("");
                    composants.setText("");
                    preparation.setText("");
                    sp.setSelection(0);

                }
                else
                {
                    afficher("Verifier les champs ");

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
