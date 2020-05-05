package com.example.myapplication.Model;

import com.example.myapplication.R;

import java.util.List;

public class Question
{
    private int Question ; // Le texte de la question
    private List<String> ChoiceList; // La liste des réponses proposées
    private int AnswerIndex; // L'index de la réponse dans la liste précédente

    public Question(int question, List<String> choiceList, int answerIndex) {
        Question = question;
        ChoiceList = choiceList;
        AnswerIndex = answerIndex;
    }

    public int getQuestion() {
        return Question;
    }

    public void setQuestion(int question) {
        Question = question;
    }

    public List<String> getChoiceList() {
        return ChoiceList;
    }

    public void setChoiceList(List<String> choiceList) {
        if (choiceList == null) {
            throw new IllegalArgumentException("Array cannot be null");
        }

        ChoiceList = choiceList;
    }

    public int getAnswerIndex() {
        return AnswerIndex;
    }

    public void setAnswerIndex(int answerIndex) {
        if (answerIndex < 0 || answerIndex >= ChoiceList.size()) {
            throw new IllegalArgumentException("Answer index is out of bound");
        }

        AnswerIndex = answerIndex;
    }

}
