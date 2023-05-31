package data;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class CompromissosDBHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "compromissos.db";
    private static final int DATABASE_VERSION = 1;

    public CompromissosDBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CompromissosDBSchema.SQL_CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(CompromissosDBSchema.SQL_DROP_TABLE);
        onCreate(db);
    }
}
