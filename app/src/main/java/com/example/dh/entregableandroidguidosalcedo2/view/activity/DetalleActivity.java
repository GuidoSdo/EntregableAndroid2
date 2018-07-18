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
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class DetalleActivity extends AppCompatActivity {

    FragmentManager fragmentManager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalle);
        FragmentDetalle fragmentDetalle = new FragmentDetalle();

        Intent unIntent = getIntent();
        Bundle unBundle = unIntent.getExtras();
        fragmentDetalle.setArguments(unBundle);


        fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.contenedorDetalleActivity, fragmentDetalle);



    }
}
