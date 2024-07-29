package com.example.gestionatennis.controller;

import com.example.gestionatennis.model.DAO.ReservaDAO;
import com.example.gestionatennis.model.DAO.SociosDAO;
import com.example.gestionatennis.model.Pista;
import com.example.gestionatennis.model.Reserva;
import com.example.gestionatennis.model.Socio;
import com.example.gestionatennis.qualifiers.DAOJPA;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Date;
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

@Named(value="crtlReserva")
@ViewScoped
public class ReservaController implements Serializable {

    private static final long serialVersionUID = 1L;
    private final Logger logger = Logger.getLogger(ReservaController.class.getName());

    @Inject
    @DAOJPA
    private ReservaDAO reservaDAO;

    @Inject
    @DAOJPA
    private SociosDAO sociosDAO;

    @Inject
    HttpServletRequest request;
    @Inject
    FacesContext fc;

    private Reserva reserva;

    private LocalDate fechaR;

    private int idPista;

    private List<Reserva> listaReservas;


    public ReservaController() {}

    @PostConstruct
    public void init(){
        reserva = new Reserva();
        listaReservas = new ArrayList<>();
        fechaR = null;
    }

    public List<Reserva> getReservas(){return reservaDAO.buscaTodos();}

    public List<Reserva> getReservasEnFecha(){return reservaDAO.reservasEnFecha(fechaR,idPista);}

    public LocalDate getFechaR() {
        return fechaR;
    }

    public void setFechaR(LocalDate fechaR) {
        this.fechaR = fechaR;
    }

    public int getIdPista() {
        return idPista;
    }

    public void setIdPista(int idPista) {
        this.idPista = idPista;
    }

    public Reserva getReserva() {
        return reserva;
    }

    public void setReserva(Reserva reserva) {
        this.reserva = reserva;
    }

    public void recupera(){
        reserva = reservaDAO.buscaId(reserva.getId());
        if (reserva == null){
            fc.addMessage(null, new FacesMessage("La reserva no existe"));
        }
    }

    public String crea(Reserva r){
        reserva.setId(0);
        renuevaReserva(r);
        reservaDAO.crea(reserva);
        return "";
    }

    public String guarda(){
        reservaDAO.guarda(reserva);
        return "";
    }

    public String borra(int id){
        reservaDAO.borra(id);
        return "";
    }

    public void editRow(Reserva r){
        this.reserva = r;
    }

    public void cancelEditRow(){
        this.reserva = new Reserva();
    }

    public void actualizaReserva(){
        reservaDAO.guarda(reserva);
        cancelEditRow();
    }

    public List<Reserva> reservasEnFecha(){
        return reservaDAO.reservasEnFecha(fechaR,idPista);
    }

    public List<Reserva> reservasTotales(){
        Reserva r;
        List<Reserva> salida = new ArrayList<>();
        LocalTime hI, hF;
        for (int i = 8; i < 22; i+=2) {
            hI = LocalTime.of(i,0);
            hF = LocalTime.of(i+2,0);
            r = new Reserva(0,hI,hF,LocalDate.of(1,1,1),0,0);
            salida.add(r);

        }
        return salida;

    }

    public boolean buscaReserva(Reserva r){
        List<Reserva> todos = getReservasEnFecha();
        renuevaReserva(r);

        for (Reserva u:todos) {
            if ( reserva.getHoraIni() == u.getHoraIni() ){
                return false;
            }
        }
        return true;

    }

    public int dameIdSocioActual(){
        String user = request.getRemoteUser();
        Socio s = sociosDAO.buscaByEmail(user);
        return s.getIdSocio();
    }

    public List<Reserva> dameReservasDeUnSocio(){
        return reservaDAO.reservasDeUnSocio(dameIdSocioActual());
    }

    public void renuevaReserva(Reserva r){
        reserva.setId(0);
        reserva.setFecha(fechaR);
        reserva.setHoraIni(r.getHoraIni());
        reserva.setHoraFin(r.getHoraFin());
        reserva.setIdPista(idPista);
        reserva.setIdSocio(dameIdSocioActual());
    }

    public void reset(){
        reserva.setId(0);
        reserva.setFecha(null);
        reserva.setHoraIni(null);
        reserva.setHoraFin(null);
        reserva.setIdPista(0);
        reserva.setIdSocio(0);

    }


}
