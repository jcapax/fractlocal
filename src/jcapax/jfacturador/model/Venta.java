package jcapax.jfacturador.model;

import java.sql.Date;

public class Venta {
	private int id;
	private int idCaja; 
	private int idMaquina;
	private int idSucursal;
	private int tipoPrecio;
	private Date fechaHora;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getIdCaja() {
		return idCaja;
	}
	public void setIdCaja(int idCaja) {
		this.idCaja = idCaja;
	}
	public int getIdMaquina() {
		return idMaquina;
	}
	public void setIdMaquina(int idMaquina) {
		this.idMaquina = idMaquina;
	}
	public int getIdSucursal() {
		return idSucursal;
	}
	public void setIdSucursal(int idSucursal) {
		this.idSucursal = idSucursal;
	}
	public int getTipoPrecio() {
		return tipoPrecio;
	}
	public void setTipoPrecio(int tipoPrecio) {
		this.tipoPrecio = tipoPrecio;
	}
	public Date getFechaHora() {
		return fechaHora;
	}
	public void setFechaHora(Date fechaHora) {
		this.fechaHora = fechaHora;
	}
	
}
