package com.example.jorav.actividad4a;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;

/**
 * Created by jorav on 16/12/2016.
 */

//Clase que controlara la BD
public class MyDBAdapter {
    //Definimos las variables
    private static final String DATABASE_NAME = "dbcolegio.db";
    private static final String DATABASE_TABLE_PROFESORES = "profesores";
    private static final String DATABASE_TABLE_ALUMNOS  = "alumnos";
    private static final int DATABASE_VERSION = 1;

    //Nombre columnas de las tablas
    private static final String NOMBRE = "nombre";
    private static final String EDAD = "edad";
    private static final String CICLO = "ciclo";
    private static final String CURSO_TUTOR = "tutoria";
    private static final String DESPACHO = "despacho";
    private static final String CURSO = "curso";
    private static final String NOTA = "nota";

    private static final String DATABASE_CREATE_PROFESORES = "CREATE TABLE "+DATABASE_TABLE_PROFESORES+" (id integer primary key autoincrement, nombre text, edad integer, ciclo text, tutoria text, despacho text);";
    private static final String DATABASE_CREATE_ALUMNOS  = "CREATE TABLE "+DATABASE_TABLE_ALUMNOS+" (id integer primary key autoincrement, nombre text, edad integer, ciclo text, curso text, nota double);";
    private static final String DATABASE_DROP_PROFESORES = "DROP TABLE IF EXISTS "+DATABASE_TABLE_PROFESORES+";";
    private static final String DATABASE_DROP_ALUMNOS = "DROP TABLE IF EXISTS "+DATABASE_TABLE_ALUMNOS+";";

    //Contexto de la app que usa la BD
    private final Context context;
    // Clase SQLiteOpenHelper para crear/actualizar la base de datos
    private MyDbHelper dbHelper;
    // Instancia de la base de datos
    private SQLiteDatabase db;


    public MyDBAdapter(Context context) {
        this.context = context;
        dbHelper = new MyDbHelper(context,DATABASE_NAME,null,DATABASE_VERSION);
    }

    public void open(){
        try{
            db = dbHelper.getWritableDatabase();
        }catch(SQLiteException e){
            db = dbHelper.getReadableDatabase();
        }
    }

    public void insertarProfesor(String nombre, int edad, String ciclo, String tutoria, String despacho){
        //Creamos un nuevo registro de valores a insertar
        ContentValues newValues = new ContentValues();
        //Asignamos los valores
        newValues.put(NOMBRE,nombre);
        newValues.put(EDAD,edad);
        newValues.put(CICLO,ciclo);
        newValues.put(CURSO_TUTOR,tutoria);
        newValues.put(DESPACHO,despacho);
        db.insert(DATABASE_TABLE_PROFESORES,null,newValues);
    }

    public void borrarProfesor(int id){
        db.delete(DATABASE_TABLE_PROFESORES,"id ="+id,null);
    }

    public void borrarAlumno(int id){
        db.delete(DATABASE_TABLE_ALUMNOS,"id ="+id,null);
    }

    public void borrarTablaAlumno(){
        db.delete(DATABASE_TABLE_ALUMNOS,"1",null);
    }

    public void borrarTablaProfesores(){
        db.delete(DATABASE_TABLE_PROFESORES,"1",null);
    }

    public void insertarAlumno(String nombre, int edad, String ciclo, String curso, double nota){
        //Creamos un nuevo registro de valores a insertar
        ContentValues newValues = new ContentValues();
        //Asignamos los valores
        newValues.put(NOMBRE,nombre);
        newValues.put(EDAD,edad);
        newValues.put(CICLO,ciclo);
        newValues.put(CURSO,curso);
        newValues.put(NOTA,nota);
        db.insert(DATABASE_TABLE_ALUMNOS,null,newValues);
    }

    public ArrayList<Alumno> recuperarAlumnos(){
        ArrayList<Alumno> alumnos = new ArrayList<>();
        //Recuperamos en un cursor la consulta
        Cursor cursor = db.query(DATABASE_TABLE_ALUMNOS,null,null,null,null,null,null);
        //Recorremos el cursor
        if (cursor != null && cursor.moveToFirst()){
            do{
                int id = cursor.getInt(0);
                String nombre = cursor.getString(1);
                String ciclo = cursor.getString(3);
                String curso = cursor.getString(4);
                int edad = cursor.getInt(2);
                double nota = cursor.getFloat(5);
                Alumno alumno = new Alumno(id,nombre,ciclo,curso,edad,nota);
                alumnos.add(alumno);
                Log.d("DATOS",nombre+" "+ciclo+" "+curso+" "+edad+" "+nota);
            }while (cursor.moveToNext());
        }
        return alumnos;
    }

    public ArrayList<Profesor> recuperarProfesores(){
        ArrayList<Profesor> profesores = new ArrayList<>();
        //Recuperamos en un cursor la consulta
        Cursor cursor = db.query(DATABASE_TABLE_PROFESORES,null,null,null,null,null,null);
        //Recorremos el cursor
        if (cursor != null && cursor.moveToFirst()){
            do{
                int id = cursor.getInt(0);
                String nombre = cursor.getString(1);
                String ciclo = cursor.getString(3);
                int edad = cursor.getInt(2);
                String tutoria = cursor.getString(4);
                String despacho = cursor.getString(5);
                Profesor profesor = new Profesor(id,edad,nombre,ciclo,tutoria,despacho);
                profesores.add(profesor);
                Log.d("DATOS",nombre+" "+ciclo+" "+tutoria+" "+edad+" "+despacho);
            }while (cursor.moveToNext());
        }
        return profesores;
    }

