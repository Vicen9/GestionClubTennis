package com.example.gestionatennis.webservices;


import com.example.gestionatennis.model.Reserva;
import com.example.gestionatennis.model.DAO.ReservaDAO;
import com.example.gestionatennis.qualifiers.DAOJPA;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import jakarta.validation.Valid;
import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.core.UriInfo;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("reserva")
@Produces(MediaType.APPLICATION_JSON)

@RequestScoped
public class ReservaRESTService {

    @Context
    private UriInfo context;

    @Inject @DAOJPA
    ReservaDAO reservaDAO;

    public ReservaRESTService() {}

    @GET
    public List<Reserva> getReserva() {
        return reservaDAO.buscaTodos();
    }

    @GET
    @Path("/{id}")
    public Response getReserva(@PathParam("id") int id) {
        Response response;
        Reserva r=reservaDAO.buscaId(id);
        if( r!=null) {
            response= Response.ok(r).build();
        } else {
            List<Map<String,Object>> errores=new ArrayList<>();
            Map<String,Object> err=new HashMap<>();
            err.put("message", "La reserva no existe");
            errores.add(err);
            response=Response.status(Response.Status.BAD_REQUEST)
                    .entity(errores).build();
        }
        return response;
    }

    @DELETE
    @Path("/{id}")
    public Response borraReserva(@PathParam("id") int id) {
        Response response;

        if (reservaDAO.borra(id)) {
            response= Response.ok(id).build();
        } else {
            List<Map<String,Object>> errores=new ArrayList<>();
            Map<String,Object> err=new HashMap<>();
            err.put("message", "La reserva no existe");
            errores.add(err);
            response=Response.status(Response.Status.BAD_REQUEST)
                    .entity(errores).build();
        }

        return response;
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response creaReserva(@Valid Reserva r) {
        Response response;
        if ( reservaDAO.crea(r)) {
            response= Response.ok(r).build();
        } else {
            List< Map<String,Object> > errores=new ArrayList<>();
            Map<String,Object> err=new HashMap<>();
            err.put("message", "No se ha podido crear la reserva");
            err.put("reserva", r);
            errores.add(err);
            response=Response.status(Response.Status.BAD_REQUEST)
                    .entity(errores).build();
        }
        return response;
    }

    @PUT
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response modificaReserva(@Valid Reserva r, @PathParam("id") Integer id) {
        Response response;
        r.setId(id);
        if (reservaDAO.guarda(r)) {
            response= Response.ok(r).build();
        } else {
            List<Map<String,Object>> errores=new ArrayList<>();
            Map<String,Object> err=new HashMap<>();
            err.put("message", "No se ha podido modificar la reserva");
            err.put("reserva", r);
            errores.add(err);
            response=Response.status(Response.Status.BAD_REQUEST)
                    .entity(errores).build();
        }
        return response;
    }
}