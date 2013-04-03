package com.mym.pfc.clases;

import java.util.ArrayList;

/**
 * 
 * @author Maria Teresa Pérez Garvín
 *
 */

public class ContenedorObjetos {
	
	static float PADDING = 60; //porcentage de contenedor usado para el padding total de los objetos
	static float POSITION = 40; //tamaño del contenedor usado para los objetos 

	private int id;
	private String nombre; 
	private float anchuraX;
	private float alturaY;
	private float numeroObjetos;
	private ArrayList<Posicion> posicionesObjetos;
	private boolean tocado;
	private float padding_unitario = 0, position_unitario = 0;
	
	private float[] m = new float[2];
	private float[] n = new float[2];
	private float[] o = new float[2];
	private float[] p = new float[2];
	private float x1, x2, y1, y2;

	
	public ContenedorObjetos (int id, String nombre, float anchuraX, float alturaY, int numeroObjetos){
		
		this.id = id;
		this.nombre = nombre; 
		this.anchuraX = anchuraX;
		this.alturaY = alturaY;
		this.numeroObjetos = numeroObjetos;
		
		posicionesObjetos = new ArrayList<Posicion>();
		this.tocado = false;
		generarPosiciones();
		
	}
	
	public void generarPosiciones(){
		/*Para la repartición equitativa de la pantalla entre todos los contenedores de
		 * objetos que pueda haber, los objetos se repartiran en los contenedores formando 
		 * un cuadrado */
		
		/*Para saber que cantidad de objetos habrá en X y en Y, hacemos la raiz cuadrada del 
		* numero de objetos totales que tendrá en contenedor*/
		float raizNumeroObjetos = (float) Math.sqrt(numeroObjetos);		
		
		//Mirar ambas funciones cuando se pruebe. No se cual funciona mejor
		//int numTotalObjetosXY = ((int) Math.floor(raizNumeroObjetos));
		float numTotalObjetosXY; 
		if((raizNumeroObjetos - (int) raizNumeroObjetos) == 0){
			numTotalObjetosXY = (int) raizNumeroObjetos;
		}else{
			numTotalObjetosXY = ((int) raizNumeroObjetos)+1;
		}
		
		padding_unitario = PADDING / (numTotalObjetosXY + 1);
		x1 = padding_unitario;
		y1 = padding_unitario;
		
		position_unitario = POSITION / (numTotalObjetosXY);
		x2 = position_unitario + x1;
		y2 = position_unitario + y1;
		
		/*Colocamos los puntos en un vector que luego colocaremos en un objeto
		 * Posicion para así tener toda la lista de las posiciones de todos los Objetos*/
		
		Posicion primerObjeto = new Posicion();
		entrarPunts(x1, x2, y1, y2, primerObjeto);
		llenarArrayPosicionObjetos(numTotalObjetosXY, x1, x2, y1, y2);
		
	}
	
	private void llenarArrayPosicionObjetos(float numObjetosXY, 
		float x1, float x2, float y1, float y2){
		float noux1 = x1, noux2 = x2, nouy1 = y1, nouy2 = y2;
		Posicion objeto = new Posicion();
		//int objetosPosicionados = 0;
		int i = 0, j = 1;
		for (; i<numObjetosXY; i++){ //columnas
			for(; j<numObjetosXY; j++){ //filas
				/*if(objetosPosicionados < numeroObjetos){}*/
				noux1 = noux2 + padding_unitario;
				noux2 = noux1 + position_unitario;
				objeto = new Posicion();
				entrarPunts(noux1, noux2, nouy1, nouy2, objeto);				
			} 
			noux1 = 0;
			noux2 = noux1;
			nouy1 = nouy2 + padding_unitario;
			nouy2 = nouy1 + position_unitario; 
			j = 0;
			/*noux1 = x1;
			noux2 = x2;
			nouy2 = nouy2 + padding_unitario; 
			nouy1 = nouy2 + position_unitario;*/ 
			//entrarPunts(noux1, noux2, nouy1, nouy2, objeto);	y wel
			//entrarPunts(x1, x2, nouy1, nouy2, objeto);
		}
		
	}
	
	private void entrarPunts(float x1, float x2, float y1, float y2, Posicion objetoPos){
		m = new float[2];
		n = new float[2];
		o = new float[2];
		p = new float[2];
		m[0] = x1; m[1] = y2;
		n[0] = x2; n[1] = y2;
		o[0] = x1; o[1] = y1;
		p[0] = x2; p[1] = y1;	
		objetoPos.setVertices(m, n, o, p);
		posicionesObjetos.add(objetoPos);
	}

	
	public ArrayList<Posicion> getPosiciones() {
		return posicionesObjetos;
		
	}
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public float getAnchuraX() {
		return anchuraX;
	}

	public void setAnchuraX(float anchuraX) {
		this.anchuraX = anchuraX;
	}

	public float getAlturaY() {
		return alturaY;
	}

	public void setAlturaY(float alturaY) {
		this.alturaY = alturaY;
	}

	public float getNumeroObjetos() {
		return numeroObjetos;
	}

	public void setNumeroObjetos(float numeroObjetos) {
		this.numeroObjetos = numeroObjetos;
	}

	public boolean isTocado() {
		return tocado;
	}

	public void setTocado(boolean tocado) {
		this.tocado = tocado;
	}
	
}
