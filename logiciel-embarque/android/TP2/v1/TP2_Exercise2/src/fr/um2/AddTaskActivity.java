package fr.um2;

import java.util.Calendar;

import fr.um2.db.TaskDbHelper;
import fr.um2.db.TaskContract.TaskEntry;

import android.os.Bundle;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.app.TimePickerDialog;
import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.text.format.DateFormat;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;

@SuppressLint("ValidFragment")
public class AddTaskActivity extends Activity {
	
	static String taskName;
	static String date;
	static String startTime;
	static String endTime;
	static String category;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_add_task);
		
		date = getIntent().getExtras().getString(MainActivity.EXTRA_DATE);
		
		ArrayAdapter<String> mAdapter = new ArrayAdapter<String>(this,
	            R.layout.spiner_item, AddCategoryActivity.getCategories());
		 Spinner categories = (Spinner) findViewById(R.id.edit_category);
		 categories.setAdapter(mAdapter);
		 categories.setOnItemSelectedListener(new OnItemSelectedListener() {

			@Override
			public void onItemSelected(AdapterView<?> arg0, View view,
					int arg2, long arg3) {
				category = ((TextView) view).getText().toString();
				
				
			}

			@Override
			public void onNothingSelected(AdapterView<?> arg0) {
				// TODO Auto-generated method stub
				
			}
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.add_task, menu);
		return true;
	}

	public void startTime(View view) {
		DialogFragment newFragment = new TimePickerFragment(view);
		newFragment.show(getFragmentManager(), "timePicker");
	}

	public void endTime(View view) {
		DialogFragment newFragment = new TimePickerFragment(view);
		newFragment.show(getFragmentManager(), "timePicker");
	}
	
	public void addTask(View view){
		taskName = ((EditText) findViewById(R.id.edit_task_name)).getText().toString();
		Log.println(Log.INFO, "Task", taskName + " , " + date + " , " + startTime + " , " + endTime + " , " + category);
		
		//insert task to DB
		// Gets the data repository in write mode
		SQLiteDatabase db = TaskDbHelper.getInstance(getApplicationContext()).getWritableDatabase();

		// Create a new map of values, where column names are the keys
		ContentValues values = new ContentValues();
		values.put(TaskEntry.COLUMN_NAME_TASK_NAME, taskName);
		values.put(TaskEntry.COLUMN_NAME_TASK_DATE, date);
		values.put(TaskEntry.COLUMN_NAME_TASK_START_TIME, startTime);
		values.put(TaskEntry.COLUMN_NAME_TASK_END_TIME, endTime);
		values.put(TaskEntry.COLUMN_NAME_CATEGORY_ID, 1);

		// Insert the new row, returning the primary key value of the new row
		long newRowId;
		newRowId = db.insert(TaskEntry.TABLE_NAME, null, values);
	}

	public static class TimePickerFragment extends DialogFragment implements
			TimePickerDialog.OnTimeSetListener {
		Button button;

		public TimePickerFragment(View view) {
			super();
			this.button = (Button) view;
		}

		@Override
		public Dialog onCreateDialog(Bundle savedInstanceState) {
			// Use the current time as the default values for the picker
			final Calendar c = Calendar.getInstance();
			int hour = c.get(Calendar.HOUR_OF_DAY);
			int minute = c.get(Calendar.MINUTE);

			// Create a new instance of TimePickerDialog and return it
			return new TimePickerDialog(getActivity(), this, hour, minute,
					DateFormat.is24HourFormat(getActivity()));
		}

		public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
			String time = hourOfDay + ":" + minute;
			button.setText(time);
			if(button.getId() == R.id.edit_start_time){
				startTime = time;
			}else if(button.getId() == R.id.edit_end_time){
				endTime = time;
			}
		}
	}

}
