package com.example.delyou.mesrecettes;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.text.method.ScrollingMovementMethod;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class ajouterRecette extends Fragment{

    Button btnAjt;
    View rootView;
    EditText titre,composants,preparation;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.ajouter_mes_recette, container, false);

        btnAjt=(Button) rootView.findViewById(R.id.btnAjouter);
        btnAjt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                DatabaseRecettes db=new DatabaseRecettes(rootView.getContext());
                titre=(EditText) rootView.findViewById(R.id.titreRecAdd);
                composants=(EditText) rootView.findViewById(R.id.CompRAdd);
                preparation=(EditText) rootView.findViewById(R.id.bodyRAdd);


                titre.setMovementMethod(new ScrollingMovementMethod());
                composants.setMovementMethod(new ScrollingMovementMethod());
                preparation.setMovementMethod(new ScrollingMovementMethod());



                if(db.insertRecette(titre.getText().toString(),composants.getText().toString(),preparation.getText().toString())==true)
                {
                    afficher("votre Recette est enregistré");
                    titre.setText("");
                    composants.setText("");
                    preparation.setText("");
                }
                else
                {
                    afficher("Verifier les champs ");

                }
            }
        });



        return rootView;
    }
    public void afficher(String Text)
    {
        Toast.makeText(rootView.getContext(),Text,Toast.LENGTH_SHORT).show();
    }

}