package com.mym.pfc;

import java.util.ArrayList;

import org.json.JSONObject;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.mym.pfc.actividades.Balones;
import com.mym.pfc.actividades.OrdenaNumeros;
import com.mym.pfc.actividades.RestaImagenes;
import com.mym.pfc.actividades.SumaImagenes;
import com.mym.pfc.actividades.Textos;
import com.mym.pfc.clases.Actividad;
import com.mym.pfc.parser.ActivitatParser;
import com.mym.pfc.parser.JSONParser;

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
		Intent intent;

		switch (position) {
			case 0:
					intent = new Intent(GestorActividades.this, Balones.class);
					startActivity(intent);
					//finish();
				break;
			case 1:
					intent = new Intent(GestorActividades.this, Textos.class);
					startActivity(intent);
					//finish();
			break;
			case 2:
				intent = new Intent(GestorActividades.this, SumaImagenes.class);
				intent.putExtra("imagen", "pelotas.png");
				intent.putExtra("numeroResultados", 4);
				intent.putExtra("rangoNum1", 5);
				intent.putExtra("rangoNum2", 7);
				startActivity(intent);
				//finish();
				break;
			case 3:
				intent = new Intent(GestorActividades.this, RestaImagenes.class);
				intent.putExtra("imagen", "pelotas.png");
				intent.putExtra("numeroResultados", 4);
				intent.putExtra("rangoNum1", 5);
				intent.putExtra("rangoNum2", 7);
				startActivity(intent);
				//finish();
				break;
			case 4:
				intent = new Intent(GestorActividades.this, OrdenaNumeros.class);
				intent.putExtra("imagen", "pelotas.png");
				intent.putExtra("numeroResultados", 4);
				intent.putExtra("rangoNum1", 5);
				intent.putExtra("rangoNum2", 7);
				startActivity(intent);
				//finish();
				break;
			default:
				break;
		}	
		
		/*Intent intent = new Intent();
        intent.putExtra(LlistatOpcions.KEY_LLISTAT, opcions[position]);
        
        intent.setClass(this, com.tarracodroid.apps.tastabirres.LlistaBars.class);
        this.startActivity(intent);*/
	}

}
