package fr.um2.db;



import fr.um2.db.TaskContract.CategoryEntry;
import fr.um2.db.TaskContract.TaskEntry;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class TaskDbHelper extends SQLiteOpenHelper{
	
	

	private static final String TEXT_TYPE = " TEXT";
	private static final String INTEGER_TYPE = " INTEGER";
	private static final String COMMA_SEP = ",";
	private static final String SQL_CREATE_TASK_TABLE = "CREATE TABLE "+TaskEntry.TABLE_NAME + 
			" (" +
			TaskEntry._ID + " INTEGER PRIMARY KEY," +
			TaskEntry.COLUMN_NAME_TASK_NAME + TEXT_TYPE + COMMA_SEP +
			TaskEntry.COLUMN_NAME_TASK_DATE + TEXT_TYPE + COMMA_SEP +
			TaskEntry.COLUMN_NAME_TASK_START_TIME + TEXT_TYPE + COMMA_SEP +
			TaskEntry.COLUMN_NAME_TASK_END_TIME + TEXT_TYPE + COMMA_SEP +
			TaskEntry.COLUMN_NAME_CATEGORY_ID + INTEGER_TYPE +
			" )";
	private static final String SQL_CREATE_CATEGORY_TABLE = "CREATE TABLE "+CategoryEntry.TABLE_NAME + 
			" (" +
			CategoryEntry._ID + " INTEGER PRIMARY KEY," +
			CategoryEntry.COLUMN_NAME_CATEGORY_NAME + TEXT_TYPE +
			" )";
	private static final String SQL_DELETE_Tasks = "DROP TABLE IF EXISTS " + TaskEntry.TABLE_NAME;
	private static final String SQL_DELETE_Categories = "DROP TABLE IF EXISTS " + CategoryEntry.TABLE_NAME;
	
	public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "Task.db";
    
    public static final String DESC = " DESC";
    public static final String ASC = " ASC";
    
    private static TaskDbHelper instatnce = null;
    
    
    
	private TaskDbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }
	
	public static TaskDbHelper getInstance(Context context) {
		if(instatnce == null){
			instatnce = new TaskDbHelper(context);
		}
		return instatnce;
	}
	@Override
	public void onCreate(SQLiteDatabase db) {
		db.execSQL(SQL_CREATE_TASK_TABLE);
		db.execSQL(SQL_CREATE_CATEGORY_TABLE);
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		db.execSQL(SQL_DELETE_Tasks);
		db.execSQL(SQL_DELETE_Categories);
        onCreate(db);
	}
	
	public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        onUpgrade(db, oldVersion, newVersion);
    }

}
