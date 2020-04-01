package me.peace.data.structure.tree;

public class TreeNode<T extends Comparable<T>> {
    public TreeNode<T> left;
    public TreeNode<T> right;
    public TreeNode<T> parent;

    public T value;

    public TreeNode() {
    }

    public TreeNode(T value) {
        this.value = value;
    }

}
