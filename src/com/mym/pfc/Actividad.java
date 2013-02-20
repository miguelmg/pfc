package com.mym.pfc;

public class Actividad {
	
	private String titulo;
	private String recursos;
	private int cantidad;
	private String objetivo;
	
	public Actividad(){
	
	}
	
	public Actividad(String titulo, String recursos, int cantidad, String objetivo){
		this.titulo = titulo;
		this.recursos = recursos;
		this.cantidad = cantidad;
		this.objetivo = objetivo;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public String getRecursos() {
		return recursos;
	}

	public void setRecursos(String recursos) {
		this.recursos = recursos;
	}

	public int getCantidad() {
		return cantidad;
	}

	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}

	public String getObjetivo() {
		return objetivo;
	}

	public void setObjetivo(String objetivo) {
		this.objetivo = objetivo;
	}

}
