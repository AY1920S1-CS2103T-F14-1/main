package com.dukeacademy.logic.problemstatement;

import com.dukeacademy.observable.Observable;

public interface ProblemStatementLogic {
    /**
     * Returns an observable that gets updated whenever a new submission is received by the logic instance. The
     * listeners of the observable can then choose to process the new result accordingly.
     * @return An observable of the latest problem statement.
     */
    public Observable<String> getProblemStatementObservable();

    public void setProblemStatementObservable(String problemStatement);
}
