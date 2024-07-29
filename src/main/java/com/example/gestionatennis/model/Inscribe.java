package com.example.gestionatennis.model;

import java.io.Serializable;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotNull;

@Entity
public class Inscribe implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NotNull
    private int idTorneo;

    @NotNull
    private int idSocio;


    public Inscribe() {}

    public Inscribe(int id, int idTorneo, int idSocio) {
        this.id = id;
        this.idTorneo = idTorneo;
        this.idSocio = idSocio;
    }

    public Inscribe(Inscribe i) {
        this.id = i.id;
        this.idTorneo = i.idTorneo;
        this.idSocio = i.idSocio;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdTorneo() {
        return idTorneo;
    }

    public void setIdTorneo(int idTorneo) {
        this.idTorneo = idTorneo;
    }

    public int getIdSocio() {
        return idSocio;
    }

    public void setIdSocio(int idSocio) {
        this.idSocio = idSocio;
    }
}
