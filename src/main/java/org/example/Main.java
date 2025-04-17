package org.example;

import java.io.File;

public class Main {
    public static void main(String[] args) {
        System.out.println("Hello world!");

        File persistenceFile = new File("src/main/resources/META-INF/persistence.xml");
        if (persistenceFile.exists()) {
            System.out.println("persistence.xml found!");
        } else {
            System.out.println("persistence.xml NOT found!");
        }

    }
}
