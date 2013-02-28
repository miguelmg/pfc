package com.mym.pfc.parser;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import com.mym.pfc.clases.Actividad;

public class ActivitatParser {
	public static ArrayList<Actividad> activitatParser(JSONObject jsonObj) throws JSONException{
		
		JSONArray jsonArray  = jsonObj.getJSONArray("actividad");
		ArrayList<Actividad> actividades = new ArrayList<Actividad>();
		
		for(int i = 0; i < jsonArray.length() && !jsonArray.isNull(i); i++){
			Actividad actividad = new Actividad();
		    actividad.setTitulo(jsonArray.getJSONObject(i).getString("titulo"));
			actividad.setRecursos(jsonArray.getJSONObject(i).getString("recursos"));
			actividad.setCantidad(jsonArray.getJSONObject(i).getInt("cantidad"));
			actividad.setObjetivo(jsonArray.getJSONObject(i).getString("objetivo"));
			actividades.add(actividad);
		}
		
		return actividades;
	}
}
