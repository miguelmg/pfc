package com.mym.pfc.modulos;

import org.andengine.entity.scene.Scene;
import org.andengine.entity.text.Text;
import org.andengine.input.touch.TouchEvent;
import org.andengine.opengl.font.Font;
import org.andengine.opengl.font.FontFactory;
import org.andengine.opengl.font.FontManager;
import org.andengine.opengl.texture.TextureManager;
import org.andengine.opengl.vbo.VertexBufferObjectManager;

import android.graphics.Typeface;

import com.mym.pfc.actividades.Actividades;
import com.mym.pfc.actividades.Balones;

public class GestorFuentes {
	private static Font mFont;
	private float positionX;
	private float positionY;
	private String texto;
	private Actividades act;
	
	
	public GestorFuentes(){
		
	}
	
	public GestorFuentes(Actividades act){
		this.act = act;
	}
	
	public boolean añadirTexto(float x, float y, String texto, Scene mScene, VertexBufferObjectManager vbom){
		
		final Text centerText = new Text(x, y, mFont, texto, vbom);
        
        mScene.registerTouchArea(centerText);
        mScene.attachChild(centerText);
		
		return true;
	}
	
	public boolean añadirTextoClicable(float x, float y, final String texto, Scene mScene, VertexBufferObjectManager vbom){
		
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
	
	public static final void cargarFuente(FontManager fm, TextureManager tm){
		mFont = FontFactory.create(fm, tm, 256, 256, Typeface.create(Typeface.DEFAULT, Typeface.BOLD), 32);
        mFont.load();
	}
	
	
	
	
}
