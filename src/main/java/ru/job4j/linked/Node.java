package ru.job4j.linked;

public final class Node<T> {
    private final Node<T> next;
    private final T value;

    public Node(final T value) {
        this.value = value;
        this.next = null;
    }

    public Node(final Node<T> next, final T value) {
        this.next = new Node<>(next.getNext(), next.getValue());
        this.value = value;
    }

    public Node<T> getNext() {
        return new Node<>(next.getNext(), next.getValue());
    }

    public T getValue() {
        return value;
    }
}