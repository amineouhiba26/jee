package org.example;

import dao.gestionUserGpa;
import entities.Role;
import entities.User;

import java.io.File;

public class Main {
    public static void main(String[] args) {

        gestionUserGpa g = new gestionUserGpa();
        gestionUserGpa g2 = new gestionUserGpa();
        User u = g2.getUser(1) ;
        System.out.println(g2.isAdmin(u));
    }
}
