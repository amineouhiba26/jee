package org.example;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class TestJpaInitialization {
    public static void main(String[] args) {
        try {
            EntityManagerFactory emf = Persistence.createEntityManagerFactory("catalogue");
            if (emf != null) {
                System.out.println("EntityManagerFactory successfully created!");
            } else {
                System.out.println("EntityManagerFactory creation failed.");
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Error: " + e.getMessage());
        }
    }
}
