package com.mym.pfc.clases;

public class Numero {
	
	private int numero;
	private float pX;
	private float pY;
	
	public Numero(int numero, float pX, float pY){
		this.numero = numero;
		this.pX = pX;
		this.pY = pY;
	}

	public int getNumero() {
		return numero;
	}

	public void setNumero(int numero) {
		this.numero = numero;
	}

	public float getpX() {
		return pX;
	}

	public void setpX(float pX) {
		this.pX = pX;
	}

	public float getpY() {
		return pY;
	}

	public void setpY(float pY) {
		this.pY = pY;
	}
	
	public void setpXpY(float pX, float pY){
		this.pX = pX;
		this.pY = pY;
	}
	
	
	

}
