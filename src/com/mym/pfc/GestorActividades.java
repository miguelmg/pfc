package com.mym.pfc;

import java.util.jar.JarOutputStream;

import org.json.JSONObject;

import com.mym.pfc.actividades.Balones;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class GestorActividades extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		
		
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_gestor_actividades);
		
		
		Button buton = (Button)findViewById(R.id.buton);
		 
        //Implementamos el evento â€œclickâ€� del botÃ³n
        buton.setOnClickListener(new OnClickListener() {
             @Override
             public void onClick(View v) {
                  //Creamos el Intent
            	 JSONParser jsonParser = new JSONParser();
            	 JSONObject jsonObject = jsonParser.getJSONFromFile("activitat.json");
            	 
                 Intent intent = new Intent(GestorActividades.this, Balones.class);
 
				 //Creamos la informaciÃ³n a pasar entre actividades
				 //Bundle b = new Bundle();
				 //b.putString("NOMBRE", txtNombre.getText().toString());
				 
				 //AÃ±adimos la informaciÃ³n al intent
				 //intent.putExtras(b);
				 
				 //Iniciamos la nueva actividad
				 startActivity(intent);
             }
        });
		
	}

	public void onClick(View view){
        startActivity(new Intent(this, Balones.class));
    }
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.gestor_actividades, menu);
		return true;
	}

}
