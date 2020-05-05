package com.example.myapplication.Controller;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.shapes.Shape;
import android.os.Bundle;
import android.os.Handler;
import android.util.Size;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;


import com.example.myapplication.Model.Question;
import com.example.myapplication.Model.QuestionBank;
import com.example.myapplication.R;

import java.util.Arrays;

import nl.dionsegijn.konfetti.KonfettiView;


public class Jouer extends AppCompatActivity implements View.OnClickListener {


    private Button button1;
    private Button button2;
    private Button button3;
    private Button button4;
    private ImageView imageView;
    private Toolbar toolbar;

    private QuestionBank QuestionBank;
    private Question CurrentQuestion;

    private boolean EnableTouchEvents;
    private int NumberOfQuestions;

    private int score;

    public static final String BUNDLE_STATE_SCORE = "currentScore";
    public static final String BUNDLE_STATE_QUESTION = "currentQuestion";




    private SharedPreferences sharedPreferences;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jouer);





        EnableTouchEvents = true;

        sharedPreferences = getPreferences(MODE_PRIVATE);


        //referencer les elements
        button1 = findViewById(R.id.button1);
        button2 = findViewById(R.id.button2);
        button3 = findViewById(R.id.button3);
        button4 = findViewById(R.id.button4);
        imageView = (ImageView) findViewById(R.id.imageView);
        toolbar = findViewById(R.id.toolbar);



        // afficher la toolbar

        setSupportActionBar(toolbar);

        //  activer le button retour sur le toolbar
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        // le onclick
        toolbar.setNavigationOnClickListener(clickBackListener);



        /*
        Initialiser l'activity
        c.a.d initialiser la banque des questions qui genere des qst aleatoire..
         */
        QuestionBank = this.generateQuestions();


        // distinguer le bouton sur lequel le joueur a appuyé ..
        // La valeur du tag sera utilisée pour distinguer le bouton déclenché..

        button1.setTag(0);
        button2.setTag(1);
        button3.setTag(2);
        button4.setTag(3);

        /*
        La méthode onClick() sera désormais appelée à chaque fois que l'utilisateur cliquera sur un des quatre boutons de réponse.
         */
        button1.setOnClickListener(this);
        button2.setOnClickListener(this);
        button3.setOnClickListener(this);
        button4.setOnClickListener(this);


        // je recupere un object de la classe Question depuis la classe QuestionBank ..
        CurrentQuestion = QuestionBank.getQuestion();
        // appeller la methode displayQuestion pour afficher les infos sur l'interface (qst , liste de reponses)
        this.displayQuestion(CurrentQuestion); //CurrentQuestion est un objet de la classe question

          // si le score et le nb de qst existent on les recuperent ..
        if (savedInstanceState != null) {
            score = savedInstanceState.getInt(BUNDLE_STATE_SCORE);
            NumberOfQuestions = savedInstanceState.getInt(BUNDLE_STATE_QUESTION);
        } else {
            score = 0;
            NumberOfQuestions = 17;
        }


    }

    // creer un enssemble de questions pour l'ajouter au QuestionBank

    private QuestionBank generateQuestions() {
        Question question1 = new Question(R.drawable.algerie,
                Arrays.asList("Allemagne", "Kosovo", "Algerie", "Irak"),
                2);

        Question question2 = new Question(R.drawable.allemagne,
                Arrays.asList("Allemagne", "Colombie", "Namibie", "Madagascar"),
                0);

        Question question3 = new Question(R.drawable.bangladesh,
                Arrays.asList("Finlande", "Corée du sud", "Bangladesh", "Algerie"),
                2);
        Question question4 = new Question(R.drawable.bresil,
                Arrays.asList("Allemagne", "Irak", "Namibie", "Bresil"),
                3);
        Question question5 = new Question(R.drawable.irak,
                Arrays.asList("Kosovo", "Irak", "Bangladesh", "Corée du sud"),
                1);
        Question question6 = new Question(R.drawable.lesotho,
                Arrays.asList("Namibie", "Bangladesh", "Colombie", "Lesotho"),
                3);
        Question question7 = new Question(R.drawable.kosovo,
                Arrays.asList("Algerie", "Kosovo", "Irak", "Colombie"),
                1);
        Question question8 = new Question(R.drawable.namibie,
                Arrays.asList("Namibie", "Kosovo", "Madagascar", "Corée du sud"),
                0);
        Question question9 = new Question(R.drawable.colombie,
                Arrays.asList("Finlande", "Colombie", "Irak", "Allemagne"),
                1);
        Question question10 = new Question(R.drawable.finlande,
                Arrays.asList("Namibie", "Finlande", "Bresil", "Madagascar"),
                1);
        Question question11 = new Question(R.drawable.coreedusud,
                Arrays.asList("Algerie", "Bangladesh", "Corée du sud", "Namibie"),
                2);
        Question question12 = new Question(R.drawable.madagascar,
                Arrays.asList("Algerie", "Lesotho", "Madagascar", "Finlande"),
                2);



        return new QuestionBank(Arrays.asList(question1,
                question2,
                question3,
                question4,
                question5,
                question6,
                question7,
                question8,
                question9,
                question10,
                question11,
                question12


                ));
    }





    private void displayQuestion(final Question question) {
        imageView.setImageResource(question.getQuestion());
        button1.setText(question.getChoiceList().get(0));
        button2.setText(question.getChoiceList().get(1));
        button3.setText(question.getChoiceList().get(2));
        button4.setText(question.getChoiceList().get(3));
        button1.setBackgroundResource(R.color.transparent);
        button2.setBackgroundResource(R.color.transparent);
        button3.setBackgroundResource(R.color.transparent);
        button4.setBackgroundResource(R.color.transparent);
    }

    // sauvegarder le score et le nb de qst posee
    // car a la rotation de l'ecran l'activity se detruit
    @Override
    protected void onSaveInstanceState(Bundle outState) {
        outState.putInt(BUNDLE_STATE_SCORE, score);
        outState.putInt(BUNDLE_STATE_QUESTION, NumberOfQuestions);

        super.onSaveInstanceState(outState);
    }


    @Override
    public void onClick(View v) {
        int responseIndex = (int) v.getTag();

        if (responseIndex == CurrentQuestion.getAnswerIndex()) {
            // Good answer
            Toast.makeText(this, "Correct", Toast.LENGTH_SHORT).show();
            v.setBackgroundResource(R.color.green);
            score++;

        } else {
            // Wrong answer
            Toast.makeText(this, "Wrong answer!", Toast.LENGTH_SHORT).show();
            v.setBackgroundResource(R.color.red);
        }


            EnableTouchEvents = false;

            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    EnableTouchEvents = true;

                    // If this is the last question, ends the game.
                    // Else, display the next question.
                    if (--NumberOfQuestions == 0) {

                        // End the game
                        endGame();
                    } else {
                        CurrentQuestion = QuestionBank.getQuestion();
                        displayQuestion(CurrentQuestion);
                    }
                }
            }, 2000); // cette methode permet de controller le temps a chaque appuie

        }

        // a chaque fois que le user touche l'ecran cette methode est appelle..
        @Override
        public boolean dispatchTouchEvent (MotionEvent ev){
            return EnableTouchEvents && super.dispatchTouchEvent(ev);
        }

        private void endGame () {
            AlertDialog.Builder builder = new AlertDialog.Builder(this);

            //afficher le score dans un Alert dialog
            //afficher le score dans un Alert dialog
            if(score<10)
            {
                builder.setTitle("C'est faible comme résultat!");
            }else if (score<17 && score>10)
            {
                builder.setTitle("Bien!");
            }else
            {
                builder.setTitle("c'est excellent !!!! bravo!");



            }

                    builder.setMessage("Votre score est : "+score+"/17")
                    .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {

                            // fermer cette activity et ouvrir une autre activity en envoyant un resultat (score)
                            Intent regame = new Intent(Jouer.this,Rejouer.class);
                            //definir une cle pour la recuperer le score dans le Main
                            //attacher le score à l'Intent, avec la clé associée
                            regame.putExtra("score", score);
                            setResult(RESULT_OK, regame);
                            // ouvrir l'activity regame
                            startActivity(regame);
                        }
                    })
                    .setCancelable(false)
                    .create()
                    .show();
        }

    // retour
    View.OnClickListener clickBackListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {

            startActivity(new Intent(getApplicationContext(), MainActivity.class));
            finish();

        }
    };



}
