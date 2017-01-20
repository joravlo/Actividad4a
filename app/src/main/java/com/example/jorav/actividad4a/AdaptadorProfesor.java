package com.example.jorav.actividad4a;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

/**
 * Created by jorav on 08/01/2017.
 */

public class AdaptadorProfesor extends RecyclerView.Adapter<AdaptadorProfesor.AdapterViewHolder> {
    private List<Profesor> profesores;

    public AdaptadorProfesor(List<Profesor> profesores) {
        this.profesores = profesores;
    }

    @Override
    public AdapterViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.adapter, parent, false);
        return new AdapterViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(AdapterViewHolder holder, int position) {

            holder.nombre.setText(profesores.get(position).getNombre());
            holder.ciclo.setText(profesores.get(position).getCiclo());
            holder.curso.setText(profesores.get(position).getTutoria());
            holder.edad.setText(String.valueOf(profesores.get(position).getEdad()));
            holder.nota.setText(profesores.get(position).getDespacho());

    }

    @Override
    public int getItemCount() {
        return profesores.size();
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
