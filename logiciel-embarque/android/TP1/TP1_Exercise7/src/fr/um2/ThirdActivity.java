package fr.um2;

import android.net.Uri;
import android.os.Bundle;
import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.TextView;

public class ThirdActivity extends Activity {

	String phone;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_third);
		
		Intent intent = getIntent();
		((TextView) findViewById(R.id.phone_number)).setText(intent.getStringExtra(SecondActivity.EXTRA_PHONE));
		phone = intent.getStringExtra(SecondActivity.EXTRA_PHONE);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.third, menu);
		return true;
	}
	
	public void call(View view){
		 try {
			 //Dont forget to add <uses-permission android:name="android.permission.CALL_PHONE"></uses-permission> in minfist.
		        Intent callIntent = new Intent(Intent.ACTION_CALL);
		        callIntent.setData(Uri.parse("tel:" + phone));
		        startActivity(callIntent);
		    } catch (ActivityNotFoundException e) {
		        Log.e("Call failed", "Call failed", e);
		    }
	}
	
	public void cancel(View view){
		finish();
	}
}
