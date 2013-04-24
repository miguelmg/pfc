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
import com.mym.pfc.actividades.OrdenaNumeros;

public class GestorTextos {
	private static Font mFont;
	private float positionX;
	private float positionY;
	private String texto;
	private Actividades act;
	private FontManager fm;
	private TextureManager tm;
	private int numeroAciertosOrdena = 0; //variable exclusiva para ordenaNumeros
	
	public GestorTextos(){
	}
	
	public GestorTextos(Actividades act){
		this.act = act;
	}
	
	//utilizado para pruebas (añade texto sin mas)
	public boolean anadirTexto(float x, float y, String texto, Scene mScene, VertexBufferObjectManager vbom){
		
		final Text centerText = new Text(x, y, mFont, texto, vbom);
        
        mScene.registerTouchArea(centerText);
        mScene.attachChild(centerText);

        return true;
	}
	
	//utilizado para pruebas (añade texto que salta notificacion cuando es clicado)
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
	
	//exclusivo de ordena
	public boolean anadirTextoArrastrable(final float x, final float y, final String texto, Scene mScene, VertexBufferObjectManager vbom){
		
		final Text centerText = new Text(x, y, mFont, texto, vbom){	
			//String text = texto.toString();
			final float pX = x;
			final float pY = y;
			boolean correcto = false;
			
			@Override
			public boolean onAreaTouched(final TouchEvent pSceneTouchEvent, final float pTouchAreaLocalX, final float pTouchAreaLocalY) 
			{
					//TODO llamara al evento que tenga que controlar la tarea(ejemplo: pantalla respuestas en caso de resultado correcto)
				if(correcto == false){
					switch (pSceneTouchEvent.getAction()) {
				        case MotionEvent.ACTION_DOWN: 
				        	 this.setPosition(pSceneTouchEvent.getX() - this.getWidth() / 2,
				            			pSceneTouchEvent.getY() - this.getHeight() / 2);
				            break;
		
				        case MotionEvent.ACTION_MOVE:
				        	 this.setPosition(pSceneTouchEvent.getX() - this.getWidth() / 2,
				            			pSceneTouchEvent.getY() - this.getHeight() / 2);
				            break;
		
				        case MotionEvent.ACTION_UP:   
				            OrdenaNumeros ordenaAct = (OrdenaNumeros) act;
				           
				            Area a = ordenaAct.esArea(pSceneTouchEvent.getX(), pSceneTouchEvent.getY());
				            int numeroArrastrado = Integer.parseInt(texto);
				            if(a != null && a.numeroCorrecto() == numeroArrastrado){
				            	float x = a.getRectanguloX()+(a.getRectanguloWidth()/2);
				            	float y = a.getRectanguloY()+(a.getRectanguloHeight()/2);
				            	this.setPosition(x, y);
				            	correcto = true;
				            	numeroAciertosOrdena++;
				            	Marcador.incrementarAciertos();
				            	//ordenaAct.actualizarActividad();
				            	if(ordenaAct.getCantidadNumeros() == numeroAciertosOrdena ){
				            		Marcador.incrementarRepeticiones();
				            		act.reiniciarActividad();				            	
				            		correcto = false;
				            	}       		
				            }else{
				            	 Marcador.incrementarErrores();
				            	 this.setPosition(pX, pY);
				            	 //ordenaAct.actualizarActividad();
				            	 correcto = false;
				            }
				            break;
					}
				}
				return true;
			}
        };
		
        mScene.registerTouchArea(centerText);
        mScene.attachChild(centerText);

		return true;
	}
	
	//utilizado en suma y resta
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
	
	//utilizado en todas
	public boolean anadirTextoMarcador(float x, float y, String texto, Scene mScene, VertexBufferObjectManager vbom, int pColor){

		setColor(pColor);
		
		Text centerText = new Text(x, y, mFont, texto, vbom);
		
        /* Attach the Text object to the Scene */
        mScene.registerTouchArea(centerText);
        mScene.attachChild(centerText);

		return true;
	}
	
	//pensando en eliminarlo y hacer que cargue la fuente la actividad para no tener un para cada fuente
	public void cargarFuente(FontManager fm, TextureManager tm){
		this.fm = fm;
		this.tm = tm;
		mFont = FontFactory.create(fm, tm, 256, 256, Typeface.create(Typeface.DEFAULT, Typeface.BOLD), 32);
        mFont.load();
	}
	
	//pensando en eliminarlo y hacer que cargue la fuente la actividad para no tener un para cada fuente
	public void cargarFuente(FontManager fm, TextureManager tm, int tamanoFuente){
		this.fm = fm;
		this.tm = tm;
		mFont = FontFactory.create(fm, tm, 256, 256, Typeface.create(Typeface.DEFAULT, Typeface.BOLD), tamanoFuente);
        mFont.load();
	}
	
	//si se carga desde la actividad lo podremos cambiar el color desde ella segun la entrada
	public void setColor(int pColor){
		mFont = FontFactory.create(fm, tm, 256, 256, Typeface.create(Typeface.DEFAULT, Typeface.BOLD), 32, true, pColor);
        mFont.load();
	}

}
