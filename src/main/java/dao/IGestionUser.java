package dao;

import entities.Produit;
import entities.User;

import java.util.List;

public interface IGestionUser {

    void addUser(User u);
    List<User> getAllUsers();
    User getUser(int id);
    void updateUser(User u);
    void deleteUser(int id);
    boolean isAdmin(User u);
    void makeAdmin(int id) ;

    }
