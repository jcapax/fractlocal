package jcapax.jfacturador.app;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import jcapax.facturador.dbutils.ConexionInternet;
import jcapax.facturador.dbutils.ConexionLocal;
import jcapax.jfacturador.controllers.HandlerDAO;
import jcapax.jfacturador.controllers.HandlerDAOImpl;
import jcapax.jfacturador.model.DetalleVenta;
import jcapax.jfacturador.model.Factura;
import jcapax.jfacturador.model.Venta;

public class Main {

	public static void main(String[] args) {
		ConexionLocal local = new ConexionLocal();
		Connection cnxLocal = local.cnx();
		
		ConexionInternet inter = new ConexionInternet();
		Connection cnxInter = inter.cnx();
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		try {
			System.out.println("ingresar año:");
			String anio_string = br.readLine();
			
			System.out.println("ingresar mes:");
			String mes_string  = br.readLine();

			int anio = Integer.parseInt(anio_string);
			int mes = Integer.parseInt(mes_string);
			
			insertarVentas(cnxInter, cnxLocal, anio, mes);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
//		listaVentasInter(cnxInter, 2017, 8);
		
//		listaFacturas(cnxInter, 2017, 8);
		
//		listarFacturas(cnxLocal);
		
	}

	private static void insertarVentas(Connection cnxInter, Connection cnxLocal, int anio, int mes) {
		HandlerDAO venta = new HandlerDAOImpl(cnxInter, cnxLocal);
		
		venta.eliminarVentaInternetDescargada(anio, mes);
		venta.eliminarDetalleVentaInternetDescargada();
		
		ArrayList<Venta> listaVentas = venta.getListaVenta(anio, mes);
		ArrayList<DetalleVenta> listaDetalleVenta = venta.getListaDetalleVenta(anio, mes);
		
		venta.insertarVentaInter2Local(listaVentas);
		System.out.println("ventas insertadas con exito");
		
		venta.insertarDetalleVentaInter2Local(listaDetalleVenta);
		System.out.println("detalleventa insertadas con exito");
		
		ArrayList<Factura> listaFactura = venta.getListaFactura(anio, mes);
		venta.eliminarFacturaInternetDescargada(anio, mes);
		venta.insertarFacturaItenr2Local(listaFactura);
		
		System.out.println("facturas insertadas con exito");
	}

	private static void listaFacturas(Connection cnxInter, int anio, int mes) {
		HandlerDAO inter = new HandlerDAOImpl(cnxInter, null);
		try {
			ArrayList<Factura> listaFacturas = inter.getListaFactura(anio, mes);
			System.out.println("Tamaño array: "+listaFacturas.size());
			
			for (int i = 0; i < listaFacturas.size(); i++) {
				System.out.println(listaFacturas.get(i).getId()+ " "+listaFacturas.get(i).getNit()+" "+listaFacturas.get(i).getFechaFactura());
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		
	}

	private static void listaVentasInter(Connection cnxInter, int anio, int mes){
		HandlerDAO venta = new HandlerDAOImpl(cnxInter, null);
		try {
			ArrayList<Venta> listaVentas = venta.getListaVenta(anio, mes);
			System.out.println("Tamaño array: "+listaVentas.size());
			
			for (int i = 0; i < listaVentas.size(); i++) {
				System.out.println(listaVentas.get(i).getId()+ " "+listaVentas.get(i).getFechaHora());
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
				
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
