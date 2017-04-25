package com.miramicodigo.sj_a_1_17_sqlite.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.miramicodigo.sj_a_1_17_sqlite.R;

import java.util.ArrayList;

/**
 *
 * @author Gustavo Lizarraga
 * @date 25-04-17
 * #DevStudyJam
 *
 */

public class Adapter extends BaseAdapter {

    private LayoutInflater inflater;
    private ArrayList<Integer> imagenes;
    private ArrayList<String> textosPrincipales;
    private ArrayList<String> textosSecundarios;

    public Adapter(Context contexto) {
        inflater = LayoutInflater.from(contexto);
        imagenes = new ArrayList<Integer>();
        textosPrincipales = new ArrayList<String>();
        textosSecundarios = new ArrayList<String>();
    }

    public int getCount() {
        return textosPrincipales.size();
    }

    public Object getItem(int position) {
        return position;
    }

    public long getItemId(int position) {
        return position;
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null) {
            convertView = inflater.inflate(R.layout.item_lista, null);
            holder = new ViewHolder();
            holder.ivImagen = (ImageView) convertView
                    .findViewById(R.id.imagenItem);
            holder.tvTitulo = (TextView) convertView
                    .findViewById(R.id.textoPrincipalItem);
            holder.tvSubTitulo = (TextView) convertView
                    .findViewById(R.id.textoSecundarioItem);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        holder.ivImagen.setImageResource(imagenes.get(position));
        holder.tvTitulo.setText(textosPrincipales.get(position));
        holder.tvSubTitulo.setText(textosSecundarios.get(position));
        return convertView;
    }

    static class ViewHolder {
        ImageView ivImagen;
        TextView tvTitulo;
        TextView tvSubTitulo;
    }

    public void adicionarItem(int idRecurso, String textoPrincipal,
                              String textoSecundario) {
        imagenes.add(idRecurso);
        textosPrincipales.add(textoPrincipal);
        textosSecundarios.add(textoSecundario);
        notifyDataSetChanged();
    }

    public void adicionarItem(String textoPrincipal, String textoSecundario) {
        imagenes.add(0);
        textosPrincipales.add(textoPrincipal);
        textosSecundarios.add(textoSecundario);
    }

    public void adicionarItem(String textoPrincipal) {
        imagenes.add(0);
        textosPrincipales.add(textoPrincipal);
        textosSecundarios.add("");
    }

    public void eliminarTodo() {
        imagenes.clear();
        textosPrincipales.clear();
        textosSecundarios.clear();
        notifyDataSetChanged();
    }

}