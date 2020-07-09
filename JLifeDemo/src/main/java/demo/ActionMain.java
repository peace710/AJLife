package demo;

import static demo.ActionAll.CLOSE;
import static demo.ActionAll.OPEN;

public class ActionMain {
    public static void main(String[] args) {
        ActionAll actionAll = OPEN;
        actionAll.doAction();
        actionAll = CLOSE;
        actionAll.doAction();
    }
}
