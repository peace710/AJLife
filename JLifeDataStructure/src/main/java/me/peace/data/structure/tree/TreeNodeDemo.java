package me.peace.data.structure.tree;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Random;

public class TreeNodeDemo {
    public static void main(String[] args) {
        TreeNodeDemo demo = new TreeNodeDemo();
        demo.traversal();
        demo.queue2Tree();
        TreeNode<Integer> node = demo.queue2SortTree();
        demo.reverse(node);
        demo.findNode();
        demo.insertNode();
        demo.deleteNode();
    }


    /**
     *          A
     *        B    C
     *      D     E   F
     *    G  H     I
     *    
     *         A.left = B;
     *         A.right = C;
     *         B.left = D;
     *         C.left = E;
     *         C.right = F;
     *         D.left = G;
     *         D.right = H;
     *         E.right = I;
     *
     */
    public TreeNode<String> getTree(){
        TreeNode<String> node = new TreeNode<>("A");
        node.left = new TreeNode<>("B");
        node.left.left = new TreeNode<>("D");
        node.left.left.left = new TreeNode<>("G");
        node.left.left.right = new TreeNode<>("H");
        node.right = new TreeNode<>("C");
        node.right.left = new TreeNode<>("E");
        node.right.right = new TreeNode<>("F");
        node.right.left.right = new TreeNode<>("I");
        return node;
    }

    TreeNodeTool.Callback<String> callback = new TreeNodeTool.Callback<String>() {
        @Override
        public void execute(String s) {
            if (s != null) {
                System.out.print(s + " ");
            }
        }
    };

    TreeNodeTool.Callback<Integer> integerCallback = new TreeNodeTool.Callback<Integer>() {
        @Override
        public void execute(Integer s) {
            if (s != null) {
                System.out.print(s + " ");
            }
        }
    };

    private void traversalInfo(String traversal){
        System.out.println();
        System.out.println(traversal);
    }
    
    public void traversal(){
        /**
         * preorderTraversal
         * A B D G H C E I F
         * inorderTraversal
         * G D H B A E I C F
         * postorderTraversal
         * G H D B I E F C A
         */

        TreeNode<String> node = getTree();
        TreeNodeTool<String> tool = new TreeNodeTool<>();
        traversalInfo("preorderTraversal");
        tool.preorderTraversal(node,callback);
        traversalInfo("inorderTraversal");
        tool.inorderTraversal(node,callback);
        traversalInfo("postorderTraversal");
        tool.postorderTraversal(node,callback);
        traversalInfo("levelTraversal");
        tool.levelTraversal(node,callback);
    }

    public void queue2Tree(){
        Queue<Integer> queue = new LinkedList<>();
        for (int i = 0 ;i < 10 ;i++){
            queue.add(i);
        }
        TreeNodeTool<Integer> tool = new TreeNodeTool<>();
        TreeNode<Integer> node = tool.queue2BalancedBinaryTree(queue);
        traversalInfo("queue2Tree");
        tool.inorderTraversal(node,integerCallback);
    }

    private Queue<Integer> getRandomQueue(){
        Queue<Integer> queue = new LinkedList<>();
        for (int i = 0 ;i < 10 ;i++){
            Random random = new Random();
            int value = random.nextInt(100);
            while (queue.contains(value)){
                value = random.nextInt(100);
            }
            System.out.print(value + " ");
            queue.add(value);
        }
        System.out.println();
        return queue;
    }

    public TreeNode<Integer> queue2SortTree(){
        traversalInfo("queue2SortTree");
        Queue<Integer> queue = getRandomQueue();

        TreeNodeTool<Integer> tool = new TreeNodeTool<>();
        TreeNode<Integer> node = tool.queue2SortTree(queue);
        tool.inorderTraversal(node,integerCallback);
        return node;
    }

    public TreeNode<Integer> reverse(TreeNode<Integer> node){
        TreeNodeTool<Integer> tool = new TreeNodeTool<>();
        tool.reverse(node);
        traversalInfo("reverse");
        tool.inorderTraversal(node,integerCallback);
        return node;
    }


    /***
     *            5
     *       3         7
     *    1     4   6     8
     * 0     2              9
     */
    
    private TreeNode<Integer> getIntegerTreeNode(){
        TreeNode<Integer> node = new TreeNode<>(5);
        node.left = new TreeNode<>(3);
        node.left.left = new TreeNode<>(1);
        node.left.right = new TreeNode<>(4);
        node.left.left.left = new TreeNode<>(0);
        node.left.left.right = new TreeNode<>(2);
        node.right = new TreeNode<>(7);
        node.right.left = new TreeNode<>(6);
        node.right.right = new TreeNode<>(8);
        node.right.right.right = new TreeNode<>(9);
        return node;
    }

    public void findNode(){
        TreeNode<Integer> node = getIntegerTreeNode();
        TreeNodeTool<Integer> tool = new TreeNodeTool<>();
        TreeNode<Integer> findNode;

        traversalInfo("queue2Tree 1");
        findNode = tool.find(node,1);
        System.out.print("find 1 -> is  " + (findNode == null?null:findNode.value));
        traversalInfo("queue2Tree 6");
        findNode = tool.find(node,6);
        System.out.print("find 6 -> is  " + (findNode == null?null:findNode.value));
        traversalInfo("queue2Tree 10");
        findNode = tool.find(node,10);
        System.out.print("find 10 -> is  " + (findNode == null?null:findNode.value));
    }

    /***
     *            15
     *       13        27
     *    6     14  16    38
     * 4     8                39
     *     7
     */

    private TreeNode<Integer> getIntegerTreeNode2(){
        TreeNode<Integer> node = new TreeNode<>(15);
        node.left = new TreeNode<>(13);
        node.left.left = new TreeNode<>(6);
        node.left.right = new TreeNode<>(14);
        node.left.left.left = new TreeNode<>(4);
        node.left.left.right = new TreeNode<>(8);
        node.left.left.right.left = new TreeNode<>(7);
        node.right = new TreeNode<>(27);
        node.right.left = new TreeNode<>(16);
        node.right.right = new TreeNode<>(38);
        node.right.right.right = new TreeNode<>(39);
        return node;
    }

    public void insertNode(){
        TreeNode<Integer> node = getIntegerTreeNode2();
        TreeNodeTool<Integer> tool = new TreeNodeTool<>();
        tool.insert(node,28);
        traversalInfo("insertNode");
        tool.inorderTraversal(node,integerCallback);
    }

    public void deleteNode(){
        TreeNode<Integer> node = getIntegerTreeNode2();
        TreeNodeTool<Integer> tool = new TreeNodeTool<>();
        TreeNode<Integer> n = tool.delete(node,15);
        traversalInfo("deleteNode");
        tool.inorderTraversal(n,integerCallback);
    }
}
