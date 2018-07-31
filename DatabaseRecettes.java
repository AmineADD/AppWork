package com.example.delyou.mesrecettes;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.FontsContract;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Hashtable;

public class DatabaseRecettes extends SQLiteOpenHelper {
    public final static String basednom = "recettes.bd";

    public DatabaseRecettes(Context cont) {
        super(cont, basednom, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("Create table recette (id INTEGER PRIMARY KEY AUTOINCREMENT,titre text,composant text,preparation text,favorite text)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("drop table if exists recette");
        onCreate(db);
    }

    public boolean insertRecette(String n,String c,String p) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put("titre", n);
        cv.put("composant", c);
        cv.put("preparation", p);
        cv.put("favorite","false");
        long execute = db.insert("recette", null, cv);
        if (execute == -1) {
            return false;
        } else return true;

    }

    public ArrayList tous_Recettes() {
        ArrayList<String> liste = new ArrayList<String>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cs = db.rawQuery("Select * from recette", null);
        cs.moveToFirst();
        while (cs.isAfterLast() == false) {
            String n = cs.getString(0);
            String n1 = cs.getString(1);
            liste.add(n + ": " + n1);
            cs.moveToNext();

        }
        return liste;
    }
    public boolean updateRecette(String titre,String composant,String preparation,String Id ){
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("titre",titre);
        contentValues.put("composant",composant);
        contentValues.put("preparation",preparation);
        db.update("recette",contentValues,"id=?",new String[]{Id});
        return true;
    }
    public Boolean DeleteRecette(String id){
        SQLiteDatabase db= this.getWritableDatabase();
        int retour = db.delete("recette", "id=?",new String[]{id});
        if (retour ==0) return false;
        else return true;
    }
    public ArrayList tous_recetteFavorite(String favorite) {
        ArrayList<String> liste = new ArrayList<String>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cs = db.rawQuery("Select * from recette", null);
        cs.moveToFirst();
        while (cs.isAfterLast() == false)
        {
            if(cs.getString(4).equals(favorite.toString()))
            {
                String n = cs.getString(0);
                String n1 = cs.getString(1);
                liste.add(n + ": " + n1 );
            }
            cs.moveToNext();

        }
        return liste;
    }
    public ArrayList tous_recetteID() {
        ArrayList<String> liste = new ArrayList<String>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cs = db.rawQuery("Select * from recette", null);
        cs.moveToFirst();
        while (cs.isAfterLast() == false)
        {
                String n = cs.getString(0);

                liste.add(n);

                 cs.moveToNext();
        }
        return liste;
    }
    public ArrayList recetteParTitre(String titre) {
        ArrayList<String> liste = new ArrayList<String>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cs = db.rawQuery("Select * from recette", null);
        cs.moveToFirst();
        while (cs.isAfterLast() == false)
        {
            if(cs.getString(4).equals(titre.toString()))
            {
                String n = cs.getString(0);
                String n1 = cs.getString(1);
                String n2 = cs.getString(2);
                String n3=cs.getString(3);
                liste.add(n + " " + n1 + " " + n2 + " "+" "+n3);
            }
            cs.moveToNext();

        }
        return liste;
    }
    public Hashtable<String,String> recetteParID(String Id) {
        Hashtable<String,String> liste = new  Hashtable<String,String>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cs = db.rawQuery("Select * from recette", null);
        cs.moveToFirst();
        while (cs.isAfterLast() == false)
        {
            if(cs.getString(0).equals(Id.toString()))
            {
                String n=cs.getString(0);
                String n1 = cs.getString(1);
                String n2 = cs.getString(2);
                String n3=cs.getString(3);
                liste.put("id",n);
                liste.put("Titre",n1);
                liste.put("comp",n2);
                liste.put("prep",n3);
            }
            cs.moveToNext();

        }
        return liste;
    }
    public boolean MarquerRecette(String id)
    {
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("favorite","true");
        db.update("recette",contentValues,"id=?",new String[]{id});
        return true;
    }
    public boolean NonMarquerRecette(String id)
    {
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("favorite","false");
        db.update("recette",contentValues,"id=?",new String[]{id});
        return true;
    }
    public boolean parmisFavorite(String id)
    {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cs = db.rawQuery("Select * from recette", null);
        cs.moveToFirst();
        boolean trouve=false;
        while (cs.isAfterLast() == false)
        {
            if(cs.getString(0).equals(id.toString()))
            {
                String n = cs.getString(0);
                String n1 = cs.getString(1);
                String n2 = cs.getString(2);
                String n3=cs.getString(3);
                String n4=cs.getString(4);
                if(n4.equals("true"))
                {
                    trouve= true;
                }
                else
                {
                    trouve=  false;
                }
            }
            cs.moveToNext();

        }
        return trouve;
    }



}