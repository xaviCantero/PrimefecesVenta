package ar.org.java.jsf.model;

public class Persona {
    private int id;
    private String nombre;
    private String sexo; 
    
    public Persona(){}
    public Persona(String nombre, String sexo){
        this.nombre = nombre;
        this.sexo = sexo;
    }
    public Persona(int id, String nombre, String sexo){
        this.id = id;
        this.nombre = nombre;
        this.sexo = sexo;
    }
    
    public int getId(){return id;}
    public void setId(int id){this.id = id;}
    
    public String getNombre(){return nombre;}
    public void setNombre(String nombre){this.nombre = nombre;}

    public String getSexo(){return sexo;}
    public void setSexo(String sexo){this.sexo = sexo;}

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 59 * hash + this.id;
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
        final Persona other = (Persona) obj;
        return true;
    }
    
    
    public String toString(){
        return "Id:"+id+". Nombre:"+nombre+", Sexo:"+sexo;
    }
}
