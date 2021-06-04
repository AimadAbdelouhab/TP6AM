package com.example.bdd;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Planete {
    @PrimaryKey
    public int uid;

    @ColumnInfo(name = "name")
    private String nom;

    @ColumnInfo(name = "size")
    private int taille;

    @ColumnInfo(name = "image")
    private int image;


    Planete(int uid, String nom, int taille,int image){
        this.uid = uid;
        this.nom = nom;
        this.taille = taille;
        this.image=image;
    }

    public int getUid() {
        return uid;
    }

    public String getNom() {
        return nom;
    }

    public int getImage() {
        return image;
    }

    public int getTaille() {
        return taille;
    }


}