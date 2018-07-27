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
import android.widget.Toast;

import com.example.dh.entregableandroidguidosalcedo2.R;
import com.example.dh.entregableandroidguidosalcedo2.model.pojo.Artista;
import com.example.dh.entregableandroidguidosalcedo2.model.pojo.Pintura;
import com.example.dh.entregableandroidguidosalcedo2.view.fragment.FragmentDetalle;
import com.example.dh.entregableandroidguidosalcedo2.view.fragment.FragmentFeed;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class MainActivity extends AppCompatActivity implements
        FragmentFeed.ComunicacionFragment,FragmentDetalle.ConexionFragmentDetalle {

    private FragmentManager fragmentManager;
    private FragmentFeed fragmentFeed;
    private DatabaseReference mDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mDatabase = FirebaseDatabase.getInstance().getReference();

        // App Bar
        Toolbar myToolbar = (Toolbar) findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar);

        // Fragments
        fragmentManager = getSupportFragmentManager();
        fragmentFeed = new FragmentFeed();
        cargarFragment(fragmentFeed);
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
        fragmentTransaction.replace(R.id.frameLayoutContenedorMainActivity, fragmentACargar);
        fragmentTransaction.addToBackStack(null).commit();
    }

    @Override
    public void clickearonLaPintura(Integer pos) {
        FragmentDetalle fragmentDetalle = new FragmentDetalle();
        cargarFragment(fragmentDetalle);
        Bundle unBundle = new Bundle();
        unBundle.putInt("posicion",pos);
        fragmentDetalle.setArguments(unBundle);
    }

    @Override
    public void clickearonDetalleFragment(Integer pos) {
        Bundle bundle = new Bundle();
        bundle.putInt("posicion",pos);
        fragmentFeed.setArguments(bundle);
    }
}
