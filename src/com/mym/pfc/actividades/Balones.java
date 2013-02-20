package com.mym.pfc.actividades;

import org.andengine.engine.options.EngineOptions;
import org.andengine.entity.scene.IOnSceneTouchListener;
import org.andengine.entity.scene.Scene;
import org.andengine.entity.scene.background.Background;
import org.andengine.entity.sprite.Sprite;

import org.andengine.engine.camera.Camera;
import org.andengine.engine.options.ScreenOrientation;
import org.andengine.engine.options.resolutionpolicy.RatioResolutionPolicy;
import org.andengine.entity.util.FPSLogger;
import org.andengine.input.touch.TouchEvent;
import org.andengine.input.touch.detector.ScrollDetector;
import org.andengine.input.touch.detector.ScrollDetector.IScrollDetectorListener;
import org.andengine.input.touch.detector.SurfaceScrollDetector;
import org.andengine.opengl.texture.atlas.bitmap.BitmapTextureAtlas;
import org.andengine.opengl.texture.atlas.bitmap.BitmapTextureAtlasTextureRegionFactory;
import org.andengine.opengl.texture.region.TiledTextureRegion;
import org.andengine.ui.activity.SimpleBaseGameActivity;

import android.os.Bundle;
import android.util.Log;

public class Balones extends SimpleBaseGameActivity implements IScrollDetectorListener, IOnSceneTouchListener {

	    // ===========================================================
	    // Constants
	    // ===========================================================
	    static final int CAMERA_WIDTH = 480;
	    static final int CAMERA_HEIGHT = 854;
	 
	    // ===========================================================
	    // Fields
	    // ===========================================================
	 
	    private Camera mCamera;
	    private Scene mMainScene;
	 
	    private BitmapTextureAtlas mBitmapTextureAtlas;
	    private TiledTextureRegion mBallTiledTextureRegion;
	    
	    private SurfaceScrollDetector mScrollDetector;
	    
	    static Sprite ball;
	    static Sprite ball2;
	    static Sprite ball3;
	    static Sprite ball4;
	    static Sprite ball5;
	    
	    //private Sprite ball;
	    
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
	    	this.mBitmapTextureAtlas = new BitmapTextureAtlas(this.getTextureManager(), 64, 64);
	        this.mBallTiledTextureRegion = BitmapTextureAtlasTextureRegionFactory.createTiledFromAsset(this.mBitmapTextureAtlas, this, "ui_ball_1.png", 0, 0, 1, 1);
	        
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
	        
	        ball = new Sprite(centerX, centerY, this.mBallTiledTextureRegion, this.getVertexBufferObjectManager())
	        {
	            @Override
	            public boolean onAreaTouched(TouchEvent pSceneTouchEvent, float X, float Y) 
	            {
	            	this.setPosition(pSceneTouchEvent.getX() - this.getWidth() / 2,
	            			pSceneTouchEvent.getY() - this.getHeight() / 2);
	                return true;
	            };
	        };
	        
	        this.mMainScene.registerTouchArea(ball);
	        this.mMainScene.attachChild(ball);
	        
	        ball2 = new Sprite(centerX-centerX/2, centerY-centerY/2, this.mBallTiledTextureRegion, this.getVertexBufferObjectManager())
	        {
	            @Override
	            public boolean onAreaTouched(TouchEvent pSceneTouchEvent, float X, float Y) 
	            {
                    this.setPosition(pSceneTouchEvent.getX() - this.getWidth() / 2,
	            			pSceneTouchEvent.getY() - this.getHeight() / 2);
	                return true;
	            };
	        };
	        
	        this.mMainScene.registerTouchArea(ball2);
	        this.mMainScene.attachChild(ball2);
	        
	        ball3 = new Sprite(centerX+centerX/2, centerY+centerY/2, this.mBallTiledTextureRegion, this.getVertexBufferObjectManager())
	        {
	            @Override
	            public boolean onAreaTouched(TouchEvent pSceneTouchEvent, float X, float Y) 
	            {
                    this.setPosition(pSceneTouchEvent.getX() - this.getWidth() / 2,
	            			pSceneTouchEvent.getY() - this.getHeight() / 2);
	                return true;
	            };
	        };
	        
	        this.mMainScene.registerTouchArea(ball3);
	        this.mMainScene.attachChild(ball3);
	        
	        ball4 = new Sprite(centerX-centerX/2, centerY+centerY/2, this.mBallTiledTextureRegion, this.getVertexBufferObjectManager())
	        {
	            @Override
	            public boolean onAreaTouched(TouchEvent pSceneTouchEvent, float X, float Y) 
	            {
                    this.setPosition(pSceneTouchEvent.getX() - this.getWidth() / 2,
	            			pSceneTouchEvent.getY() - this.getHeight() / 2);
	                return true;
	            };
	        };
	        
	        this.mMainScene.registerTouchArea(ball4);
	        this.mMainScene.attachChild(ball4);
	        
	        ball5 = new Sprite(centerX+centerX/2, centerY-centerY/2, this.mBallTiledTextureRegion, this.getVertexBufferObjectManager())
	        {
	            @Override
	            public boolean onAreaTouched(TouchEvent pSceneTouchEvent, float X, float Y) 
	            {
                    this.setPosition(pSceneTouchEvent.getX() - this.getWidth() / 2,
	            			pSceneTouchEvent.getY() - this.getHeight() / 2);
	                return true;
	            };
	        };
	        
	        this.mMainScene.registerTouchArea(ball5);
	        this.mMainScene.attachChild(ball5);
	        
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
