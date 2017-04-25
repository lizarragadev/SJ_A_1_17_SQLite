package com.miramicodigo.sj_a_1_17_sqlite;

import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import com.miramicodigo.sj_a_1_17_sqlite.db.AlumnoDatabaseAdapter;

/**
 *
 * @author Gustavo Lizarraga
 * @date 25-04-17
 * #DevStudyJam
 *
 */

public class AlumnoActivity extends AppCompatActivity {

    private ImageView ivImagen;
    private TextView tvNombre;
    private TextView tvTelefono;
    private TextView tvCorreo;
    private TextView tvSexo;

    private long id;
    private AlumnoDatabaseAdapter db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alumno);

        ivImagen = (ImageView) findViewById(R.id.imagen);
        tvNombre = (TextView) findViewById(R.id.textoNombre);
        tvTelefono = (TextView) findViewById(R.id.textoTelefono);
        tvCorreo = (TextView) findViewById(R.id.textoCorreo);
        tvSexo = (TextView) findViewById(R.id.textoSexo);

        db = new AlumnoDatabaseAdapter(this);
        db.abrir();

        id = getIntent().getLongExtra("id", 0);

        Cursor cursor = db.obtenerAlumno(id);
        if (cursor.moveToFirst()) {
            String nombre = cursor.getString(1);
            String telefono = cursor.getString(2);
            String correo = cursor.getString(3);
            String sexo = cursor.getString(4);
            tvNombre.setText(nombre);
            tvTelefono.setText(telefono);
            tvCorreo.setText(correo);
            if (sexo.equals("m")) {
                tvSexo.setText("Masculino");
                ivImagen.setImageResource(R.drawable.ic_man);
            } else {
                tvSexo.setText("Femenino");
                ivImagen.setImageResource(R.drawable.ic_woman);
            }
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_alumno, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_editar:
                Intent intent = new Intent(this, FormularioActivity.class);
                intent.putExtra("id", id);
                startActivity(intent);
                finish();
                break;
            case R.id.menu_eliminar:
                db.eliminarAlumno(id);
                finish();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onStop() {
        super.onStop();
        db.cerrar();

    }
}