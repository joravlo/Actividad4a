package com.example.jorav.actividad4a;

import android.content.Context;
import android.content.Intent;
import android.os.Parcelable;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import java.io.Serializable;
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
    public void onBindViewHolder(AdapterViewHolder holder, final int position) {
            holder.nombre.setText(alumnos.get(position).getNombre());
            holder.ciclo.setText(alumnos.get(position).getCiclo());
            holder.curso.setText(alumnos.get(position).getCurso());
            holder.edad.setText(String.valueOf(alumnos.get(position).getEdad()));
            holder.nota.setText(String.valueOf(alumnos.get(position).getNota()));
        holder.imageButtonVer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(view.getContext(),Editar.class);
                intent.putExtra("opcion","ver");
                intent.putExtra("cargo","alumno");
                intent.putExtra("objeto", alumnos.get(position));
                view.getContext().startActivity(intent);
            }
        });
        holder.imageButtonEditar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(view.getContext(),Editar.class);
                intent.putExtra("opcion","editar");
                intent.putExtra("cargo","alumno");
                intent.putExtra("objeto",  alumnos.get(position));
                view.getContext().startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return alumnos.size();
    }

    public class AdapterViewHolder extends RecyclerView.ViewHolder{
        TextView nombre, edad, curso, ciclo, nota;
        ImageButton imageButtonVer, imageButtonEditar;
        public AdapterViewHolder(View itemView) {
            super(itemView);
            nombre = (TextView) itemView.findViewById(R.id.txtNombre);
            edad = (TextView) itemView.findViewById(R.id.txtEdad);
            curso = (TextView) itemView.findViewById(R.id.txtCurso);
            ciclo = (TextView) itemView.findViewById(R.id.txtCiclo);
            nota = (TextView) itemView.findViewById(R.id.txtNota);
            imageButtonVer = (ImageButton) itemView.findViewById(R.id.imageButtonVer);
            imageButtonEditar = (ImageButton) itemView.findViewById(R.id.imageButtonEditar);
        }
    }
}
