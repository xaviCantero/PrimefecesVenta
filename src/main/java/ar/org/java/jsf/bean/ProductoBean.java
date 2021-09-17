package ar.org.java.jsf.bean;

import ar.org.java.jsf.dao.Connectors.Connector;
import ar.org.java.jsf.dao.jdbc.ProductoDao;
import ar.org.java.jsf.model.Producto;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

@ManagedBean
@ViewScoped
public class ProductoBean {
    private ProductoDao productoDao = new ProductoDao(Connector.getConnection());
    private Producto producto = new Producto();
    private List<Producto>listarProducto;
    private String action;

    public Producto getProducto(){return producto;}

    public void setProducto(Producto producto){this.producto = producto;}

    public List<Producto> getListarProducto(){return listarProducto;}

    public void setListarProducto(List<Producto>listarProducto){this.listarProducto = listarProducto;}

    public String getAction(){return action;}

    public void setAction(String action){this.action = action;}
    
    public boolean isPostBack(){
        return FacesContext.getCurrentInstance().isPostback();
    }
    
    public void operar(){
        switch(action){
            case "registrar":
                this.registrar();
                this.limpiar();
                break;
            case "modificar":
                this.modificar();
                this.limpiar();
                break;
        }
    }
    
    public void limpiar(){
        this.producto.setCodigo(0);
        this.producto.setNombre("");
        this.producto.setPrecio(0);
    }
    
    public void registrar(){
        productoDao.registrar(producto);
        this.listar("V");
    }
    
    public void modificar(){
        productoDao.modificar(producto);
        this.listar("V");
    }
    
    public void listar(String valor){
        if(valor.equals("F")){
            if(isPostBack() == false){
            listarProducto = productoDao.listar();
            }
        }else{
            listarProducto = productoDao.listar();
        }
    }
    
    public void leerCodigo(Producto producto){
        Producto producTemp;
        producTemp = productoDao.leerCodigo(producto);
        if(producTemp != null){
            this.producto = producTemp;
            this.action = "modificar";
        }
    }
    
    public void eliminar(Producto producto){
        productoDao.eliminar(producto);
        this.listar("V");
    }
}
