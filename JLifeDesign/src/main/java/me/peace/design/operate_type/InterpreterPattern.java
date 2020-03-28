package me.peace.design.operate_type;

import me.peace.design.LogUtils;

/**
 * 解释器模式（Interpreter Pattern）提供了评估语言的语法或表达式的方式，它属于行为型模式。
 * 这种模式实现了一个表达式接口，该接口解释一个特定的上下文。这种模式被用在 SQL 解析、符号处理引擎等。
 */
public class InterpreterPattern {
    private static final String TAG = InterpreterPattern.class.getSimpleName();

    public static void main(String[] args) {
        UnaryExpression expressionA = new UnaryExpression(10);
        UnaryExpression expressionB = new UnaryExpression(2);
        BinaryExpression binaryExpression = new BinaryExpression(expressionA,expressionB);
        LogUtils.i(TAG,"10 + 2 = " + binaryExpression.interpreter(BinaryExpression.OPERATOR_ADD));
        LogUtils.i(TAG,"10 - 2 = " + binaryExpression.interpreter(BinaryExpression.OPERATOR_MINUS));
        LogUtils.i(TAG,"10 * 2 = " + binaryExpression.interpreter(BinaryExpression.OPERATOR_TIMES));
        LogUtils.i(TAG,"10 / 2 = " + binaryExpression.interpreter(BinaryExpression.OPERATOR_DIVIDE));

        BoolExpression boolExpression = new BoolExpression(false);
        TernaryExpression ternaryExpression = new TernaryExpression(boolExpression,expressionA,
            expressionB);
        LogUtils.i(TAG,
            "boolExpression ? expressionA : expressionB ==> " + ternaryExpression.interpreter());
    }

    interface Interpreter<T>{
        T interpreter(String...str);
    }

    static class UnaryExpression implements Interpreter<Integer>{
        private Integer value;

        public UnaryExpression(Integer value) {
            this.value = value;
        }

        @Override
        public Integer interpreter(String... str) {
            return value;
        }
    }

    static class BoolExpression implements Interpreter<Boolean>{
        private Boolean value;

        public BoolExpression(Boolean value) {
            this.value = value;
        }

        @Override
        public Boolean interpreter(String... str) {
            return value;
        }
    }

    static class BinaryExpression implements Interpreter<Integer>{
        public static final String OPERATOR_ADD = "1";
        public static final String OPERATOR_MINUS = "2";
        public static final String OPERATOR_TIMES = "3";
        public static final String OPERATOR_DIVIDE = "4";

        UnaryExpression expressionA;
        UnaryExpression expressionB;

        public BinaryExpression(UnaryExpression expressionA, UnaryExpression expressionB) {
            this.expressionA = expressionA;
            this.expressionB = expressionB;
        }

        @Override
        public Integer interpreter(String... str) {
            if (str != null && str.length > 0){
                String operator = str[0];
                switch (operator){
                    case OPERATOR_ADD:
                        return expressionA.interpreter() + expressionB.interpreter();
                    case OPERATOR_MINUS:
                        return expressionA.interpreter() - expressionB.interpreter();
                    case OPERATOR_TIMES:
                        return expressionA.interpreter() * expressionB.interpreter();
                    case OPERATOR_DIVIDE:
                        return expressionA.interpreter() / expressionB.interpreter();
                }
            }
            return 0;
        }
    }

    static class TernaryExpression implements Interpreter<Integer>{
        BoolExpression expression;
        UnaryExpression expressionA;
        UnaryExpression expressionB;

        public TernaryExpression(BoolExpression expression, UnaryExpression expressionA, UnaryExpression expressionB) {
            this.expression = expression;
            this.expressionA = expressionA;
            this.expressionB = expressionB;
        }

        @Override
        public Integer interpreter(String... str) {
            return expression.interpreter() ? expressionA.interpreter() : expressionB.interpreter();
        }
    }
}
