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
import org.andengine.opengl.texture.atlas.bitmap.BitmapTextureAtlas;
import org.andengine.opengl.texture.region.TiledTextureRegion;
import org.andengine.ui.activity.SimpleBaseGameActivity;

import android.content.Context;
import android.view.Display;
import android.view.WindowManager;

import com.mym.pfc.clases.ContenedorObjetos;
import com.mym.pfc.image.GestorImagenes;

public class Balones extends SimpleBaseGameActivity implements IScrollDetectorListener, IOnSceneTouchListener {

	    // ===========================================================
	    // Constants
	    // ===========================================================
	    static int CAMERA_WIDTH = 480;
	    static int CAMERA_HEIGHT = 854;
	    
	    static final int OBJETIVO_ENCONTRADO = 0;
	 
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
	    	co = new ContenedorObjetos(0, "pelotas.png", 64, 64, 8);
	    	
	    	gi = new GestorImagenes(this.getBaseContext(), this.getTextureManager(), co);
	    	
	    	co2 = new ContenedorObjetos(1, "pelotas.png", 64, 64, 3);
	    	
	    	gi2 = new GestorImagenes(this.getBaseContext(), this.getTextureManager(), co2);
	    	
	    	//this.mBitmapTextureAtlas = new BitmapTextureAtlas(this.getTextureManager(), 64, 64);
	        //this.mBallTiledTextureRegion = BitmapTextureAtlasTextureRegionFactory.createTiledFromAsset(this.mBitmapTextureAtlas, this, "pelotas.png", 0, 0, 1, 1);
	        
	        //this.mBitmapTextureAtlas.load();
	 
	    }
	 
	    @Override
	    protected Scene onCreateScene() {
	        this.mEngine.registerUpdateHandler(new FPSLogger()); // logs the frame rate
	        this.mMainScene = new Scene();
	 
	        this.mMainScene.setBackground(new Background(0, 0, 0.8784f));
	        
	        float[] area = new float[]{0.0f, 0.5f, 0.0f, 1f};
	        
	        gi.dibujaImagenes(area, new float[]{CAMERA_WIDTH, CAMERA_HEIGHT}, this.getVertexBufferObjectManager(), this.mMainScene);
	        
	        float[] area2 = new float[]{0.5f, 1f, 0.0f, 1f};
	        
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
		
		
//		@Override
//		protected Dialog onCreateDialog(int id) {
//			Dialog newDialog = null;
//			
//			switch (id) {
//				case OBJETIVO_ENCONTRADO:
//					Intent intent = new Intent(Balones.this, Resultados.class);
//					startActivity(intent);
//					finish();
////		                AlertDialog.Builder builder = new AlertDialog.Builder(this);
////		                builder.setMessage("Muy bien!!!! \n Objetivo logrado.");
////		                builder.setCancelable(false);
////		                builder.setPositiveButton("Continuar", new DialogInterface.OnClickListener() {
////		                    @Override
////		                    public void onClick(DialogInterface dialogInterface, int id) {
////		                    	Intent intent = new Intent(Balones.this, GestorActividades.class);
////		    					startActivity(intent);
////		                    }
////		                });
////
////		                builder.setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
////
////		                    @Override
////		                    public void onClick(DialogInterface dialog, int id) {
////		                        dialog.cancel();
////		                    }
////		                });
////		                newDialog = builder.create();
//		                break;
//			}
//			return newDialog;
//		}
//		
//		
//		public void objetivoFinal(){
////			 AlertDialog alertDialog = new AlertDialog.Builder(this)
////		        .setTitle("Pop-up/Diálogo de alerta")//Título del diálogo
////		        .setMessage("¿Desea Continuar?")//Mensaje del diálogo
////		        .setPositiveButton("Si"/*Texto del botón positivo*/, new AlertDialog.OnClickListener() {
////		            public void onClick(DialogInterface arg0, int arg1) {
////		                // Aquí irá lo que se desee hacer cuando hagamos click en el boton Si
////		                //Llamo al método alertaSi()
////		            	Intent intent = new Intent(Balones.this, GestorActividades.class);
////	            		startActivity(intent);
////		            }
////		 
////		        })
////		        .setNegativeButton("No"/*Texto del botón negativo*/, new AlertDialog.OnClickListener() {
////		            public void onClick(DialogInterface dialog, int which) {
////		                //Aquí irá lo que se desee hacer cuando hagamos click en el botón Cancelar
////		                //Llamo al método alertaNo
////		                //alertaNo();
////		            }
////		        })
////		        .setNeutralButton("Cancelar"/*Texto del botón cancelar*/, new AlertDialog.OnClickListener() {
////		            public void onClick(DialogInterface dialog, int which) {
////		                //Aquí irá lo que se desee hacer cuando hagamos click en el botón No
////		                //Llamo al método alertaCancelar
////		                //alertaCancelar();
////		            }
////		        }).create();
////		        //Muestro el diálogo
////		        alertDialog.show();
//			
//			
//			
//			
//			
//			/*AlertDialog alertDialog = new AlertDialog.Builder(this)
//				.setMessage("Muy Bien!!! \n Objetivo logrado.")
//				.setCancelable(false)
//				.setPositiveButton("Continuar",
//                    new DialogInterface.OnClickListener() {
//						public void onClick(DialogInterface dialog, int id) {
//		                    Intent intent = new Intent(Balones.this, GestorActividades.class);
//		            		startActivity(intent);
//						}
//				}).create();
//				alertDialog.show();*/
//		}
		

}
