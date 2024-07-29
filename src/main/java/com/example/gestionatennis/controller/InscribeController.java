package com.example.gestionatennis.controller;

import com.example.gestionatennis.model.DAO.InscribeDAO;
import com.example.gestionatennis.model.DAO.SociosDAO;
import com.example.gestionatennis.model.Socio;
import com.example.gestionatennis.model.Inscribe;
import com.example.gestionatennis.model.Torneo;
import com.example.gestionatennis.qualifiers.DAOJPA;
import jakarta.annotation.PostConstruct;
import jakarta.faces.context.FacesContext;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import jakarta.servlet.http.HttpServletRequest;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

@Named(value="crtlInscribir")
@ViewScoped
public class InscribeController implements Serializable {

    private static final long serialVersionUID = 1L;
    private final Logger logger = Logger.getLogger(TorneoController.class.getName());

    @Inject @DAOJPA
    private InscribeDAO inscribeDAO;

    @Inject @DAOJPA
    private SociosDAO sociosDAO;

    @Inject
    FacesContext fc;

    @Inject
    HttpServletRequest request;

    public InscribeController() {}

    @PostConstruct
    public void init(){}

    public List<Torneo> dameTorneosIns(){
        String user = request.getRemoteUser();
        Socio s = sociosDAO.buscaByEmail(user);
        return inscribeDAO.buscaInscripcionesSocio(s.getIdSocio());
    }

    public Integer dameIdSocioActual(){
        String user = request.getRemoteUser();
        Socio s = sociosDAO.buscaByEmail(user);
        return s.getIdSocio();
    }
}
