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
import com.example.dh.entregableandroidguidosalcedo2.view.fragment.FragmentFeed;

public class MainActivity extends AppCompatActivity implements FragmentFeed.ComunicacionFragment {

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
//        HomeFragment homeFragment = new HomeFragment();
        FragmentFeed fragmentFeed = new FragmentFeed();
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
        fragmentTransaction.replace(R.id.frameLayoutContenedor, fragmentACargar);
        fragmentTransaction.addToBackStack(null).commit();
    }

    public void clickearonLaPintura(Integer posicion,Integer idDePinturaSeleccionada) {

        Intent unIntent = new Intent(this, DetalleActivity.class);
        Bundle unBundle = new Bundle();
        unBundle.putInt("idPintura",idDePinturaSeleccionada);
        unBundle.putInt("posicion",posicion);
        unIntent.putExtras(unBundle);
        startActivity(unIntent);
    }
}
