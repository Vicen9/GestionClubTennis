package com.example.gestionatennis.webservices;

import  com.example.gestionatennis.model.Inscribe;
import com.example.gestionatennis.model.DAO.InscribeDAO;
import com.example.gestionatennis.model.Torneo;
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


@Path("inscribir")
@Produces(MediaType.APPLICATION_JSON)

@RequestScoped
public class InscribeRESTService {

    @Context
    private UriInfo context;

    @Inject @DAOJPA
    InscribeDAO inscribeDAO;

    public InscribeRESTService() {}

    @GET
    public List<Inscribe> getInscribe() {
        return inscribeDAO.buscaTodos();
    }

    @GET
    @Path("/{id}")
    public Response getInscribe(@PathParam("id") int id) {
        Response response;
        Inscribe i=inscribeDAO.buscaId(id);
        if( i!=null) {
            response= Response.ok(i).build();
        } else {
            List<Map<String,Object>> errores=new ArrayList<>();
            Map<String,Object> err=new HashMap<>();
            err.put("message", "La inscripción no existe");
            errores.add(err);
            response=Response.status(Response.Status.BAD_REQUEST).entity(errores).build();
        }
        return response;
    }


    @GET
    @Path("socio/{id}")
    public List<Torneo> getInscribeSocio(@PathParam("id") int id) {
        return inscribeDAO.buscaInscripcionesSocio(id);
    }

    @GET
    @Path("dameI/{idS}/{idT}")
    public Response buscaInscripcionUni(@PathParam("idS") int idS,@PathParam("idT") int idT) {
        Response response;
        Inscribe i=inscribeDAO.buscaInscripcionUnica(idS,idT);
        if( i!=null) {
            response= Response.ok(i).build();
        } else {
            List<Map<String,Object>> errores=new ArrayList<>();
            Map<String,Object> err=new HashMap<>();
            err.put("message", "La inscripción no existe");
            errores.add(err);
            response=Response.status(Response.Status.BAD_REQUEST).entity(errores).build();
        }
        return response;
    }

    @DELETE
    @Path("/{id}")
    public Response borraInscribe(@PathParam("id") int id) {
        Response response;

        if (inscribeDAO.borra(id)) {
            response= Response.ok(id).build();
        } else {
            List<Map<String,Object>> errores=new ArrayList<>();
            Map<String,Object> err=new HashMap<>();
            err.put("message", "La inscripción no existe");
            errores.add(err);
            response=Response.status(Response.Status.BAD_REQUEST).entity(errores).build();
        }

        return response;
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response creaInscribe(@Valid Inscribe i) {
        Response response;
        if ( inscribeDAO.crea(i)) {
            response= Response.ok(i).build();
        } else {
            List< Map<String,Object> > errores=new ArrayList<>();
            Map<String,Object> err=new HashMap<>();
            err.put("message", "No se ha podido crear la inscripción");
            err.put("inscripción", i);
            errores.add(err);
            response=Response.status(Response.Status.BAD_REQUEST)
                    .entity(errores).build();
        }
        return response;
    }

    @PUT
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response modificaInscribe(@Valid Inscribe i, @PathParam("id") Integer id) {
        Response response;
        i.setId(id);
        if ( inscribeDAO.guarda(i)) {
            response= Response.ok(i).build();
        } else {
            List<Map<String,Object>> errores=new ArrayList<>();
            Map<String,Object> err=new HashMap<>(); //Error messages
            err.put("message", "No se ha podido modificar la inscripción");
            err.put("inscripción", i);
            errores.add(err);
            response=Response.status(Response.Status.BAD_REQUEST)
                    .entity(errores).build();
        }
        return response;
    }
}
