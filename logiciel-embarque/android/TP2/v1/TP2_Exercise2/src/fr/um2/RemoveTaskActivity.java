package fr.um2;

import fr.um2.db.TaskDbHelper;
import fr.um2.db.TaskContract.TaskEntry;
import android.os.Bundle;
import android.app.Activity;
import android.database.sqlite.SQLiteDatabase;
import android.view.Menu;

public class RemoveTaskActivity extends Activity {

	static String date;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_remove_task);
		
		date = getIntent().getExtras().getString(MainActivity.EXTRA_DATE);
		
		SQLiteDatabase db = TaskDbHelper.getInstance(getApplicationContext()).getWritableDatabase();
		// Define 'where' part of query.
		String selection = TaskEntry.COLUMN_NAME_TASK_DATE+ " LIKE ?";
		// Specify arguments in placeholder order.
		String[] selectionArgs = { String.valueOf("id = ?") };
		// Issue SQL statement.
		db.delete(TaskEntry.TABLE_NAME, selection, selectionArgs);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.remove_task, menu);
		return true;
	}

}
