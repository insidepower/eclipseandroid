package kn.app.goodsentence;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

public class GoodSentenceDatabase {
    public static final int TOTAL_LINE_TO_READ = 20;
    public static final int HALF_TOTAL_LINE_TO_READ = (TOTAL_LINE_TO_READ/2);
    public static final String KEY_ID = "_id";
    public static final String QUOTE = "my_quote";
    public static final String CURRENT_POSITION = "current_pos";
    public static final String TOTAL_LINE_READ = "line_read";
    public int db_total_line_read = 0;
    public boolean is_file_read_finish = false;
    public Context myContext;

    private SQLiteDatabase db;
    private GoodSentenceSQLiteHelper dbHelper;

    public GoodSentenceDatabase(Context context) {
        myContext = context;
        dbHelper = new GoodSentenceSQLiteHelper(myContext);
        open();
    }

    public void open() throws SQLException {
        db = dbHelper.getWritableDatabase();
        int current_pos = read_current_pos(CURRENT_POSITION);
        if (current_pos == 0){
            /// read from file
            if (!is_file_read_finish){
                int total_line_read = getFileContent(current_pos);
                update_total_read(total_line_read);
                db_total_line_read = total_line_read;
                if (total_line_read == 0) {
                    is_file_read_finish = true;
                }
            }
        }
    }

    public void close() {
        dbHelper.close();
    }

    public void insert_quote(String quote){
        ContentValues value = new ContentValues();
        value.put(QUOTE, quote);
        long insertId = db.insert(
                GoodSentenceSQLiteHelper.DATABASE_TABLE_QUOTE,null, value);
    }

    public String read_next_quote(){
        int current_pos = read_current_pos(CURRENT_POSITION)+1;
        String result = read_quote(KEY_ID+"="+current_pos);
        update_current_pos(current_pos);

        if (current_pos > (db_total_line_read-HALF_TOTAL_LINE_TO_READ)) {
            /// read more from the file if user is getting near to end
        	int skip = (current_pos/TOTAL_LINE_TO_READ)*TOTAL_LINE_TO_READ;
            int total_line_read = getFileContent(skip);
            db_total_line_read = db_total_line_read+total_line_read;
            if (!is_file_read_finish){
                update_total_read(db_total_line_read);
                if (total_line_read==0){
                    is_file_read_finish = true;
                }
            }
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

    public int read_current_pos(String column){
        int info = 0;
        String[] result_columns = new String[] { KEY_ID, CURRENT_POSITION };
        Cursor cursor = db.query(GoodSentenceSQLiteHelper.DATABASE_TABLE_INFO,
        		result_columns, null, null, null, null, "1");

        /// The Cursor is inititalized at before first, so we can check only
        /// if there is a "next" row available.
        if (cursor.moveToNext()) {
        	int index = cursor.getColumnIndexOrThrow(column);
            info = cursor.getInt(index);
        }

        cursor.close();
        return info;
    }

    public void update_current_pos(int new_pos) {
        ContentValues value = new ContentValues();
        value.put(CURRENT_POSITION, new_pos);
        db.update(dbHelper.DATABASE_TABLE_INFO, value, KEY_ID+"="+1, null);
    }

    public void update_total_read(int new_total) {
        ContentValues value = new ContentValues();
        value.put(TOTAL_LINE_READ, new_total);
        db.update(dbHelper.DATABASE_TABLE_INFO, value, KEY_ID+"="+1, null);
    }

    public void delete_table(){
        /// future improvement, to allow user to save new file
    }

    public int getFileContent(int skip){
        int count = 0;
        int total_line_read = 0;
    	String line = null;
    	try {
            //InputStream instream = openFileInput("myfilename.txt");
            InputStream instream = myContext.getResources()
                .openRawResource(R.raw.goodsentence);
            InputStreamReader inputreader = new InputStreamReader(instream);
            BufferedReader buffreader = new BufferedReader(inputreader);

            while (count < skip ) {
                buffreader.readLine();
                count++;
            }

            int read = count+TOTAL_LINE_TO_READ;
            while (count < read ){
                line = buffreader.readLine();
                insert_quote(line);
                count++;
                total_line_read++;
            }

            instream.close();
            inputreader.close();
            buffreader.close();

        } catch (java.io.FileNotFoundException e) {	
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
		return total_line_read;
    }

}
