package fr.um2;

import fr.um2.db.TaskContract.CategoryEntry;
import fr.um2.db.TaskContract.TaskEntry;
import fr.um2.db.TaskDbHelper;
import android.os.Bundle;
import android.app.Activity;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteQueryBuilder;
import android.support.v4.widget.SimpleCursorAdapter;
import android.util.Log;
import android.view.Menu;
import android.widget.ListView;

public class DisplayTaskActivity extends Activity {

	static String date;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_display_task);
		
		date = getIntent().getExtras().getString(MainActivity.EXTRA_DATE);


		SQLiteDatabase db = TaskDbHelper.getInstance(getApplicationContext())
				.getReadableDatabase();

		// Define a projection that specifies which columns from the database
		// you will actually use after this query.
		String[] colomn = { 
				TaskEntry._ID,
				TaskEntry.COLUMN_NAME_TASK_NAME,
				TaskEntry.COLUMN_NAME_TASK_DATE,
				TaskEntry.COLUMN_NAME_TASK_START_TIME,
				TaskEntry.COLUMN_NAME_TASK_END_TIME,
				CategoryEntry.COLUMN_NAME_CATEGORY_NAME};
		
		String[] projection = { 
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

//		Cursor cursor = db.query(
//				TaskEntry.TABLE_NAME, // The table to query
//				null, // The columns to return
//				null, // The columns for the WHERE clause
//				null, // The values for the WHERE clause
//				null, // group the rows
//				null, // filter by row groups
//				sortOrder // The sort order
//				);
		
//		String selectQuery = "SELECT  * FROM " + TaskEntry.TABLE_NAME + " task LEFT OUTER JOIN "
//				+ CategoryEntry.TABLE_NAME + " category" + " ON task."
//				+ TaskEntry.COLUMN_NAME_CATEGORY_ID + " = " + "category. " + CategoryEntry._ID;
		
//		Cursor cursor = db.rawQuery(selectQuery, null);
		
		//Create new querybuilder
		SQLiteQueryBuilder _QB = new SQLiteQueryBuilder();
		 
		//Specify books table and add join to categories table (use full_id for joining categories table)
		_QB.setTables(TaskEntry.TABLE_NAME + 
		        " LEFT OUTER JOIN " + CategoryEntry.TABLE_NAME + " ON " + 
		        TaskEntry.COLUMN_NAME_CATEGORY_ID + " = " + CategoryEntry.FULL_ID);
		 
		//Get cursor
		Cursor cursor = _QB.query(db, projection, null, null, null, null, sortOrder);

		
		startManagingCursor(cursor);
		
		

				Log.println(Log.INFO, "cursor", cursor.toString());
		
		
		int[] to = new int[] {/* R.id.id, */R.id.name, R.id.date, R.id.start, R.id.end, R.id.category};
		
		// CREATE THE ADAPTER USING THE CURSOR POINTING TO THE DESIRED DATA AS WELL AS THE LAYOUT INFORMATION
        SimpleCursorAdapter mAdapter = new SimpleCursorAdapter(this, R.layout.task_items, cursor, colomn, to);

        // SET THIS ADAPTER AS YOUR LISTACTIVITY'S ADAPTER
        ListView listTasks = (ListView) findViewById(R.id.tasks_list);
        listTasks.setAdapter(mAdapter);

//		// test
//		if (cursor.moveToFirst()) {
//			do {
//				int id = cursor.getInt(0);
//				String name = cursor.getString(1);
//				String date = cursor.getString(2);
//				String start = cursor.getString(3);
//				String end = cursor.getString(4);
//				String cat = cursor.getString(5);
//
//				Log.println(Log.INFO, "Task[" + id + "]", name + " , " + date
//						+ " , " + start + " , " + end + " , " + cat);
//			} while (cursor.moveToNext());
//		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.display_task, menu);
		return true;
	}

}
