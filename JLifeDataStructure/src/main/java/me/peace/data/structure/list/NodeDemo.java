package me.peace.data.structure.list;

public class NodeDemo {
    public static void main(String[] args) {
        singleList();
        circleList();
        deleteNode();
        insertNode();
        reverse();
    }

    private static void singleList(){
        Node<String> A = new Node<>("A");
        Node<String> B = new Node<>("B");
        Node<String> C = new Node<>("C");
        Node<String> D = new Node<>("D");
        Node<String> E = new Node<>("E");
        Node<String> F = new Node<>("F");
        Node<String> G = new Node<>("G");
        Node<String> H = new Node<>("H");

        A.next = B;
        B.next = C;
        C.next = D;
        D.next = E;
        E.next = F;
        F.next = G;
        G.next = H;

        ListTool.Callback<String> callback = new ListTool.Callback<String>(){
            @Override
            public void execute(String s) {
                if (s != null) {
                    System.out.print(s + " ");
                }
            }
        };

        ListTool<String> tool = new ListTool<>();
        nodeTip("nodeList next");
        tool.next(A,callback);
    }

    private static void circleList(){
        Node<String> A = new Node<>("A");
        Node<String> B = new Node<>("B");
        Node<String> C = new Node<>("C");
        Node<String> D = new Node<>("D");
        Node<String> E = new Node<>("E");
        Node<String> F = new Node<>("F");
        Node<String> G = new Node<>("G");
        Node<String> H = new Node<>("H");

        A.next = B;
        B.next = C;
        C.next = D;
        D.next = E;
        E.next = F;
        F.next = G;
        G.next = H;
        H.next = A;

        ListTool.Callback<String> callback = new ListTool.Callback<String>(){
            @Override
            public void execute(String s) {
                if (s != null) {
                    System.out.print(s + " ");
                }
            }
        };

        ListTool<String> tool = new ListTool<>();
        nodeTip("circleList next");
        tool.traversalNext(A,callback,16);
    }

    private static void deleteNode(){
        Node<String> A = new Node<>("A");
        Node<String> B = new Node<>("B");
        Node<String> C = new Node<>("C");
        Node<String> D = new Node<>("D");
        Node<String> E = new Node<>("E");
        Node<String> F = new Node<>("F");
        Node<String> G = new Node<>("G");
        Node<String> H = new Node<>("H");

        A.next = B;
        B.next = C;
        C.next = D;
        D.next = E;
        E.next = F;
        F.next = G;
        G.next = H;
        H.next = A;

        ListTool.Callback<String> callback = new ListTool.Callback<String>(){
            @Override
            public void execute(String s) {
                if (s != null) {
                    System.out.print(s + " ");
                }
            }
        };

        ListTool<String> tool = new ListTool<>();
        tool.delete(F);
        nodeTip("deleteNode");
        tool.next(A,callback);
    }


    private static void insertNode(){
        Node<String> A = new Node<>("A");
        Node<String> B = new Node<>("B");
        Node<String> C = new Node<>("C");
        Node<String> D = new Node<>("D");
        Node<String> E = new Node<>("E");
        Node<String> F = new Node<>("F");
        Node<String> G = new Node<>("G");
        Node<String> H = new Node<>("H");

        A.next = B;
        B.next = C;
        C.next = D;
        D.next = E;
        E.next = F;
        F.next = G;
        G.next = H;

        ListTool.Callback<String> callback = new ListTool.Callback<String>(){
            @Override
            public void execute(String s) {
                if (s != null) {
                    System.out.print(s + " ");
                }
            }
        };

        ListTool<String> tool = new ListTool<>();
        tool.insert(D,new Node<>("X"));
        nodeTip("insertNode");
        tool.next(A,callback);
    }

    private static void reverse(){
        Node<String> A = new Node<>("A");
        Node<String> B = new Node<>("B");
        Node<String> C = new Node<>("C");
        Node<String> D = new Node<>("D");
        Node<String> E = new Node<>("E");
        Node<String> F = new Node<>("F");
        Node<String> G = new Node<>("G");
        Node<String> H = new Node<>("H");

        A.next = B;
        B.next = C;
        C.next = D;
        D.next = E;
        E.next = F;
        F.next = G;
        G.next = H;

        ListTool.Callback<String> callback = new ListTool.Callback<String>(){
            @Override
            public void execute(String s) {
                if (s != null) {
                    System.out.print(s + " ");
                }
            }
        };

        Node<String> header = A;

        ListTool<String> tool = new ListTool<>();
        header = tool.reverse(header);
        nodeTip("reverse");
        tool.next(header,callback);
    }

    private static void nodeTip(String tip){
        System.out.println();
        System.out.println(tip);
    }
}
