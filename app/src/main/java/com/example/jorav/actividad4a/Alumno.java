package com.example.jorav.actividad4a;

/**
 * Created by jorav on 19/12/2016.
 */

public class Alumno {
    String nombre, ciclo, curso;
    int edad,id;
    double nota;

    public Alumno(int id,String nombre, String ciclo, String curso, int edad, double nota) {
        this.id = id;
        this.nombre = nombre;
        this.ciclo = ciclo;
        this.curso = curso;
        this.edad = edad;
        this.nota = nota;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCiclo() {
        return ciclo;
    }

    public void setCiclo(String ciclo) {
        this.ciclo = ciclo;
    }

    public String getCurso() {
        return curso;
    }

    public void setCurso(String curso) {
        this.curso = curso;
    }

    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

    public double getNota() {
        return nota;
    }

    public void setNota(double nota) {
        this.nota = nota;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
