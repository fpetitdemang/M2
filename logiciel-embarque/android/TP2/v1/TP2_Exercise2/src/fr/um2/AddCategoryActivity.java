package fr.um2;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

import fr.um2.db.TaskContract.CategoryEntry;
import fr.um2.db.TaskDbHelper;
import fr.um2.db.TaskContract.TaskEntry;

import android.os.Bundle;
import android.app.Activity;
import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;

public class AddCategoryActivity extends Activity {

	private static Set<String> categories = new HashSet<String>();
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_add_category);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.add_category, menu);
		return true;
	}
	
	public void addCategory(View view){
		EditText editCategory = (EditText) findViewById(R.id.edit_category);
		categories.add(editCategory.getText().toString());
		
		SQLiteDatabase db = TaskDbHelper.getInstance(getApplicationContext()).getWritableDatabase();

		// Create a new map of values, where column names are the keys
		ContentValues values = new ContentValues();
		values.put(CategoryEntry.COLUMN_NAME_CATEGORY_NAME, editCategory.getText().toString());

		// Insert the new row, returning the primary key value of the new row
		long newRowId;
		newRowId = db.insert(CategoryEntry.TABLE_NAME, null, values);
	}
	
	public static String[] getCategories(){
		return categories.toArray(new String[categories.size()]);
	}

}
