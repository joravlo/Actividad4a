package com.example.jorav.actividad4a;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

/**
 * Created by jorav on 24/12/2016.
 */

public class Adaptador extends RecyclerView.Adapter<Adaptador.AdapterViewHolder> {
    private List<Alumno> alumnos;

    public Adaptador(List<Alumno> alumnos) {
        this.alumnos = alumnos;
    }

    @Override
    public AdapterViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.adapter, parent, false);
        return new AdapterViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(AdapterViewHolder holder, int position) {
            holder.nombre.setText(alumnos.get(position).getNombre());
            holder.ciclo.setText(alumnos.get(position).getCiclo());
            holder.curso.setText(alumnos.get(position).getCurso());
            holder.edad.setText(String.valueOf(alumnos.get(position).getEdad()));
            holder.nota.setText(String.valueOf(alumnos.get(position).getNota()));
    }

    @Override
    public int getItemCount() {
        return alumnos.size();
    }

    public class AdapterViewHolder extends RecyclerView.ViewHolder{
        TextView nombre, edad, curso, ciclo, nota;
        public AdapterViewHolder(View itemView) {
            super(itemView);
            nombre = (TextView) itemView.findViewById(R.id.txtNombre);
            edad = (TextView) itemView.findViewById(R.id.txtEdad);
            curso = (TextView) itemView.findViewById(R.id.txtCurso);
            ciclo = (TextView) itemView.findViewById(R.id.txtCiclo);
            nota = (TextView) itemView.findViewById(R.id.txtNota);
        }
    }
}
