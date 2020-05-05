package com.example.myapplication.Model;

import java.util.Collections;
import java.util.List;

/*
         à chaque nouvelle partie, il faudrait que les questions soient différentes,
         et affichées dans un ordre aléatoire.
         Pour ce faire, nous avons crée une classe Java spécifique nommée QuestionBank
         */

public class QuestionBank {
    private List<Question> QuestionList;
    private int NextQuestionIndex;

    public QuestionBank(List<Question> questionList) {
        QuestionList = questionList;

        //Mélangez la liste de questions avant de la stocker
        Collections.shuffle(QuestionList);

        NextQuestionIndex = 0;
    }

    public Question getQuestion() {
        // Parcourez les questions et renvoyez-en une nouvelle à chaque appel
        if (NextQuestionIndex == QuestionList.size()) {
            NextQuestionIndex = 0;
        }

        // retourner un objet de la classe question depuis la liste des questions en incrementant ..
        return QuestionList.get(NextQuestionIndex++);
    }
}
