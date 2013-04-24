package com.mym.pfc.modulos;

import java.util.ArrayList;

import org.andengine.entity.scene.Scene;
import org.andengine.entity.sprite.Sprite;
import org.andengine.input.touch.TouchEvent;
import org.andengine.opengl.texture.TextureManager;
import org.andengine.opengl.texture.atlas.bitmap.BitmapTextureAtlas;
import org.andengine.opengl.texture.atlas.bitmap.BitmapTextureAtlasTextureRegionFactory;
import org.andengine.opengl.texture.region.TiledTextureRegion;
import org.andengine.opengl.vbo.VertexBufferObjectManager;

import android.content.Context;

import com.mym.pfc.actividades.Actividades;
import com.mym.pfc.clases.ContenedorObjetos;
import com.mym.pfc.clases.Posicion;

public class GestorImagenes {
	private TextureManager mTextureManager;
	private BitmapTextureAtlas mBitmapTextureAtlas;
    private TiledTextureRegion mBallTiledTextureRegion;
    //private Context contexto;
    private ContenedorObjetos elementos;
    private int width, height;
    private String nombreImagen;
    //private Sprite elemento;
    private ArrayList<Sprite> sprites;
    private Actividades act;
	
    public GestorImagenes(Actividades act, TextureManager tm, ContenedorObjetos elementos){
    	this.act = act;
		mTextureManager = tm;
		this.elementos = elementos;
		nombreImagen = elementos.getNombre();
		width = (int)elementos.getAnchuraX();
		height = (int)elementos.getAlturaY();
		sprites = new ArrayList<Sprite>();
		cargaImagen(nombreImagen, width, height);
		
    }
    
    private boolean cargaImagen(String nombreImagen, int width, int height){
		
    	this.mBitmapTextureAtlas = new BitmapTextureAtlas(mTextureManager, width, height);
        this.mBallTiledTextureRegion = BitmapTextureAtlasTextureRegionFactory.createTiledFromAsset(mBitmapTextureAtlas, act.getBaseContext(), nombreImagen, 0, 0, 1, 1);
        this.mBitmapTextureAtlas.load();
		return true;
		
	}
	
    //utilizada en sumas y restas
    /* 
     * area[0] = xIni
     * area[1] = xFin
     * area[2] = yIni
     * area[3] = yFin
     */
	public boolean dibujaImagenes(float[] area, float[] screenSize, VertexBufferObjectManager mVertexBufferObjectManager, Scene mScene){
		
		ArrayList<Posicion> posiciones = elementos.getPosiciones();
		
		float x = area[1]-area[0];
		float y = area[3]-area[2];
		Sprite mSprite;
		sprites = null;
		sprites = new ArrayList<Sprite>();
		int objDibujados = 0;
		for(Posicion pos : posiciones){
			if(objDibujados < elementos.getNumeroObjetos()){
				objDibujados++;
			}else{
				break;
			}
			
//			float pX = ((((pos.getN()[0] - pos.getM()[0]) / 2) * x) / 100) * screenSize[0];
//			float pY = ((((pos.getM()[1] - pos.getO()[1]) / 2) * y) / 100) * screenSize[1];

			
//			float pX = ((((pos.getM()[0]) / 2) * x) / 100) * screenSize[0];
//			float pY = ((((pos.getO()[1]) / 2) * y) / 100) * screenSize[1];
			
			float pX = (((pos.getM()[0]) * (x) ) * (screenSize[0]/100)) + (area[0] * screenSize[0]);
			float pY = (((pos.getO()[1]) * (y) ) * (screenSize[1]/100)) + (area[2] * screenSize[1]);
			
			mSprite = new Sprite(pX, pY, this.mBallTiledTextureRegion, mVertexBufferObjectManager)
	        {
	            @Override
	            public boolean onAreaTouched(TouchEvent pSceneTouchEvent, float X, float Y) 
	            {
	            	elementos.setTocado(true);
	            	//act.gameToast("Has tocado el conjunto de " + elementos.getNumeroObjetos() + " objetos.");
	                return true;
	            };
	        };
	        
	        //mSprite.setScale(x, y);
	        
	        mScene.registerTouchArea(mSprite);
	        mScene.attachChild(mSprite);
	        sprites.add(mSprite);
		}
        
		return false;
		
	}
	

}
