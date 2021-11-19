package ru.job4j.linked;

public class Node<T> {
    private final Node<T> next;
    private final T value;

    public Node(final T value) {
        this.value = value;
        this.next = null;
    }

    public Node(final Node<T> next, final T value) {
        this.next = next;
        this.value = value;
    }

    public Node<T> copyValue(T value) {
        return new Node<>(value);
    }

    public Node<T> copyNext(Node<T> next) {
        return new Node<>(next, value);
    }

    public Node<T> getNext() {
        return next;
    }

    public T getValue() {
        return value;
    }
}