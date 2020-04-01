package me.peace.data.structure.tree;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

import me.peace.data.structure.LogUtils;

public class TreeNodeTool<T extends Comparable<T>> {
    //先序
    public void preorderTraversal(TreeNode<T> treeNode,Callback<T> callback){
        if (treeNode != null) {
            if (callback != null){
                callback.execute(treeNode.value);
            }
            preorderTraversal(treeNode.left,callback);
            preorderTraversal(treeNode.right,callback);
        }
    }

    //中序
    public void inorderTraversal(TreeNode<T> treeNode, Callback<T> callback){
        if (treeNode != null) {
            inorderTraversal(treeNode.left,callback);
            if (callback != null){
                callback.execute(treeNode.value);
            }
            inorderTraversal(treeNode.right,callback);
        }
    }

    //后序
    public void postorderTraversal(TreeNode<T> treeNode, Callback<T> callback){
        if (treeNode != null) {
            postorderTraversal(treeNode.left,callback);
            postorderTraversal(treeNode.right,callback);
            if (callback != null){
                callback.execute(treeNode.value);
            }
        }
    }

    //层次
    public void levelTraversal(TreeNode<T> treeNode, Callback<T> callback){
        if (treeNode != null){
            Queue<TreeNode<T>> queue = new LinkedList<>();
            queue.add(treeNode);
            while (!queue.isEmpty()) {
                treeNode = queue.poll();
                if (callback != null){
                    callback.execute(treeNode.value);
                }
                if (treeNode.left != null) {
                    queue.add(treeNode.left);
                }
                if (treeNode.right != null) {
                    queue.add(treeNode.right);
                }
            }
        }
    }

    //集合转二叉树
    public TreeNode<T> list2Tree(List<T> list, int index){
        if (list == null || list.isEmpty() || index < 0 || index >= list.size()){
            return null;
        }
        TreeNode<T> treeNode = new TreeNode<>(list.get(index));
        treeNode.left = list2Tree(list,2 * index + 1);
        treeNode.right = list2Tree(list,2 * index + 2);
        return treeNode;
    }

    //二叉树转集合
    public List<T> tree2List(List<T> list, TreeNode<T> treeNode){
        if (treeNode != null){
            if (list == null){
                list = new ArrayList<>();
            }
            Queue<TreeNode<T>> queue = new LinkedList<>();
            queue.add(treeNode);
            while (!queue.isEmpty()) {
                treeNode = queue.poll();
                list.add(treeNode.value);
                if (treeNode.left != null) {
                    queue.add(treeNode.left);
                }
                if (treeNode.right != null) {
                    queue.add(treeNode.right);
                }
            }
        }
        return list;
    }

    public TreeNode<T> queue2BalancedBinaryTree(Queue<T> queue){
        if (queue != null && !queue.isEmpty()){
            return balancedBinaryTree(queue,queue.size());
        }
        return null;
    }

    private TreeNode<T> balancedBinaryTree(Queue<T> queue, int n){
        if (n == 0){
            return null;
        }
        TreeNode<T> node = new TreeNode<>();
        node.left = balancedBinaryTree(queue,n / 2);
        node.value = queue.poll();
        node.right = balancedBinaryTree(queue,n - n / 2 - 1);
        return node;
    }

    public TreeNode<T> queue2SortTree(Queue<T> queue){
        if (queue != null && !queue.isEmpty()){
            TreeNode<T> node = new TreeNode<>(queue.poll());
            if (!queue.isEmpty()){
                queue2SortTree(node,queue);
            }
            return node;
        }
        return null;
    }

    public void reverse(TreeNode<T> node){
        if (node != null){
            if (node.left != null){
                reverse(node.left);
            }
            if (node.right != null){
                reverse(node.right);
            }
            TreeNode<T> n = node.left;
            node.left = node.right;
            node.right = n;
        }
    }

    private void queue2SortTree(TreeNode<T> node, Queue<T> queue){
        while (!queue.isEmpty()) {
            insert(node, queue.poll());
        }
    }


    public TreeNode<T> find(TreeNode<T> node, T value){
        if (node != null){
            if (value.compareTo(node.value) == 0){
                return node;
            }else if (value.compareTo(node.value) < 0){
                if (node.left != null){
                    node.left.parent = node;
                }
                return find(node.left,value);
            }else {
                if (node.right != null){
                    node.right.parent = node;
                }
                return find(node.right,value);
            }
        }
        return null;
    }

    public int insert(TreeNode<T> node, T value){
        if (node != null){
            if (value.compareTo(node.value) == 0){
                return -1;
            }else if (value.compareTo(node.value) < 0){
                if (node.left == null){
                    node.left = new TreeNode<>(value);
                    return 0;
                }
                return insert(node.left,value);
            }else {
                if (node.right == null){
                    node.right = new TreeNode<>(value);
                    return 0;
                }
                return insert(node.right,value);
            }
        }
        return -1;
    }

    public TreeNode<T> delete(TreeNode<T> node,T value){
        if (node != null) {
            TreeNode<T> findNode = find(node,value);
            if (findNode != null){
                TreeNode<T> parent = findNode.parent;
                if (parent != null) {
                    return parent(node, parent, findNode);
                }else{
                    return noParent(node);
                }
            }
        }
        return node;
    }

    private TreeNode<T> parent(TreeNode<T> node,TreeNode<T> parent,TreeNode<T> findNode){
        if (findNode.left != null && findNode.right != null) {
            TreeNode<T> n = findReplaceNode(findNode);
            LogUtils.i("","n -> value = " + n.value);
            TreeNode<T> r = n.right;
            n.left = findNode.left;
            if (n != findNode.right) {
                n.right = findNode.right;
                n.parent.left = r;
            }
            if (parent.left == findNode){
                parent.left = n;
            }else{
                parent.right = n;
            }
            n.parent = parent;
            return node;
        } else if (findNode.left != null) {
            if (parent.left == findNode){
                parent.left = findNode.left;
            }else{
                parent.right = findNode.left;
            }
            return node;
        } else if (findNode.right != null) {
            if (parent.left == findNode){
                parent.left = findNode.right;
            }else{
                parent.right = findNode.right;
            }
            return node;
        }else{
            if (parent.left == findNode){
                parent.left = null;
            }else{
                parent.right = null;
            }
        }
        return node;
    }


    private TreeNode<T> noParent(TreeNode<T> node){
        if (node.left != null && node.right != null) {
            TreeNode<T> n = findReplaceNode(node);
            TreeNode<T> r = n.right;
            if (n != node.right) {
                n.right = node.right;
                n.parent.left = r;
            }
            n.left = node.left;
            n.parent = null;
            return n;
        } else if (node.left != null) {
            return node.left;
        } else if (node.right != null) {
            return node.right;
        }
        return null;
    }

    private TreeNode<T> findReplaceNode(TreeNode<T> node){
        TreeNode<T> n = node.right;
        n.parent = node;
        while (n.left != null) {
            n.left.parent = n;
            n = n.left;
        }
        return n;
    }


    interface Callback<T>{
        void execute(T t);
    }
}
