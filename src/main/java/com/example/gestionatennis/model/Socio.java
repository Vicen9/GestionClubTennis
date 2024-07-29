package com.example.gestionatennis.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;

import java.io.Serializable;
import java.time.LocalDate;

@Entity()
public class Socio implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idSocio;

    @Pattern(regexp = "[0-9]{8}[a-zA-Z]", message = "Formato de dni incorrecto, formato correcto [0-9]8[a-z]")
    private String dni;

    @NotNull
    private Integer telefono;

    @NotBlank(message = "Debe rellenar la dirección")
    private String direccion;

    @NotNull(message = "Fecha nacimiento no puede estar vacio")
    @Past(message = "La fecha debe ser pasada")
    private LocalDate fechaNacimiento;

    @NotNull(message = "Email no puede estar vacío")
    @Email(message = "Error en el formato")
    private String email;

    @NotNull(message = "Debe proporcionar un nivel")
    @Min(value = 1, message = "El nivel mínimo es 1")
    @Max(value = 6, message = "El nivel maximo es 6")
    private int nivel;
    @NotBlank(message = "El nombre no puede estar en blanco")
    @Size(min = 4, max = 25, message = "La longitud ${validatedValue} debe estar entre {min} y {max} caracteres")
    private String nombre;
    @NotBlank(message = "Los apellidos no pueden estar en blanco")
    @Size(min = 4, max = 40, message = "La longitud ${validatedValue} debe estar entre {min} y {max} caracteres")
    private String apellidos;

    private String sexo;
    @NotNull(message = "Introduzca un medio de pago")
    private String mediodePago;

    @NotNull(message = "La contraseña no puede estar vacia")
    private String password;

    @NotNull(message = "Debe seleccionar al menos un rol")
    private String rol;


public Socio(){
    idSocio=0;
    telefono=000000000;
    nombre="";
    apellidos="";
    email="";
}
    public Socio(Integer idSocio, String dni, Integer telefono, String direccion, LocalDate fechaNacimiento, String email, int nivel, String nombre, String apellidos, String sexo, String mediodePago, String password, String rol) {
        this.idSocio = idSocio;
        this.dni = dni;
        this.telefono = telefono;
        this.direccion = direccion;
        this.fechaNacimiento = fechaNacimiento;
        this.email = email;
        this.nivel = nivel;
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.sexo = sexo;
        this.mediodePago = mediodePago;
        this.rol = rol;
        this.password = password;
    }

    public Socio(Socio s){
        this.idSocio = s.idSocio;
        this.dni = s.dni;
        this.telefono = s.telefono;
        this.direccion = s.direccion;
        this.fechaNacimiento = s.fechaNacimiento;
        this.email = s.email;
        this.nivel = s.nivel;
        this.nombre = s.nombre;
        this.apellidos = s.apellidos;
        this.sexo = s.sexo;
        this.mediodePago = s.mediodePago;
        this.rol = s.rol;
        this.password = s.password;
    }


    public Integer getIdSocio() {
        return idSocio;
    }

    public void setIdSocio(Integer idSocio) {
        this.idSocio = idSocio;
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public int getTelefono() {
        return telefono;
    }

    public void setTelefono(int telefono) {
        this.telefono = telefono;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public LocalDate getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(LocalDate fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getNivel() {
        return nivel;
    }

    public void setNivel(int nivel) {
        this.nivel = nivel;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    public String getMediodePago() {
        return mediodePago;
    }

    public void setMediodePago(String mediodePago) {
        this.mediodePago = mediodePago;
    }

    public void setTelefono(Integer telefono) {
        this.telefono = telefono;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRol() {
        return rol;
    }

    public void setRol(String rol) {
        this.rol = rol;
    }

    @Override
    public String toString(){
    return "Socio[Id="+idSocio+"]";
    }


    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof Socio)){
            return false;
        }
        Socio other = (Socio) obj;
        return this.idSocio == other.idSocio;
    }
}

