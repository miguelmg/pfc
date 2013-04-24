package com.mym.pfc.actividades;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

import org.andengine.engine.camera.Camera;
import org.andengine.engine.options.EngineOptions;
import org.andengine.engine.options.ScreenOrientation;
import org.andengine.engine.options.resolutionpolicy.RatioResolutionPolicy;
import org.andengine.entity.primitive.Rectangle;
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
import com.mym.pfc.modulos.Area;
import com.mym.pfc.modulos.GestorTextos;
import com.mym.pfc.modulos.GestorImagenes;
import com.mym.pfc.modulos.Marcador;

public class OrdenaNumeros extends Actividades implements IScrollDetectorListener, IOnSceneTouchListener {

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
	    private int cantidadNumeros;
	    private int valorMinimo;
	    private int valorMaximo;
	    private int maximo;
	    private float controlPosicionX = 0.2f;
	    private float posicionX = 0.1f;
	    private int posicionCorrecto;
	    private int numero = 0;
	    private ArrayList<Integer> elementosAOrdenar = new ArrayList<Integer>();
	    private ArrayList<Integer> elementosOrdenados = new ArrayList<Integer>();
	    private ArrayList<Area> areasOrdena = new ArrayList<Area>();
	    
	    
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
	    	cantidadNumeros = bundle.getInt("numeroResultados");
	    	valorMinimo = bundle.getInt("rangoNum1");
	    	valorMaximo = bundle.getInt("rangoNum2");
	    	this.generaNumeros(true);
	    	

	    	//cargarValores();
	 
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
			//cargarValores();
			reiniciarDatos();
			cargarScena();
			this.mEngine.setScene(mMainScene);
		}
		
		
		public void reiniciarDatos(){
			elementosAOrdenar = new ArrayList<Integer>();
			elementosOrdenados = new ArrayList<Integer>();
			areasOrdena = new ArrayList<Area>();
			generaNumeros(true);
		}
		
		public void cargarScena(){
			this.mMainScene = new Scene();
			this.mMainScene.setBackground(new Background(0.7f, 0.7f, 0.7f));
			
			//OrdenaNumeros
			dibujarCajas();
			dibujarNumeros(true);
			
			//Marcador
			gfMarcador = new GestorTextos(this);
	        gfMarcador.cargarFuente(this.getFontManager(), this.getTextureManager(), 36);
	        int mX = (int)(CAMERA_WIDTH*0.75f);
	        int mY = (int)(CAMERA_HEIGHT*0.05f);
	        Marcador.cargarMarcador(mMainScene, this.getVertexBufferObjectManager(), mX, mY);
	        Marcador.actualizaMarcador(gfMarcador);

		        
		}
		
		private void generaNumeros(boolean aleatorio){	
			int numero;
			if(aleatorio){
				for (int i = 0; i < this.cantidadNumeros; i++){
					numero = valorMinimo + (int)(Math.random() * valorMaximo);
					while(elementosAOrdenar.contains(numero)){
						numero = valorMinimo + (int)(Math.random() * valorMaximo);
					}
					elementosAOrdenar.add(numero);
				}
			}else{
				for (int i = 0; i < this.cantidadNumeros; i++){
					elementosAOrdenar.add(valorMinimo+i);
				}
			}
			
		}
		
		public void dibujarCajas(){
			
			float padding = (30f*CAMERA_WIDTH)/100f;
			float position = (70f*CAMERA_WIDTH)/100f;
			
			float padding_unitario = padding / (this.cantidadNumeros+1);
			float tamano_unitario = position / this.cantidadNumeros;
			
			float x1, x2, y1, y2;
			
			x1 = padding_unitario;
			y1 = (int)((CAMERA_HEIGHT*0.65f));
			
			x2 = padding_unitario + tamano_unitario;
			y2 = y1 + tamano_unitario;
			//TODO
			ordenarNumeros();
			for (int i = 0; i<(int)this.cantidadNumeros; i++){
				Area area = new Area(x1, y1, tamano_unitario, tamano_unitario);
				area.dibujarAreaOrdena(elementosOrdenados.get(i), this.getVertexBufferObjectManager(), mMainScene);
				areasOrdena.add(area);
				x1 = x1 + tamano_unitario + padding_unitario;
			}
		}
		
		private void ordenarNumeros(){
			for(int i = 0; i < elementosAOrdenar.size(); i++){
				elementosOrdenados.add(elementosAOrdenar.get(i));
			}
			Collections.sort(elementosOrdenados);
		}
		
		public void dibujarNumeros(boolean aleatorio){
			//elementosAOrdenar = new ArrayList<Integer>();
			String numero;
		 	int tamanoFuente = 70;
	        gf = new GestorTextos(this);
	        gf.cargarFuente(this.getFontManager(), this.getTextureManager(), tamanoFuente);
	        
	        for (int i = 0; i < this.cantidadNumeros; i++){
	        	numero = elementosAOrdenar.get(i).toString();
	        	gf.anadirTextoArrastrable(CAMERA_WIDTH*(posicionX+(controlPosicionX*i)) + (tamanoFuente/2), 100f, numero, this.mMainScene, this.getVertexBufferObjectManager());
	        }
		}
		
		public Area esArea(float pX, float pY){
			for(Area a : areasOrdena){
				if(a.estaTocada(pX, pY)){
					return a;
				}
			}return null;
			
		}
		
		//Hay dos métodos iguales aunque uno debería estar borrado para esta actividad
		//Actualizar escena es para crear una nueva escena; 
		//Reiniciar escena nos servía para sumar y restar que cuando te equivocabas tenías que volver a cargar la misma escena

		@Override
		public void actualizarActividad() {
			cargarScena();
			this.mEngine.setScene(mMainScene);
		}

		public int getCantidadNumeros() {
			return cantidadNumeros;
		}	
		
		

}
