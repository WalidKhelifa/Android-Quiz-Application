package com.example.myapplication.Controller;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.myapplication.R;

public class MainActivity extends AppCompatActivity {

    private TextView textView ;
    private Button button;
    private Button button2;
    private SharedPreferences sharedPreferences;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // referencer les elements ...
        button = findViewById(R.id.button);
        button2 = findViewById(R.id.button2);



        //button.setEnabled(false);

        // permer de stocker les donnes dans le telephones ..
        sharedPreferences = getPreferences(MODE_PRIVATE);

        /*
         Text change action..
         editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            button.setEnabled(s.toString().length() != 0);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
         */


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
                Intent game = new Intent(MainActivity.this,Jouer.class);
                startActivity(game);


            }
        });

        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });



    }





}
