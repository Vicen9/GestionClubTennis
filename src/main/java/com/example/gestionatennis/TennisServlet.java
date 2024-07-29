package com.example.gestionatennis;

import java.io.*;

import jakarta.faces.annotation.FacesConfig;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

@WebServlet(name = "tennis", value = "/tennis")
public class TennisServlet extends HttpServlet {
    private String message;

    public void init() {}

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("text/html");
    }


    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String nombre = request.getParameter("nombre");
        String apellidos = request.getParameter("apellidos");
        String dni = request.getParameter("dni");
        Integer telefono = Integer.parseInt(request.getParameter("telefono"));
        Integer edad = Integer.parseInt(request.getParameter("edad"));
        Integer nivel = Integer.parseInt(request.getParameter("nivel"));
        String password = request.getParameter(request.getParameter("password"));
    }

    public void destroy() {
    }

    @FacesConfig
    public static class AppConfig{}
}
