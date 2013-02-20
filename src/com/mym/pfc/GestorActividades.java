package com.mym.pfc;

import com.mym.pfc.actividades.Balones;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;

public class GestorActividades extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		
		
		
		//super.onCreate(savedInstanceState);
		//setContentView(R.layout.activity_gestor_actividades);
		try {
			
			Intent intent = new Intent();
	        intent.setClass(this, Balones.class);
	        this.startActivity(intent);
			/*Bundle myData = new Bundle();
			myData.putString("myString1", "Hello Android");
			myData.putDouble("myDouble1", 3.141592);
			int[] myLittleArray = { 1, 2, 3 };
			myData.putIntArray("myIntArray1", myLittleArray);

			myIntentA1A2.putExtras(myData);

			startActivityForResult(myIntentA1A2,IPC_ID);*/
		} catch (Exception e) {
			
		}
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.gestor_actividades, menu);
		return true;
	}

}
