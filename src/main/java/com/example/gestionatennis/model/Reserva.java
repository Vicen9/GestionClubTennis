package com.example.gestionatennis.model;

import java.io.Serializable;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotNull;

import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Objects;

@Entity
public class Reserva implements Serializable{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotNull
    private LocalTime horaIni;

    @NotNull
    private LocalTime horaFin;

    @NotNull
    private LocalDate fecha;

    @NotNull
    private Integer idSocio;

    @NotNull
    private Integer idPista;

    public Reserva() {
        id=0;
        idSocio=0;
        idPista=0;
    }

    public Reserva(int id, LocalTime horaIni, LocalTime horaFin, LocalDate fecha, int idSocio, int idPista) {
        this.id = id;
        this.horaIni = horaIni;
        this.horaFin = horaFin;
        this.fecha = fecha;
        this.idSocio = idSocio;
        this.idPista = idPista;
    }

    public Reserva(Reserva r) {
        this.id = r.id;
        this.horaIni = r.horaIni;
        this.horaFin = r.horaFin;
        this.fecha = r.fecha;
        this.idSocio = r.idSocio;
        this.idPista = r.idPista;
    }

    public int getIdPista() {
        return idPista;
    }

    public void setIdPista(int idPista) {
        this.idPista = idPista;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

    public LocalTime getHoraIni() {
        return horaIni;
    }

    public void setHoraIni(LocalTime horaIni) {
        this.horaIni = horaIni;
    }

    public LocalTime getHoraFin() {
        return horaFin;
    }

    public void setHoraFin(LocalTime horaFin) {
        this.horaFin = horaFin;
    }

    public int getIdSocio() {
        return idSocio;
    }

    public void setIdSocio(int idSocio) {
        this.idSocio = idSocio;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Reserva reserva = (Reserva) o;
        return Objects.equals(horaIni, reserva.horaIni) && Objects.equals(horaFin, reserva.horaFin);
    }
}
