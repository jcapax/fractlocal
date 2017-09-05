package jcapax.facturador.dbutils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexionInternet {
	Connection c = null;
	public Connection cnx(){
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
			
			String servidor = "jdbc:mysql://sids.com.bo:3306/sucre_sureguila";
			String usuario = "sucre_root";
            String pass = "morecaca..";
            
            c = DriverManager.getConnection(servidor, usuario, pass);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
            
		catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
		return c;		
	}

}