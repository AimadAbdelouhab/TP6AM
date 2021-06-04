package com.example.bdd;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;

public class FragmentInterface extends DialogFragment {
    private int id,taille,image;
    private SimpleDialogListener listener;
    private String Nom;
    private EditText editId,editNom,editTaille,editImage;
    private Button button;

    public FragmentInterface() {

    }

    public static FragmentInterface newInstance(int id,String nom,int taille,int image) {

        FragmentInterface frag = new FragmentInterface();
        Bundle args = new Bundle();
        frag.setArguments(args);
        return frag;
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
        return inflater.inflate(R.layout.layoutfragment, container);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {

        super.onViewCreated(view, savedInstanceState);

        listener = (SimpleDialogListener) getActivity();
        editId = (EditText) view.findViewById(R.id.editeTextId);
        editNom = (EditText) view.findViewById(R.id.editTextNom);
        editTaille = (EditText) view.findViewById(R.id.editTextTaille);
        button = (Button) view.findViewById(R.id.Valider);

        MainActivity mainActivity=(MainActivity)getActivity();

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onOkClickDialog(editId.getText().toString()+" "+editNom.getText().toString()+" "+editTaille.getText().toString());
                int I=Integer.parseInt(editId.getText().toString());
                int T=Integer.parseInt(editTaille.getText().toString());

                Planete planete=new Planete(I,editNom.getText().toString(),T,R.drawable.earth);
                mainActivity.AjoutPlaneteDansLaBaseDonee(planete);

            }
        });

        editId.requestFocus();


        getDialog().getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_VISIBLE);

    }




}
