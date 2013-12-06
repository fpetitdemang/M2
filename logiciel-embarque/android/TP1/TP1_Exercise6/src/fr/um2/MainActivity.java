package fr.um2;

import android.os.Bundle;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends Activity {

	public final static String EXTRA_LAST_NAME = "FR.UM2.MainActivity.last_name";
	public final static String EXTRA_FIRST_NAME = "FR.UM2.MainActivity.first_name";
	public final static String EXTRA_AGE = "FR.UM2.MainActivity.age";
	public final static String EXTRA_SKILL = "FR.UM2.MainActivity.skill";
	public final static String EXTRA_PHONE = "FR.UM2.MainActivity.phone";
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	
	public void confirm (View view){
		AlertDialog.Builder builder = new AlertDialog.Builder(this);
		builder.setTitle(R.string.dialog_title);
		builder.setMessage(R.string.dialog_message);
		
		builder.setPositiveButton(R.string.yes, new DialogInterface.OnClickListener() {
	           public void onClick(DialogInterface dialog, int id) {
	               Button confirm = (Button) findViewById(R.id.confirm);
	               confirm.setTextColor(Color.GREEN);
	               
	               Intent intent = new Intent(MainActivity.this, SecondActivity.class);
	               intent.putExtra(EXTRA_LAST_NAME, ":  " + ((EditText) findViewById(R.id.edit_last_name)).getText().toString());
	               intent.putExtra(EXTRA_FIRST_NAME, ":  " + ((EditText) findViewById(R.id.edit_first_name)).getText().toString());
	               intent.putExtra(EXTRA_PHONE, ":  " + ((EditText) findViewById(R.id.edit_age)).getText().toString());
	               intent.putExtra(EXTRA_SKILL, ":  " + ((EditText) findViewById(R.id.edit_skill)).getText().toString());
	               intent.putExtra(EXTRA_PHONE, ":  " + ((EditText) findViewById(R.id.edit_phone)).getText().toString());
	               startActivity(intent);
	           }
	       });
	builder.setNegativeButton(R.string.no, new DialogInterface.OnClickListener() {
	           public void onClick(DialogInterface dialog, int id) {
	        	   Button confirm = (Button) findViewById(R.id.confirm);
	               confirm.setTextColor(Color.BLACK);
	           }
	       });
		AlertDialog dialog = builder.create();
		dialog.show();
	}

}
