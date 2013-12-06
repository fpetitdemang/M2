package fr.um2;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class MainActivity extends Activity {
	
	String[] garTimes = {
			  "6:00", "6:30", "7:00", "7:30", "8:30", "9:30", "10:30", "11:00"
			, "11:30", "12:00", "12:30", "13:00", "14:30", "15:30", "16:30", "17:00"
			, "17:30", "18:00", "18:30", "19:30", "20:00", "21:30", "22:30"};

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		ArrayAdapter<String> mAdapter = new ArrayAdapter<String>(getApplicationContext(),
	            R.layout.item, garTimes);
		 final ListView list = (ListView) findViewById(R.id.list);
	        list.setAdapter(mAdapter);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
