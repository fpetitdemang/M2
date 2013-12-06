package fr.um2;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class SecondActivity extends Activity {

	public final static String EXTRA_PHONE = "FR.UM2.SecondActivity.phone";
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_second);
		
		//Get the intent message and set it to the GUI
		Intent intent = getIntent();
		((TextView) findViewById(R.id.value_last_name)).setText(":  " + intent.getStringExtra(MainActivity.EXTRA_LAST_NAME));
		((TextView) findViewById(R.id.value_first_name)).setText(":  " + intent.getStringExtra(MainActivity.EXTRA_FIRST_NAME));
		((TextView) findViewById(R.id.value_age)).setText(":  " + intent.getStringExtra(MainActivity.EXTRA_AGE));
		((TextView) findViewById(R.id.value_skill)).setText(":  " + intent.getStringExtra(MainActivity.EXTRA_SKILL));
		((TextView) findViewById(R.id.value_phone)).setText(":  " + intent.getStringExtra(MainActivity.EXTRA_PHONE));

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.second, menu);
		return true;
	}
	
	public void send(View view){
		Intent intent = new Intent(this, ThirdActivity.class);
		intent.putExtra(EXTRA_PHONE, getIntent().getStringExtra(MainActivity.EXTRA_PHONE));
		startActivity(intent);
	}
	
	public void back(View view){
		finish();
	}

}
