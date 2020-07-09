package demo;

public enum ActionAll implements ActionListener{
    OPEN{
        @Override
        public void doAction() {
            System.out.println("open");
        }
    },
    CLOSE{
        @Override
        public void doAction() {
            System.out.println("close");
        }
    },
    MAX{
        @Override
        public void doAction() {
            System.out.println("max");
        }
    },
    MIN{
        @Override
        public void doAction() {
            System.out.println("min");
        }
    }
}
