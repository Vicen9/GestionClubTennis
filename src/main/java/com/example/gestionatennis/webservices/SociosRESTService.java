package com.example.gestionatennis.webservices;


import com.example.gestionatennis.model.DAO.TorneoDAO;
import com.example.gestionatennis.model.Socio;
import com.example.gestionatennis.model.DAO.SociosDAO;
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


@Path("socios")
@Produces(MediaType.APPLICATION_JSON)
@RequestScoped
public class SociosRESTService {
    @Context
    private UriInfo context;

    @Inject @DAOJPA
    SociosDAO sociosDAO;

    public SociosRESTService(){}


    @GET
    public List<Socio> getSocios() {
        return sociosDAO.buscaTodos();
    }

    @GET
    @Path("/{id}")
    public Response getSocio(@PathParam("id") int id) {
        Response response;
        Socio s=sociosDAO.buscaId(id);
        if( s!=null) {
            response= Response.ok(s).build();
        } else {
            List<Map<String,Object>> errores=new ArrayList<>();
            Map<String,Object> err=new HashMap<>();
            err.put("message", "El socio no existe");
            errores.add(err);
            response=Response.status(Response.Status.BAD_REQUEST)
                    .entity(errores).build();
        }
        return response;
    }

    @GET
    @Path("/dni/{dni}")
    public Response getSocioByDni(@PathParam("dni") String dni) {
        Response response;
        Socio s=sociosDAO.buscaByDNI(dni);
        if( s!=null) {
            response= Response.ok(s).build();
        } else {
            List<Map<String,Object>> errores=new ArrayList<>();
            Map<String,Object> err=new HashMap<>();
            err.put("message", "El socio no existe");
            errores.add(err);
            response=Response.status(Response.Status.BAD_REQUEST)
                    .entity(errores).build();
        }
        return response;
    }

    @DELETE
    @Path("/{id}")
    public Response borraSocios(@PathParam("id") int id) {
        Response response;

        if (sociosDAO.borra(id) == true) {
            response= Response.ok(id).build();
        } else {
            List<Map<String,Object>> errores=new ArrayList<>();
            Map<String,Object> err=new HashMap<>();
            err.put("message", "El socio no existe");
            errores.add(err);
            response=Response.status(Response.Status.BAD_REQUEST)
                    .entity(errores).build();
        }

        return response;
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response creaSocio(@Valid Socio s) {
        Response response;
        if ( sociosDAO.crea(s) == true) {
            response= Response.ok(s).build();
        } else {
            List< Map<String,Object> > errores=new ArrayList<>();
            Map<String,Object> err=new HashMap<>();
            err.put("message", "No se ha podido crear el socio");
            err.put("socio", s);
            errores.add(err);
            response=Response.status(Response.Status.BAD_REQUEST)
                    .entity(errores).build();
        }
        return response;
    }

    @PUT
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response modificaSocio(@Valid Socio s, @PathParam("id") Integer id) {
        Response response;
        s.setIdSocio(id);
        if ( sociosDAO.guarda(s)) {
            response= Response.ok(s).build();
        } else {
            List<Map<String,Object>> errores=new ArrayList<>();
            Map<String,Object> err=new HashMap<>(); //Error messages
            err.put("message", "No se ha podido modificar el socio");
            err.put("socio", s);
            errores.add(err);
            response=Response.status(Response.Status.BAD_REQUEST)
                    .entity(errores).build();
        }
        return response;
    }

}
