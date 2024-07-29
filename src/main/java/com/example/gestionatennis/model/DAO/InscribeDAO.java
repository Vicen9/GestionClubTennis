package com.example.gestionatennis.model.DAO;

import com.example.gestionatennis.model.Inscribe;
import com.example.gestionatennis.model.Torneo;

import java.util.List;

public interface InscribeDAO extends GenericDAO<Inscribe,Integer> {
    public List<Torneo> buscaInscripcionesSocio(int idS);
    public Inscribe buscaInscripcionUnica(int idS, int idT);
}
