package com.miramicodigo.sj_a_1_17_sqlite.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 *
 * @author Gustavo Lizarraga
 * @date 25-04-17
 * #DevStudyJam
 *
 */

public class AlumnoDatabaseAdapter {

    private AlumnosDatabaseHelper databaseHelper;
    private SQLiteDatabase db;

    public AlumnoDatabaseAdapter(Context context) {
        databaseHelper = new AlumnosDatabaseHelper(context);
    }

    public void abrir() {
        db = databaseHelper.getWritableDatabase();
    }

    public void cerrar() {
        databaseHelper.close();
    }

    public long adicionarAlumno(String nombre, long telefono,
                                String correo, String sexo) {
        ContentValues contentValues = new ContentValues();

        contentValues.put("nombre", nombre);
        contentValues.put("telefono", telefono);
        contentValues.put("correo", correo);
        contentValues.put("sexo", sexo);

        return db.insert("alumno", null, contentValues);
    }

    public int actualizarAlumno(long id, String nombre,
                                long telefono, String correo,
                                String sexo) {
        ContentValues contentValues = new ContentValues();
        contentValues.put("nombre", nombre);
        contentValues.put("telefono", telefono);
        contentValues.put("correo", correo);
        contentValues.put("sexo", sexo);

        return db.update("alumno", contentValues, "_id=?",
                new String[] { id+"" });
    }

    public boolean eliminarAlumno(long id) {
        return db.delete("alumno", "_id="+id, null) > 0;
    }

    public Cursor obtenerAlumno(long id) {
        return db.query("alumno", new String[] {"_id",
                "nombre", "telefono", "correo", "sexo"}, "_id=?",
                new String[] { id+""}, null, null, null);
    }

    public Cursor obtenerTodosAlumnos() {
        return db.query("alumno", new String[] {"_id, " +
                "nombre", "telefono", "correo", "sexo" },
                null, null, null, null, null);
    }

    private static class AlumnosDatabaseHelper extends SQLiteOpenHelper {

        public AlumnosDatabaseHelper(Context context) {
            super(context, "alumnos.db", null, 1);
        }

        @Override
        public void onCreate(SQLiteDatabase db) {
            db.execSQL("CREATE TABLE alumno (_id INTEGER PRIMARY KEY " +
                    "AUTOINCREMENT, nombre TEXT NOT NULL, " +
                    "telefono INTEGER, correo TEXT, sexo TEXT)");
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            db.execSQL("DROP TABLE IF EXISTS alumno");
            onCreate(db);
        }
    }

}