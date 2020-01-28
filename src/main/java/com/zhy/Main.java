package com.zhy;

public class Main {
    public static void main(String[] args) {

        Crawler thread1 = new Crawler();
        Crawler thread2 = new Crawler();
        Crawler thread3 = new Crawler();
        Crawler thread4 = new Crawler();

        thread1.start();
        thread2.start();
        thread3.start();
        thread4.start();
    }
}
