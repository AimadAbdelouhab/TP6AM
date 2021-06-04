package com.example.bdd;

import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class MonRecyclerViewAdapter extends RecyclerView.Adapter<MonRecyclerViewAdapter.ConteneurDeDonnee> {
    private List<Planete> donnees;
    private static DetecteurDeClicSurRecycler detecteurDeClicSurRecycler;

    public MonRecyclerViewAdapter(List<Planete> donnees) {
        this.donnees = donnees;
    }
    @Override
    public ConteneurDeDonnee onCreateViewHolder(ViewGroup parent, int
            viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.recyclerview_item, parent, false);
        return new ConteneurDeDonnee(view);
    }
    @Override
    public void onBindViewHolder(ConteneurDeDonnee conteneur, int
            position) {
        conteneur.tv_principal.setText(donnees.get(position).getNom());
       conteneur.tv_auxiliaire.setText(donnees.get(position).getUid()+"");
        conteneur.tv_taille.setText(donnees.get(position).getTaille()+"");
        conteneur.image.setImageResource(donnees.get(position).getImage());
    }
    @Override
    public int getItemCount() {
        return donnees.size();
    }



    public static class ConteneurDeDonnee extends RecyclerView.ViewHolder implements View.OnClickListener {

        TextView tv_principal;
       TextView tv_auxiliaire;
        ImageView image;
        TextView tv_taille;


        public ConteneurDeDonnee(View itemView) {
            super(itemView);
            tv_principal = (TextView) itemView.findViewById(R.id.tv_principal);
           tv_auxiliaire = (TextView) itemView.findViewById(R.id.tv_auxiliaire);
            tv_taille = (TextView) itemView.findViewById(R.id.tv_taille);
            image=(ImageView) itemView.findViewById(R.id.image);


        }

        @Override
        public void onClick(View v) {

            detecteurDeClicSurRecycler.clicSurRecyclerItem(getAdapterPosition(), v);
        }

    }




    public void setDetecteurDeClicSurRecycler(DetecteurDeClicSurRecycler detecteurDeClicSurRecycler) {
        this.detecteurDeClicSurRecycler = detecteurDeClicSurRecycler;
    }






}
