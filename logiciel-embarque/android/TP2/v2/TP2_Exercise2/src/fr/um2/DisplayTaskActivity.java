package fr.um2;

import fr.um2.db.TaskContract.CategoryEntry;
import fr.um2.db.TaskContract.TaskEntry;
import fr.um2.db.TaskDbHelper;
import android.os.Bundle;
import android.app.Activity;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteQueryBuilder;
import android.support.v4.app.TaskStackBuilder;
import android.support.v4.widget.SimpleCursorAdapter;
import android.util.Log;
import android.view.ActionMode;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.ListView;
import android.widget.Toast;

public class DisplayTaskActivity extends Activity {

	Cursor cursor;
	SimpleCursorAdapter simpleAdapter;
	static String date;
	protected Object actionMode;
	public int selectedItem = -1;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_display_task);

		date = getIntent().getExtras().getString(MainActivity.EXTRA_DATE);

		SQLiteDatabase db = TaskDbHelper.getInstance(getApplicationContext())
				.getReadableDatabase();

		// Define a projection that specifies which columns from the database
		// you will actually use after this query.
		String[] projection = { TaskEntry.FULL_ID,
				TaskEntry.COLUMN_NAME_TASK_NAME,
				TaskEntry.COLUMN_NAME_TASK_DATE,
				TaskEntry.COLUMN_NAME_TASK_START_TIME,
				TaskEntry.COLUMN_NAME_TASK_END_TIME,
				CategoryEntry.COLUMN_NAME_CATEGORY_NAME };
		
		String[] itemListView = { 
				TaskEntry.COLUMN_NAME_TASK_NAME,
				TaskEntry.COLUMN_NAME_TASK_DATE,
				TaskEntry.COLUMN_NAME_TASK_START_TIME,
				TaskEntry.COLUMN_NAME_TASK_END_TIME,
				CategoryEntry.COLUMN_NAME_CATEGORY_NAME };

		// Define 'where' part of query.
		String selection = TaskEntry.COLUMN_NAME_TASK_DATE + " LIKE ?";
		// Specify arguments in placeholder order.
		String[] selectionArgs = { String.valueOf("id = ?") };

		// How you want the results sorted in the resulting Cursor
		String sortOrder = TaskEntry.COLUMN_NAME_TASK_START_TIME
				+ TaskDbHelper.ASC;

		// Create new querybuilder
		SQLiteQueryBuilder _QB = new SQLiteQueryBuilder();

		// Specify books table and add join to categories table (use full_id for
		// joining categories table)
		_QB.setTables(TaskEntry.TABLE_NAME + " LEFT OUTER JOIN "
				+ CategoryEntry.TABLE_NAME + " ON "
				+ TaskEntry.COLUMN_NAME_CATEGORY_ID + " = "
				+ CategoryEntry.FULL_ID);

		// Get cursor
		cursor = _QB.query(db, projection, null, null, null, null, sortOrder);
		startManagingCursor(cursor);

		int[] to = new int[] {R.id.name, R.id.date, R.id.start,
				R.id.end, R.id.category_item };

		// CREATE THE ADAPTER USING THE CURSOR POINTING TO THE DESIRED DATA AS
		// WELL AS THE LAYOUT INFORMATION
		simpleAdapter = new SimpleCursorAdapter(this,
				R.layout.task_items, cursor, itemListView, to);

		// SET THIS ADAPTER AS YOUR LISTACTIVITY'S ADAPTER
		ListView listTasks = (ListView) findViewById(R.id.tasks_list);
		listTasks.setAdapter(simpleAdapter);
		listTasks.setOnItemLongClickListener(new OnItemLongClickListener() {

			@Override
			public boolean onItemLongClick(AdapterView<?> parent, View view,
					int position, long id) {

				if (actionMode != null) {
					return false;
				}
				selectedItem = position;

				// start the CAB using the ActionMode.Callback defined above
				actionMode = DisplayTaskActivity.this
						.startActionMode(mActionModeCallback);
				view.setSelected(true);
				Log.println(Log.INFO, "" + view,
						view.toString() + " , " + view.getId());
				return true;
			}
		});
	}

	private ActionMode.Callback mActionModeCallback = new ActionMode.Callback() {

		// called when the action mode is created; startActionMode() was called
		public boolean onCreateActionMode(ActionMode mode, Menu menu) {
			// Inflate a menu resource providing context menu items
			MenuInflater inflater = mode.getMenuInflater();
			// assumes that you have "contexual.xml" menu resources
			inflater.inflate(R.menu.remove_task, menu);
			return true;
		}

		// the following method is called each time
		// the action mode is shown. Always called after
		// onCreateActionMode, but
		// may be called multiple times if the mode is invalidated.
		public boolean onPrepareActionMode(ActionMode mode, Menu menu) {
			return false; // Return false if nothing is done
		}

		// called when the user selects a contextual menu item
		public boolean onActionItemClicked(ActionMode mode, MenuItem item) {
			switch (item.getItemId()) {
			case R.id.remove_task_item:
				show();
				removeItem();
				// the Action was executed, close the CAB
				mode.finish();
				return true;
			default:
				return false;
			}
		}

		// called when the user exits the action mode
		public void onDestroyActionMode(ActionMode mode) {
			actionMode = null;
			selectedItem = -1;
		}
	};

	private void removeItem() {
		cursor.moveToPosition(selectedItem);
		int taskId = cursor.getInt(selectedItem);
		Log.println(Log.INFO, "selected task to remove, id = ", ""+taskId);
		// remove selected row from Data Base
		SQLiteDatabase db = TaskDbHelper.getInstance(getApplicationContext())
				.getReadableDatabase();
		// Define 'where' part of query.
		String selection = TaskEntry.FULL_ID + " = " + taskId;
		// Specify arguments in placeholder order.
		String[] selectionArgs = null;
		// Issue SQL statement.
		int deletedTaskNo = db.delete(TaskEntry.TABLE_NAME, selection, selectionArgs);
		db.beginTransaction();
		Log.println(Log.INFO, "Deleted Task No", ""+deletedTaskNo);
		
		
		// remove from list view (Refresh list view) by re create the activity
		//note: this way is developed just to simplify the app.
		this.recreate();
	}

	private void show() {
		cursor.moveToFirst();
		cursor.moveToPosition(selectedItem);
		String taskName = cursor.getString(1);

		Toast.makeText(DisplayTaskActivity.this, getResources().getString(R.string.remove_task) + " " + taskName, Toast.LENGTH_LONG)
				.show();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.display_task, menu);
		return true;
	}

}
