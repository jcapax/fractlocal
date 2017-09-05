package jcapax.jfacturador.app;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;



import jcapax.facturador.dbutils.ConexionLocal;

public class Main {

	public static void main(String[] args) {
		ConexionLocal local = new ConexionLocal();
		Connection cnxLocal = local.cnx();
		
		listarFacturas(cnxLocal);
		

	}

	private static void listarFacturas(Connection cnxLocal) {
		String sql = "select * from factura order by id desc limit 20";
		
		try {
			PreparedStatement ps = cnxLocal.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while(rs.next()){
				System.out.println(rs.getInt("id")+ " "+ rs.getString("razonSocial")+" "+rs.getString("nit"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}

}
