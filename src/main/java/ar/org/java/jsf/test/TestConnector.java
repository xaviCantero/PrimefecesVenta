package ar.org.java.jsf.test;

import ar.org.java.jsf.dao.Connectors.Connector;
import java.sql.SQLException;

public class TestConnector {
    public static void main(String[] args) throws SQLException{
    
        Connector.getConnection().createStatement()
                .executeUpdate("insert into persona(nombre, sexo) values ('Javier','M')");
    }
}
