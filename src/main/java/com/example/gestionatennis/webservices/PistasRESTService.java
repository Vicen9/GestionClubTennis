package com.example.gestionatennis.webservices;

import com.example.gestionatennis.model.Pista;
import com.example.gestionatennis.model.DAO.PistasDAO;
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


@Path("pistas")
@Produces(MediaType.APPLICATION_JSON)

@RequestScoped
public class PistasRESTService {

    @Context
    private UriInfo context;

    @Inject @DAOJPA
    PistasDAO pistasDAO;

    public PistasRESTService() {};


    @GET
    public List<Pista> getPistas() {
        return pistasDAO.buscaTodos();
    }

    @GET
    @Path("/{id}")
    public Response getPista(@PathParam("id") int id) {
        Response response;
        Pista p=pistasDAO.buscaId(id);
        if( p!=null) {
            response= Response.ok(p).build();
        } else {
            List<Map<String,Object>> errores=new ArrayList<>();
            Map<String,Object> err=new HashMap<>();
            err.put("message", "La pista no existe");
            errores.add(err);
            response=Response.status(Response.Status.BAD_REQUEST)
                    .entity(errores).build();
        }
        return response;
    }

    @DELETE
    @Path("/{id}")
    public Response borraPista(@PathParam("id") int id) {
        Response response;

        if (pistasDAO.borra(id) == true) {
            response= Response.ok(id).build();
        } else {
            List<Map<String,Object>> errores=new ArrayList<>();
            Map<String,Object> err=new HashMap<>();
            err.put("message", "La pista no existe");
            errores.add(err);
            response=Response.status(Response.Status.BAD_REQUEST)
                    .entity(errores).build();
        }

        return response;
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response creaPista(@Valid Pista p) {
        Response response;
        if ( pistasDAO.crea(p) == true) {
            response= Response.ok(p).build();
        } else {
            List< Map<String,Object> > errores=new ArrayList<>();
            Map<String,Object> err=new HashMap<>();
            err.put("message", "No se ha podido crear la pista");
            err.put("pista", p);
            errores.add(err);
            response=Response.status(Response.Status.BAD_REQUEST)
                    .entity(errores).build();
        }
        return response;
    }

    @PUT
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response modificaPista(@Valid Pista p, @PathParam("id") Integer id) {
        Response response;
        p.setId(id);
        if ( pistasDAO.guarda(p)) {
            response= Response.ok(p).build();
        } else {
            List<Map<String,Object>> errores=new ArrayList<>();
            Map<String,Object> err=new HashMap<>(); //Error messages
            err.put("message", "No se ha podido modificar la pista");
            err.put("pista", p);
            errores.add(err);
            response=Response.status(Response.Status.BAD_REQUEST)
                    .entity(errores).build();
        }
        return response;
    }




}
