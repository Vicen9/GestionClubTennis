package com.example.gestionatennis.model.DAO;



import com.example.gestionatennis.model.Torneo;
import com.example.gestionatennis.qualifiers.DAOJPA;
import jakarta.enterprise.context.RequestScoped;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;

import java.io.Serializable;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
@RequestScoped
@DAOJPA
@Transactional
public class TorneoDAOJPA implements TorneoDAO, Serializable {
    private final Logger logger = Logger.getLogger(TorneoDAOJPA.class.getName());
    @PersistenceContext(unitName = "tennisPU")

    private EntityManager em;
    public TorneoDAOJPA(){}


    public Torneo buscaByName(String name){
        Torneo t = null;
        try {
            TypedQuery<Torneo> q = em.createQuery("Select t from Torneo t where t.nombre=:name",Torneo.class);
            q.setParameter("nombre", name);
            t = q.getSingleResult();
        } catch (Exception ex) {
            logger.log(Level.SEVERE, ex.getMessage(), ex);
        }
        return t;
    }

    @Override
    public Torneo buscaId(Integer id){
        Torneo t=null;
        try {
            t=em.find(Torneo.class, id);
        } catch (Exception ex) {
            logger.log(Level.SEVERE, ex.getMessage(), ex);
        }
        return t;
    }

    @Override
    public List<Torneo> buscaTodos(){
        List<Torneo> ls = null;
        try {
            Query q = em.createQuery("Select t from Torneo t", Torneo.class);
            ls = (List<Torneo>)q.getResultList();
        } catch (Exception ex) {
            logger.log(Level.SEVERE, ex.getMessage(), ex);
        }
        return ls;
    }


    @Override
    public boolean crea(Torneo t){
        boolean creado = false;
        try {
            em.persist(t);
            creado = true;
        } catch (Exception ex) {
            logger.log(Level.SEVERE, ex.getMessage(), ex);
        }
        return creado;
    }

    @Override
    public boolean guarda(Torneo t){
        boolean guardado = false;
        try {
            t = em.merge(t);
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
            Torneo t = null;
            t = em.find(Torneo.class, id);
            em.remove(t);
            borrado = true;
        } catch (Exception ex) {
            logger.log(Level.SEVERE, ex.getMessage(), ex);
        }
        return borrado;
    }



}
