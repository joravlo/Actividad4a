package com.example.jorav.actividad4a;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class Editar extends AppCompatActivity {
    private MyDBAdapter dbAdapter;
    EditText etNombreEditar, etEdadEditar, etCicloEditar, etCursoEditar, etNotaEditar;
    TextView txtNotaEditar, txtCursoEditar;
    Button button;
    Profesor profesor;
    Alumno alumno;
    String cargo;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editar);

        dbAdapter = new MyDBAdapter(this);
        dbAdapter.open();

        etNombreEditar = (EditText) findViewById(R.id.etNombreEditar);
        etEdadEditar = (EditText) findViewById(R.id.etEdadEditar);
        etCicloEditar = (EditText) findViewById(R.id.etCicloEditar);
        etCursoEditar = (EditText) findViewById(R.id.etCursoEditar);
        etNotaEditar = (EditText) findViewById(R.id.etNotaEditar);
        txtNotaEditar = (TextView) findViewById(R.id.txtNotaEditar);
        txtCursoEditar = (TextView) findViewById(R.id.txtCursoEditar);
        button = (Button) findViewById(R.id.btnGuardarEditar);

        String opcion = getIntent().getExtras().getString("opcion");
         cargo = getIntent().getExtras().getString("cargo");

        if (opcion.equals("ver")){
            etNombreEditar.setEnabled(false);
            etEdadEditar.setEnabled(false);
            etCicloEditar.setEnabled(false);
            etCursoEditar.setEnabled(false);
            etNotaEditar.setEnabled(false);
            button.setVisibility(View.GONE);
        }

        if (cargo.equals("profesor")){
            txtNotaEditar.setText("Despacho");
            txtCursoEditar.setText("Tutoria");
             profesor = (Profesor) getIntent().getExtras().getSerializable("objeto");
            etNombreEditar.setText(profesor.getNombre());
            etEdadEditar.setText(profesor.getEdad()+"");
            etCicloEditar.setText(profesor.getCiclo());
            etCursoEditar.setText(profesor.getTutoria());
            etNotaEditar.setText(profesor.getDespacho());
        }else {
             alumno = (Alumno) getIntent().getExtras().getSerializable("objeto");
            etNombreEditar.setText(alumno.getNombre());
            etEdadEditar.setText(alumno.getEdad()+"");
            etCicloEditar.setText(alumno.getCiclo());
            etCursoEditar.setText(alumno.getCurso());
            etNotaEditar.setText(alumno.getNota()+"");
        }

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (cargo.equals("profesor")){
                    dbAdapter.updateProfesor(etNombreEditar.getText().toString(),Integer.parseInt(etEdadEditar.getText().toString()),etCicloEditar.getText().toString(),etCursoEditar.getText().toString(),etNotaEditar.getText().toString(),profesor.getId());
                }else {
                    dbAdapter.updateAlumno(etNombreEditar.getText().toString(),Integer.parseInt(etEdadEditar.getText().toString()),etCicloEditar.getText().toString(),etCursoEditar.getText().toString(),Double.parseDouble(etNotaEditar.getText().toString()),alumno.getId());
                }
                Toast.makeText(Editar.this, "Los datos se han actualizado correctamente", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
