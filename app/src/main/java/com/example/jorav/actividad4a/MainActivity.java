package com.example.jorav.actividad4a;

import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private MyDBAdapter dbAdapter;
    RecyclerView recyclerView, recyclerViewProfesores;
    TextView txtAlumno, txtProfesor;
    Adaptador adaptador;
    AdaptadorProfesor adaptadorProfesor;
    ArrayList<Alumno> alumnos;
    ArrayList<Profesor> profesores;
    Spinner spinner;
    int seleccionSpinner;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        spinner = (Spinner)findViewById(R.id.spinner);
        recyclerView = (RecyclerView) findViewById(R.id.recycler);
        recyclerViewProfesores = (RecyclerView) findViewById(R.id.recyclerProfesores);
        txtAlumno = (TextView) findViewById(R.id.txtAlumno);
        txtProfesor = (TextView) findViewById(R.id.txtProfesor);
        dbAdapter = new MyDBAdapter(this);
        dbAdapter.open();

        recyclerViewProfesores.setVisibility(View.GONE);
        txtAlumno.setVisibility(View.GONE);
        txtProfesor.setVisibility(View.GONE);
        /*dbAdapter.insertarAlumno("Juan",18,"DAM","2º DAM", 7.5);
        dbAdapter.insertarAlumno("Pepe",18,"DAM","1º DAM", 3.5);
        dbAdapter.insertarAlumno("Luis",38,"DAM","2º DAM", 8.5);
        dbAdapter.insertarProfesor("Manel",38,"DAM","2º DAM", "Despacho 1.2");
        dbAdapter.insertarProfesor("Tomas",38,"DAM","1º DAM", "Despacho 1.6");
        dbAdapter.insertarProfesor("Juanmi",38,"DAW","2º DAW","Despacho 1.4");*/

        alumnos = dbAdapter.recuperarAlumnos();
        profesores = dbAdapter.recuperarProfesores();


        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(mLayoutManager);
        RecyclerView.LayoutManager mLayoutManagerProfesores = new LinearLayoutManager(this);
        recyclerViewProfesores.setLayoutManager(mLayoutManagerProfesores);

        adaptador = new Adaptador(alumnos);
        recyclerView.setAdapter(adaptador);

        String[] items = {"Alumnos","Profesores","Todo"};
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(MainActivity.this,android.R.layout.simple_spinner_item,items);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                seleccionSpinner = i;
                switch (i){
                    case 0:
                        recyclerViewProfesores.setVisibility(View.GONE);
                        txtAlumno.setVisibility(View.GONE);
                        txtProfesor.setVisibility(View.GONE);
                        alumnos.clear();
                        alumnos = dbAdapter.recuperarAlumnos();
                        adaptador = new Adaptador(alumnos);
                        recyclerView.setAdapter(adaptador);
                        break;
                    case 1:
                        recyclerViewProfesores.setVisibility(View.GONE);
                        txtAlumno.setVisibility(View.GONE);
                        txtProfesor.setVisibility(View.GONE);
                        profesores.clear();
                        profesores = dbAdapter.recuperarProfesores();
                        adaptadorProfesor = new AdaptadorProfesor(profesores);
                        recyclerView.setAdapter(adaptadorProfesor);
                        break;
                    case 2:
                        recyclerViewProfesores.setVisibility(View.VISIBLE);
                        txtAlumno.setVisibility(View.VISIBLE);
                        txtProfesor.setVisibility(View.VISIBLE);
                        alumnos.clear();
                        alumnos = dbAdapter.recuperarAlumnos();
                        adaptador = new Adaptador(alumnos);
                        recyclerView.setAdapter(adaptador);
                        profesores.clear();
                        profesores = dbAdapter.recuperarProfesores();
                        adaptadorProfesor = new AdaptadorProfesor(profesores);
                        recyclerViewProfesores.setAdapter(adaptadorProfesor);
                        break;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.nuevoEstudiante:
                recyclerViewProfesores.setVisibility(View.GONE);
                txtAlumno.setVisibility(View.GONE);
                txtProfesor.setVisibility(View.GONE);
                spinner.setSelection(0);
                AlertDialog.Builder builder = new AlertDialog.Builder(this);
                LayoutInflater inflater = this.getLayoutInflater();
                View dialogView = inflater.inflate(R.layout.alumno_dialog,null);
                builder.setView(dialogView);

                final EditText etNombre = (EditText) dialogView.findViewById(R.id.etNombre);
                final EditText etEdad = (EditText) dialogView.findViewById(R.id.etEdad);
                final EditText etCiclo = (EditText) dialogView.findViewById(R.id.etCiclo);
                final EditText etCurso = (EditText) dialogView.findViewById(R.id.etCurso);
                final EditText etNota = (EditText) dialogView.findViewById(R.id.etNota);

                Button btnGuradar = (Button) dialogView.findViewById(R.id.btnGuradar);
                Button btnCancelar = (Button) dialogView.findViewById(R.id.btnCancelar);
                final AlertDialog alertDialog = builder.create();
                alertDialog.show();
                btnGuradar.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        dbAdapter.insertarAlumno(etNombre.getText().toString(),Integer.parseInt(etEdad.getText().toString()),etCiclo.getText().toString(),etCurso.getText().toString(), Double.parseDouble(etNota.getText().toString()));
                        if (seleccionSpinner == 0){
                            alumnos.clear();
                            alumnos = dbAdapter.recuperarAlumnos();
                            adaptador = new Adaptador(alumnos);
                            recyclerView.setAdapter(adaptador);
                        }
                        alertDialog.dismiss();
                    }
                });

                btnCancelar.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        alertDialog.dismiss();
                    }
                });
                return true;
            case R.id.borraEstudiante:
                recyclerViewProfesores.setVisibility(View.GONE);
                txtAlumno.setVisibility(View.GONE);
                txtProfesor.setVisibility(View.GONE);
                spinner.setSelection(0);
                AlertDialog.Builder builderBorrarAlumno = new AlertDialog.Builder(this);
                LayoutInflater inflaterBorrarAlumno = this.getLayoutInflater();
                View dialogViewBorrarAlumno= inflaterBorrarAlumno.inflate(R.layout.borrar_dialog,null);
                builderBorrarAlumno.setView(dialogViewBorrarAlumno);

                final EditText etIdBorrarAlumno = (EditText) dialogViewBorrarAlumno.findViewById(R.id.etIdBorrar);

                Button btnBorrarAlumno = (Button) dialogViewBorrarAlumno.findViewById(R.id.btnBorrar);
                Button btnCancelarBorrarAlumno = (Button) dialogViewBorrarAlumno.findViewById(R.id.btnCancelar);
                final AlertDialog alertDialoBorrarAlumno = builderBorrarAlumno.create();
                alertDialoBorrarAlumno.show();
                btnBorrarAlumno.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        dbAdapter.borrarAlumno(Integer.parseInt(etIdBorrarAlumno.getText().toString()));
                        if (seleccionSpinner == 0){
                            alumnos.clear();
                            alumnos = dbAdapter.recuperarAlumnos();
                            adaptador = new Adaptador(alumnos);
                            recyclerView.setAdapter(adaptador);
                        }
                        alertDialoBorrarAlumno.dismiss();
                    }
                });

                btnCancelarBorrarAlumno.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        alertDialoBorrarAlumno.dismiss();
                    }
                });
                return true;
            case R.id.nuevoProfesor:
                recyclerViewProfesores.setVisibility(View.GONE);
                txtAlumno.setVisibility(View.GONE);
                txtProfesor.setVisibility(View.GONE);
                spinner.setSelection(1);
                AlertDialog.Builder builderProfesor = new AlertDialog.Builder(this);
                LayoutInflater inflaterProfesor = this.getLayoutInflater();
                View dialogViewProfesor = inflaterProfesor.inflate(R.layout.profesor_dialog,null);
                builderProfesor.setView(dialogViewProfesor);

                final EditText etNombreProfesor = (EditText) dialogViewProfesor.findViewById(R.id.etNombreProfesor);
                final EditText etEdadProfesor = (EditText) dialogViewProfesor.findViewById(R.id.etEdadProfesor);
                final EditText etCicloProfesor = (EditText) dialogViewProfesor.findViewById(R.id.etCicloProfesor);
                final EditText etTutoria = (EditText) dialogViewProfesor.findViewById(R.id.etTutoria);
                final EditText etDespacho = (EditText) dialogViewProfesor.findViewById(R.id.etDespacho);

                Button btnGuradarProfesor = (Button) dialogViewProfesor.findViewById(R.id.btnGuradar);
                Button btnCancelarProfesor = (Button) dialogViewProfesor.findViewById(R.id.btnCancelar);
                final AlertDialog alertDialogProfesor = builderProfesor.create();
                alertDialogProfesor.show();
                btnGuradarProfesor.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        dbAdapter.insertarProfesor(etNombreProfesor.getText().toString(),Integer.parseInt(etEdadProfesor.getText().toString()),etCicloProfesor.getText().toString(),etTutoria.getText().toString(), etDespacho.getText().toString());
                        if (seleccionSpinner == 1){
                            profesores.clear();
                            profesores = dbAdapter.recuperarProfesores();
                            adaptadorProfesor = new AdaptadorProfesor(profesores);
                            recyclerView.setAdapter(adaptadorProfesor);
                        }
                        alertDialogProfesor.dismiss();

                    }
                });

                btnCancelarProfesor.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        alertDialogProfesor.dismiss();
                    }
                });
                return true;
            case R.id.borraProfesor:
                spinner.setSelection(1);
                recyclerViewProfesores.setVisibility(View.GONE);
                txtAlumno.setVisibility(View.GONE);
                txtProfesor.setVisibility(View.GONE);
                AlertDialog.Builder builderBorrarProfesor = new AlertDialog.Builder(this);
                LayoutInflater inflaterBorrarProfesor = this.getLayoutInflater();
                View dialogViewBorrarProfesor= inflaterBorrarProfesor.inflate(R.layout.borrar_dialog,null);
                builderBorrarProfesor.setView(dialogViewBorrarProfesor);

                final EditText etIdBorrarProfesor = (EditText) dialogViewBorrarProfesor.findViewById(R.id.etIdBorrar);

                Button btnBorrarProfesor = (Button) dialogViewBorrarProfesor.findViewById(R.id.btnBorrar);
                Button btnCancelarBorrarProfesor = (Button) dialogViewBorrarProfesor.findViewById(R.id.btnCancelar);
                final AlertDialog alertDialoBorrarProfesor = builderBorrarProfesor.create();
                alertDialoBorrarProfesor.show();
                btnBorrarProfesor.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        dbAdapter.borrarProfesor(Integer.parseInt(etIdBorrarProfesor.getText().toString()));
                        if (seleccionSpinner == 1){
                            profesores.clear();
                            profesores = dbAdapter.recuperarProfesores();
                            adaptadorProfesor = new AdaptadorProfesor(profesores);
                            recyclerView.setAdapter(adaptadorProfesor);
                        }
                        alertDialoBorrarProfesor.dismiss();
                    }
                });

                btnCancelarBorrarProfesor.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        alertDialoBorrarProfesor.dismiss();
                    }
                });

                return true;
            case R.id.consulta:
                spinner.setSelection(0);
                recyclerViewProfesores.setVisibility(View.GONE);
                txtAlumno.setVisibility(View.GONE);
                txtProfesor.setVisibility(View.GONE);
                AlertDialog.Builder builder1 = new AlertDialog.Builder(this);
                LayoutInflater inflater1 = this.getLayoutInflater();
                View dialogConsulta = inflater1.inflate(R.layout.consulta_dialog,null);
                builder1.setView(dialogConsulta);

                final EditText etNombreConsulta = (EditText) dialogConsulta.findViewById(R.id.etNombreConsulta);
                final EditText etId = (EditText) dialogConsulta.findViewById(R.id.etId);

                Button btnGuradarConsulta = (Button) dialogConsulta.findViewById(R.id.btnGuradar);
                Button btnCancelarConsulta = (Button) dialogConsulta.findViewById(R.id.btnCancelar);
                final AlertDialog alertDialogConsulta = builder1.create();
                alertDialogConsulta.show();
                btnGuradarConsulta.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        if (!etNombreConsulta.getText().toString().isEmpty() && !etId.getText().toString().isEmpty()){
                            String[] args = new String[] {etNombreConsulta.getText().toString(),etId.getText().toString()};
                            alumnos.clear();
                            alumnos = dbAdapter.consultaAlumnos("curso=? AND ciclo=?",args);
                        } else if (etNombreConsulta.getText().toString().isEmpty() && !etId.getText().toString().isEmpty()){
                            alumnos.clear();
                            String[] args = new String[] {etId.getText().toString()};
                            alumnos = dbAdapter.consultaAlumnos("ciclo=?",args);
                        } else if (!etNombreConsulta.getText().toString().isEmpty() && etId.getText().toString().isEmpty()){
                            String[] args = new String[] {etNombreConsulta.getText().toString()};
                            alumnos.clear();
                            alumnos = dbAdapter.consultaAlumnos("curso=?",args);
                        } else {
                            Log.d("DATOS","No se ha seleccionado ninguna casilla");
                        }
                        AdaptadorConsulta adaptadorConsulta = new AdaptadorConsulta(alumnos);
                        recyclerView.setAdapter(adaptadorConsulta);
                        alertDialogConsulta.dismiss();

                    }
                });

                btnCancelarConsulta.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        alertDialogConsulta.dismiss();
                    }
                });
                return true;
            case R.id.consultaProfesor:
                recyclerViewProfesores.setVisibility(View.GONE);
                txtAlumno.setVisibility(View.GONE);
                txtProfesor.setVisibility(View.GONE);
                spinner.setSelection(1);
                AlertDialog.Builder builderConsultaProfesor = new AlertDialog.Builder(this);
                LayoutInflater inflaterConsultaProfesor = this.getLayoutInflater();
                View dialogConsultaProfesor = inflaterConsultaProfesor.inflate(R.layout.consulta_dialog,null);
                builderConsultaProfesor.setView(dialogConsultaProfesor);

                final EditText etNombreConsultaProfesor = (EditText) dialogConsultaProfesor.findViewById(R.id.etNombreConsulta);
                final EditText etIdProfesor = (EditText) dialogConsultaProfesor.findViewById(R.id.etId);

                etNombreConsultaProfesor.setHint("Tutoria");
                Button btnGuradarConsultaProfesor = (Button) dialogConsultaProfesor.findViewById(R.id.btnGuradar);
                Button btnCancelarConsultaProfesor = (Button) dialogConsultaProfesor.findViewById(R.id.btnCancelar);
                final AlertDialog alertDialogConsultaProfesor = builderConsultaProfesor.create();
                alertDialogConsultaProfesor.show();
                btnGuradarConsultaProfesor.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        if (!etNombreConsultaProfesor.getText().toString().isEmpty() && !etIdProfesor.getText().toString().isEmpty()){
                            String[] args = new String[] {etNombreConsultaProfesor.getText().toString(),etIdProfesor.getText().toString()};
                            profesores.clear();
                            profesores = dbAdapter.consultaProfesores("tutoria=? AND ciclo=?",args);
                        } else if (etNombreConsultaProfesor.getText().toString().isEmpty() && !etIdProfesor.getText().toString().isEmpty()){
                            profesores.clear();
                            String[] args = new String[] {etIdProfesor.getText().toString()};
                            profesores = dbAdapter.consultaProfesores("ciclo=?",args);
                        } else if (!etNombreConsultaProfesor.getText().toString().isEmpty() && etIdProfesor.getText().toString().isEmpty()){
                            String[] args = new String[] {etNombreConsultaProfesor.getText().toString()};
                            profesores.clear();
                            profesores = dbAdapter.consultaProfesores("tutoria=?",args);
                        } else {
                            Log.d("DATOS","No se ha seleccionado ninguna casilla");
                        }
                        AdaptadorConsultaProfesor adaptadorConsulta = new AdaptadorConsultaProfesor(profesores);
                        recyclerView.setAdapter(adaptadorConsulta);
                        alertDialogConsultaProfesor.dismiss();

                    }
                });

                btnCancelarConsultaProfesor.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        alertDialogConsultaProfesor.dismiss();
                    }
                });
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
