package com.example.gestionatennis.model.DAO;

import com.example.gestionatennis.model.Reserva;

import java.time.LocalDate;
import java.util.List;

public interface ReservaDAO extends GenericDAO<Reserva,Integer> {
    public List<Reserva> reservasEnFecha(LocalDate fecha,int pista);
    public List<Reserva> reservasDeUnSocio(int idSocio);
}
