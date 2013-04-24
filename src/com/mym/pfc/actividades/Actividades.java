package com.mym.pfc.actividades;

import org.andengine.engine.options.EngineOptions;
import org.andengine.entity.scene.Scene;
import org.andengine.ui.activity.SimpleBaseGameActivity;

import android.widget.Toast;

public abstract class Actividades extends SimpleBaseGameActivity {

	public int resultadoCorrecto = 0;
	public boolean respuestaCorrecta = false;
	//Method for generating Toast messages as they need to run on UI thread
    public void gameToast(final String msg) {
	    this.runOnUiThread(new Runnable() {
	        @Override
	        public void run() {
	           Toast.makeText(Actividades.this, msg, Toast.LENGTH_SHORT).show();
	        }
	    });
	}
    
    //reinicia la actividad con otros valores (cambia de pantalla)
    public abstract void reiniciarActividad();

    
    //actualiza la pantalla o la repinta (la misma pantalla)
	public abstract void actualizarActividad();
    
    
}
