package com.example.gestionatennis.services;

import java.util.Set;

public interface ClubAuthService {
    boolean authUser(String username, String password);

    Set<String> getRoles(String username);

    String encryptPassword(String password);

    boolean verifyPassword(String password, String hashedPassword);
}
