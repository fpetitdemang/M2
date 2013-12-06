package fr.um2;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import fr.um2.AddTaskActivity.TimePickerFragment;
import fr.um2.db.TaskDbHelper;
import fr.um2.db.TaskContract.CategoryEntry;
import android.os.Bundle;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.text.format.DateFormat;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.DatePicker;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.CalendarView.OnDateChangeListener;

@SuppressLint("ValidFragment")
public class MainActivity extends Activity {

	public static final String EXTRA_DATE = "fr.um2.MainActivity.date";
	public static final String EXTRA_DATE_START = "fr.um2.MainActivity.date_start";
	public static final String EXTRA_DATE_END = "fr.um2.MainActivity.date_end";
	public static final String EXTRA_CATEGORY = "fr.um2.MainActivity.category";

	public static final String ACTION_NULL = "fr.um2.MainActivity.action.null";
	public static final String ACTION_DATE = "fr.um2.MainActivity.action.date";
	public static final String ACTION_CATEGORY = "fr.um2.MainActivity.action.category";
	public static final String ACTION_CATEGORY_DATE = "fr.um2.MainActivity.action.category_date";

	static String startDate;
	static String endDate;
	static String category;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		// create DB
		TaskDbHelper.getInstance(getApplicationContext());

		CalendarView calendar = (CalendarView) findViewById(R.id.calendarView);
		calendar.setOnDateChangeListener(new OnDateChangeListener() {
			@Override
			public void onSelectedDayChange(CalendarView view, int year,
					int month, int dayOfMonth) {
				Toast.makeText(getApplicationContext(),
						dayOfMonth + "/" + month + "/" + year,
						Toast.LENGTH_SHORT).show();
			}
		});
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
		switch (item.getItemId()) {
		case R.id.add_task:
			addTask();
			return true;

		case R.id.display_task_all:
			displayTaskAll();
			return true;

		case R.id.display_task_by_category:
			displayTaskByCategory();
			return true;

		case R.id.display_task_by_date:
			displayTaskByDate();
			return true;

		case R.id.display_task_by_category_and_date:
			displayTaskByCategoryAndDate();
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
	public void addTask() {
		Intent intent = new Intent(this, AddTaskActivity.class);
		SimpleDateFormat ss = new SimpleDateFormat("dd-MM-yyyy");
		String date = ss
				.format(((CalendarView) findViewById(R.id.calendarView))
						.getDate());
		intent.putExtra(EXTRA_DATE, date);
		startActivity(intent);
	}

	public void displayTaskAll() {
		Intent intent = new Intent(this, DisplayTaskActivity.class);
		intent.setAction(ACTION_NULL);
		startActivity(intent);
	}

	public void displayTaskByCategory() {
		showDialog(ACTION_CATEGORY);
	}

	public void displayTaskByDate() {
		showDialog(ACTION_DATE);
	}

	public void displayTaskByCategoryAndDate() {
		showDialog(ACTION_CATEGORY_DATE);
	}

	public void removeTask() {
		Intent intent = new Intent(this, RemoveTaskActivity.class);
		SimpleDateFormat ss = new SimpleDateFormat("dd-MM-yyyy");
		String date = ss
				.format(((CalendarView) findViewById(R.id.calendarView))
						.getDate());
		intent.putExtra(EXTRA_DATE, date);
		startActivity(intent);
	}

	// Create a custom dialog to input search task parameters
	public void showDialog(final String action) {

		// custom dialog
		final Dialog dialog = new Dialog(MainActivity.this);
		if (action.equals(ACTION_DATE)) {
			dialog.setContentView(R.layout.date_dialog);
			dialog.setTitle("Date");
			Button startDate = (Button) dialog
					.findViewById(R.id.groupe_by_start_date);
			startDate.setOnClickListener(new OnClickListener() {
				@Override
				public void onClick(View v) {
					DialogFragment newFragment = new DatePickerFragment(v);
					newFragment.show(getFragmentManager(), "timePicker");
				}
			});

			Button endDate = (Button) dialog
					.findViewById(R.id.groupe_by_end_date);
			endDate.setOnClickListener(new OnClickListener() {
				@Override
				public void onClick(View v) {
					DialogFragment newFragment = new DatePickerFragment(v);
					newFragment.show(getFragmentManager(), "timePicker");
				}
			});
		}

		else if (action.equals(ACTION_CATEGORY)) {
			dialog.setContentView(R.layout.category_dialog);
			dialog.setTitle("Category");

			ArrayAdapter<String> mAdapter = new ArrayAdapter<String>(this,
					R.layout.spiner_item, AddCategoryActivity.getCategories());
			Spinner categories = (Spinner) dialog
					.findViewById(R.id.groupe_by_category);

			Log.println(Log.INFO, "ArrayAdapter<String> = ", "" + mAdapter);
			Log.println(Log.INFO, "Spinner = ", "" + categories);

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

		else if (action.equals(ACTION_CATEGORY_DATE)) {
			dialog.setContentView(R.layout.category_date_dialog);
			dialog.setTitle("Category and Date");

			// chose category
			ArrayAdapter<String> mAdapter = new ArrayAdapter<String>(this,
					R.layout.spiner_item, AddCategoryActivity.getCategories());
			Spinner categories = (Spinner) dialog
					.findViewById(R.id.groupe_by_category_date);

			Log.println(Log.INFO, "ArrayAdapter<String> = ", "" + mAdapter);
			Log.println(Log.INFO, "Spinner = ", "" + categories);

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

			// start date
			Button startDate = (Button) dialog
					.findViewById(R.id.groupe_by_start_date);
			startDate.setOnClickListener(new OnClickListener() {
				@Override
				public void onClick(View v) {
					DialogFragment newFragment = new DatePickerFragment(v);
					newFragment.show(getFragmentManager(), "timePicker");
				}
			});
			// end date
			Button endDate = (Button) dialog
					.findViewById(R.id.groupe_by_end_date);
			endDate.setOnClickListener(new OnClickListener() {
				@Override
				public void onClick(View v) {
					DialogFragment newFragment = new DatePickerFragment(v);
					newFragment.show(getFragmentManager(), "timePicker");
				}
			});
		}

		Button ok = (Button) dialog.findViewById(R.id.dialog_ok);
		ok.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent intent = new Intent(MainActivity.this,
						DisplayTaskActivity.class);

				if (action.equals(ACTION_DATE)) {
					intent.putExtra(EXTRA_DATE, startDate);
					intent.putExtra(EXTRA_DATE, endDate);
					intent.setAction(ACTION_DATE);

				} else if (action.equals(ACTION_CATEGORY)) {
					intent.putExtra(EXTRA_CATEGORY, category);
					intent.setAction(ACTION_CATEGORY);

				} else if (action.equals(ACTION_CATEGORY_DATE)) {
					intent.putExtra(EXTRA_DATE, startDate);
					intent.putExtra(EXTRA_DATE, endDate);
					intent.putExtra(EXTRA_CATEGORY, category);
					intent.setAction(ACTION_CATEGORY_DATE);
				}

				startActivity(intent);
				dialog.dismiss();
			}
		});

