package com.zhy;

public class Main {
    public static void main(String[] args) {

        Object lock = new Object();
        for (int i = 0; i < 4; i++) {
            Crawler crawler = new Crawler(lock);
            crawler.start();
        }
    }
}
