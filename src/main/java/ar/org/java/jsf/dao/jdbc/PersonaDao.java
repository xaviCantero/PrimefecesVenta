package ar.org.java.jsf.dao.jdbc;

import ar.org.java.jsf.model.Persona;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class PersonaDao {

    private Connection conn;
    //private Persona persona;

    //public PersonaDao(){}
    public PersonaDao(Connection conn) {
        this.conn = conn;
    }

    public void registrar(Persona persona) {
        //if(persona == null) return;
        try ( PreparedStatement ps = conn.prepareStatement("insert into Persona (nombre, sexo) values(?,?)"
        //,PreparedStatement.RETURN_GENERATED_KEYS
        )) {
            ps.setString(1, persona.getNombre());
            ps.setString(2, persona.getSexo());
            ps.execute();
            //ResultSet rs = ps.getGeneratedKeys();
            //if(rs.next()) persona.setCodigo(rs.getInt(1));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public List<Persona> listar() {
        List<Persona> lista = new ArrayList();
        try ( ResultSet rs = conn.createStatement().executeQuery("select * from Persona")) {
            while (rs.next()) {
                lista.add(new Persona(
                        rs.getInt("id"),
                        rs.getString("nombre"),
                        rs.getString("sexo")
                ));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return lista;
    }
    
    public Persona leerId(Persona persona){
        ResultSet rs;
        Persona persTemp = null;
        try (PreparedStatement ps = conn.prepareStatement("select id, nombre, sexo from Persona where id=?")){
            ps.setInt(1, persona.getId());
            rs = ps.executeQuery();
            while(rs.next()){
                persTemp = new Persona(
                rs.getInt("id"),
                rs.getString("nombre"),
                rs.getString("sexo")
                );
            }
        } catch (Exception e) {e.printStackTrace();}
        return persTemp;
    }
    
    public void modificar(Persona persona){
        try (PreparedStatement ps = conn.prepareStatement("update Persona set nombre=?, sexo=? where id=?")){
            ps.setString(1, persona.getNombre());
            ps.setString(2, persona.getSexo());
            ps.setInt(3, persona.getId());
            ps.executeUpdate();
        } catch (Exception e) {e.printStackTrace();}
    }
    
        public void eliminar(Persona persona){
        try (PreparedStatement ps = conn.prepareStatement("delete from Persona where id=?")){
            ps.setInt(1, persona.getId());
            ps.executeUpdate();
        } catch (Exception e) {e.printStackTrace();}
    }
}
