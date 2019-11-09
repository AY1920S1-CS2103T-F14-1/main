package com.dukeacademy.data;

public class Pair<T, U> {
    private final T head;
    private final U tail;

    public Pair(T head, U tail) {
        this.head = head;
        this.tail = tail;
    }

    public T getHead() {
        return this.head;
    }

    public U getTail() {
        return this.tail;
    }
}
