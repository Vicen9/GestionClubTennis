package com.example.gestionatennis.controller;

import com.example.gestionatennis.model.DAO.SociosDAO;
import com.example.gestionatennis.model.DAO.SociosDAOMap;
import com.example.gestionatennis.model.Socio;
import com.example.gestionatennis.qualifiers.DAOJPA;
import com.example.gestionatennis.qualifiers.DAOMap;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;
import java.util.logging.Logger;
import jakarta.annotation.PostConstruct;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;

import java.io.Serializable;

@Named(value="crtlSocios")
@ViewScoped
public class SociosController implements Serializable {
    private static final long serialVersionUID = 1L;
    private final Logger logger = Logger.getLogger(SociosController.class.getName());

    @Inject @DAOJPA
    private SociosDAO sociosDAO;

    @Inject
    HttpServletRequest request;
    @Inject
    FacesContext fc;

    private Socio socio;

    public SociosController() {
    }

    public String logout() throws ServletException {
        request.logout();
        request.getSession().invalidate();
        return "/index?faces-redirect=true";
    }
    @PostConstruct
    public void init(){
        socio = new Socio();
    }

    public List<Socio> getSocios() {
        return sociosDAO.buscaTodos();
    }

    public Socio getSocio(){
        return socio;
    }

    public void recupera(){
        socio = sociosDAO.buscaId(socio.getIdSocio());
        if (socio == null){
            fc.addMessage(null, new FacesMessage("El socio no existe"));
        }
    }


    public String crea(){
        socio.setIdSocio(0);
        socio.setRol("SOCIO");
        sociosDAO.crea(socio);
        return "/tennis/index?faces-redirect=true";
    }

    public String guarda(){
        sociosDAO.guarda(socio);
        return "crudSocios?faces-redirect=true";
    }

    public String borra(){
        sociosDAO.borra(socio.getIdSocio());
        return "";
    }

    void reset(){
        socio.setIdSocio(0);
        socio.setDni("");
        socio.setApellidos("");
        socio.setNombre("");
        socio.setNivel(0);
        socio.setFechaNacimiento(LocalDate.ofEpochDay(00/00/00));
        socio.setDireccion("");
        socio.setTelefono(0);
        socio.setMediodePago("");
        socio.setEmail("");
    }

    public String borra(Socio s){
        sociosDAO.borra(s.getIdSocio());
        return "crudSocios";
    }


    public void editRow(Socio s){
        this.socio = s;
    }

    public void cancelEditRow(){
        this.socio = new Socio();
    }

    public void actualizaSocio(){
        sociosDAO.guarda(socio);
        cancelEditRow();
    }

}
