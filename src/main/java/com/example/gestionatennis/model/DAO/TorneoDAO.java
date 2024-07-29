package com.example.gestionatennis.model.DAO;

import com.example.gestionatennis.model.Torneo;

public interface TorneoDAO extends GenericDAO<Torneo,Integer>{
    public Torneo buscaByName(String name);
}
