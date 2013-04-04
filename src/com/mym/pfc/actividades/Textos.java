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

import com.mym.pfc.GestorActividades;
import com.mym.pfc.clases.ContenedorObjetos;
import com.mym.pfc.modulos.GestorFuentes;
import com.mym.pfc.modulos.GestorImagenes;

public class Textos extends Actividades implements IScrollDetectorListener, IOnSceneTouchListener {

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
	    
	    private GestorFuentes gf;
	    
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
	 
	        return new EngineOptions(true, ScreenOrientation.PORTRAIT_FIXED, new RatioResolutionPolicy(CAMERA_WIDTH, CAMERA_HEIGHT), this.mCamera);
	    }
	 
	    @Override
	    protected void onCreateResources() {
	    	 
	    }
	 
	    @Override
	    protected Scene onCreateScene() {
	        this.mEngine.registerUpdateHandler(new FPSLogger()); // logs the frame rate
	        this.mMainScene = new Scene();
	 
	        this.mMainScene.setBackground(new Background(1, 1, 1));

	        float centerX = 0.5f;//CAMERA_WIDTH/2.0f;
	        float centerY = 0.5f;//CAMERA_HEIGHT/2.0f;
	        
	        
	        
	        gf = new GestorFuentes(this);  
	        gf.cargarFuente(this.getFontManager(), this.getTextureManager());      
	        gf.anadirTexto(10, 40, "Escoja respuesta correcta", mMainScene, this.getVertexBufferObjectManager());
	        gf.anadirTextoClicable(10, 400, "Hola", mMainScene, this.getVertexBufferObjectManager());
	        gf.anadirTextoClicable(10, 500, "Adios", mMainScene, this.getVertexBufferObjectManager());
	        
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
		           Toast.makeText(Textos.this, msg, Toast.LENGTH_SHORT).show();
		        }
		    });
		}*/
		
}
