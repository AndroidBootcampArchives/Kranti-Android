package Storage;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import model.Issue;

import java.util.ArrayList;
import java.util.List;

import static android.provider.BaseColumns._ID;


public class DataStorage extends SQLiteOpenHelper {
  private static final String TABLE_NAME = "issues";
  private static final String TITLE_COL = "title";
  private static final String DESCRIPTION_COL = "description";
  private static final String DB_NAME = "kranti.db";
  private static final String DATABASE_CREATE = "CREATE TABLE " + TABLE_NAME + " (" + _ID
      + " INTEGER PRIMARY KEY AUTOINCREMENT, " + TITLE_COL
      + " TEXT NOT NULL ," + DESCRIPTION_COL + " TEXT );";

  public DataStorage(Context context) {
    super(context, DB_NAME, null, 1);
  }

  @Override
  public void onCreate(SQLiteDatabase db) {
    db.execSQL(DATABASE_CREATE);
  }

  @Override
  public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
  }


  public void store(Issue issue) {
    SQLiteDatabase db = getWritableDatabase();

    ContentValues values = new ContentValues();
    values.put(TITLE_COL, issue.getTitle());
    values.put(DESCRIPTION_COL, issue.getDescription());

    db.insert(TABLE_NAME, null, values);

  }

  public List<Issue> getAllIssues() {
    String selectQuery = "SELECT  * FROM " + TABLE_NAME;

    SQLiteDatabase db = getReadableDatabase();
    ArrayList<Issue> issues = new ArrayList<>();

    Cursor cursor = db.rawQuery(selectQuery, null);
    if (cursor.moveToFirst()) {
      while (!cursor.isAfterLast()) {
        String title = cursor.getString(cursor.getColumnIndex(TITLE_COL));
        String description = cursor.getString(cursor.getColumnIndex(DESCRIPTION_COL));
        Issue issue = new Issue(title, description);
        issues.add(issue);
        cursor.moveToNext();
      }
    }
    return issues;


  }

}
