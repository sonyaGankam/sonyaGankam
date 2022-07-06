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
    TextView distance, speed, acceleration,treshold;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_serveur);

        setContentView(R.layout.activity_serveur);
        mDatabase = FirebaseDatabase.getInstance().getReference();

        distance = findViewById(R.id.distanceserveur);
        speed = findViewById(R.id.speedserveur);
        acceleration = findViewById(R.id.accelerationserveur);
        treshold=findViewById(R.id.threshold_speed);


        // Creer Une array List ici
        double[] arr = new double[0];
        List<Double> arrlist = new ArrayList<Double>();
        List<Double> speedArrList = new ArrayList<Double>();

        mDatabase.child("appVariables").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                double thresholdSpeed = Double.parseDouble(snapshot.child("thresholdSpeed").getValue().toString());
                Log.d("Threshold Speed", "FINAL DISTANCE: " + thresholdSpeed);
                treshold.setText(String.valueOf(thresholdSpeed));
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });


        mDatabase.child("node").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                double finalDistance = Double.parseDouble(snapshot.child("finalDistance").getValue().toString());
                Log.d("TAG", "FINAL DISTANCE: " + finalDistance);


                // Ajouter les distances au tableau et incrementer la variable d'iteration
                arrlist.add(finalDistance);
                Log.d("Taille", String.valueOf(arrlist.size()));
                while (arrlist.size() > 4) {
                    while (speedArrList.size() > 2) {
                        speedArrList.add((arrlist.get(arrlist.size() - 1) - arrlist.get(arrlist.size() - 2) / 2.5));
                        Log.d("SPEED", speedArrList.toString());
                        speed.setText(String.valueOf(speedArrList.get(speedArrList.size() - 1)));
                        Log.d(" TAG - Acc", String.valueOf(speedArrList.get((speedArrList).size()-1)- speedArrList.get((speedArrList.size()-2))));
                        speedArrList.remove(0);
                    }
                }
                arrlist.remove(0);


            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }

        });
    }
}