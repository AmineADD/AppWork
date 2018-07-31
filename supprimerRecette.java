package com.example.delyou.mesrecettes;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

public class supprimerRecette extends Fragment{

    Button btnSupp;
    Spinner sp;
    View rootView;
    DatabaseRecettes db;
    ArrayAdapter da;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
       rootView = inflater.inflate(R.layout.supprimer_mes_recette, container, false);

         db= new DatabaseRecettes(rootView.getContext());
        sp=(Spinner)rootView.findViewById(R.id.SpinRece);

        da=new ArrayAdapter(rootView.getContext(),android.R.layout.simple_spinner_dropdown_item,db.tous_recetteID());
        sp.setAdapter(da);
        da.setNotifyOnChange(true);

        btnSupp=(Button)rootView.findViewById(R.id.btnSupp);
        btnSupp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
        if(db.DeleteRecette(String.valueOf(sp.getSelectedItem()))==true)
            {
                afficher("la recette est supprimé");
                da.notifyDataSetChanged();

            }
            else
        {
            afficher("Suppresion est arreté");
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