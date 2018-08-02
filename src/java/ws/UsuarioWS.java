/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ws;

import com.google.gson.Gson;
import dao.UsuarioDAO;
import java.sql.SQLException;
import java.util.List;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Produces;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PUT;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.MediaType;
import modelo.Usuario;

/**
 * REST Web Service
 *
 * @author ronaldo.silva
 */
@Path("usuario")
public class UsuarioWS {

    @Context
    private UriInfo context;

    /**
     * Creates a new instance of UsuarioWS
     */
    public UsuarioWS() {
    }

    /**
     * Retrieves representation of an instance of ws.UsuarioWS
     * @return an instance of java.lang.String
     */
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String getJson() {
        //TODO return proper representation object
        return "Olá meu webservice manes";
    }
    
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("get/usuarios")
    public String getUsuarios() throws ClassNotFoundException, SQLException{
        Gson g = new Gson();
        UsuarioDAO dao = new UsuarioDAO();
        List<Usuario> usuarios = dao.getUsuarios();
        
        return g.toJson(usuarios);
    }
    
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("/inserir")
    public boolean inserirUsuario(String content){
        Gson g = new Gson();
        Usuario u = (Usuario) g.fromJson(content, Usuario.class);
        
        UsuarioDAO dao = new UsuarioDAO();
        return dao.inserirUsuario(u);
    }
    
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/deletar/{id}")
    public String deletarUsuario(@PathParam("id")int id){
        UsuarioDAO dao = new UsuarioDAO();
        if(dao.deletarUsuario(id)){
            return "true";
        }else{
            return "false";
        }
    }
    
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("/atualizar")
    public boolean atualizarUsuario(String content){
        Gson g = new Gson();
        Usuario u = (Usuario) g.fromJson(content, Usuario.class);
        
        UsuarioDAO dao = new UsuarioDAO();
        return dao.atualizarUsuario(u);
    }

    /**
     * PUT method for updating or creating an instance of UsuarioWS
     * @param content representation for the resource
     */
    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    public void putJson(String content) {
    }
}
