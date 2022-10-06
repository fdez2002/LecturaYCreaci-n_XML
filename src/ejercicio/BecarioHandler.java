package ejercicio;


import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
/**
 *
 * @author Antonio
 */
public class BecarioHandler extends DefaultHandler {

    private StringBuilder value;//Atributo

    public BecarioHandler() {//Constructor
        this.value = new StringBuilder();
    }

    @Override
    public void startElement(String uri, String localName,
            String qName, Attributes attributes)
            throws SAXException {
        this.value.setLength(0);//Reseteamos el valor

    }

    @Override
    public void characters(char ch[], int start, int length)
            throws SAXException {
        this.value.append(ch, start, length);//Cogemos el contenido y lo metemos en el StringBuilder

    }

    @Override
    public void endElement(String uri, String localName, String qName)
            throws SAXException {
        switch (qName) {
            case "becario":
                break;
            case "nombre":
                System.out.println("Nombre: " + this.value.toString());//leemos el nombre
                break;
            case "apellidos":
                System.out.println("Apellidos: " + this.value.toString());//leemos los apelldios
                break;
            case "sexo":
                System.out.println("Sexo: " + this.value.toString());//leemos el sexo
                break;
            case "edad":
                System.out.println("Edad: " + this.value.toString());//leemos la edad
                break;
            case "numSuspensos":
                System.out.println("Suspensos: " + this.value.toString());//leemos los suspensos
                break;
            case "resiFamiliar":
                System.out.println("Residencia familiar: " + this.value.toString());//leemos la residencia
                break;
            case "ingrsosAnuales":
                System.out.println("Ingresos anuales: " + this.value.toString());//leemos los ingresos
                break;

        }

    }

}
