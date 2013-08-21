-------------------- [ check if a database exists ] start -------------------- 
public boolean isTableExists(String tableName, boolean openDb) {
    if(openDb) {
        if(mDatabase == null || !mDatabase.isOpen()) {
            mDatabase = getReadableDatabase();
        }

        if(!mDatabase.isReadOnly()) {
            mDatabase.close();
            mDatabase = getReadableDatabase();
        }
    }

    Cursor cursor = mDatabase.rawQuery("select DISTINCT tbl_name from sqlite_master where tbl_name = '"+tableName+"'", null);
    if(cursor!=null) {
        if(cursor.getCount()>0) {
                            cursor.close();
            return true;
        }
                    cursor.close();
    }
    return false;
}
