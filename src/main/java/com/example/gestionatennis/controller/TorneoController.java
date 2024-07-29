package com.example.gestionatennis.controller;

import com.example.gestionatennis.model.DAO.TorneoDAO;
import com.example.gestionatennis.model.Torneo;
import com.example.gestionatennis.qualifiers.DAOJPA;
import jakarta.annotation.PostConstruct;
import jakarta.ejb.Stateless;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;

import java.io.Serializable;
import java.util.List;
import java.util.logging.Logger;

@Named(value="crtlTorneo")
@ViewScoped
public class TorneoController implements Serializable {
    private static final long serialVersionUID = 1L;
    private final Logger logger = Logger.getLogger(TorneoController.class.getName());

    @Inject @DAOJPA
    private TorneoDAO torneoDAO;

    @Inject
    FacesContext fc;

    @Inject
    HttpServletRequest request;


    private Torneo torneo;

    public TorneoController(){}

    @PostConstruct
    public void init(){ torneo=new Torneo();}

    public List<Torneo> getTorneos() {return torneoDAO.buscaTodos();}

    public Torneo getTorneo(){
        return torneo;
    }

    public void recupera(){
        torneo = torneoDAO.buscaId(torneo.getId());
        if (torneo == null){
            fc.addMessage(null, new FacesMessage("El torneo no existe"));
        }
    }

    public void recupera(String name){
        torneo=torneoDAO.buscaByName(name);
        if (torneo == null){
            fc.addMessage(null, new FacesMessage("El torneo no existe"));
        }
    }

    public String crea(){
        torneo.setId(0);
        torneoDAO.crea(torneo);
        return "crudTorneos?faces-redirect=true";
    }

    public String guarda(){
        torneoDAO.guarda(torneo);
        return "crudTorneos?faces-redirect=true";
    }

    public String borra(){
        torneoDAO.borra(torneo.getId());
        return "";
    }

    void reset(){
        torneo.setId(0);
        torneo.setNombre("");
    }

    public String borra(Torneo t){
        torneoDAO.borra(t.getId());
        return "";
    }


    public void editRow(Torneo t){
        this.torneo = t;
    }

    public void cancelEditRow(){
        this.torneo = new Torneo();
    }

    public void actualizaTorneo(){
        torneoDAO.guarda(torneo);
        cancelEditRow();
    }

    }
