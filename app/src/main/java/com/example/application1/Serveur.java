package com.example.application1;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.PrecomputedText;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.firestore.FirebaseFirestore;

import java.math.BigDecimal;
import java.math.MathContext;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class Serveur extends AppCompatActivity {
    private DatabaseReference mDatabase;
    TextView distance, speed, acceleration,treshold, notif_frein;
    static double first=0, second=0, third=0, fourth=0, threshhold=0;


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
        notif_frein = findViewById(R.id.notif_frein);


        // Creer Une array List ici
        double[] arr = new double[0];
        List<Double> arrlist = new ArrayList<Double>();
        List<Double> speedArrList = new ArrayList<Double>();

        mDatabase.child("appVariables").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                double thresholdSpeed = Double.parseDouble(snapshot.child("thresholdSpeed").getValue().toString());
                Log.d("TAG", "THRESHOLD: " + thresholdSpeed);
                threshhold = thresholdSpeed;
                treshold.setText("Limite: "+thresholdSpeed);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });


        mDatabase.child("node").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                fourth = third;
                third = second;
                second = first;
                first = Double.parseDouble(snapshot.child("finalDistance").getValue().toString());
                Log.d("TAG", "FINAL DISTANCE: " + first);


                // Ajouter les distances au tableau et incrementer la variable d'iteration
                arrlist.add(first*1000);
                distance.setText("Distance: "+ first*1000);
                speed.setText("Speed: "+ BigDecimal.valueOf(((first-second)/2.5)*1000).toPlainString());
                double vi , vf , acc;
                vi = (((third-fourth)/2.5)*1000);
                vf = (((first-second)/2.5)*1000);
                acc = ((vf-vi)/2.5)*1000;
                acceleration.setText("Acc : "+ BigDecimal.valueOf(acc));

                if (acc>threshhold){
                    notif_frein.setVisibility(View.VISIBLE);
                    mDatabase.child("appVariables").child("brake").setValue("Freinnage Enclanch?? par Serveur");
                }

                Log.d("Taille", String.valueOf(arrlist.size()));
                while (arrlist.size() >= 1) {
                    while (speedArrList.size() >= 1) {
                        speedArrList.add(first - second / 5);
                        speed.setText("Speed: "+BigDecimal.valueOf(first - second / 5).toPlainString());
                        Log.d("SPEED", speedArrList.toString());
                        Log.d(" TAG - Acc", String.valueOf((first - second / 5)-(third - fourth / 5)));
                        acceleration.setText("Acceleration: "+BigDecimal.valueOf((((first-second)/5)-((third-fourth)/5))/5).toPlainString());

                        if (acc>threshhold){
                            notif_frein.setVisibility(View.VISIBLE);
                            mDatabase.child("appVariables").child("brake").setValue("Freinnage Enclanch?? par Serveur");
                        }

                    }
                    //speedArrList.remove(0);

                }
                //arrlist.remove(0);



            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }

        });
    }
}