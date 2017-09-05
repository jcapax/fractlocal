package jcapax.facturador.dbutils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexionLocal {
	Connection c = null;
	public Connection cnx(){
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
			
			String servidor = "jdbc:mysql://192.168.1.201:3306/dbfacturador";
			String usuario = "root";
            String pass = "mysqlroot";
            
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
