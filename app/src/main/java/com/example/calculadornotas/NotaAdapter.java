package com.example.calculadornotas;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;



import java.util.ArrayList;

/**
 * Clase NotaAdapter, que funcionara como adaptador para utilizar cada Nota
 */
public class NotaAdapter extends BaseAdapter {
    /**
     * Lista de elementos de la clasificacion
     */
    private ArrayList<Nota> lista;
    /**
     * Inflater encargado de pintar los elementos de la interfaz
     */
    private LayoutInflater inflater;

    public NotaAdapter(Activity context, ArrayList<Nota> lista) {
        this.lista = lista;
        this.inflater = LayoutInflater.from(context);
    }

    /**
     * Patron utilizado para ganar eficiencia en la carga y visualizacion de la clasificacion.
     * Esto ahorra hacer 4 findViewById cada vez que pinta un elemento de la lista, solo
     * lo pintara para el primero.
     */
    static class ViewHolder {
        TextView acertadas;
        TextView falladas;
        TextView notaTotal;
        TextView notaDiez;
    }

    @Override
    public int getCount() {

        // el total de elementos en el listview es el total de la lista que representa
        return lista.size();
    }

    @Override
    public Object getItem(int position) {

        // cuando devuelve un elemento, devuelve el que coincide con la posicion seleccionada
        return lista.get(position);
    }

    @Override
    public long getItemId(int position) {

        // el id de cada item en la lista coincide con su posicion
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        // metodo que pinta un elemento de la lista
        ViewHolder holder;
        if (convertView == null) {

            // si es nulo, sea establece el holder en la convertView
            convertView = inflater.inflate(R.layout.fila, null);

            holder = new ViewHolder();
            holder.acertadas = convertView.findViewById(R.id.txtAcertadas);
            holder.falladas = convertView.findViewById(R.id.txtFalladas);
            holder.notaTotal = convertView.findViewById(R.id.txtNotaTotal);
            holder.notaDiez = convertView.findViewById(R.id.txtNotaDiez);

            convertView.setTag(holder);

        } else {

            // si no es nulo, se toma el ya creado
            holder = (ViewHolder) convertView.getTag();
        }

        // elemento a pintar
        Nota ri = lista.get(position);

        // cada dato en su elemento de interfaz en el holder
        holder.acertadas.setText(ri.getAcertadas());
        holder.falladas.setText(ri.getFalladas());
        holder.notaTotal.setText(ri.getNotaTotal());
        holder.notaDiez.setText(ri.getNotaDiez());

        return convertView;
    }

}