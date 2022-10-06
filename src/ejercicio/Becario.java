/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ejercicio;


/**
 *
 * @author Antonio
 */
public class Becario {
    
    private String nombre;
    private String apellidos;
    private char sexo;
    private int edad;
    private int numSuspensos;
    private String resFamiliar;
    private double ingresosAnuales;
    
    public Becario(){}

    public Becario(String nombre, String apellidos, char sexo, int edad, int numSuspensos, String resFamiliar, double ingresosAnuales) {
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.sexo = sexo;
        this.edad = edad;
        this.numSuspensos = numSuspensos;
        this.resFamiliar = resFamiliar;
        this.ingresosAnuales = ingresosAnuales;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
     public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public char getSexo() {
        return sexo;
    }

    public void setSexo(char sexo) {
        this.sexo = sexo;
    }

    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

    public int getNumSuspensos() {
        return numSuspensos;
    }

    public void setNumSuspensos(int numSuspensos) {
        this.numSuspensos = numSuspensos;
    }

    public String getResFamiliar() {
        return resFamiliar;
    }

    public void setResFamiliar(String resFamiliar) {
        this.resFamiliar = resFamiliar;
    }

    public double getIngresosAnuales() {
        return ingresosAnuales;
    }

    public void setIngresosAnuales(double ingresosAnuales) {
        this.ingresosAnuales = ingresosAnuales;
    }
    
      
 

   
    
}
