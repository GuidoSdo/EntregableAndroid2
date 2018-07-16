package com.example.dh.entregableandroidguidosalcedo2.view.activity;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.dh.entregableandroidguidosalcedo2.R;
import com.example.dh.entregableandroidguidosalcedo2.view.fragment.FragmentDetalle;
import com.example.dh.entregableandroidguidosalcedo2.view.fragment.FragmentFeed;

public class DetalleActivity extends AppCompatActivity {

    FragmentManager fragmentManager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalle);

        fragmentManager = getSupportFragmentManager();

        Intent unIntent = getIntent();
        final Bundle unBundle = unIntent.getExtras();

     /*   FragmentDetalle fragmentDetalle = new FragmentDetalle();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.frameLayoutContenedor, fragmentDetalle);
        fragmentTransaction.addToBackStack(null).commit();
*/
    }
}