		Button cancel = (Button) dialog.findViewById(R.id.dialog_cancel);
		cancel.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				dialog.dismiss();
			}
		});

		dialog.show();
	}

	public void addCategory() {
		Intent intent = new Intent(this, AddCategoryActivity.class);
		startActivity(intent);
	}

	public static class DatePickerFragment extends DialogFragment implements
			DatePickerDialog.OnDateSetListener {
		
		Button button;
		
		public DatePickerFragment(View view) {
			super();
			this.button = (Button) view;
		}


		@Override
		public Dialog onCreateDialog(Bundle savedInstanceState) {
			// Use the current date as the default date in the picker
			final Calendar c = Calendar.getInstance();
			int year = c.get(Calendar.YEAR);
			int month = c.get(Calendar.MONTH);
			int day = c.get(Calendar.DAY_OF_MONTH);

			// Create a new instance of DatePickerDialog and return it
			return new DatePickerDialog(getActivity(), this, year, month, day);
		}

		public void onDateSet(DatePicker view, int year, int month, int day) {
			
			SimpleDateFormat ss = new SimpleDateFormat("dd-MM-yyyy");
			String date = ss.format(new Date(year, month, day));
			button.setText(date);
			
			if (button.getId() == R.id.groupe_by_start_date) {
				startDate = date;
			} else if (button.getId() == R.id.groupe_by_end_date) {
				endDate = date;
			}
		}
	}
}
