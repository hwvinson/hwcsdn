package com.example.csdnbff.util;

public class TheadUtil implements Runnable{

    private static int size = 10;

    @Override
    public void run() {
        for (int i = 0; i < 10; i++) {
            size --;
            if (size>0) {
                System.out.println(Thread.currentThread().getName() + "剩余" + size);
            }
        }
    }


    public static void main(String[] args) {
        TheadUtil theadUtil = new TheadUtil();
        Thread thread = new Thread(theadUtil);
        thread.setName("A");
        Thread thread1 = new Thread(theadUtil);
        thread1.setName("B");
        thread.start();
        thread1.start();
    }
}
