package fr.um2;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		RelativeLayout relativeLayout = new RelativeLayout(this);
		
		//Add Last Name TextVeiw
		TextView lastName = new TextView(this);
		lastName.setText(R.string.last_name);
		relativeLayout.addView(lastName);
	
		
		//Add First name
		TextView firstName = new TextView(this);
		lastName.setText(R.string.first_name);
		relativeLayout.addView(firstName);
		
		//Add Age
		TextView age = new TextView(this);
		lastName.setText(R.string.age);
		relativeLayout.addView(age);
		
		//Add professional skill
		TextView skill = new TextView(this);
		lastName.setText(R.string.skill);
		relativeLayout.addView(skill);
		
		//Add phone number
		TextView phoneNumber = new TextView(this);
		lastName.setText(R.string.phone);
		relativeLayout.addView(phoneNumber);
		
		//Add OK button
		Button ok = new Button(this);
		ok.setText(R.string.ok);
		relativeLayout.addView(ok);
		
		setContentView(relativeLayout);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
