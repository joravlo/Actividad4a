package com.example.jorav.actividad4a;

/**
 * Created by jorav on 19/12/2016.
 */

public class Profesor {
    String nombre, ciclo, tutoria, despacho;
    int edad, id;

    public Profesor(int id,int edad, String nombre, String ciclo, String tutoria, String despacho) {
        this.id = id;
        this.edad = edad;
        this.nombre = nombre;
        this.ciclo = ciclo;
        this.tutoria = tutoria;
        this.despacho = despacho;
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

    public String getTutoria() {
        return tutoria;
    }

    public void setTutoria(String tutoria) {
        this.tutoria = tutoria;
    }

    public String getDespacho() {
        return despacho;
    }

    public void setDespacho(String despacho) {
        this.despacho = despacho;
    }

    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
