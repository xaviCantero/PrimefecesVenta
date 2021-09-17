package ar.org.java.jsf.model;

public class Producto {
    private int codigo;
    private String nombre;
    private double precio;
    
    public Producto(){}
    public Producto(String nombre, double precio){
        this.nombre = nombre;
        this.precio = precio;
    }
    public Producto(int codigo, String nombre, double precio){
        this.codigo = codigo;
        this.nombre = nombre;
        this.precio = precio;
    }
    
    public int getCodigo(){return codigo;}
    public void setCodigo(int codigo){this.codigo = codigo;}
    
    public String getNombre(){return nombre;}
    public void setNombre(String nombre){this.nombre = nombre;}
    
    public double getPrecio(){return precio;}
    public void setPrecio(double precio){this.precio = precio;}

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 97 * hash + this.codigo;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Producto other = (Producto) obj;
        if (this.codigo != other.codigo) {
            return false;
        }
        return true;
    }
    
    
    @Override
    public String toString(){
        return "Codigo:"+codigo+". "+nombre+". "+precio;
    }
    
}
