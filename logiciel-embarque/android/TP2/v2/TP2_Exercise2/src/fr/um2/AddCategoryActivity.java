package fr.um2;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

import fr.um2.db.TaskContract.CategoryEntry;
import fr.um2.db.TaskDbHelper;
import fr.um2.db.TaskContract.TaskEntry;
import fr.um2.model.Category;

import android.os.Bundle;
import android.app.Activity;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class AddCategoryActivity extends Activity {
	
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
		
		SQLiteDatabase db = TaskDbHelper.getInstance(getApplicationContext()).getWritableDatabase();

		// Create a new map of values, where column names are the keys
		ContentValues values = new ContentValues();
		values.put(CategoryEntry.COLUMN_NAME_CATEGORY_NAME, editCategory.getText().toString());

		// Insert the new row, returning the primary key value of the new row
		 long categoryId = db.insert(CategoryEntry.TABLE_NAME, null, values);
		 
		 Toast.makeText(this, getResources().getString(R.string.add_category) +" " + editCategory.getText().toString(), Toast.LENGTH_SHORT).show();
	}
	
	public static ArrayList<String> getCategories(){
		
		ArrayList<String> categories = new ArrayList<String>();
		
		SQLiteDatabase db = TaskDbHelper.getInstance(null)
				.getReadableDatabase();
		
		// Define a projection that specifies which columns from the database
				// you will actually use after this query.
				String[] projection = {CategoryEntry.COLUMN_NAME_CATEGORY_NAME};
				
				// How you want the results sorted in the resulting Cursor
				String sortOrder = CategoryEntry.COLUMN_NAME_CATEGORY_NAME + TaskDbHelper.ASC;
				
				Cursor cursor = db.query(CategoryEntry.TABLE_NAME, projection, null, null, null, null, sortOrder);	

				if(cursor != null){
					while(cursor.moveToNext()){
						String category = cursor.getString(0);
						categories.add(category);
					}
				}
				return categories;
	}

}
