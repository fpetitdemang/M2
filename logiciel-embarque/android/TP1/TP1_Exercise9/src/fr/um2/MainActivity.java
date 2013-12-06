package fr.um2;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.widget.CalendarView;
import android.widget.CalendarView.OnDateChangeListener;
import android.widget.Toast;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		CalendarView calendar = (CalendarView)findViewById(R.id.calendarView);
		calendar.setOnDateChangeListener(new OnDateChangeListener(){
		@Override
		public void onSelectedDayChange(CalendarView view,
		int year, int month, int dayOfMonth) {
		Toast.makeText(getApplicationContext(),
		dayOfMonth +"/"+month+"/"+ year,Toast.LENGTH_SHORT).show();}});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
