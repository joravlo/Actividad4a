package com.example.jorav.actividad4a;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

/**
 * Created by jorav on 07/01/2017.
 */

public class AdaptadorConsulta extends RecyclerView.Adapter<AdaptadorConsulta.AdapterViewHolder> {
    private List<Alumno> alumnos;

    public AdaptadorConsulta(List<Alumno> alumnos) {
        this.alumnos = alumnos;
    }

    @Override
    public AdapterViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.adaptador_consulta, parent, false);
        return new AdaptadorConsulta.AdapterViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(AdapterViewHolder holder, int position) {
        holder.nombre.setText(alumnos.get(position).getNombre());
        holder.id.setText(String.valueOf(alumnos.get(position).getId()));
    }

    @Override
    public int getItemCount() {
        return alumnos.size();
    }

    public class AdapterViewHolder extends RecyclerView.ViewHolder {
        TextView id, nombre;
        public AdapterViewHolder(View itemView) {
            super(itemView);
            nombre = (TextView) itemView.findViewById(R.id.txtNombreConsulta);
            id = (TextView) itemView.findViewById(R.id.txtId);
        }
    }
}
