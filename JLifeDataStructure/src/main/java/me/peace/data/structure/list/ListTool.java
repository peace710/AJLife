package me.peace.data.structure.list;

public class ListTool<T> {

    public void traversalNext(Node<T> node, Callback<T> callback,int size){
        if (size-- > 0) {
            if (node != null) {
                if (callback != null) {
                    callback.execute(node.value);
                }
                if (node.next != null) {
                    traversalNext(node.next, callback,size);
                }
            }
        }
    }

    public void traversalNext(DoubleNode<T> node, Callback<T> callback,int size){
        if (size-- > 0) {
            if (node != null) {
                if (callback != null) {
                    callback.execute(node.value);
                }
                if (node.next != null) {
                    traversalNext(node.next, callback,size);
                }
            }
        }
    }

    public void traversalPrev(DoubleNode<T> node, Callback<T> callback,int size){
        if (size-- > 0) {
            if (node != null) {
                if (callback != null) {
                    callback.execute(node.value);
                }
                if (node.prev!= null) {
                    traversalPrev(node.prev, callback,size);
                }
            }
        }
    }


    public void next(Node<T> node, Callback<T> callback){
        if (node != null) {
            if (callback != null){
                callback.execute(node.value);
            }
            if (node.next != null){
                next(node.next,callback);
            }
        }
    }

    public void next(DoubleNode<T> node, Callback<T> callback){
        if (node != null) {
            if (callback != null){
                callback.execute(node.value);
            }
            if (node.next != null){
                next(node.next,callback);
            }
        }
    }

    public void prev(DoubleNode<T> node, Callback<T> callback){
        if (node != null) {
            if (callback != null){
                callback.execute(node.value);
            }
            if (node.prev != null){
                prev(node.prev,callback);
            }
        }
    }

    public void delete(Node<T> node){
        if (node != null){
            node.next = null;
            node.value = null;
        }
    }

    public void insert(Node<T> node,Node<T> newNode){
        if (node != null && newNode != null){
            newNode.next = node.next;
            node.next = newNode;
        }
    }


    public void delete(DoubleNode<T> node){
        if (node != null){
            node.next = null;
            node.prev = null;
            node.value = null;
        }
    }

    public void insert(DoubleNode<T> node,DoubleNode<T> newNode){
        if (node != null && newNode != null){
            newNode.prev = node;
            newNode.next = node.next;
            node.next.prev = newNode;
            node.next = newNode;
        }
    }

    public Node<T> reverse(Node<T> node){
        if (node != null) {
            Node<T> n = node.next;
            if (n != null){
                node.next = null;
            }
            while (n != null) {
                Node<T> next = n.next;
                n.next = node;
                node = n;
                n = next;
            }
        }
        return node;
    }

    public DoubleNode<T> reverse(DoubleNode<T> node){
        if (node != null) {
            DoubleNode<T> n = node.next;
            if (n != null){
                node.next = null;
                node.prev = n;
            }
            while (n != null) {
                n.prev = n.next;
                n.next = node;
                node = n;
                n = n.prev;
            }
        }
        return node;
    }

    interface Callback<T>{
        void execute(T t);
    }
}
