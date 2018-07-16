package jcapax.jfacturador.controllers;

import java.math.BigInteger;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import jcapax.jfacturador.model.DetalleVenta;
import jcapax.jfacturador.model.Factura;
import jcapax.jfacturador.model.Venta;

public class HandlerDAOImpl implements HandlerDAO {

	Connection cnxInter = null;
	Connection cnxLocal = null;

	public HandlerDAOImpl(Connection cnxInter, Connection cnxLocal) {
		this.cnxInter = cnxInter;
		this.cnxLocal = cnxLocal;
	}

	@Override
	public ArrayList<Venta> getListaVenta(int anio, int mes) {
		ArrayList<Venta> listaVentas = new ArrayList<Venta>();

		String sql = "select * from venta where extract(year from fechaHora) = ?"
				+ " and extract(month from fechaHora) = ?";
		PreparedStatement ps;
		try {
			ps = cnxInter.prepareStatement(sql);
			ps.setInt(1, anio);
			ps.setInt(2, mes);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				Venta v = new Venta();

				v.setFechaHora(rs.getDate("fechaHora"));
				v.setId(rs.getInt("id"));
				v.setIdCaja(rs.getInt("idCaja"));
				v.setIdMaquina(rs.getInt("idMaquina"));
				v.setIdSucursal(rs.getInt("idSucursal"));
				v.setTipoPrecio(rs.getInt("tipoPrecio"));

				listaVentas.add(v);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return listaVentas;
	}

	@Override
	public ArrayList<DetalleVenta> getListaDetalleVenta(int anio, int mes) {
		ArrayList<DetalleVenta> listaDetalleVentas = new ArrayList<DetalleVenta>();

		String sql = "SELECT dv.id, dv.idVenta, dv.idProducto, dv.cantidad, dv.costoUnitario,"
				+ " dv.precioUnitario, dv.iceUnitario, dv.precioTotal, dv.iceTotal, dv.alicuota"
				+ " FROM detalleVenta dv join venta v on dv.idVenta = v.id" + " where extract(year from fechaHora) = ?"
				+ " and extract(month from fechaHora) = ?";
		PreparedStatement ps;
		try {
			ps = cnxInter.prepareStatement(sql);
			ps.setInt(1, anio);
			ps.setInt(2, mes);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				DetalleVenta dv = new DetalleVenta();

				dv.setAlicuota(rs.getDouble("alicuota"));
				dv.setCantidad(rs.getDouble("cantidad"));
				dv.setCostoUnitario(rs.getDouble("costoUnitario"));
				dv.setIceTotal(rs.getDouble("iceTotal"));
				dv.setIceUnitario(rs.getDouble("iceUnitario"));
				dv.setId(rs.getInt("id"));
				dv.setIdProducto(rs.getInt("idProducto"));
				dv.setIdVenta(rs.getInt("idVenta"));
				dv.setPrecioTotal(rs.getDouble("precioTotal"));
				dv.setPrecioUnitario(rs.getDouble("precioUnitario"));

				listaDetalleVentas.add(dv);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return listaDetalleVentas;

	}

	@Override
	public ArrayList<Factura> getListaFactura(int anio, int mes) {
		ArrayList<Factura> listaFacturas = new ArrayList<Factura>();

		String sql = "select * from factura where extract(year from fechaFactura) = ?"
				+ " and extract(month from fechaFactura) = ?";
		PreparedStatement ps;
		try {
			ps = cnxInter.prepareStatement(sql);
			ps.setInt(1, anio);
			ps.setInt(2, mes);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				Factura f = new Factura();

				f.setCodigoControl(rs.getString("codigoControl"));
				f.setCorrelativoSucursal(rs.getInt("correlativoSucursal"));
				f.setDebitoFiscal(rs.getDouble("debitoFiscal"));
				f.setEspecificacion(rs.getInt("especificacion"));
				f.setEstado(rs.getString("estado"));
				f.setFechaFactura(rs.getDate("fechaFactura"));
				f.setFechaLimiteEmision(rs.getDate("fechaLimiteEmision"));
				f.setId(rs.getInt("id"));
				f.setIdDosificacion(rs.getInt("idDosificacion"));
				f.setIdSucursal(rs.getInt("idSucursal"));
				f.setIdVenta(rs.getInt("idVenta"));
				f.setImporteBaseDebitoFiscal(rs.getDouble("importeBaseDebitoFiscal"));
				f.setImporteExportaciones(rs.getDouble("importeExportaciones"));
				f.setImporteIce(rs.getDouble("importeIce"));
				f.setImporteRebajas(rs.getDouble("importeRebajas"));
				f.setImporteSubtotal(rs.getDouble("importeSubtotal"));
				f.setImporteTotal(rs.getDouble("importeTotal"));
				f.setImporteVentasTasaCero(rs.getDouble("importeVentasTasaCero"));
				f.setNit(rs.getString("nit"));
				f.setNroAutorizacion(rs.getString("nroAutorizacion"));
				f.setNroFactura(rs.getInt("nroFactura"));
				f.setRazonSocial(rs.getString("razonSocial"));

				listaFacturas.add(f);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return listaFacturas;
	}

	@Override
	public void insertarVentaInter2Local(ArrayList<Venta> listaVenta) {
		String sql = "insert into ventaInternet(id, idCaja, idSucursal, idMaquina, tipoPrecio, usuario, fechaHora)"
				+ "values(?,?,?,?,?,?,?)";
		try {
			for (int i = 0; i < listaVenta.size(); i++) {

				PreparedStatement ps = cnxLocal.prepareStatement(sql);

				ps.setInt(1, listaVenta.get(i).getId());
				ps.setInt(2, listaVenta.get(i).getIdCaja());
				ps.setInt(3, listaVenta.get(i).getIdSucursal());
				ps.setInt(4, listaVenta.get(i).getIdMaquina());
				ps.setInt(5, listaVenta.get(i).getTipoPrecio());
				ps.setString(6, "XXX");
				ps.setDate(7, listaVenta.get(i).getFechaHora());

				ps.executeUpdate();
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	@Override
	public void eliminarVentaInternetDescargada(int anio, int mes) {
		String sql = "delete from ventainternet where extract(year from fechaHora) = ?"
				+ " and extract(month from fechaHora) = ?";
		try {
			PreparedStatement ps = cnxLocal.prepareStatement(sql);
			ps.setInt(1, anio);
			ps.setInt(2, mes);
			ps.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	@Override
	public void eliminarDetalleVentaInternetDescargada() {
		String sql = "delete from detalleVentaInternet where idVenta not in(select id from ventaInternet)";
		try {
			PreparedStatement ps = cnxLocal.prepareStatement(sql);
			ps.execute();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	@Override
	public void insertarDetalleVentaInter2Local(ArrayList<DetalleVenta> listaDetalleVenta) {
		String sql = "insert into detalleventainternet(id,idVenta,idProducto,cantidad,"
				+ "costoUnitario,precioUnitario,iceUnitario,precioTotal,iceTotal,alicuota)"
				+ "values(?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
		try {
			for (int i = 0; i < listaDetalleVenta.size(); i++) {

				PreparedStatement ps = cnxLocal.prepareStatement(sql);

				ps.setInt(1, listaDetalleVenta.get(i).getId());
				ps.setInt(2, listaDetalleVenta.get(i).getIdVenta());
				ps.setInt(3, listaDetalleVenta.get(i).getIdProducto());
				ps.setDouble(4, listaDetalleVenta.get(i).getCantidad());
				ps.setDouble(5, listaDetalleVenta.get(i).getCostoUnitario());
				ps.setDouble(6, listaDetalleVenta.get(i).getPrecioUnitario());
				ps.setDouble(7, listaDetalleVenta.get(i).getIceUnitario());
				ps.setDouble(8, listaDetalleVenta.get(i).getPrecioTotal());
				ps.setDouble(9, listaDetalleVenta.get(i).getIceTotal());
				ps.setDouble(10, listaDetalleVenta.get(i).getAlicuota());

				ps.executeUpdate();
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	@Override
	public void eliminarFacturaInternetDescargada(int anio, int mes) {
		String sql = "delete from facturaInternet where extract(year from fechaFactura) = ?"
				+ " and extract(month from fechaFactura) = ?";

		try {
			PreparedStatement ps = cnxLocal.prepareStatement(sql);
			ps.setInt(1, anio);
			ps.setInt(2, mes);
			ps.execute();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	@Override
	public void insertarFacturaItenr2Local(ArrayList<Factura> listaFactura) {
		String sql = "insert into facturainternet(id,idSucursal,especificacion,correlativoSucursal,"
				+ "fechaFactura,nroFactura,nroAutorizacion,estado," + "nit,razonSocial,importeTotal,importeIce,"
				+ "importeExportaciones,importeVentasTasaCero,importeSubtotal,"
				+ "importeRebajas,importeBaseDebitoFiscal,debitoFiscal,codigoControl,"
				+ "idVenta,fechaLimiteEmision,idDosificacion)"
				+ "values(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

		try {

			for (int i = 0; i < listaFactura.size(); i++) {

				PreparedStatement ps = cnxLocal.prepareStatement(sql);
				ps.setInt(1, listaFactura.get(i).getId());
				ps.setInt(2, listaFactura.get(i).getIdSucursal());
				ps.setInt(3, listaFactura.get(i).getEspecificacion());
				ps.setInt(4, listaFactura.get(i).getCorrelativoSucursal());
				ps.setDate(5, listaFactura.get(i).getFechaFactura());
				ps.setInt(6, listaFactura.get(i).getNroFactura());
				ps.setString(7, listaFactura.get(i).getNroAutorizacion());
				ps.setString(8, listaFactura.get(i).getEstado());
				ps.setString(9, listaFactura.get(i).getNit());
				ps.setString(10, listaFactura.get(i).getRazonSocial());
				ps.setDouble(11, listaFactura.get(i).getImporteTotal());
				ps.setDouble(12, listaFactura.get(i).getImporteIce());
				ps.setDouble(13, listaFactura.get(i).getImporteExportaciones());
				ps.setDouble(14, listaFactura.get(i).getImporteVentasTasaCero());
				ps.setDouble(15, listaFactura.get(i).getImporteSubtotal());
				ps.setDouble(16, listaFactura.get(i).getImporteRebajas());
				ps.setDouble(17, listaFactura.get(i).getImporteBaseDebitoFiscal());
				ps.setDouble(18, listaFactura.get(i).getDebitoFiscal());
				ps.setString(19, listaFactura.get(i).getCodigoControl());
				ps.setInt(20, listaFactura.get(i).getIdVenta());
				ps.setDate(21, listaFactura.get(i).getFechaLimiteEmision());
				ps.setInt(22, listaFactura.get(i).getIdDosificacion());
				
				ps.execute();
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
