package me.peace.design.operate_type;

import java.util.LinkedList;
import java.util.Queue;

import me.peace.design.LogUtils;


/**
 * 命令模式
 *
 * 命令模式 (Command Pattern)：将一个请求封装为一个对象，从而让我们可用不同的请求对客户进行参数化；对请求排队或者
 * 记录请求日志，以及支持可撤销的操作。命令模式是一种对象行为型模式，其别名为动作 (Action) 模式或
 * 事务 (Transaction) 模式
 *
 */
public class CommandPattern {
    public static void main(String[] args) {
        CommandQueue queue = new CommandQueue();
        Invoker invoker = new Invoker(queue);
        queue.addCommand(new PrintCommand("Give me money"));
        invoker.execute();
        invoker.undo();

        queue.addCommand(new PrintCommand("Hello"));
        queue.addCommand(new PrintCommand("I'm James"));
        queue.addCommand(new PrintCommand("What's your name?"));
        queue.addCommand(new DeleteCommand("How to be rich"));
        invoker.execute();
        invoker.undo();

    }

    interface Command{
        void execute();

        void undo();
    }

    static class PrintCommand implements Command{
        private static final String TAG = PrintCommand.class.getSimpleName();

        private String value;

        public PrintCommand(String value) {
            this.value = value;
        }

        @Override
        public void execute() {
            LogUtils.i(TAG,"print " + value + " on the screen");
        }

        @Override
        public void undo() {
            if (value != null && !value.equalsIgnoreCase("")){
                LogUtils.i(TAG,"clear " + value + " from the screen");
            }
        }
    }

    static class DeleteCommand implements Command{
        private static final String TAG = DeleteCommand.class.getSimpleName();

        private String value;

        public DeleteCommand(String value) {
            this.value = value;
        }

        @Override
        public void execute() {
            LogUtils.i(TAG,"delete file " + value);
        }

        @Override
        public void undo() {
            if (value != null){
                LogUtils.i(TAG,"restore file " + value);
            }
        }
    }

    static class Invoker implements Command{
        CommandQueue queue;

        public Invoker(CommandQueue queue) {
            this.queue = queue;
        }

        @Override
        public void execute() {
            if (queue != null){
                queue.execute();
            }
        }

        @Override
        public void undo() {
            if (queue != null){
                queue.undo();
            }
        }
    }

    static class CommandQueue implements Command{
        Queue<Command> queue = new LinkedList<>();

        Command command;

        public void addCommand(Command command){
            queue.add(command);
        }

        public void removeCommand(Command command){
            queue.remove(command);
        }

        @Override
        public void execute() {
            while (!queue.isEmpty()){
                this.command = queue.poll();
                this.command.execute();
            }
        }

        @Override
        public void undo() {
            if (command != null){
                command.undo();
                command = null;
            }
        }
    }
}
