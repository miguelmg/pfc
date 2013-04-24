package com.mym.pfc.modulos;

import org.andengine.entity.primitive.Rectangle;
import org.andengine.entity.scene.Scene;
import org.andengine.input.touch.TouchEvent;
import org.andengine.opengl.vbo.VertexBufferObjectManager;

import android.view.MotionEvent;

import com.mym.pfc.actividades.OrdenaNumeros;

public class Area {
	
	private float rectanguloX;
	private float rectanguloY;
	private float rectanguloWidth;
	private float rectanguloHeight; 
	private int numeroCorrectoOrdena;
	private boolean tocada;
	private Rectangle caja;
	
	
	public Area (float rectanguloX, float rectanguloY, float rectanguloWidth, float rectanguloHeight){
		this.rectanguloX = rectanguloX;
		this.rectanguloY = rectanguloY;
		this.rectanguloWidth = rectanguloWidth;
		this.rectanguloHeight = rectanguloHeight;
	}
	
	public void dibujarAreaOrdena(int numeroCorrectoOrdena, VertexBufferObjectManager mVertexBufferObjetManager, Scene mScene){
		this.numeroCorrectoOrdena = numeroCorrectoOrdena;
		caja = new Rectangle(rectanguloX, rectanguloY, rectanguloWidth, rectanguloHeight, mVertexBufferObjetManager);
		caja.setColor(1f, 1f, 1f);
		mScene.attachChild(caja);
	}
	
	public boolean estaTocada(float pX, float pY){
		if(rectanguloX<=pX && pX<=(rectanguloX+rectanguloWidth)
				&& rectanguloY<=pY && pY<=(rectanguloY+rectanguloHeight)){
						return true;
		}else return false;
	}
	
	public int numeroCorrecto(){
		return numeroCorrectoOrdena;		
	}

	public float getRectanguloWidth() {
		return rectanguloWidth;
	}

	public float getRectanguloHeight() {
		return rectanguloHeight;
	}

	public float getRectanguloX() {
		return rectanguloX;
	}

	public float getRectanguloY() {
		return rectanguloY;
	}
	
	
	
	


	
}
