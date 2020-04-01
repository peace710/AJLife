package me.peace.data.structure.list;

public class DoubleNodeDemo {
    public static void main(String[] args) {
        singleList();
        circleList();
        deleteNode();
        insertNode();
        reverse();
    }

    private static void singleList(){
        DoubleNode<String> A = new DoubleNode<>("A");
        DoubleNode<String> B = new DoubleNode<>("B");
        DoubleNode<String> C = new DoubleNode<>("C");
        DoubleNode<String> D = new DoubleNode<>("D");
        DoubleNode<String> E = new DoubleNode<>("E");
        DoubleNode<String> F = new DoubleNode<>("F");
        DoubleNode<String> G = new DoubleNode<>("G");
        DoubleNode<String> H = new DoubleNode<>("H");

        A.next = B;
        B.prev = A;
        B.next = C;
        C.prev = B;
        C.next = D;
        D.prev = C;
        D.next = E;
        E.prev = D;
        E.next = F;
        F.prev = E;
        F.next = G;
        G.prev = F;
        G.next = H;
        H.prev = G;

        ListTool.Callback<String> callback = new ListTool.Callback<String>(){
            @Override
            public void execute(String s) {
                if (s != null) {
                    System.out.print(s + " ");
                }
            }
        };

        ListTool<String> tool = new ListTool<>();
        nodeTip("doubleNodeList next");
        tool.next(A,callback);

        nodeTip("doubleNodeList prev");
        tool.prev(H,callback);
    }

    private static void circleList(){
        DoubleNode<String> A = new DoubleNode<>("A");
        DoubleNode<String> B = new DoubleNode<>("B");
        DoubleNode<String> C = new DoubleNode<>("C");
        DoubleNode<String> D = new DoubleNode<>("D");
        DoubleNode<String> E = new DoubleNode<>("E");
        DoubleNode<String> F = new DoubleNode<>("F");
        DoubleNode<String> G = new DoubleNode<>("G");
        DoubleNode<String> H = new DoubleNode<>("H");

        A.prev = H;
        A.next = B;
        B.prev = A;
        B.next = C;
        C.prev = B;
        C.next = D;
        D.prev = C;
        D.next = E;
        E.prev = D;
        E.next = F;
        F.prev = E;
        F.next = G;
        G.prev = F;
        G.next = H;
        H.prev = G;
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

        nodeTip("circleList prev");
        tool.traversalPrev(H,callback,16);
    }

    private static void deleteNode(){
        DoubleNode<String> A = new DoubleNode<>("A");
        DoubleNode<String> B = new DoubleNode<>("B");
        DoubleNode<String> C = new DoubleNode<>("C");
        DoubleNode<String> D = new DoubleNode<>("D");
        DoubleNode<String> E = new DoubleNode<>("E");
        DoubleNode<String> F = new DoubleNode<>("F");
        DoubleNode<String> G = new DoubleNode<>("G");
        DoubleNode<String> H = new DoubleNode<>("H");

        A.prev = H;
        A.next = B;
        B.prev = A;
        B.next = C;
        C.prev = B;
        C.next = D;
        D.prev = C;
        D.next = E;
        E.prev = D;
        E.next = F;
        F.prev = E;
        F.next = G;
        G.prev = F;
        G.next = H;
        H.prev = G;
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
        tool.delete(E);

        nodeTip("deleteNode circleList next");
        tool.traversalNext(A,callback,16);

        nodeTip("deleteNode circleList prev");
        tool.traversalPrev(H,callback,16);
    }

    private static void insertNode(){
        DoubleNode<String> A = new DoubleNode<>("A");
        DoubleNode<String> B = new DoubleNode<>("B");
        DoubleNode<String> C = new DoubleNode<>("C");
        DoubleNode<String> D = new DoubleNode<>("D");
        DoubleNode<String> E = new DoubleNode<>("E");
        DoubleNode<String> F = new DoubleNode<>("F");
        DoubleNode<String> G = new DoubleNode<>("G");
        DoubleNode<String> H = new DoubleNode<>("H");

        A.prev = H;
        A.next = B;
        B.prev = A;
        B.next = C;
        C.prev = B;
        C.next = D;
        D.prev = C;
        D.next = E;
        E.prev = D;
        E.next = F;
        F.prev = E;
        F.next = G;
        G.prev = F;
        G.next = H;
        H.prev = G;
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
        tool.insert(E,new DoubleNode<>("X"));

        nodeTip("insertNode circleList next");
        tool.traversalNext(A,callback,16);

        nodeTip("insertNode circleList prev");
        tool.traversalPrev(H,callback,16);
    }


    private static void reverse(){
        DoubleNode<String> A = new DoubleNode<>("A");
        DoubleNode<String> B = new DoubleNode<>("B");
        DoubleNode<String> C = new DoubleNode<>("C");
        DoubleNode<String> D = new DoubleNode<>("D");
        DoubleNode<String> E = new DoubleNode<>("E");
        DoubleNode<String> F = new DoubleNode<>("F");
        DoubleNode<String> G = new DoubleNode<>("G");
        DoubleNode<String> H = new DoubleNode<>("H");

        A.next = B;
        B.prev = A;
        B.next = C;
        C.prev = B;
        C.next = D;
        D.prev = C;
        D.next = E;
        E.prev = D;
        E.next = F;
        F.prev = E;
        F.next = G;
        G.prev = F;
        G.next = H;
        H.prev = G;

        ListTool.Callback<String> callback = new ListTool.Callback<String>(){
            @Override
            public void execute(String s) {
                if (s != null) {
                    System.out.print(s + " ");
                }
            }
        };

        DoubleNode<String> header = A;
        ListTool<String> tool = new ListTool<>();
        header = tool.reverse(header);
        nodeTip("reverse");
        tool.next(header,callback);

        nodeTip("reverse prev");
        tool.prev(A,callback);

    }

    private static void nodeTip(String tip){
        System.out.println();
        System.out.println(tip);
    }
}
