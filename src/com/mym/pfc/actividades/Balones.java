package com.mym.pfc.actividades;

import org.andengine.engine.camera.Camera;
import org.andengine.engine.handler.IUpdateHandler;
import org.andengine.engine.options.EngineOptions;
import org.andengine.engine.options.ScreenOrientation;
import org.andengine.engine.options.resolutionpolicy.RatioResolutionPolicy;
import org.andengine.entity.scene.IOnSceneTouchListener;
import org.andengine.entity.scene.Scene;
import org.andengine.entity.scene.background.Background;
import org.andengine.entity.text.Text;
import org.andengine.entity.util.FPSLogger;
import org.andengine.input.touch.TouchEvent;
import org.andengine.input.touch.detector.ScrollDetector;
import org.andengine.input.touch.detector.ScrollDetector.IScrollDetectorListener;
import org.andengine.input.touch.detector.SurfaceScrollDetector;
import org.andengine.opengl.font.Font;
import org.andengine.opengl.font.FontFactory;
import org.andengine.opengl.texture.atlas.bitmap.BitmapTextureAtlas;
import org.andengine.opengl.texture.region.TiledTextureRegion;
import org.andengine.ui.activity.SimpleBaseGameActivity;
import org.andengine.util.color.Color;

import android.R;
import android.content.Context;
import android.graphics.Typeface;
import android.view.Display;
import android.view.WindowManager;
import android.widget.Toast;

import com.mym.pfc.clases.ContenedorObjetos;
import com.mym.pfc.modulos.GestorTextos;
import com.mym.pfc.modulos.GestorImagenes;

public class Balones extends Actividades implements IScrollDetectorListener, IOnSceneTouchListener {

	    // ===========================================================
	    // Constants
	    // ===========================================================
	    static int CAMERA_WIDTH = 480;
	    static int CAMERA_HEIGHT = 854;
	    
	    static final int OBJETIVO_ENCONTRADO = 0;
	 
	    static boolean hasTouched = false;
	    static boolean touchText = false;
	    static boolean digText = false;
	    private Font mFont;
	    
	    // ===========================================================
	    // Fields
	    // ===========================================================
	 
	    private Camera mCamera;
	    private Scene mMainScene;
	 
	    private BitmapTextureAtlas mBitmapTextureAtlas;
	    private TiledTextureRegion mBallTiledTextureRegion;
	    
	    private SurfaceScrollDetector mScrollDetector;
	    
//	    static Sprite ball;
//	    static Sprite ball2;
//	    static Sprite ball3;
//	    static Sprite ball4;
//	    static Sprite ball5;
	    
	    //private Sprite ball;
	    
	    
	    private ContenedorObjetos co;
	    private GestorImagenes gi;
	    
	    private ContenedorObjetos co2;
	    private GestorImagenes gi2;
	    
	    private GestorTextos gf;
	    
	    // ===========================================================
	    // Constructors
	    // ===========================================================
	 
	    // ===========================================================
	    // Getter & Setter
	    // ===========================================================
	 
	    // ===========================================================
	    // Methods for/from SuperClass/Interfaces
	    // ===========================================================
	 

	    public EngineOptions onCreateEngineOptions() {
	    	Display display = ((WindowManager) getSystemService(Context.WINDOW_SERVICE)).getDefaultDisplay();
	        CAMERA_WIDTH = display.getWidth();
	        CAMERA_HEIGHT = display.getHeight();
	    	
	        this.mCamera = new Camera(0, 0, CAMERA_WIDTH, CAMERA_HEIGHT);
	 
	        return new EngineOptions(true, ScreenOrientation.LANDSCAPE_FIXED, new RatioResolutionPolicy(CAMERA_WIDTH, CAMERA_HEIGHT), this.mCamera);
	    }
	 
	    @Override
	    protected void onCreateResources() {
	    	co = new ContenedorObjetos(0, "pelotas.png", 64, 64,16);
	    	
	    	gi = new GestorImagenes(this, this.getTextureManager(), co);
	    	
	    	co2 = new ContenedorObjetos(1, "pelotas.png", 64, 64, 1);
	    	
	    	gi2 = new GestorImagenes(this, this.getTextureManager(), co2);
	    	
	    	//this.mBitmapTextureAtlas = new BitmapTextureAtlas(this.getTextureManager(), 64, 64);
	        //this.mBallTiledTextureRegion = BitmapTextureAtlasTextureRegionFactory.createTiledFromAsset(this.mBitmapTextureAtlas, this, "pelotas.png", 0, 0, 1, 1);
	        
	        //this.mBitmapTextureAtlas.load();
	 
	    }
	 
	    @Override
	    protected Scene onCreateScene() {
	        this.mEngine.registerUpdateHandler(new FPSLogger()); // logs the frame rate
	        this.mMainScene = new Scene();
	 
	        this.mMainScene.setBackground(new Background(0, 0, 0.8784f));

	        float centerX = 0.5f;//CAMERA_WIDTH/2.0f;
	        float centerY = 0.5f;//CAMERA_HEIGHT/2.0f;

//	        GestorFuentes.cargarFuente(this.getFontManager(), this.getTextureManager());
//	        
//	        gf = new GestorFuentes();
//	        
//	        gf.añadirTexto(10, 40, "Escoja respuesta correcta", mMainScene, this.getVertexBufferObjectManager());
//
//	        
//	        gf.añadirTextoClicable(10, 400, "Hola", mMainScene, this.getVertexBufferObjectManager());
	        
	        
	        
	        float[] area = new float[]{0.0f, 0.4f, 0.1f, 0.8f};
	        
	        gi.dibujaImagenes(area, new float[]{CAMERA_WIDTH, CAMERA_HEIGHT}, this.getVertexBufferObjectManager(), this.mMainScene);
	        
	        float[] area2 = new float[]{0.6f, 1f, 0.1f, 0.8f};
	        
	        gi2.dibujaImagenes(area2, new float[]{CAMERA_WIDTH, CAMERA_HEIGHT}, this.getVertexBufferObjectManager(), this.mMainScene);

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
			// TODO Auto-generated method stub
			
		}

		@Override
		public void actualizarActividad() {
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
