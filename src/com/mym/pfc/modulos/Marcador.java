package com.mym.pfc.modulos;

import org.andengine.entity.scene.Scene;
import org.andengine.opengl.vbo.VertexBufferObjectManager;
import org.andengine.util.color.Color;

import com.mym.pfc.actividades.Actividades;
import com.mym.pfc.actividades.SumaImagenes;

public class Marcador {
	
	private static int aciertos;
	private static int errores;
	private static int repeticiones;
	private static Scene mMainScene;
	private static VertexBufferObjectManager mVbom;
	private static int mX, mY;
	
	public static void incrementarAciertos(){
		aciertos++;
	}

	public static void incrementarErrores(){
		errores++;
	}

	public static void incrementarRepeticiones(){
		repeticiones++;
	}
	
	public static void cargarMarcador(Scene mScene, VertexBufferObjectManager vbom, int x, int y){
		mMainScene = mScene;
		mVbom = vbom;
		mX = x;
		mY = y;
	}
	
	public static void actualizaMarcador(GestorFuentes gf){

        gf.anadirTextoMarcador(mX, mY, String.valueOf(aciertos),  mMainScene, mVbom, Color.GREEN_ARGB_PACKED_INT);
        gf.anadirTextoMarcador(mX*1.1f, mY, String.valueOf(errores), mMainScene, mVbom, Color.RED_ARGB_PACKED_INT);
        gf.anadirTextoMarcador(mX*1.2f, mY, String.valueOf(repeticiones), mMainScene, mVbom, Color.BLUE_ARGB_PACKED_INT);
        
        gf.setColor(Color.BLACK_ARGB_PACKED_INT);
	}

	
	
}
