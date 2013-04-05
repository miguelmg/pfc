package com.mym.pfc.actividades;

import java.util.ArrayList;

import org.andengine.engine.camera.Camera;
import org.andengine.engine.options.EngineOptions;
import org.andengine.engine.options.ScreenOrientation;
import org.andengine.engine.options.resolutionpolicy.RatioResolutionPolicy;
import org.andengine.entity.scene.IOnSceneTouchListener;
import org.andengine.entity.scene.Scene;
import org.andengine.entity.scene.background.Background;
import org.andengine.entity.util.FPSLogger;
import org.andengine.input.touch.TouchEvent;
import org.andengine.input.touch.detector.ScrollDetector;
import org.andengine.input.touch.detector.ScrollDetector.IScrollDetectorListener;
import org.andengine.input.touch.detector.SurfaceScrollDetector;

import android.R.integer;
import android.content.Context;
import android.os.Bundle;
import android.view.Display;
import android.view.WindowManager;

import com.mym.pfc.clases.ContenedorObjetos;
import com.mym.pfc.modulos.GestorTextos;
import com.mym.pfc.modulos.GestorImagenes;
import com.mym.pfc.modulos.Marcador;

public class RestaImagenes extends Actividades implements IScrollDetectorListener, IOnSceneTouchListener {

	    // ===========================================================
	    // Constants
	    // ===========================================================
	    static int CAMERA_WIDTH = 480;
	    static int CAMERA_HEIGHT = 854;
	    
	    static final int OBJETIVO_ENCONTRADO = 0;
	 
	    static boolean hasTouched = false;
	    static boolean touchText = false;
	    static boolean digText = false;
	    
	    
	    // ===========================================================
	    // Fields
	    // ===========================================================
	 
	    private Camera mCamera;
	    private Scene mMainScene;
	    
	    private SurfaceScrollDetector mScrollDetector;

	    private ContenedorObjetos co;
	    private GestorImagenes gi;
	    
	    private ContenedorObjetos co2;
	    private GestorImagenes gi2;
	    
	    private GestorTextos gf;
	    private GestorTextos gfMarcador;
	    
	    //variables de actividad
	    private String imagen;
	    private int numeroResultados;
	    private int rangoNum1;
	    private int rangoNum2;
	    private int maximo;
	    private float controlPosicionX = 0.2f;
	    private float posicionX = 0.1f;
	    private int posicionCorrecto;
	    private int numero = 0;
	    private ArrayList<Integer> resultados = new ArrayList<Integer>();
	    
	    
	    public EngineOptions onCreateEngineOptions() {
	    	Display display = ((WindowManager) getSystemService(Context.WINDOW_SERVICE)).getDefaultDisplay();
	        
	    	CAMERA_WIDTH = display.getWidth();
	        CAMERA_HEIGHT = display.getHeight();
	    	
	        this.mCamera = new Camera(0, 0, CAMERA_WIDTH, CAMERA_HEIGHT);
	 
	        return new EngineOptions(true, ScreenOrientation.LANDSCAPE_FIXED, new RatioResolutionPolicy(CAMERA_WIDTH, CAMERA_HEIGHT), this.mCamera);
	    }
	 
	    @Override
	    protected void onCreateResources() {
	    	
	    	Bundle bundle = getIntent().getExtras();
	    	imagen = bundle.getString("imagen");
	    	numeroResultados = bundle.getInt("numeroResultados");
	    	rangoNum1 = bundle.getInt("rangoNum1");
	    	rangoNum2 = bundle.getInt("rangoNum2");
	    	if(rangoNum1>rangoNum2){
	    		maximo = rangoNum1;
	    	}else{
	    		maximo = rangoNum2;	
	    	}
	    	

	    	cargarValores();
	 
	    }
	 
	    @Override
	    protected Scene onCreateScene() {
	        this.mEngine.registerUpdateHandler(new FPSLogger()); // logs the frame rate
	        //this.mMainScene = new Scene();
	        
	        cargarScena();
	        
	        return this.mMainScene; 
	    } 

		public boolean onSceneTouchEvent(Scene pScene,
				TouchEvent pSceneTouchEvent) {
			this.mScrollDetector.onTouchEvent(pSceneTouchEvent);
			return true;
		}

		@Override
		public void onScroll(ScrollDetector arg0, int arg1, float arg2,
				float arg3) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void onScrollFinished(ScrollDetector arg0, int arg1, float arg2,
				float arg3) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void onScrollStarted(ScrollDetector arg0, int arg1, float arg2,
				float arg3) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void reiniciarActividad() {
			//onCreateScene();
			//add your sprites and stuff to the scene...
			cargarValores();
			cargarScena();
			this.mEngine.setScene(mMainScene);
		}
		
