package com.mym.pfc.clases;

import java.util.Vector;

public class Posicion {
	
	//VERTICES//
	
	/*    m  ______  n
	 *   	|      |
	 * 		|      |
	 * 		|______|
	 *    o          p
	 */

	
	private float[][] puntos = new float[4][2];
	
	public Posicion(){}
	
	public Posicion(float[] m, float[] n, float[] o, float[] p){
		this.puntos[0] = m;
		this.puntos[1] = n;
		this.puntos[2] = o;
		this.puntos[3] = p;
	}
	
	public void setVertices(float[] m, float[] n, float[] o, float[] p){
		this.puntos[0] = m;
		this.puntos[1] = n;
		this.puntos[2] = o;
		this.puntos[3] = p;
	}
	
	public float[][] getVertices (){
		return puntos;
	}

	public float[] getM() {
		return puntos[0];
	}

	public void setM(float[] m) {
		this.puntos[0] = m;
	}

	public float[] getN() {
		return puntos[1];
	}

	public void setN(float[] n) {
		this.puntos[1] = n;
	}

	public float[] getO() {
		return puntos[2];
	}

	public void setO(float[] o) {
		this.puntos[2] = o;
	}

	public float[] getP() {
		return puntos[3];
	}

	public void setP(float[] p) {
		this.puntos[3] = p;
	}

}
