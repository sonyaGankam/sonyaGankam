package com.example.application1;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.PrecomputedText;
import android.util.Log;
import android.widget.TextView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class Serveur extends AppCompatActivity {
    private DatabaseReference mDatabase;
    TextView distace,av_speed;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_serveur);

        setContentView(R.layout.activity_serveur);
        mDatabase = FirebaseDatabase.getInstance().getReference();

        distace = findViewById(R.id.dist);
        av_speed = findViewById(R.id.av_speed);



        // Creer Une array List ici
        double [] arr = new double[0];
        List<Double> arrlist = new ArrayList<Double>();
        List<Double> speedArrList = new ArrayList<Double>();



        mDatabase.child("node").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                double finalDistance = Double.parseDouble(snapshot.child("finalDistance").getValue().toString());
                Log.d("TAG","FINAL DISTANCE: " + finalDistance);

                    // Ajouter les distances au tableau et incrementer la variable d'iteration
                    arrlist.add(finalDistance);
                    Log.d("Taille", String.valueOf(arrlist.size()));
                    while (arrlist.size()>2) {
                        while (speedArrList.size()>2) {
                            speedArrList.add((arrlist.get(arrlist.size() - 1) - arrlist.get(arrlist.size() - 2) / 5));
                            Log.d("SPEED", speedArrList.toString());
                            speedArrList.remove(0);
                        }}
                        arrlist.remove(0);
                    }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }

    });
}}