package ar.org.java.jsf.test;

import ar.org.java.jsf.dao.Connectors.Connector;
import ar.org.java.jsf.dao.jdbc.PersonaDao;
import ar.org.java.jsf.model.Persona;

public class PersonaTest {
    public static void main(String[] args) {
        PersonaDao personaDao = new PersonaDao(Connector.getConnection());
        Persona persona;
        
        //Registrar
        /*
        persona = new Persona("Javier", "M");
        personaDao.registrar(persona);
        System.out.println(persona);
        */
        
        //Listar
        personaDao.listar().forEach(System.out::println);
    }
}
