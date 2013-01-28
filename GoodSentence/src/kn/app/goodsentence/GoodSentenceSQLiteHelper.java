package kn.app.goodsentence;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class GoodSentenceSQLiteHelper extends SQLiteOpenHelper{
    private static final String DATABASE_NAME = "gdSentenceDatabase.db";
    public static final String DATABASE_TABLE_QUOTE = "table_quote";
    public static final String DATABASE_TABLE_INFO = "table_info";
    private static final int DATABASE_VERSION = 1;

    /// SQL Statement to create a new database
    private static final String DATABASE_CREATE_INFO = "create table " +
        DATABASE_TABLE_INFO + " (" + GoodSentenceDatabase.KEY_ID +
        " integer primary key autoincrement, " +
        GoodSentenceDatabase.CURRENT_POSITION + " integer, "+
        GoodSentenceDatabase.TOTAL_LINE_READ + " integer);";

    private static final String DATABASE_CREATE_QUOTE = "create table " +
        DATABASE_TABLE_QUOTE + " (" + GoodSentenceDatabase.KEY_ID +
        " integer primary key autoincrement, " +
        GoodSentenceDatabase.QUOTE + " text not null);";

    public GoodSentenceSQLiteHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    /// Called when no database exists in disk and the helper class needs to
    /// create a new one
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(DATABASE_CREATE_INFO);
        db.execSQL(DATABASE_TABLE_QUOTE);
    }

    /// Called when there is a database version mismatch meaning that the
    /// version of the database on disk needs to be upgraded to current version
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        Log.w("GoodSentence TaskDBAdapter", "Upgrading from version "
                +oldVersion+ " to "+ newVersion +
                ", which will destroy all old data");

        db.execSQL("DROP TABLE IF IT EXISTS " +DATABASE_TABLE_INFO);
        db.execSQL("DROP TABLE IF IT EXISTS " +DATABASE_TABLE_QUOTE);
        onCreate(db);
    }
}
