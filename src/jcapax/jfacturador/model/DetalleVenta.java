package jcapax.jfacturador.model;

public class DetalleVenta {
	private int id;
	private int idVenta;
	private int idProducto;
	private double cantidad;
	private double costoUnitario;
	private double precioUnitario;
	private double iceUnitario;
	private double precioTotal;
	private double iceTotal;
	private double alicuota;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getIdVenta() {
		return idVenta;
	}
	public void setIdVenta(int idVenta) {
		this.idVenta = idVenta;
	}
	public int getIdProducto() {
		return idProducto;
	}
	public void setIdProducto(int idProducto) {
		this.idProducto = idProducto;
	}
	public double getCantidad() {
		return cantidad;
	}
	public void setCantidad(double cantidad) {
		this.cantidad = cantidad;
	}
	public double getCostoUnitario() {
		return costoUnitario;
	}
	public void setCostoUnitario(double costoUnitario) {
		this.costoUnitario = costoUnitario;
	}
	public double getPrecioUnitario() {
		return precioUnitario;
	}
	public void setPrecioUnitario(double precioUnitario) {
		this.precioUnitario = precioUnitario;
	}
	public double getIceUnitario() {
		return iceUnitario;
	}
	public void setIceUnitario(double iceUnitario) {
		this.iceUnitario = iceUnitario;
	}
	public double getPrecioTotal() {
		return precioTotal;
	}
	public void setPrecioTotal(double precioTotal) {
		this.precioTotal = precioTotal;
	}
	public double getIceTotal() {
		return iceTotal;
	}
	public void setIceTotal(double iceTotal) {
		this.iceTotal = iceTotal;
	}
	public double getAlicuota() {
		return alicuota;
	}
	public void setAlicuota(double alicuota) {
		this.alicuota = alicuota;
	}
	
	

}
