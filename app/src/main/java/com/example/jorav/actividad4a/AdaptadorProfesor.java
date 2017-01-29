package com.example.jorav.actividad4a;

import android.content.Context;
import android.content.Intent;
import android.os.Parcelable;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

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
    public void onBindViewHolder(AdapterViewHolder holder, final int position) {

            holder.nombre.setText(profesores.get(position).getNombre());
            holder.ciclo.setText(profesores.get(position).getCiclo());
            holder.curso.setText(profesores.get(position).getTutoria());
            holder.edad.setText(String.valueOf(profesores.get(position).getEdad()));
            holder.nota.setText(profesores.get(position).getDespacho());
            holder.imageButtonVer.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(view.getContext(),Editar.class);
                    intent.putExtra("opcion","ver");
                    intent.putExtra("cargo","profesor");
                    intent.putExtra("objeto",  profesores.get(position));
                    view.getContext().startActivity(intent);
                }
            });
        holder.imageButtonEditar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(view.getContext(),Editar.class);
                intent.putExtra("opcion","editar");
                intent.putExtra("cargo","profesor");
                intent.putExtra("objeto",  profesores.get(position));
                view.getContext().startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return profesores.size();
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
