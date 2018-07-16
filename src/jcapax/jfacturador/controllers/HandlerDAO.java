package jcapax.jfacturador.controllers;

import java.util.ArrayList;

import jcapax.jfacturador.model.DetalleVenta;
import jcapax.jfacturador.model.Factura;
import jcapax.jfacturador.model.Venta;

public interface HandlerDAO {
	public ArrayList<Venta> getListaVenta(int anio, int mes);
	public void eliminarVentaInternetDescargada(int anio, int mes);
	public void insertarVentaInter2Local(ArrayList<Venta> listaVenta);
	
	public ArrayList<DetalleVenta> getListaDetalleVenta(int anio, int mes);
	public void eliminarDetalleVentaInternetDescargada();
	public void insertarDetalleVentaInter2Local(ArrayList<DetalleVenta> listaDetalleVenta);
	
	public ArrayList<Factura> getListaFactura(int anio, int mes);
	public void eliminarFacturaInternetDescargada(int anio, int mes);
	public void insertarFacturaItenr2Local(ArrayList<Factura> listaFactura);
	

}
