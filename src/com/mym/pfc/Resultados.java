package com.mym.pfc;

import org.andengine.engine.camera.Camera;
import org.andengine.engine.options.EngineOptions;
import org.andengine.engine.options.ScreenOrientation;
import org.andengine.engine.options.resolutionpolicy.RatioResolutionPolicy;
import org.andengine.entity.scene.IOnSceneTouchListener;
import org.andengine.entity.scene.Scene;
import org.andengine.entity.scene.background.Background;
import org.andengine.entity.sprite.Sprite;
import org.andengine.entity.util.FPSLogger;
import org.andengine.input.touch.TouchEvent;
import org.andengine.input.touch.detector.ScrollDetector;
import org.andengine.input.touch.detector.SurfaceScrollDetector;
import org.andengine.input.touch.detector.ScrollDetector.IScrollDetectorListener;
import org.andengine.opengl.texture.atlas.bitmap.BitmapTextureAtlas;
import org.andengine.opengl.texture.atlas.bitmap.BitmapTextureAtlasTextureRegionFactory;
import org.andengine.opengl.texture.region.TiledTextureRegion;
import org.andengine.ui.activity.SimpleBaseGameActivity;

import com.mym.pfc.actividades.Balones;

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;

public class Resultados extends SimpleBaseGameActivity implements IScrollDetectorListener, IOnSceneTouchListener {

	 // ===========================================================
    // Constants
    // ===========================================================
    static final int CAMERA_WIDTH = 480;
    static final int CAMERA_HEIGHT = 854;
    
    static final int OBJETIVO_ENCONTRADO = 0;
 
    // ===========================================================
    // Fields
    // ===========================================================
 
    private Camera mCamera;
    private Scene mMainScene;
 
    private BitmapTextureAtlas mBitmapTextureAtlas;
    private TiledTextureRegion mBallTiledTextureRegion;
    
    private SurfaceScrollDetector mScrollDetector;
    
    static Sprite bContinuar;
    
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
        this.mCamera = new Camera(0, 0, CAMERA_WIDTH, CAMERA_HEIGHT);
 
        return new EngineOptions(true, ScreenOrientation.PORTRAIT_FIXED, new RatioResolutionPolicy(CAMERA_WIDTH, CAMERA_HEIGHT), this.mCamera);
    }
 
    @Override
    protected void onCreateResources() {
    	this.mBitmapTextureAtlas = new BitmapTextureAtlas(this.getTextureManager(), 160, 45);
        this.mBallTiledTextureRegion = BitmapTextureAtlasTextureRegionFactory.createTiledFromAsset(this.mBitmapTextureAtlas, this, "continuar.png", 0, 0, 1, 1);
        
        this.mBitmapTextureAtlas.load();
 
    }
 
    @Override
    protected Scene onCreateScene() {
        this.mEngine.registerUpdateHandler(new FPSLogger()); // logs the frame rate
        this.mMainScene = new Scene();
 
        this.mMainScene.setBackground(new Background(0, 0, 0.8784f));
        
        final float centerX = (CAMERA_WIDTH - this.mBallTiledTextureRegion.getWidth()) / 2;
        final float centerY = (CAMERA_HEIGHT - this.mBallTiledTextureRegion.getHeight()) / 2;
        
        /* Dibujamos la bola en el centro de la pantalla. */
        //final Sprite ball = new Sprite(centerX, centerY, this.mBallTiledTextureRegion, this.getVertexBufferObjectManager());
        
        bContinuar = new Sprite(centerX, centerY/2 , this.mBallTiledTextureRegion, this.getVertexBufferObjectManager())
        {
            @Override
            public boolean onAreaTouched(TouchEvent pSceneTouchEvent, float X, float Y) 
            {
            	Intent intent = new Intent(Resultados.this, GestorActividades.class);
        		startActivity(intent);
        		finish();
                return true;
            };
        };
        
        this.mMainScene.registerTouchArea(bContinuar);
        this.mMainScene.attachChild(bContinuar);

        return this.mMainScene;
        
    }

	public boolean onSceneTouchEvent(Scene pScene,
			TouchEvent pSceneTouchEvent) {
		this.mScrollDetector.onTouchEvent(pSceneTouchEvent);
		return true;
	}

	public void onScrollStarted(ScrollDetector pScollDetector,
			int pPointerID, float pDistanceX, float pDistanceY) {
		// TODO Auto-generated method stub
		
	}

	public void onScroll(ScrollDetector pScollDetector, int pPointerID,
			float pDistanceX, float pDistanceY) {
		
	}

	public void onScrollFinished(ScrollDetector pScollDetector,
			int pPointerID, float pDistanceX, float pDistanceY) {
		// TODO Auto-generated method stub
		
	}


}
