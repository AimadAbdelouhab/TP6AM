package com.example.bdd;

import androidx.appcompat.app.AppCompatActivity;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements SimpleDialogListener{

    final String PREFS_NAME = "preferences_file";

    private RecyclerView mRecyclerView;
    private MonRecyclerViewAdapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    CoordinatorLayout mcoordinatorLayout;
    private FloatingActionButton fb;
    ArrayList<Planete> planetes = new ArrayList<>();
    PlaneteDao planeteDao;

    TextView tv ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        mRecyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        mRecyclerView.setHasFixedSize(true);
        mLayoutManager=new LinearLayoutManager(this);

        mRecyclerView.setLayoutManager(mLayoutManager);

        mcoordinatorLayout = (CoordinatorLayout) findViewById(R.id.coordinatorLayout);
        fb=(FloatingActionButton)findViewById(R.id.fab);
        fb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AjouterUnePlanete();

            }
        });


        AppDatabase db = Room.databaseBuilder(getApplicationContext(),
                AppDatabase.class, "planetesDB").build();

        planeteDao = db.planeteDao();

        loadData(planeteDao);
    }



    private void initData(PlaneteDao planeteDao) {


        planetes.add(new Planete(1,"Mércure",4900,R.drawable.mercure));
        planetes.add(new Planete(2,"Vénus",12000,R.drawable.venus));
        planetes.add(new Planete(3,"Terre",12800,R.drawable.earth));
        planetes.add(new Planete(4,"Mars",6800,R.drawable.mars));
        planetes.add(new Planete(5,"Jupiter",144000,R.drawable.jupiter));
        planetes.add(new Planete(6,"Saturne",120000,R.drawable.saturne));
        planetes.add(new Planete(7,"Uranus",52000,R.drawable.uranus));
        planetes.add(new Planete(8,"Neptune",50000,R.drawable.neptune));
        planetes.add(new Planete(9,"Pluton",2300,R.drawable.pluton));

        for (int index = 0; index < planetes.size(); index++) {
            Planete planete = planetes.get(index);
            planeteDao.insert(planete);
        }
    }


    private void loadData(PlaneteDao planeteDao) {

        SharedPreferences settings = getSharedPreferences(PREFS_NAME, 0);

        new Thread(new Runnable() {
            @Override
            public void run() {

                if (settings.getBoolean("is_data_loaded", true)) {
                    initData(planeteDao);
                    settings.edit().putBoolean("is_data_loaded", false).commit();
                }

                List<Planete> planetes = planeteDao.getAll();

                mRecyclerView.post(new Runnable() {
                    @Override
                    public void run() {
                        mAdapter = new MonRecyclerViewAdapter(planetes);
                        mRecyclerView.setAdapter(mAdapter);
                    }
                });

            }
        }).start();

    }

    private void AjouterUnePlanete(){
        FragmentManager fm = getSupportFragmentManager();
        FragmentInterface simpleDialogFragment = FragmentInterface.newInstance(69,"Azgard",2000,R.drawable.mars);
        simpleDialogFragment.show(fm, "fragment_simple_dialog");

    }


    public void clicSurRecyclerItem(int position, View v) {
        TextView titre= v.findViewById(R.id.tv_principal);
        Snackbar.make(mcoordinatorLayout, "La planète " + titre.getText(),Snackbar.LENGTH_LONG).show();
    }


    public void AjoutPlaneteDansLaBaseDonee(Planete planete) {

        SharedPreferences settings = getSharedPreferences(PREFS_NAME, 0);

        new Thread(new Runnable() {
            @Override
            public void run() {
                planeteDao.insert(planete);
                planetes.add(planete);


                mRecyclerView.post(new Runnable() {
                    @Override
                    public void run() {
                        mAdapter.notifyDataSetChanged();

                    }
                });

            }
        }).start();

    }


    @Override
    public void onOkClickDialog(String nom) {
        Toast.makeText(this, "Cliquez une nouvelle fois pour valider puis revenez dans l'application: " + nom, Toast.LENGTH_SHORT).show();
    }


}