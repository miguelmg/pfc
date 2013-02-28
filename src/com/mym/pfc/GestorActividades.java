package com.mym.pfc;

import java.util.ArrayList;
import java.util.jar.JarOutputStream;

import org.json.JSONObject;

import com.mym.pfc.actividades.Balones;
import com.mym.pfc.clases.Actividad;
import com.mym.pfc.parser.ActivitatParser;
import com.mym.pfc.parser.JSONParser;

import android.os.Bundle;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.database.DataSetObserver;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.ListView;

public class GestorActividades extends Activity implements OnItemClickListener{

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		
		
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_gestor_actividades);
		final Context c = GestorActividades.this.getBaseContext();
		
		JSONParser jsonParser = new JSONParser(c);
		JSONObject jsonObject = jsonParser.getJSONFromFile("activitat.json");
		ArrayList<Actividad> actividades = new ArrayList<Actividad>();
		
		try{
			actividades = ActivitatParser.activitatParser(jsonObject);
		}catch(Exception ex){
			System.out.println();
		}
		
		ArrayList<String> titulosActividades = new ArrayList<String>();
		
		for (Actividad actividad : actividades) {
			titulosActividades.add(actividad.getTitulo());
		}
		
		
		ListView listaActividades = (ListView)findViewById(R.id.lista_actividades);
		
		listaActividades.setAdapter(new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1 , titulosActividades));
		listaActividades.setOnItemClickListener(this);
		
		/*Haciendo funcional la lista*/
		
        

		 //Creamos la informaciÃ³n a pasar entre actividades
		 //Bundle b = new Bundle();
		 //b.putString("NOMBRE", txtNombre.getText().toString());
		 
		 //AÃ±adimos la informaciÃ³n al intent
		 //intent.putExtras(b);
		 
		 //Iniciamos la nueva actividad
		 //startActivity(intent);
		
		
		
		
		//Button buton = (Button)findViewById(R.id.buton);
		 
        //Implementamos el evento â€œclickâ€� del botÃ³n
        /*buton.setOnClickListener(new OnClickListener() {
             @Override
             public void onClick(View v) {
                  //Creamos el Intent
            	 
             }
        });*/
		
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

	@Override
	public void onItemClick(AdapterView<?> arg0, View arg1, int position, long arg3) {
		
		Intent intent = new Intent(GestorActividades.this, Balones.class);
		startActivity(intent);
		/*Intent intent = new Intent();
        intent.putExtra(LlistatOpcions.KEY_LLISTAT, opcions[position]);
        
        intent.setClass(this, com.tarracodroid.apps.tastabirres.LlistaBars.class);
        this.startActivity(intent);*/
	}

}
