package kn.app.goodsentence;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

public class GoodSentenceDatabase {
    public static final String KEY_ID = "_id";
    public static final String QUOTE = "my_quote";
    public static final String CURRENT_POSITION = "current_pos";
    public static final String TOTAL_LINE_READ = "line_read";
    public static final String TAG="GdSentenceDatabase";
    public Context myContext;
    public int current_pos = 0;
    public int db_total_line = 0;

    private SQLiteDatabase db;
    private GoodSentenceSQLiteHelper dbHelper;

    public GoodSentenceDatabase(Context context) {
        myContext = context;
        dbHelper = new GoodSentenceSQLiteHelper(myContext);
        db = dbHelper.getWritableDatabase();
        current_pos = read_info(CURRENT_POSITION);
        db_total_line = read_info(TOTAL_LINE_READ);
    }

    public boolean checkDatabaseAvailability() {
        boolean isDatabaseReady = false;
        if (null != read_quote(KEY_ID+"=1")) {
            /// need to construct the database
            isDatabaseReady = true;
        }
        Log.i(TAG, "isDatabaseReady="+isDatabaseReady);
        return isDatabaseReady;
    }

    public void close() {
        update_current_pos(current_pos);
        dbHelper.close();
    }

    public void insert_quote(String quote){
        ContentValues value = new ContentValues();
        value.put(QUOTE, quote);
        long insertId = db.insert(
                GoodSentenceSQLiteHelper.DATABASE_TABLE_QUOTE,null,value);
    }

    public String read_next_quote(){
        //int current_pos = read_info(CURRENT_POSITION);
        String result = read_quote(KEY_ID+"="+current_pos);
        Log.i(TAG, "current_pos="+current_pos+"; sentence="+result);
        ++current_pos;
        if ( current_pos > db_total_line )
        {
            current_pos = 1;
        }

        return result;
    }

    public String read_quote(String where){
        String quote = null;
        String[] result_columns = new String[] { KEY_ID, QUOTE};
        Cursor cursor = db.query(GoodSentenceSQLiteHelper.DATABASE_TABLE_QUOTE,
        		result_columns, where, null, null, null, null);
        /// The Cursor is inititalized at before first, so we can check only
        /// if there is a "next" row available.
        if (cursor.moveToNext()) {
        	int index = cursor.getColumnIndexOrThrow(QUOTE);
            quote = cursor.getString(index);
        }

        cursor.close();
        return quote;
    }

    public int read_info(String column){
        int info = 0;
        String[] result_columns =
            new String[] { KEY_ID, CURRENT_POSITION, TOTAL_LINE_READ };
        Cursor cursor = db.query(GoodSentenceSQLiteHelper.DATABASE_TABLE_INFO,
        		result_columns, null, null, null, null, "1");

        /// The Cursor is inititalized at before first, so we can check only
        /// if there is a "next" row available.
        if (cursor.moveToNext()) {
        	int index = cursor.getColumnIndexOrThrow(column);
            info = cursor.getInt(index);
            Log.i(TAG, "read_info, inside pos="+info);
        } else {
            /// create the first entry for current position
            if ( column.equals(CURRENT_POSITION) ) {
                ContentValues value = new ContentValues();
                value.put(CURRENT_POSITION, 1);
                info = 1;
                long insertId = db.insert(
                        GoodSentenceSQLiteHelper.DATABASE_TABLE_INFO,null,value);
            } else if ( column.equals(TOTAL_LINE_READ)){
                ContentValues value = new ContentValues();
                value.put(CURRENT_POSITION, 0);
                info = 0;
                long insertId = db.insert(
                        GoodSentenceSQLiteHelper.DATABASE_TABLE_INFO,null,value);
            }
        }

        Log.i(TAG, "read_info, pos="+info);
        cursor.close();
        return info;
    }

    public void update_current_pos(int new_pos) {
        ContentValues value = new ContentValues();
        value.put(CURRENT_POSITION, new_pos);
        db.update(GoodSentenceSQLiteHelper.DATABASE_TABLE_INFO,
                value, KEY_ID+"="+1, null);
    }

    public void update_total_read(int new_total) {
        ContentValues value = new ContentValues();
        value.put(TOTAL_LINE_READ, new_total);
        db.update(GoodSentenceSQLiteHelper.DATABASE_TABLE_INFO,
                value, KEY_ID+"="+1, null);
    }

    public void delete_table(){
        /// future improvement, to allow user to save new file
    }

    public boolean constructDatabase(){
        boolean result = false;
    	String line = null;
    	try {
            Log.i(TAG, "constructing database");
            //InputStream instream = openFileInput("myfilename.txt");
            InputStream instream = myContext.getResources()
                .openRawResource(R.raw.goodsentence);
            InputStreamReader inputreader = new InputStreamReader(instream);
            BufferedReader buffreader = new BufferedReader(inputreader);

            int total_line = 0;
            db.beginTransaction();
            do{
                line = buffreader.readLine();
                Log.i(TAG, "file: line="+line);
                if (null!=line)
                {
                    insert_quote(line);
                    total_line++;
                }
            }while ( null != line );
            update_total_read(total_line);
            db_total_line = total_line;
            db.setTransactionSuccessful();
            db.endTransaction();

            instream.close();
            inputreader.close();
            buffreader.close();
            result = true;

        } catch (java.io.FileNotFoundException e) {	
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
		return result;
    }

}
