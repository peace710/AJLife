package me.peace.thread.exchanger;


import java.util.concurrent.Exchanger;
import java.util.concurrent.TimeUnit;

public class ExchangerDemo {
    public static void main(String[] args) throws InterruptedException {
        Exchanger<Integer> exchanger = new Exchanger<Integer>();
        Thread producer = new Thread(new Producer( exchanger));
        Thread consumer = new Thread(new Consumer(exchanger));
        producer.start();
        consumer.start();
        TimeUnit.SECONDS.sleep(7);
        System.exit(-1);
    }
}
