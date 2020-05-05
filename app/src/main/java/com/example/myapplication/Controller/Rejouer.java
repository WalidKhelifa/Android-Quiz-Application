package com.example.myapplication.Controller;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.myapplication.R;

import java.util.prefs.Preferences;

public class Rejouer extends AppCompatActivity {

    private Button button;
    private Button button2;
    private TextView textView;
    private static final int GAME_ACTIVITY_REQUEST_CODE = 2;
    SharedPreferences preferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rejouer);

        button = findViewById(R.id.button);
        button2 = findViewById(R.id.button2);


        Intent intent = getIntent();
        if(intent!=null)
        {
            int res = 0;
            if(intent.hasExtra("score")) {
             res= intent.getIntExtra("score",0);

            }
            //textView.setText(String.valueOf(res));
        }



        // Faire une action a chaque fois que le button sois presser..
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                /*
                elle permet de sauvegarder temporarement le prenom de user
                pour l'afficher dans une autre activity
                sharedPreferences.edit().putString("Prenom", user.getPrenom()).apply();
                 */


                //Lancer une autre activity ..
                Intent game = new Intent(Rejouer.this, Jouer.class);
                startActivity(game);
            }
        });

        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                System.exit(0);
                finish();

            }
        });


    }







        }




