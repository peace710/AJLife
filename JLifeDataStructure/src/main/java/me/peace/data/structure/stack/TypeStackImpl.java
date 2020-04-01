package me.peace.data.structure.stack;

import java.util.Stack;

import me.peace.data.structure.LogUtils;

public class TypeStackImpl<T> implements TypeStack<T> {
    private static final String TAG = TypeStackImpl.class.getSimpleName();

    private Stack<T> stack;
    private int index = -1;

    public TypeStackImpl(Stack<T> stack) {
        if (stack == null){
            throw new NullPointerException();
        }
        this.stack = stack;
    }

    @Override
    public void reverse() {
        if (!stack.isEmpty()){
            T t = last(stack);
            reverse();
            stack.push(t);
        }
    }

    @Override
    public boolean hasNext() {
        if (!stack.isEmpty() && index < stack.size() - 1){
            return true;
        }
        return false;
    }

    @Override
    public boolean moveToFirst() {
        index = -1;
        return true;
    }

    @Override
    public T next() {
        return stack.elementAt(++index);
    }

    private T last(Stack<T> stack){
        T item = stack.pop();
        if (stack.isEmpty()){
            return item;
        }
        T last = last(stack);
        stack.push(item);
        return last;
    }


    public static void main(String[] args) {
        Stack<Integer> stack = new Stack<>();
        stack.push(1);
        stack.push(2);
        stack.push(3);
        stack.push(4);
        stack.push(5);
        stack.push(6);


        TypeStackImpl<Integer> stackImpl = new TypeStackImpl<>(stack);
        list(stackImpl);
        stackImpl.reverse();
        list(stackImpl);

    }

    private static void list(TypeStackImpl<Integer> stackImpl){
        stackImpl.moveToFirst();
        while (stackImpl.hasNext()){
            Integer integer = stackImpl.next();
            LogUtils.i(TAG,"stack -> " + integer);
        }
    }
}

interface TypeStack<T>{
    void reverse();

    boolean hasNext();

    boolean moveToFirst();

    T next();
}
