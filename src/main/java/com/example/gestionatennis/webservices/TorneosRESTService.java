package com.example.gestionatennis.webservices;

import com.example.gestionatennis.model.Torneo;
import com.example.gestionatennis.model.DAO.TorneoDAO;
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


@Path("torneos")
@Produces(MediaType.APPLICATION_JSON)
@RequestScoped
public class TorneosRESTService {

    @Context
    private UriInfo context;

    @Inject @DAOJPA
    TorneoDAO torneoDAO;

    public TorneosRESTService(){};


    @GET
    public List<Torneo> getTorneos() {
        return torneoDAO.buscaTodos();
    }

    @GET
    @Path("/{id}")
    public Response getTorneo(@PathParam("id") int id) {
        Response response;
        Torneo t=torneoDAO.buscaId(id);
        if( t!=null) {
            response= Response.ok(t).build();
        } else {
            List<Map<String,Object>> errores=new ArrayList<>();
            Map<String,Object> err=new HashMap<>();
            err.put("message", "El torneo no existe");
            errores.add(err);
            response=Response.status(Response.Status.BAD_REQUEST)
                    .entity(errores).build();
        }
        return response;
    }

    @DELETE
    @Path("/{id}")
    public Response borraTorneo(@PathParam("id") int id) {
        Response response;

        if (torneoDAO.borra(id) == true) {
            response= Response.ok(id).build();
        } else {
            List<Map<String,Object>> errores=new ArrayList<>();
            Map<String,Object> err=new HashMap<>();
            err.put("message", "El torneo no existe");
            errores.add(err);
            response=Response.status(Response.Status.BAD_REQUEST)
                    .entity(errores).build();
        }

        return response;
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response creaTorneo(@Valid Torneo t) {
        Response response;
        if ( torneoDAO.crea(t) == true) {
            Integer newId=t.getId();
            response= Response.ok(t).build();
        } else {
            List< Map<String,Object> > errores=new ArrayList<>();
            Map<String,Object> err=new HashMap<>();
            err.put("message", "No se ha podido crear el torneo");
            err.put("torneo", t);
            errores.add(err);
            response=Response.status(Response.Status.BAD_REQUEST)
                    .entity(errores).build();
        }
        return response;
    }

    @PUT
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response modificaTorneo(@Valid Torneo t, @PathParam("id") Integer id) {
        Response response;
        t.setId(id);
        if (torneoDAO.guarda(t)) {
            response= Response.ok(t).build();
        } else {
            List<Map<String,Object>> errores=new ArrayList<>();
            Map<String,Object> err=new HashMap<>(); //Error messages
            err.put("message", "No se ha podido modificar el torneo");
            err.put("torneo", t);
            errores.add(err);
            response=Response.status(Response.Status.BAD_REQUEST)
                    .entity(errores).build();
        }
        return response;
    }


}
