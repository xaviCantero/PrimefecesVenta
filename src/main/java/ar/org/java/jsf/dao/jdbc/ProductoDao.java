package ar.org.java.jsf.dao.jdbc;

import ar.org.java.jsf.model.Producto;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class ProductoDao {
    private Connection conn;
    //private Producto producto;
    public ProductoDao(Connection conn){
        this.conn = conn;
    }
    
    public void registrar(Producto producto){
        try (PreparedStatement ps = 
                conn.prepareStatement("insert into producto (nombre, precio) values(?,?)")){
            ps.setString(1, producto.getNombre());
            ps.setDouble(2, producto.getPrecio());
            ps.execute();
        } catch (Exception e) {e.printStackTrace();}
    }
    
    public List<Producto>listar(){
        List<Producto>lista=new ArrayList();
        try (ResultSet rs = conn.createStatement().executeQuery("select * from producto")){
            while(rs.next()){
                lista.add(new Producto(
                        rs.getInt("codigo"),
                        rs.getString("nombre"),
                        rs.getDouble("precio")
                ));
            }
        } catch (Exception e) {e.printStackTrace();}
        return lista;
    }
    
    public Producto leerCodigo(Producto producto){
        Producto producTemp = null;
        try (PreparedStatement ps = 
                conn.prepareStatement("select codigo, nombre, precio from producto where codigo=?")){
            ps.setInt(1, producto.getCodigo());
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                producTemp = new Producto(
                rs.getInt("codigo"),
                rs.getString("nombre"),
                rs.getDouble("precio")
                );
            }
        } catch (Exception e) {e.printStackTrace();}
        return producTemp;
    }
    
    public void modificar(Producto producto){
        try (PreparedStatement ps = conn.prepareStatement("update producto set nombre=?, precio=? where codigo=?")){
                ps.setString(1, producto.getNombre());
                ps.setDouble(2, producto.getPrecio());
                ps.setInt(3, producto.getCodigo());
                ps.executeUpdate();
        } catch (Exception e) {e.printStackTrace();}
    }
    
    public void eliminar(Producto producto){
        try (PreparedStatement ps = conn.prepareStatement("delete from producto where codigo=?")){
            ps.setInt(1, producto.getCodigo());
            ps.execute();
        } catch (Exception e) {e.printStackTrace();}
    }
}
