package com.example.gestionatennis.model.DAO;


import com.example.gestionatennis.model.Pista;
import com.example.gestionatennis.qualifiers.DAOJPA;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.logging.Level;
import java.util.logging.Logger;
import jakarta.enterprise.context.RequestScoped;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;


@RequestScoped
@DAOJPA
@Transactional
public class PistasDAOJPA implements PistasDAO, Serializable {

    private final Logger logger = Logger.getLogger(PistasDAO.class.getName());

    @PersistenceContext(unitName = "tennisPU")

    private EntityManager em;

    public PistasDAOJPA(){}

    @Override
    public Pista buscaId(Integer id){
        Pista p=null;
        try {
            p=em.find(Pista.class, id);
        } catch (Exception ex) {
            logger.log(Level.SEVERE, ex.getMessage(), ex);

        }
        return p;
    }

    @Override
    public List<Pista> buscaTodos(){
        List<Pista> lp = null;
        try {
            Query q = em.createQuery("Select p from Pista p", Pista.class);
            lp = (List<Pista>)q.getResultList();
        } catch (Exception ex) {
            logger.log(Level.SEVERE, ex.getMessage(), ex);
        }
        return lp;
    }

    @Override
    public boolean crea(Pista p){
        boolean creado = false;
        try {
            em.persist(p);
            creado = true;
        } catch (Exception ex) {
            logger.log(Level.SEVERE, ex.getMessage(), ex);
        }
        return creado;
    }

    @Override
    public boolean guarda(Pista p){
        boolean guardado = false;
        try {
            p = em.merge(p);
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
            Pista p = null;
            p = em.find(Pista.class, id);
            em.remove(p);
            borrado = true;
        } catch (Exception ex) {
            logger.log(Level.SEVERE, ex.getMessage(), ex);
        }
        return borrado;
    }

}
