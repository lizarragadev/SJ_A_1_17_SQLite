package com.miramicodigo.sj_a_1_17_sqlite;


import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;

import com.miramicodigo.sj_a_1_17_sqlite.db.AlumnoDatabaseAdapter;

/**
 *
 * @author Gustavo Lizarraga
 * @date 25-04-17
 * #DevStudyJam
 *
 */

public class FormularioActivity extends AppCompatActivity {

    private AlumnoDatabaseAdapter db;
    private EditText etNombre;
    private EditText etTelefono;
    private EditText etCorreo;
    private RadioButton rbRadio;

    private boolean edicion;

    private long id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_formulario);

        etNombre = (EditText) findViewById(R.id.cajaNombre);
        etTelefono = (EditText) findViewById(R.id.cajaTelefono);
        etCorreo = (EditText) findViewById(R.id.cajaCorreo);
        rbRadio = (RadioButton) findViewById(R.id.botonRadio1);

        db = new AlumnoDatabaseAdapter(this);
        db.abrir();

        if (getIntent().getExtras() != null) {
            edicion = true;
            setTitle("Editar Alumno");
            id = getIntent().getLongExtra("id", 0);
            Cursor cursor = db.obtenerAlumno(id);
            if (cursor.moveToFirst()) {
                String nombre = cursor.getString(1);
                String telefono = cursor.getString(2);
                String correo = cursor.getString(3);
                String sexo = cursor.getString(4);
                etNombre.setText(nombre);
                etTelefono.setText(telefono);
                etCorreo.setText(correo);
                if (sexo.equals("m")) {
                    rbRadio.setSelected(true);
                } else {
                    rbRadio.setSelected(false);
                }
            }
        } else {
            edicion = false;
            setTitle("Nuevo Alumno");
        }
    }

    public void aceptar(View view) {
        String nombre = etNombre.getText().toString();
        long telefono = Long.parseLong(etTelefono.getText().toString());
        String correo = etCorreo.getText().toString();
        String sexo = rbRadio.isChecked() ? "m" : "f";
        if (edicion) {
            db.actualizarAlumno(id, nombre, telefono, correo, sexo);
        } else {
            db.adicionarAlumno(nombre, telefono, correo, sexo);
        }
        finish();
    }

    public void cancelar(View view) {
        finish();
    }

    @Override
    protected void onStop() {
        super.onStop();
        db.cerrar();
    }

}