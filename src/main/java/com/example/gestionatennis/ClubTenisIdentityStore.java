package com.example.gestionatennis;


import com.example.gestionatennis.qualifiers.DAOJPA;
import com.example.gestionatennis.controller.LoginController;
import com.example.gestionatennis.model.DAO.SociosDAO;
import com.example.gestionatennis.qualifiers.DAOJPA;



import jakarta.inject.Inject;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.security.enterprise.credential.UsernamePasswordCredential;
import jakarta.security.enterprise.identitystore.CredentialValidationResult;
import jakarta.security.enterprise.identitystore.IdentityStore;

import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;

import static jakarta.security.enterprise.identitystore.CredentialValidationResult.INVALID_RESULT;

@ApplicationScoped
public class ClubTenisIdentityStore implements IdentityStore {
    private static final Logger logger = Logger.getLogger(ClubTenisIdentityStore.class.getName());
    private Map<String,String> credenciales;

    @Inject
    @DAOJPA
    private SociosDAO sociosDAO;

    public ClubTenisIdentityStore() {
        credenciales = new HashMap<>();
        credenciales.put("socio1", "clave1");
        credenciales.put("socio2", "clave2");
    }

    public CredentialValidationResult validate (UsernamePasswordCredential usernamePasswordCredential){
        String username = usernamePasswordCredential.getCaller();
        String password = usernamePasswordCredential.getPasswordAsString();

        String pass = sociosDAO.buscaByEmail(username).getPassword();//  getPass(username);
        String rol = sociosDAO.buscaByEmail(username).getRol();// getRol(username);
        if(pass.length() != 0){
            if (password.equals(pass)) {
                String r[] = new String[1];
                r[0] = rol;
                Set<String> ro = new HashSet<>(Arrays.asList(r));
                return new CredentialValidationResult(username, ro);
            }
        }

        return INVALID_RESULT; //Autenticación inválida



    }

}
