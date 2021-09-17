package ar.org.java.jsf.bean;

import ar.org.java.jsf.dao.Connectors.Connector;
import ar.org.java.jsf.dao.jdbc.PersonaDao;
import ar.org.java.jsf.model.Persona;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

@ManagedBean
@ViewScoped
public class PersonaBean {
    private PersonaDao personaDao = new PersonaDao(Connector.getConnection());
    private Persona persona = new Persona();
    private List<Persona>listaPersona;
    private String accion;
    
    public String getAccion(){return accion;}
    public void setAccion(String accion){
        this.limpiar();
        this.accion = accion;
    }
    
    public List<Persona> getListaPersona(){return listaPersona;}
    public void setListaPersona(List<Persona> listaPersona){this.listaPersona = listaPersona;}    
    
    public Persona getPersona() {return persona;}
    public void setPersona(Persona persona) {this.persona = persona;}
    
    public boolean isPostBack(){
        return FacesContext.getCurrentInstance().isPostback();
    }
    
    public void operar(){
        switch(accion){
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
        this.persona.setId(0);
        this.persona.setNombre("");
        this.persona.setSexo("");
    }
    
    private void registrar(){
        personaDao.registrar(persona);
        this.listar("V");
    } 
    
    private void modificar(){
        personaDao.modificar(persona);
        this.listar("V");
    }
    
    public void listar(String valor){
        if(valor.equals("F")){
            if(isPostBack() == false){
            listaPersona = personaDao.listar();
            }
        }else{
            listaPersona = personaDao.listar();
        }
    }
    
    public void leerID(Persona persona){
        Persona persTemp;
        persTemp = personaDao.leerId(persona);
        if(persTemp != null){
            this.persona = persTemp;
            this.accion = "modificar";
        }
    }

    public void eliminar(Persona persona){
        personaDao.eliminar(persona);
        this.listar("V");
    }

}
