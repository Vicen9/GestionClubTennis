package com.example.gestionatennis.model.DAO;

import com.example.gestionatennis.model.Socio;
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

@RequestScoped  //Elegible for Dependency Injection
@DAOJPA
@Transactional
public class SociosDAOJPA implements SociosDAO,Serializable {
    private final Logger logger = Logger.getLogger(SociosDAOJPA.class.getName());

    @PersistenceContext(unitName = "tennisPU")

    private EntityManager em;

    public SociosDAOJPA(){}

    @Override
    public Socio buscaId(Integer id){
        Socio c=null;
        try {
            c=em.find(Socio.class, id);
        } catch (Exception ex) {
            logger.log(Level.SEVERE, ex.getMessage(), ex);

        }
        return c;
    }

    @Override
    public List<Socio> buscaTodos(){
        List<Socio> ls = null;
        try {
            Query q = em.createQuery("Select s from Socio s", Socio.class);
            ls = (List<Socio>)q.getResultList();
        } catch (Exception ex) {
            logger.log(Level.SEVERE, ex.getMessage(), ex);
        }
        return ls;
    }


    public Socio buscaByDNI(String dni){
        Socio s = null;
        try {
            TypedQuery<Socio> q = em.createQuery("Select s from Socio s where s.dni=:dni",Socio.class);
            q.setParameter("dni", dni);
            s = q.getSingleResult();
        } catch (Exception ex) {
            logger.log(Level.SEVERE, ex.getMessage(), ex);
        }
        return s;
    }

    @Override
    public boolean crea(Socio s){
        boolean creado = false;
        try {
            em.persist(s);
            creado = true;
        } catch (Exception ex) {
            logger.log(Level.SEVERE, ex.getMessage(), ex);
        }
        return creado;
    }

    @Override
    public boolean guarda(Socio s){
        boolean guardado = false;
        try {
            s = em.merge(s);
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
            Socio s = null;
            s = em.find(Socio.class, id);
            em.remove(s);
            borrado = true;
        } catch (Exception ex) {
            logger.log(Level.SEVERE, ex.getMessage(), ex);
        }
        return borrado;
    }

    @Override
    public Socio buscaByEmail(String correo){
        Socio s = null;
        try {
            TypedQuery<Socio> q = em.createQuery("Select s from Socio s where s.email =:correo",Socio.class);
            q.setParameter("correo", correo);
            s = q.getSingleResult();
        } catch (Exception ex) {
            logger.log(Level.SEVERE, ex.getMessage(), ex);
        }
        return s;
    }



}
