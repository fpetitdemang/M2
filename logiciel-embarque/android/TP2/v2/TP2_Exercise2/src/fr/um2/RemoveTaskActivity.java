package fr.um2;

import fr.um2.db.TaskDbHelper;
import fr.um2.db.TaskContract.CategoryEntry;
import fr.um2.db.TaskContract.TaskEntry;
import android.os.Bundle;
import android.app.Activity;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteQueryBuilder;
import android.support.v4.widget.SimpleCursorAdapter;
import android.view.Menu;
import android.widget.ListView;

public class RemoveTaskActivity extends Activity {

	static String date;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_remove_task);
		
		date = getIntent().getExtras().getString(MainActivity.EXTRA_DATE);

		SQLiteDatabase db = TaskDbHelper.getInstance(getApplicationContext())
				.getReadableDatabase();

		// Define a projection that specifies which columns from the database
		// you will actually use after this query.
		String[] projection = { 
				TaskEntry.FULL_ID,
				TaskEntry.COLUMN_NAME_TASK_NAME,
				TaskEntry.COLUMN_NAME_TASK_DATE,
				TaskEntry.COLUMN_NAME_TASK_START_TIME,
				TaskEntry.COLUMN_NAME_TASK_END_TIME,
				CategoryEntry.COLUMN_NAME_CATEGORY_NAME};

		// Define 'where' part of query.
		String selection = TaskEntry.COLUMN_NAME_TASK_DATE + " LIKE ?";
		// Specify arguments in placeholder order.
		String[] selectionArgs = { String.valueOf("id = ?") };

		// How you want the results sorted in the resulting Cursor
		String sortOrder = TaskEntry.COLUMN_NAME_TASK_START_TIME + TaskDbHelper.ASC;
		
		//Create new querybuilder
		SQLiteQueryBuilder _QB = new SQLiteQueryBuilder();
		 		
		//Specify books table and add join to categories table (use full_id for joining categories table)
		_QB.setTables(TaskEntry.TABLE_NAME + 
		        " LEFT OUTER JOIN " + CategoryEntry.TABLE_NAME + " ON " + 
		        TaskEntry.COLUMN_NAME_CATEGORY_ID + " = " + CategoryEntry.FULL_ID);

		//Get cursor
		Cursor cursor = _QB.query(db, projection, null, null, null, null, sortOrder);
		startManagingCursor(cursor);
		
		int[] to = new int[] {R.id.remove_id, R.id.remove_name, R.id.remove_date, R.id.remove_start, R.id.remove_end, R.id.remove_category_item};
		
		// CREATE THE ADAPTER USING THE CURSOR POINTING TO THE DESIRED DATA AS WELL AS THE LAYOUT INFORMATION
        SimpleCursorAdapter mAdapter = new SimpleCursorAdapter(this, R.layout.remove_task_items, cursor, projection, to);

        // SET THIS ADAPTER AS YOUR LISTACTIVITY'S ADAPTER
        ListView listTasks = (ListView) findViewById(R.id.remeve_tasks_list);
        listTasks.setAdapter(mAdapter);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.remove_task, menu);
		return true;
	}

}
