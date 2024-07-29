package com.example.gestionatennis.model.DAO;

import java.time.LocalDate;
import java.util.logging.Logger;

import com.example.gestionatennis.model.Reserva;
import com.example.gestionatennis.qualifiers.DAOJPA;
import java.io.Serializable;
import java.util.List;

import java.util.logging.Level;
import jakarta.enterprise.context.RequestScoped;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;


@RequestScoped
@DAOJPA
@Transactional
public class ReservaDAOJPA implements ReservaDAO,Serializable {

    private final Logger logger = Logger.getLogger(PistasDAO.class.getName());

    @PersistenceContext(unitName = "tennisPU")

    private EntityManager em;

    public ReservaDAOJPA() {}

    @Override
    public Reserva buscaId(Integer id){
        Reserva r=null;
        try {
            r=em.find(Reserva.class, id);
        } catch (Exception ex) {
            logger.log(Level.SEVERE, ex.getMessage(), ex);

        }
        return r;
    }

    @Override
    public List<Reserva> buscaTodos(){
        List<Reserva> lr = null;
        try {
            Query q = em.createQuery("Select r from Reserva r", Reserva.class);
            lr = (List<Reserva>)q.getResultList();
        } catch (Exception ex) {
            logger.log(Level.SEVERE, ex.getMessage(), ex);
        }
        return lr;
    }

    @Override
    public boolean crea(Reserva r){
        boolean creado = false;
        try {
            em.persist(r);
            creado = true;
        } catch (Exception ex) {
            logger.log(Level.SEVERE, ex.getMessage(), ex);
        }
        return creado;
    }

    @Override
    public boolean guarda(Reserva r){
        boolean guardado = false;
        try {
            r = em.merge(r);
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
            Reserva r = null;
            r = em.find(Reserva.class, id);
            em.remove(r);
            borrado = true;
        } catch (Exception ex) {
            logger.log(Level.SEVERE, ex.getMessage(), ex);
        }
        return borrado;
    }

    public List<Reserva> reservasEnFecha(LocalDate fecha,int pista){
        List<Reserva> lr = null;
        try {
            Query q = em.createQuery("Select r from Reserva r where r.fecha = :fecha and r.idPista = :pista", Reserva.class).setParameter("fecha",fecha).setParameter("pista",pista);
            lr = (List<Reserva>)q.getResultList();
        } catch (Exception ex) {
            logger.log(Level.SEVERE, ex.getMessage(), ex);
        }
        return lr;
    }

    public List<Reserva> reservasDeUnSocio(int idSocio){
        List<Reserva> lr = null;
        try {
            Query q = em.createQuery("Select r from Reserva r where r.idSocio = :idSocio", Reserva.class).setParameter("idSocio",idSocio);
            lr = (List<Reserva>)q.getResultList();
        } catch (Exception ex) {
            logger.log(Level.SEVERE, ex.getMessage(), ex);
        }
        return lr;
    }

}
