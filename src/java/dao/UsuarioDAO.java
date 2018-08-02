package dao;

import controle.C;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import modelo.Usuario;

public class UsuarioDAO {
    
    private PreparedStatement pst;
    private ResultSet rs;
    private Connection con;
    private String sql;
    
    public List<Usuario> getUsuarios() throws ClassNotFoundException, SQLException{
        List<Usuario> usuarios = new ArrayList<>();
        Usuario u = null;
        sql = "SELECT * FROM usuario;";
        con = C.cb();
        pst = con.prepareStatement(sql);
        rs = pst.executeQuery();
        while(rs.next()){
            u = new Usuario();
            u.setId(rs.getInt("id"));
            u.setNome(rs.getString("nome"));
            u.setSenha(rs.getString("senha"));
            
            usuarios.add(u);
        }
        C.db();
        return usuarios;
    }

    public boolean inserirUsuario(Usuario u){
        try {
            sql = "INSERT INTO usuario (nome,senha) VALUES (?,?);";
            con = C.cb();
            pst = con.prepareStatement(sql);
            pst.setString(1, u.getNome());
            pst.setString(2, u.getSenha());
            pst.execute();
            C.db();
            return true;
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(UsuarioDAO.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }
    
    public boolean atualizarUsuario(Usuario u){
        try {
            sql = "UPDATE usuario SET nome = ?, senha = ? WHERE id = ?;";
            con = C.cb();
            pst = con.prepareStatement(sql);
            pst.setString(1, u.getNome());
            pst.setString(2, u.getSenha());
            pst.setInt(3, u.getId());
            pst.executeUpdate();
            C.db();
            return true;
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(UsuarioDAO.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }
    
    public boolean deletarUsuario(int id){
        try {
            sql = "DELETE FROM usuario WHERE id = ?;";
            con = C.cb();
            pst = con.prepareStatement(sql);
            pst.setInt(1, id);
            pst.execute();
            C.db();
            return true;
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(UsuarioDAO.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }
}
