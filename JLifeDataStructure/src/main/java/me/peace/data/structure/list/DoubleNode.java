package me.peace.data.structure.list;

public class DoubleNode<T>{
    public DoubleNode<T> prev;
    public DoubleNode<T> next;
    public T value;

    public DoubleNode(T value) {
        this.value = value;
    }
}
