package ar.org.java.jsf.test;

import ar.org.java.jsf.dao.Connectors.Connector;
import ar.org.java.jsf.dao.jdbc.ProductoDao;
import ar.org.java.jsf.model.Producto;


public class ProductoTest {
    public static void main(String[] args) {
        ProductoDao productoDao = new ProductoDao(Connector.getConnection());
        
        //Registrar
        /*
        Producto producto = new Producto("Mouse", 650);
        productoDao.registrar(producto);
        */
        //Leer producto por codigo
        /*Producto producto1 = new Producto();
        producto1.setCodigo(4);
        System.out.println(productoDao.leerId(producto1));
        */
        //Modificar Producto
        /*Producto producto2 = new Producto();
        producto2.setCodigo(5);
        producto2.setNombre("Mouse Gammer");
        producto2.setPrecio(650);
        productoDao.modificar(producto2);
        */
        //Eliminar
        /*Producto producto3 = new Producto();
        producto3.setCodigo(4);
        productoDao.eliminar(producto3);
        */
                //Listar
        productoDao.listar().forEach(System.out::println);
        
    }
}
