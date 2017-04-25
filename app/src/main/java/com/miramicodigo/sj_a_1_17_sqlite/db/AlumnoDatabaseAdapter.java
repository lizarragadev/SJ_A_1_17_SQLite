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

    public long adicionarAlumno(String nombre, long telefono, String correo,
                                 String sexo) {
        return 0;
    }

    public int actualizarAlumno(long id, String nombre, long telefono,
                                 String correo, String sexo) {
        return 0;
    }

    public boolean eliminarAlumno(long id) {
        return false;
    }

    public Cursor obtenerAlumno(long id) {
        return null;
    }

    public Cursor obtenerTodosAlumnos() {
        return null;
    }

    private static class AlumnosDatabaseHelper extends SQLiteOpenHelper {

        public AlumnosDatabaseHelper(Context context) {
            super(context, "alumnos.db", null, 1);
        }

        @Override
        public void onCreate(SQLiteDatabase db) {

        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            db.execSQL("DROP TABLE IF EXISTS alumno");
            onCreate(db);
        }
    }

}