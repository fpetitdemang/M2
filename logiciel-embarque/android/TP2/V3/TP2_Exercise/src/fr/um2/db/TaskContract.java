package fr.um2.db;

import android.provider.BaseColumns;

public final class TaskContract {

	public TaskContract() {
		// TODO Auto-generated constructor stub
	}
	
	public static abstract class TaskEntry implements BaseColumns{
		public static final String TABLE_NAME = "task";
		public static final String FULL_ID =  TABLE_NAME + "." + _ID;
		public static final String COLUMN_NAME_TASK_NAME= "tname";
		public static final String COLUMN_NAME_TASK_DATE = "date";
		public static final String COLUMN_NAME_TASK_START_TIME = "start_time";
		public static final String COLUMN_NAME_TASK_END_TIME = "end_time";
		public static final String COLUMN_NAME_CATEGORY_ID = "category_id";
		
	}
	
	public static abstract class CategoryEntry implements BaseColumns{
		public static final String TABLE_NAME = "category";
		public static final String FULL_ID =  TABLE_NAME + "." + _ID;
		public static final String COLUMN_NAME_CATEGORY_NAME= "cname";
		
	}
}
