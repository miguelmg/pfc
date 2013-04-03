package com.mym.pfc.actividades;

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

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Display;
import android.view.WindowManager;

import com.mym.pfc.clases.ContenedorObjetos;
import com.mym.pfc.image.GestorImagenes;
import com.mym.pfc.modulos.GestorFuentes;

public class SumaImagenes extends Actividades implements IScrollDetectorListener, IOnSceneTouchListener {

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
	    
	    private GestorFuentes gf;
	    
	    //variables de actividad
	    private String imagen;
	    private int numeroResultados;
	    private int rangoNum1;
	    private int rangoNum2;
	    
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
	    	
//	    	String imagenCo1 = "pelotas.png";
//	    	String imagenCo2 = "pelotas.png";
	    	
	    	//int randNum1 = minimum + (int)(Math.random()*maximum); 
	    	int randNum1 = 0 + (int)(Math.random() * rangoNum1); 
	    	int randNum2 = 0 + (int)(Math.random() * rangoNum2); 
	    	
	    	resultadoCorrecto = randNum1 + randNum2;
	    	
	    	co = new ContenedorObjetos(0, imagen, 64, 64, randNum1);
	    	gi = new GestorImagenes(this, this.getTextureManager(), co);
	    	co2 = new ContenedorObjetos(1, imagen, 64, 64, randNum2);
	    	gi2 = new GestorImagenes(this, this.getTextureManager(), co2);
	 
	    }
	 
	    @Override
	    protected Scene onCreateScene() {
	        this.mEngine.registerUpdateHandler(new FPSLogger()); // logs the frame rate
	        this.mMainScene = new Scene();
	 
	        this.mMainScene.setBackground(new Background(0.7f, 0.7f, 0.7f));

//	        float centerX = 0.5f;//CAMERA_WIDTH/2.0f;
//	        float centerY = 0.5f;//CAMERA_HEIGHT/2.0f;

//	        GestorFuentes.cargarFuente(this.getFontManager(), this.getTextureManager());
//	        gf = new GestorFuentes();
//	        gf.añadirTexto(10, 40, "Escoja respuesta correcta", mMainScene, this.getVertexBufferObjectManager());
//	        gf.añadirTextoClicable(10, 400, "Hola", mMainScene, this.getVertexBufferObjectManager());
	        
	        float[] area = new float[]{0.0f, 0.4f, 0.1f, 0.8f};        
	        gi.dibujaImagenes(area, new float[]{CAMERA_WIDTH, CAMERA_HEIGHT}, this.getVertexBufferObjectManager(), this.mMainScene);	        
	        float[] area2 = new float[]{0.6f, 1f, 0.1f, 0.8f};	        
	        gi2.dibujaImagenes(area2, new float[]{CAMERA_WIDTH, CAMERA_HEIGHT}, this.getVertexBufferObjectManager(), this.mMainScene);

	        
	        int tamanoFuente = 70;
	        gf = new GestorFuentes(this);
	        gf.cargarFuente(this.getFontManager(), this.getTextureManager(), tamanoFuente);
	        int operadorX = (CAMERA_WIDTH/2)-(tamanoFuente/2);
	        int operadorY = (CAMERA_HEIGHT/2)-(tamanoFuente/2);
	        gf.añadirTexto(operadorX, operadorY, " + ", mMainScene, this.getVertexBufferObjectManager());
	        
	        int resultadosY = (int)((CAMERA_HEIGHT*0.9f) - (tamanoFuente/2));        
	        
	        gf.añadirTextoResultados(CAMERA_WIDTH*0.1f - (tamanoFuente/2), resultadosY, "1", mMainScene, this.getVertexBufferObjectManager());
	        gf.añadirTextoResultados(CAMERA_WIDTH*0.3f - (tamanoFuente/2), resultadosY, "42", mMainScene, this.getVertexBufferObjectManager());
	        gf.añadirTextoResultados(CAMERA_WIDTH*0.5f - (tamanoFuente/2), resultadosY, String.valueOf(resultadoCorrecto), mMainScene, this.getVertexBufferObjectManager());
	        gf.añadirTextoResultados(CAMERA_WIDTH*0.7f - (tamanoFuente/2), resultadosY, "123", mMainScene, this.getVertexBufferObjectManager());
//	        pintaResultados(numeroResultados);
	        
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
		
		/*//Method for generating Toast messages as they need to run on UI thread
	    public void gameToast(final String msg) {
		    this.runOnUiThread(new Runnable() {
		        @Override
		        public void run() {
		           Toast.makeText(Balones.this, msg, Toast.LENGTH_SHORT).show();
		        }
		    });
		}*/
		

}
