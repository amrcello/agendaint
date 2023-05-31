package data;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import java.util.ArrayList;
import java.util.List;
import model.Compromisso;

public class CompromissosDB {
    private SQLiteDatabase database;

    public CompromissosDB(Context context) {
        CompromissosDBHelper dbHelper = new CompromissosDBHelper(context);
        database = dbHelper.getWritableDatabase();
    }

    public void cadastrarCompromisso(Compromisso compromisso) {
        ContentValues values = new ContentValues();
        values.put(CompromissosDBSchema.COLUMN_DATA, compromisso.getData());
        values.put(CompromissosDBSchema.COLUMN_HORA, compromisso.getHora());
        values.put(CompromissosDBSchema.COLUMN_DESCRICAO, compromisso.getDescricao());

        database.insert(CompromissosDBSchema.TABLE_COMPROMISSOS, null, values);
    }

    public List<Compromisso> getCompromissos(String data) {
        List<Compromisso> compromissos = new ArrayList<>();

        String[] projection = {
                CompromissosDBSchema.COLUMN_DATA,
                CompromissosDBSchema.COLUMN_HORA,
                CompromissosDBSchema.COLUMN_DESCRICAO
        };

        String selection = CompromissosDBSchema.COLUMN_DATA + " = ?";
        String[] selectionArgs = {data};

        Cursor cursor = database.query(
                CompromissosDBSchema.TABLE_COMPROMISSOS,
                projection,
                selection,
                selectionArgs,
                null,
                null,
                CompromissosDBSchema.COLUMN_HORA + " ASC"
        );

        while (cursor.moveToNext()) {
            Compromisso compromisso = new Compromisso();
            compromisso.setData(cursor.getString(cursor.getColumnIndexOrThrow(CompromissosDBSchema.COLUMN_DATA)));
            compromisso.setHora(cursor.getString(cursor.getColumnIndexOrThrow(CompromissosDBSchema.COLUMN_HORA)));
            compromisso.setDescricao(cursor.getString(cursor.getColumnIndexOrThrow(CompromissosDBSchema.COLUMN_DESCRICAO)));

            compromissos.add(compromisso);
        }

        cursor.close();
        return compromissos;
    }
}
