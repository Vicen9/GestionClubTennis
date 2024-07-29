package com.example.gestionatennis.model.DAO;

import com.example.gestionatennis.model.Inscribe;
import com.example.gestionatennis.model.Torneo;
import com.example.gestionatennis.qualifiers.DAOJPA;
import jakarta.enterprise.context.RequestScoped;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import jakarta.transaction.Transactional;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

@RequestScoped
@DAOJPA
@Transactional
public class InscribeDAOJPA implements InscribeDAO, Serializable {
    private final Logger logger = Logger.getLogger(PistasDAO.class.getName());

    @PersistenceContext(unitName = "tennisPU")

    private EntityManager em;


    public InscribeDAOJPA() {}

    @Override
    public Inscribe buscaId(Integer id){
        Inscribe i=null;
        try {
            i=em.find(Inscribe.class, id);
        } catch (Exception ex) {
            logger.log(Level.SEVERE, ex.getMessage(), ex);

        }
        return i;
    }

    @Override
    public List<Inscribe> buscaTodos(){
        List<Inscribe> li = null;
        try {
            Query q = em.createQuery("Select i from Inscribe i", Inscribe.class);
            li = (List<Inscribe>)q.getResultList();
        } catch (Exception ex) {
            logger.log(Level.SEVERE, ex.getMessage(), ex);
        }
        return li;
    }

    @Override
    public boolean crea(Inscribe i){
        boolean creado = false;
        try {
            em.persist(i);
            creado = true;
        } catch (Exception ex) {
            logger.log(Level.SEVERE, ex.getMessage(), ex);
        }
        return creado;
    }

    @Override
    public boolean guarda(Inscribe i){
        boolean guardado = false;
        try {
            i = em.merge(i);
            guardado = true;
        } catch (Exception ex) {
            logger.log(Level.SEVERE, ex.getMessage(), ex);
        }
        return guardado;
    }

    @Override
    public boolean borra(Integer id){
        boolean borrado = false;
        try {
            Inscribe i = null;
            i = em.find(Inscribe.class, id);
            em.remove(i);
            borrado = true;
        } catch (Exception ex) {
            logger.log(Level.SEVERE, ex.getMessage(), ex);
        }
        return borrado;
    }

    public List<Torneo> buscaInscripcionesSocio(int idS){
        List<Torneo> salida=new ArrayList<>();
        try {
            Query q = em.createQuery("select t from Inscribe i, Torneo t WHERE i.idTorneo = t.id and i.idSocio=:idS", Torneo.class).setParameter("idS",idS);
            salida = (List<Torneo>)q.getResultList();
        } catch (Exception ex) {
            logger.log(Level.SEVERE, ex.getMessage(), ex);
        }

        return salida;
    }

    public Inscribe buscaInscripcionUnica(int idS, int idT){
        Inscribe salida=null;
        try {
            Query q = em.createQuery("select i from Inscribe i where i.idTorneo = :idT and i.idSocio = :idS", Inscribe.class).setParameter("idS",idS).setParameter("idT",idT);
            salida = (Inscribe)q.getSingleResult();
        } catch (Exception ex) {
            logger.log(Level.SEVERE, ex.getMessage(), ex);
        }

        return salida;
    }




}