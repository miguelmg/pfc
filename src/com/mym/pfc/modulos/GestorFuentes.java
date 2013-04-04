package com.mym.pfc.modulos;

import org.andengine.entity.scene.Scene;
import org.andengine.entity.text.Text;
import org.andengine.input.touch.TouchEvent;
import org.andengine.opengl.font.Font;
import org.andengine.opengl.font.FontFactory;
import org.andengine.opengl.font.FontManager;
import org.andengine.opengl.texture.TextureManager;
import org.andengine.opengl.vbo.VertexBufferObjectManager;
import org.andengine.util.color.Color;

import android.graphics.Typeface;
import android.view.MotionEvent;

import com.mym.pfc.actividades.Actividades;

public class GestorFuentes {
	private static Font mFont;
	private float positionX;
	private float positionY;
	private String texto;
	private Actividades act;
	private FontManager fm;
	private TextureManager tm;
	
	public GestorFuentes(){
		
	}
	
	public GestorFuentes(Actividades act){
		this.act = act;
	}
	
	public boolean anadirTexto(float x, float y, String texto, Scene mScene, VertexBufferObjectManager vbom){
		
		final Text centerText = new Text(x, y, mFont, texto, vbom);
        
        mScene.registerTouchArea(centerText);
        mScene.attachChild(centerText);
		
		return true;
	}
	
	public boolean anadirTextoClicable(float x, float y, final String texto, Scene mScene, VertexBufferObjectManager vbom){
		
		final Text centerText = new Text(x, y, mFont, texto, vbom){	
			String text = "Respuesta escogida: " + texto.toString();
			@Override
			public boolean onAreaTouched(final TouchEvent pSceneTouchEvent, final float pTouchAreaLocalX, final float pTouchAreaLocalY) {
				//TODO llamara al evento que tenga que controlar la tarea(ejemplo: pantalla respuestas en caso de resultado correcto)
				act.gameToast(text);
				return true;
			}
        };
        
        /* Attach the Text object to the Scene */
        mScene.registerTouchArea(centerText);
        mScene.attachChild(centerText);

		return true;
	}
	
	public boolean anadirTextoResultados(float x, float y, final String texto, Scene mScene, VertexBufferObjectManager vbom){

		final Text centerText = new Text(x, y, mFont, texto, vbom){	
			String text = texto;
			@Override
			public boolean onAreaTouched(final TouchEvent pSceneTouchEvent, final float pTouchAreaLocalX, final float pTouchAreaLocalY) {
				//TODO llamara al evento que tenga que controlar la tarea(ejemplo: pantalla respuestas en caso de resultado correcto)
				switch (pSceneTouchEvent.getAction()) {
			        case MotionEvent.ACTION_DOWN: 
			            if(act.resultadoCorrecto == Integer.parseInt(text)){
							//act.gameToast("Respuesta correcta");
							act.respuestaCorrecta = true;
							Marcador.incrementarAciertos();
							Marcador.incrementarRepeticiones();
							act.reiniciarActividad();
		//					act.pasarSiguienteActividad();
						}else{
							Marcador.incrementarErrores();
							act.actualizarActividad();
						}
			            break;
	
			        case MotionEvent.ACTION_MOVE:
			            // finger moves on the screen
			            break;
	
			        case MotionEvent.ACTION_UP:   
			            // finger leaves the screen
			            break;
				}
				return true;
			}
        };

        /* Attach the Text object to the Scene */
        mScene.registerTouchArea(centerText);
        mScene.attachChild(centerText);

		return true;
	}
	
	public boolean anadirTextoMarcador(float x, float y, String texto, Scene mScene, VertexBufferObjectManager vbom, int pColor){

		setColor(pColor);
		
		Text centerText = new Text(x, y, mFont, texto, vbom);
		
        /* Attach the Text object to the Scene */
        mScene.registerTouchArea(centerText);
        mScene.attachChild(centerText);

		return true;
	}
	
	
	public void cargarFuente(FontManager fm, TextureManager tm){
		this.fm = fm;
		this.tm = tm;
		mFont = FontFactory.create(fm, tm, 256, 256, Typeface.create(Typeface.DEFAULT, Typeface.BOLD), 32);
        mFont.load();
	}
	
	
	
	public void cargarFuente(FontManager fm, TextureManager tm, int tamanoFuente){
		this.fm = fm;
		this.tm = tm;
		mFont = FontFactory.create(fm, tm, 256, 256, Typeface.create(Typeface.DEFAULT, Typeface.BOLD), tamanoFuente);
        mFont.load();
	}
	
	public void setColor(int pColor){
		mFont = FontFactory.create(fm, tm, 256, 256, Typeface.create(Typeface.DEFAULT, Typeface.BOLD), 32, true, pColor);
        mFont.load();
	}

}
