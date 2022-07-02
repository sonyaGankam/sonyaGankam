package com.example.application1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    Button client;
    Button serveur;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        client= findViewById(R.id.button);
        serveur=findViewById(R.id.button2);

        client.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent A=new Intent(getApplicationContext(), Client.class);
                startActivity(A);
                finish();
            }
        });
       serveur.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               Intent B = new Intent(getApplicationContext(),Serveur.class);
               startActivity(B);
               finish();
           }
       });



















    }
}