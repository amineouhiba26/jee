package dao;

import entities.Produit;
import entities.User;

import java.util.List;

public interface IGestionUser {

    void addUser(User p);
    List<User> getAllUsers();
    User getUser(int id);
    void updateUser(User p);
    void deleteUser(int id);
}
