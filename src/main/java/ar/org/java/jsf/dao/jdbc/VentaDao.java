package ar.org.java.jsf.dao.jdbc;

import ar.org.java.jsf.model.DetalleVenta;
import ar.org.java.jsf.model.Venta;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;

public class VentaDao {
    private Connection conn;
    
    public VentaDao(Connection conn){this.conn = conn;}
    
    public void registrar(Venta venta, List<DetalleVenta> lista){
        try {
            
            PreparedStatement ps = conn.prepareStatement("insert into venta(fecha, codPersona, monto) values (?,?,?)");
            conn.setAutoCommit(false);
            ps.setDate(1, new java.sql.Date(venta.getFecha().getTime()));
            ps.setInt(2, venta.getPersona().getId());
            ps.setDouble(3, venta.getMonto());
            ps.executeUpdate();
            ps.close();
            
            PreparedStatement ps2 = conn.prepareStatement("select last_insert_id() from venta limit 1");
            int codVenta= 0;
            ResultSet rs = ps2.executeQuery();
            while(rs.next()){
                codVenta = rs.getInt(1);
            }
            rs.close();
            
            for(DetalleVenta detalle : lista){
            PreparedStatement ps3 = conn.prepareStatement("insert into detalleVenta(codVenta, codProducto, cantidad) values (?,?,?)");
            ps3.setInt(1, codVenta);
            ps3.setInt(2, detalle.getProducto().getCodigo());
            ps3.setInt(3, detalle.getCantidad());
            ps3.executeUpdate();
            ps3.close();
            }
            conn.commit();
        } catch (Exception e) {e.printStackTrace();}
    }
}
