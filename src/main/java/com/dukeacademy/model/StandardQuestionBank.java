package com.dukeacademy.model;

import java.util.Collection;

import com.dukeacademy.model.question.Question;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 * Wraps all data at the question-bank level
 * Duplicates are not allowed (by .isSameQuestion comparison)
 */
public class StandardQuestionBank implements QuestionBank {

    private final ObservableList<Question> questionList = FXCollections.observableArrayList();
    private final ObservableList<Question> unmodifiableQuestionList =
            FXCollections.unmodifiableObservableList(questionList);

    public StandardQuestionBank() {}

    public StandardQuestionBank(Collection<Question> questions) {
        this();
        this.setQuestions(questions);
    }

    public StandardQuestionBank(QuestionBank bank) {
        this();
        this.setQuestions(bank.getReadOnlyQuestionListObservable());
    }

    @Override
    public ObservableList<Question> getReadOnlyQuestionListObservable() {
        return this.unmodifiableQuestionList;
    }

    @Override
    public void addQuestion(Question question) {
        this.questionList.add(question);
    }

    @Override
    public void setQuestions(Collection<Question> questions) {
        this.questionList.setAll(questions);
    }

    @Override
    public void replaceQuestion(int id, Question question) {
        this.questionList.set(id - 1, question);
    }

    @Override
    public void removeQuestion(int id) {
        this.questionList.remove(id - 1);
    }

    @Override
    public void resetQuestions() {
        this.questionList.clear();
    }
}