    public ArrayList<Alumno> consultaAlumnos(String condicion,String[] args){
        ArrayList<Alumno> alumnos = new ArrayList<>();
        String[] campos = new String[] {"id", "nombre","edad","ciclo","curso","nota"};
        Cursor cursor = db.query(DATABASE_TABLE_ALUMNOS,campos,condicion,args,null,null,null);
        if (cursor != null && cursor.moveToFirst()){
            do{
                int id = cursor.getInt(0);
                String nombre = cursor.getString(1);
                String ciclo = cursor.getString(3);
                String curso = cursor.getString(4);
                int edad = cursor.getInt(2);
                double nota = cursor.getFloat(5);
                Alumno alumno = new Alumno(id,nombre,ciclo,curso,edad,nota);
                alumnos.add(alumno);
                Log.d("DATOS CONSULTA",id+" "+nombre+" "+ciclo+" "+curso+" "+edad+" "+nota);
            }while (cursor.moveToNext());
        }

        return alumnos;
    }

    public ArrayList<Profesor> consultaProfesores(String condicion,String[] args){

        ArrayList<Profesor> profesores = new ArrayList<>();
        String[] campos = new String[] {"id", "nombre","edad","ciclo","tutoria","despacho"};
        Cursor cursor = db.query(DATABASE_TABLE_PROFESORES,campos,condicion,args,null,null,null);
        if (cursor != null && cursor.moveToFirst()){
            do{
                int id = cursor.getInt(0);
                String nombre = cursor.getString(1);
                String ciclo = cursor.getString(3);
                String tutoria = cursor.getString(4);
                int edad = cursor.getInt(2);
                String despacho = cursor.getString(5);
                Profesor profesor = new Profesor(id,edad,nombre,ciclo,tutoria,despacho);
                profesores.add(profesor);
                Log.d("DATOS CONSULTA",id+" "+nombre+" "+ciclo+" "+tutoria+" "+edad+" "+despacho);
            }while (cursor.moveToNext());
        }

        return profesores;
    }

    //Consulta añadida para el examen
    public ArrayList<Profesor> consultaProfesorExamen(String condicion,String letra){
        ArrayList<Profesor> profesores = new ArrayList<>();
        String[] campos = new String[] {"id", "nombre","edad","ciclo","tutoria","despacho"};
        Cursor cursor = db.query(DATABASE_TABLE_PROFESORES,campos,condicion,new String[] { letra+"%" },null,null,null);
        if (cursor != null && cursor.moveToFirst()){
            do{
                int id = cursor.getInt(0);
                String nombre = cursor.getString(1);
                String ciclo = cursor.getString(3);
                String tutoria = cursor.getString(4);
                int edad = cursor.getInt(2);
                String despacho = cursor.getString(5);
                Profesor profesor = new Profesor(id,edad,nombre,ciclo,tutoria,despacho);
                profesores.add(profesor);
                Log.d("DATOS CONSULTA",id+" "+nombre+" "+ciclo+" "+tutoria+" "+edad+" "+despacho);
            }while (cursor.moveToNext());
        }

        return profesores;
    }

    //Consulta añadida para el examen
    public  ArrayList<Alumno> consultaAlumnoExamen(String condicion, String letra){
        ArrayList<Alumno> alumnos = new ArrayList<>();
        String[] campos = new String[] {"id", "nombre","edad","ciclo","curso","nota"};
        Cursor cursor = db.query(DATABASE_TABLE_ALUMNOS,campos,condicion,new String[] { letra+"%" },null,null,null);
        if (cursor != null && cursor.moveToFirst()){
            do{
                int id = cursor.getInt(0);
                String nombre = cursor.getString(1);
                String ciclo = cursor.getString(3);
                String curso = cursor.getString(4);
                int edad = cursor.getInt(2);
                double nota = cursor.getFloat(5);
                Alumno alumno = new Alumno(id,nombre,ciclo,curso,edad,nota);
                alumnos.add(alumno);
                Log.d("DATOS CONSULTA",id+" "+nombre+" "+ciclo+" "+curso+" "+edad+" "+nota);
            }while (cursor.moveToNext());
        }

        return alumnos;
    }

    public void updateAlumno(String nombre, int edad, String ciclo, String curso, double nota, int  id){
        ContentValues valores = new ContentValues();
        valores.put("nombre",nombre);
        valores.put("edad",edad);
        valores.put("ciclo",ciclo);
        valores.put("curso",curso);
        valores.put("nota",nota);
        db.update(DATABASE_TABLE_ALUMNOS,valores,"id="+id,null);
    }

    public void updateProfesor(String nombre, int edad, String ciclo, String tutoria, String despacho, int  id){
        ContentValues valores = new ContentValues();
        valores.put("nombre",nombre);
        valores.put("edad",edad);
        valores.put("ciclo",ciclo);
        valores.put("tutoria",tutoria);
        valores.put("despacho",despacho);
        db.update(DATABASE_TABLE_PROFESORES,valores,"id="+id,null);
    }

    private static class MyDbHelper extends SQLiteOpenHelper{

        public MyDbHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
            super(context, name, factory, version);
        }

        @Override
        public void onCreate(SQLiteDatabase sqLiteDatabase) {
            sqLiteDatabase.execSQL(DATABASE_CREATE_PROFESORES);
            sqLiteDatabase.execSQL(DATABASE_CREATE_ALUMNOS);
        }

        @Override
        public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
            sqLiteDatabase.execSQL(DATABASE_DROP_PROFESORES);
            sqLiteDatabase.execSQL(DATABASE_DROP_ALUMNOS);
            onCreate(sqLiteDatabase);
        }
    }
}
