package com.example.dh.entregableandroidguidosalcedo2.view.activity;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.example.dh.entregableandroidguidosalcedo2.R;
import com.example.dh.entregableandroidguidosalcedo2.controller.ControllerPintura;
import com.example.dh.entregableandroidguidosalcedo2.model.pojo.Artista;
import com.example.dh.entregableandroidguidosalcedo2.model.pojo.Pintura;
import com.example.dh.entregableandroidguidosalcedo2.utils.ResultListener;
import com.example.dh.entregableandroidguidosalcedo2.view.fragment.FragmentFeed;
import com.example.dh.entregableandroidguidosalcedo2.view.fragment.HomeFragment;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.List;

public class MainActivity extends AppCompatActivity implements FragmentFeed.comunicacionFragment {

    FragmentManager fragmentManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // App Bar
        Toolbar myToolbar = (Toolbar) findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar);

        // Fragments
        fragmentManager = getSupportFragmentManager();
        HomeFragment homeFragment = new HomeFragment();
        FragmentFeed fragmentFeed = new FragmentFeed();
        cargarFragment(fragmentFeed);
        cargarPinturas();




    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_toolbar, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {

            case R.id.buscador:
                Toast.makeText(this, "Buscador", Toast.LENGTH_SHORT).show();
                break;

            case R.id.iniciarSesion:
                Intent intent2 = new Intent (this,LoginActivity.class);
                startActivity(intent2);
            break;

            case R.id.cerrarSesion:
                Intent intent = new Intent(this, AccountActivity.class);
                startActivity(intent);
            default:
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    private void cargarFragment(Fragment fragmentACargar) {

        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.frameLayoutContenedor, fragmentACargar);
        fragmentTransaction.addToBackStack(null).commit();
    }

    public void cargarPinturas() {

        ControllerPintura controllerPintura = new ControllerPintura();
        controllerPintura.obtenerPintura(new ResultListener<List<Pintura>>() {
            @Override
            public void finish(List<Pintura> pinturas) {
               // Toast.makeText(MainActivity.this, "cargo las pinturas", Toast.LENGTH_LONG).show();

            }
        });

    }

    public void clickearonLaPintura(Integer posicion,Integer idDePinturaSeleccionada) {

        Intent unIntent = new Intent(this, DetalleActivity.class);
        Bundle unBundle = new Bundle();
        leerSimple();
        unBundle.putInt("pos",posicion);
        unBundle.putInt("idPintura",idDePinturaSeleccionada);
        unIntent.putExtras(unBundle);
        startActivity(unIntent);

    }

    public void leerSimple(){
        DatabaseReference mDatabase;

        //Referencia al JSON del Firebase de la BD del proyecto
        FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
        mDatabase = firebaseDatabase.getReference();

        ValueEventListener valueEventListener = new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Artista artistaLeido = dataSnapshot.getValue(Artista.class);
                Toast.makeText(MainActivity.this, artistaLeido.toString(), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        };
    }
}