		public void cargarScena(){
			this.mMainScene = new Scene();
			this.mMainScene.setBackground(new Background(0.7f, 0.7f, 0.7f));
	        
	        float[] area = new float[]{0.0f, 0.4f, 0.1f, 0.8f};        
	        gi.dibujaImagenes(area, new float[]{CAMERA_WIDTH, CAMERA_HEIGHT}, this.getVertexBufferObjectManager(), this.mMainScene);	        
	        float[] area2 = new float[]{0.6f, 1f, 0.1f, 0.8f};	        
	        gi2.dibujaImagenes(area2, new float[]{CAMERA_WIDTH, CAMERA_HEIGHT}, this.getVertexBufferObjectManager(), this.mMainScene);

	        
	        int tamanoFuente = 70;
	        gf = new GestorTextos(this);
	        gf.cargarFuente(this.getFontManager(), this.getTextureManager(), tamanoFuente);
	        int operadorX = (CAMERA_WIDTH/2)-(tamanoFuente/2);
	        int operadorY = (CAMERA_HEIGHT/2)-(tamanoFuente/2);
	        gf.anadirTexto(operadorX, operadorY, " - ", mMainScene, this.getVertexBufferObjectManager());
	        int resultadosY = (int)((CAMERA_HEIGHT*0.9f) - (tamanoFuente/2));        
	        
	        //calculo posiciones de resultados
//	        float controlPosicionX = 0.2f;
//	        float posicionX = 0.1f;
//	        int posicionCorrecto = 0 + (int)(Math.random() * numeroResultados);
//	        int numero = 0;
//	        ArrayList<Integer> resultados = new ArrayList<Integer>();
	        //resultados.add(resultadoCorrecto);
	        for(int i = 0; i < numeroResultados; i++){
//	        	while(resultados.contains(numero)){
//	        		numero = 0 + (int)(Math.random() * maximo);
//	        	}
//	        	resultados.add(numero);
//	        	if(i==posicionCorrecto){
//	        		gf.anadirTextoResultados(CAMERA_WIDTH*(posicionX+(controlPosicionX*i)) + (tamanoFuente/2), resultadosY, String.valueOf(resultadoCorrecto), mMainScene, this.getVertexBufferObjectManager());
//	        	}else{
//	        		gf.anadirTextoResultados(CAMERA_WIDTH*(posicionX+(controlPosicionX*i)) + (tamanoFuente/2), resultadosY, String.valueOf(numero), mMainScene, this.getVertexBufferObjectManager());
//	        	}
	        	gf.anadirTextoResultados(CAMERA_WIDTH*(posicionX+(controlPosicionX*i)) + (tamanoFuente/2), resultadosY, String.valueOf(resultados.get(i)), mMainScene, this.getVertexBufferObjectManager());
	        }
	        
//	        gf.anadirTextoResultados(CAMERA_WIDTH*0.1f + (tamanoFuente/2), resultadosY, "1", mMainScene, this.getVertexBufferObjectManager());
//	        gf.anadirTextoResultados(CAMERA_WIDTH*0.3f + (tamanoFuente/2), resultadosY, "42", mMainScene, this.getVertexBufferObjectManager());
//	        gf.anadirTextoResultados(CAMERA_WIDTH*0.5f + (tamanoFuente/2), resultadosY, String.valueOf(resultadoCorrecto), mMainScene, this.getVertexBufferObjectManager());
//	        gf.anadirTextoResultados(CAMERA_WIDTH*0.7f + (tamanoFuente/2), resultadosY, "123", mMainScene, this.getVertexBufferObjectManager());
//	        pintaResultados(numeroResultados);
	        
	        gfMarcador = new GestorTextos(this);
	        gfMarcador.cargarFuente(this.getFontManager(), this.getTextureManager(), 36);
	        int mX = (int)(CAMERA_WIDTH*0.75f);
	        int mY = (int)(CAMERA_HEIGHT*0.05f);
	        
	        Marcador.cargarMarcador(mMainScene, this.getVertexBufferObjectManager(), mX, mY);
	        
	        Marcador.actualizaMarcador(gfMarcador);
	        
		}
		
		public void cargarValores(){
			int randNum1 = 0 + (int)(Math.random() * rangoNum1); 
			int randNum2 = 0 + (int)(Math.random() * rangoNum2);
			if(randNum1<randNum2){
				int aux = randNum1;
				randNum1 = randNum2;
				randNum2 = aux;
			}
			 
	    	
	    	resultadoCorrecto = randNum1 - randNum2;
	    	
	    	co = new ContenedorObjetos(0, imagen, 64, 64, randNum1);
	    	gi = new GestorImagenes(this, this.getTextureManager(), co);
	    	co2 = new ContenedorObjetos(1, imagen, 64, 64, randNum2);
	    	gi2 = new GestorImagenes(this, this.getTextureManager(), co2);
	    	
	    	posicionCorrecto = 0 + (int)(Math.random() * numeroResultados);
	    	resultados = new ArrayList<Integer>();
	    	 for(int i = 0; i < numeroResultados; i++){
		        	if(i == posicionCorrecto){
		        		resultados.add(resultadoCorrecto);
		        	}else{
		        		while(resultados.contains(numero) || numero == resultadoCorrecto){
			        		numero = 0 + (int)(Math.random() * maximo);
			        	}
		        		resultados.add(numero);
		        	}
		        	
		        }
	    	
		}

		@Override
		public void actualizarActividad() {
			cargarScena();
			this.mEngine.setScene(mMainScene);
		}		

}
