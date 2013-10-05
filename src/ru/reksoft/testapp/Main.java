package ru.reksoft.testapp;

import java.io.IOException;

public class Main {

    public static void main(String[] args) {
        try {
            new UI().showMainMenu();
        } catch (IOException e) {
            System.out.println("IO exception.");
        }
    }
}
