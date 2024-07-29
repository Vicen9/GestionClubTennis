package com.example.gestionatennis.controller;

import com.example.gestionatennis.model.DAO.PistasDAO;
import com.example.gestionatennis.model.Pista;
import com.example.gestionatennis.qualifiers.DAOJPA;
import jakarta.annotation.PostConstruct;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import jakarta.servlet.http.HttpServletRequest;

import java.io.Serializable;
import java.util.List;
import java.util.logging.Logger;


@Named(value="crtlPista")
@ViewScoped
public class PistaController implements Serializable {

    private static final long serialVersionUID = 1L;
    private final Logger logger = Logger.getLogger(PistaController.class.getName());

    @Inject @DAOJPA
    private PistasDAO pistasDAO;

    @Inject
    FacesContext fc;

    @Inject
    HttpServletRequest request;

    private Pista pista;


    public PistaController(){}

    @PostConstruct
    public void init(){ pista=new Pista();}

    public List<Pista> getPistas(){return pistasDAO.buscaTodos();}

    public Pista getPista(){
        return pista;
    }

    public void recupera(){
        pista = pistasDAO.buscaId(pista.getId());
        if (pista == null){
            fc.addMessage(null, new FacesMessage("La pista no existe"));
        }
    }

    public String crea(){
        pista.setId(0);
        pistasDAO.crea(pista);
        return "crudPistas?faces-redirect=true";
    }

    public String guarda(){
        pistasDAO.guarda(pista);
        return "crudPistas?faces-redirect=true";
    }

    public String borra(){
        pistasDAO.borra(pista.getId());
        return "";
    }

    void reset(){
        pista.setId(0);
    }

    public String borra(Pista p){
        pistasDAO.borra(p.getId());
        return "";
    }

    public void editRow(Pista p){this.pista = p;}

    public void cancelEditRow(){this.pista = new Pista();}

    public void actualizaTorneo(){
        pistasDAO.guarda(pista);
        cancelEditRow();
    }

}
