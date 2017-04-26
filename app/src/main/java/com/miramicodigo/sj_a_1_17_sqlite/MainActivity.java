package com.miramicodigo.sj_a_1_17_sqlite;

import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ListView;

import com.miramicodigo.sj_a_1_17_sqlite.adapters.Adapter;
import com.miramicodigo.sj_a_1_17_sqlite.db.AlumnoDatabaseAdapter;

import java.util.ArrayList;

/**
 *
 * @author Gustavo Lizarraga
 * @date 25-04-17
 * #DevStudyJam
 *
 */

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemClickListener {

    private ListView lvLista;
    private Adapter adaptadorLista;
    private AlumnoDatabaseAdapter db;
    private ArrayList<Long> ids = new ArrayList<Long>();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        lvLista = (ListView) findViewById(R.id.lista);
        lvLista.setOnItemClickListener(this);
        adaptadorLista = new Adapter(this);
        lvLista.setAdapter(adaptadorLista);
        registerForContextMenu(lvLista);

        db = new AlumnoDatabaseAdapter(this);
    }

    @Override
    protected void onStart() {
        super.onStart();
        db.abrir();
        cargarDatosLista();
    }

    public void cargarDatosLista() {
        ids.clear();
        adaptadorLista.eliminarTodo();
        Cursor cursor = db.obtenerTodosAlumnos();
        if (cursor.moveToFirst()) {
            do {
                int id = cursor.getInt(0);
                String nombre = cursor.getString(1);
                String telefono = cursor.getString(2);
                String correo = cursor.getString(3);
                String sexo = cursor.getString(4);
                ids.add((long) id);
                if(sexo.equals("m")) {
                    adaptadorLista.adicionarItem(R.drawable.ic_man,
                            nombre, correo);
                } else {
                    adaptadorLista.adicionarItem(R.drawable.ic_woman,
                            nombre, correo);
                }
            } while (cursor.moveToNext());
        }
        adaptadorLista.notifyDataSetChanged();
    }

    public void onItemClick(AdapterView<?> arg0, View view, int position,
                            long id) {
        Intent intent = new Intent(this, AlumnoActivity.class);
        intent.putExtra("id", ids.get(position));
        startActivity(intent);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_adicionar:
                Intent intent = new Intent(this, FormularioActivity.class);
                startActivity(intent);
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v,
                                    ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        getMenuInflater().inflate(R.menu.item_lista, menu);
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
        int index = info.position;
        switch (item.getItemId()) {
            case R.id.menu_editar:
                Intent intent = new Intent(this, FormularioActivity.class);
                intent.putExtra("id", ids.get(index));
                startActivity(intent);
                break;
            case R.id.menu_eliminar:
                db.eliminarAlumno(ids.get(index));
                ids.remove(index);
                cargarDatosLista();
                break;
        }
        return super.onContextItemSelected(item);
    }

    @Override
    protected void onStop() {
        super.onStop();
        db.cerrar();
    }
}
