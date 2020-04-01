package me.peace.data.structure.list;

public class Node<T> {
    public Node<T> next ;
    public T value;

    public Node(T value) {
        this.value = value;
    }
}
