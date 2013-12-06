package fr.um2;

import java.text.SimpleDateFormat;

import fr.um2.db.TaskDbHelper;
import android.os.Bundle;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.text.format.DateFormat;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.CalendarView;
import android.widget.Toast;
import android.widget.CalendarView.OnDateChangeListener;

public class MainActivity extends Activity {
	
	public static final String EXTRA_DATE = "fr.um2.MainActivity.date";
	public static final String EXTRA_CATEGORY = "fr.um2.MainActivity.category";
	public static final String EXTRA_TIME = "fr.um2.MainActivity.time";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		//create DB
		TaskDbHelper.getInstance(getApplicationContext());
		
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
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		 // Handle presses on the action bar items
		switch(item.getItemId())
		{
		case R.id.add_task:
			addTask();
			return true;
			
		case R.id.display_task_all:
			displayTaskAll();
			return true;
			
		case R.id.display_task_by_category:
			displayTaskByCategory();
			return true;
			
		case R.id.display_task_by_time:
			displayTaskByTime();
			return true;
			
		case R.id.display_task_by_category_and_time:
			displayTaskByCategoryAndTime();
			return true;
			
		case R.id.remove_task:
			removeTask();
			return true;
			
		case R.id.add_category:
			addCategory();
			return true;
			
			default:
				return super.onOptionsItemSelected(item);
		}
	}
		
		@SuppressLint("NewApi")
		public void addTask(){
			Intent intent = new Intent(this, AddTaskActivity.class);
			SimpleDateFormat ss = new SimpleDateFormat("dd-MM-yyyy");
			String date= ss.format(((CalendarView)findViewById(R.id.calendarView)).getDate());
			intent.putExtra(EXTRA_DATE, date);
			startActivity(intent);
		}
		
		public void displayTaskAll(){
			Intent intent = new Intent(this, DisplayTaskActivity.class);
			SimpleDateFormat ss = new SimpleDateFormat("dd-MM-yyyy");
			String date= ss.format(((CalendarView)findViewById(R.id.calendarView)).getDate());
			intent.putExtra(EXTRA_DATE, date);
			startActivity(intent);
		}
		
		public void displayTaskByCategory(){
			Intent intent = new Intent(this, DisplayTaskActivity.class);
			SimpleDateFormat ss = new SimpleDateFormat("dd-MM-yyyy");
			String date= ss.format(((CalendarView)findViewById(R.id.calendarView)).getDate());
			intent.putExtra(EXTRA_DATE, date);
			startActivity(intent);
		}
		
		public void displayTaskByTime(){
			Intent intent = new Intent(this, DisplayTaskActivity.class);
			SimpleDateFormat ss = new SimpleDateFormat("dd-MM-yyyy");
			String date= ss.format(((CalendarView)findViewById(R.id.calendarView)).getDate());
			intent.putExtra(EXTRA_DATE, date);
			startActivity(intent);
		}
		
		public void displayTaskByCategoryAndTime(){
			Intent intent = new Intent(this, DisplayTaskActivity.class);
			SimpleDateFormat ss = new SimpleDateFormat("dd-MM-yyyy");
			String date= ss.format(((CalendarView)findViewById(R.id.calendarView)).getDate());
			intent.putExtra(EXTRA_DATE, date);
			startActivity(intent);
		}
		
		public void removeTask(){
			Intent intent = new Intent(this, RemoveTaskActivity.class);
			SimpleDateFormat ss = new SimpleDateFormat("dd-MM-yyyy");
			String date= ss.format(((CalendarView)findViewById(R.id.calendarView)).getDate());
			intent.putExtra(EXTRA_DATE, date);
			startActivity(intent);
		}
		
		public void addCategory(){
			Intent intent = new Intent(this, AddCategoryActivity.class);
			startActivity(intent);
		}

}
