package ar.org.java.jsf.bean;

import ar.org.java.jsf.dao.Connectors.Connector;
import ar.org.java.jsf.dao.jdbc.VentaDao;
import ar.org.java.jsf.model.DetalleVenta;
import ar.org.java.jsf.model.Producto;
import ar.org.java.jsf.model.Venta;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

@ManagedBean
@ViewScoped
public class VentaBean {
    private Venta venta = new Venta();
    private Producto producto = new Producto();
    private int cantidad;
    private List<DetalleVenta>lista = new ArrayList();
    private VentaDao ventaDao = new VentaDao(Connector.getConnection());
    
    public List<DetalleVenta>getLista(){return lista;}
    public void setLista(List<DetalleVenta> lista){this.lista = lista;}
    
    public int getCantidad(){return cantidad;}
    public void setCantidad(int cantidad){this.cantidad = cantidad;}
    
    public Producto getProducto(){return producto;}
    public void setProducto(Producto producto){this.producto = producto;}
    
    public Venta getVenta(){return venta;}
    public void setVenta(Venta venta){this.venta = venta;}
    
    public void agregar(){
        DetalleVenta detalleVenta = new DetalleVenta(); 
        detalleVenta.setCantidad(cantidad);
        detalleVenta.setProducto(producto);
        this.lista.add(detalleVenta); 
    }
    
    public void registrar(){
        double monto = 0;
        try{
        for(DetalleVenta detalle : lista){
            monto += detalle.getProducto().getPrecio()*detalle.getCantidad();
        }
        venta.setMonto(monto);
        venta.setFecha(Calendar.getInstance().getTime());
        ventaDao.registrar(venta, lista);
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,"Aviso","Exito"));
        }catch(Exception e){
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL,"Aviso","Error"));        
        }
        FacesContext.getCurrentInstance().getExternalContext().getFlash().setKeepMessages(true);
    }
    
}
