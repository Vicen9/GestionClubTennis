package com.example.gestionatennis.model.DAO;

import com.example.gestionatennis.model.Socio;

public interface SociosDAO extends GenericDAO<Socio,Integer> {
    public Socio buscaByDNI(String dni);
    public Socio buscaByEmail(String correo);


}
