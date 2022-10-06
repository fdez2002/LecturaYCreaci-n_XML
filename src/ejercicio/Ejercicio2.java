package ejercicio;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.text.Format;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.JDOMException;
import org.jdom2.input.SAXBuilder;
import org.jdom2.output.XMLOutputter;
import org.xml.sax.SAXException;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
/**
 *
 * @author Antonio
 *
 * Realizar un programa que lea de teclado (la entrada estandar) los siguientes
 * datos: Nombre y apellido de un supuesto becario. Sexo (H/M) Edad (20 a 60)
 * Numero de suspensos del curso anterior (0 a 4) Residencia familiar (Sí/No)
 * Ingresos anuales de la familia. Trabajaremos agrupando los atributos en una
 * clase llamada Becario. Los datos se almacenan en un fichero binario llamado
 * datosbeca.bin
 */
public class Ejercicio2 {

    public static void main(String[] args) {
        Datos fichero = new Datos();
        fichero.opciones();
    }

}

class Datos {

    ArrayList<Becario> becarioArray = new ArrayList<>();

    char sexo;
    int edad, suspensos;
    String resicencia;

    Becario becario = new Becario();

    Scanner sc = new Scanner(System.in);

    //Metodo con las distintas opciones del ejercicio
    public void opciones() {
        int menu = 0;
        System.out.println("Selecciona una opcion");
        System.out.println("1---Intruducir datos en un fichero XML");
        System.out.println("2---Leer datos del XML");
        System.out.println("3---Leer datos del XML utilizando SAX");
        System.out.println("4---Salir");
        do {
            menu = sc.nextInt();
        } while (menu != 1 && menu != 2 && menu != 3 && menu != 4);

        menu(menu);

    }

    //Nos redirige a los metodos que hemos seleccionado
    public void menu(int menu) {

        switch (menu) {

            case 1 ->
                datosBecario();
            case 2 ->
                leerXML();
            case 3 ->
                leerXMLSAX();
            case 4 ->
                System.exit(0);

        }

        opciones();

    }

    //Añadimos los datos para el posterior guardado
    void datosBecario() {

        sc.nextLine();
        System.out.println("Nombre del becario");
        becario.setNombre(sc.nextLine());

        System.out.println("Apellidos del becario");
        becario.setApellidos(sc.nextLine());

        System.out.println("Sexo (H/M)");
        do {
            sexo = sc.next().charAt(0);

        } while (sexo != 'H' && sexo != 'M');

        becario.setSexo(sexo);

        System.out.println("Edad (20 a 60)");

        do {
            edad = sc.nextInt();
        } while (edad < 20 || edad > 60);
        becario.setEdad(edad);

        System.out.println("Numero de suspensos del curso anterior (0 a 4)");

        do {
            suspensos = sc.nextInt();
        } while (suspensos < 0 || suspensos > 4);
        becario.setNumSuspensos(suspensos);
        sc.nextLine();
        System.out.println("Residencia familiar (Sí/No)");

        do {
            resicencia = sc.nextLine();
        } while (resicencia == "Si" || resicencia == "No");
        becario.setResFamiliar(resicencia);

        System.out.println("Ingresos anuales de la familia.");
        double ingresosAnuales = sc.nextDouble();
        becario.setIngresosAnuales(ingresosAnuales);

        becarioArray.add(becario);

        guardadoDeDatos();
        crearXMLstatic();

    }

    //Crea y añade los datos que hay en el arrayList al XML
    public void crearXMLstatic() {
        try {
            Element becarios = new Element("becarios");//Elemento root
            Document doc = new Document(becarios);//Le decimos que ese es el root

            //Recorremos el array
            for (Becario b : becarioArray) {
                Element becario2 = new Element("becario");//Creamos el elemento becario

                //Dentro del elemento becario tendra todos estos elementos
                Element nombre = new Element("nombre");
                Element apellidos = new Element("apellidos");
                Element sexo = new Element("sexo");
                Element edad = new Element("edad");
                Element numSuspensos = new Element("numSuspensos");
                Element resiFamiliar = new Element("resiFamiliar");
                Element ingresosAnuales = new Element("ingresosAnuales");
//                nombre.setText(b.getNombre());
//                apellidos.setText(b.getApellidos());
//                sexo.setText(String.valueOf(b.getSexo()));
//                edad.setText(String.valueOf(b.getEdad()));
//                numSuspensos.setText(String.valueOf(b.getEdad()));
//                resiFamiliar.setText(b.getResFamiliar());
//                ingresosAnuales.setText(String.valueOf(b.getIngresosAnuales()));

                //Guardamos el contenido en los elementos
                becario2.addContent(nombre.setText(b.getNombre()));
                becario2.addContent(apellidos.setText(b.getApellidos()));
                becario2.addContent(sexo.setText(String.valueOf(b.getSexo())));
                becario2.addContent( edad.setText(String.valueOf(b.getEdad())));
                becario2.addContent(numSuspensos.setText(String.valueOf(b.getEdad())));
                becario2.addContent(resiFamiliar.setText(b.getResFamiliar()));
                becario2.addContent(ingresosAnuales.setText(String.valueOf(b.getIngresosAnuales())));

                becarios.addContent(becario2);//Añadimos los elementos
            }

            XMLOutputter xml = new XMLOutputter();//Creamos el xml
            xml.setFormat(org.jdom2.output.Format.getPrettyFormat());//Le damos formato

            FileWriter filee = new FileWriter("Becario.xml");//Creacion del fichero
            xml.output(doc, filee);//

        } catch (IOException ex) {
            Logger.getLogger(Ejercicio2.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    //Leemos nuestro XML con formato
    public void leerXML() {

        try {
            File inputFile = new File("becario.xml");
            SAXBuilder saxBuilder = new SAXBuilder();
            Document document = saxBuilder.build(inputFile);
            System.out.println("Root element :" + document.getRootElement().getName());
            Element classElement = document.getRootElement();

            List<Element> becarioList = classElement.getChildren();
            System.out.println("----------------------------");

            for (int temp = 0; temp < becarioList.size(); temp++) {
                Element becarios = becarioList.get(temp);
                System.out.println("\nCurrent Element :"
                        + becarios.getName() + " " + temp);
                System.out.println("Nombre : "
                        + becarios.getChild("nombre").getText());
                System.out.println("Apellidos : "
                        + becarios.getChild("apellidos").getText());
                System.out.println("Sexo : "
                        + becarios.getChild("sexo").getText());
                System.out.println("Edad : "
                        + becarios.getChild("edad").getText());
                System.out.println("Suspensos : "
                        + becarios.getChild("numSuspensos").getText());
                System.out.println("Residencia familiar : "
                        + becarios.getChild("resiFamiliar").getText());
                System.out.println("Ingresos anuales : "
                        + becarios.getChild("ingresosAnuales").getText());
            }
        } catch (JDOMException | IOException e) {
            e.printStackTrace();
        }
    }

////Leemos nuestro XML usando SAX
    public void leerXMLSAX() {

        try {
            SAXParserFactory factory = SAXParserFactory.newInstance();

            SAXParser parser = factory.newSAXParser();

            BecarioHandler handler = new BecarioHandler();//Creamos la instancia de la clase
            parser.parse("becario.xml", handler);//el handler porcesa el xml

        } catch (ParserConfigurationException | SAXException | IOException ex) {
            Logger.getLogger(Ejercicio2.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
    /**
     * EJERCICIO ANTERIOR
     * 
     * 
     * 
     * 
     * 
     * 
     * 
     * 
    */
    //Guarda los datos que hemos introducido
    public void guardadoDeDatos() {
        FileOutputStream file;
        try {
            file = new FileOutputStream("datosbeca.bin");
            for (Becario b : becarioArray) {
                file.write((" Becario{" + "nombre=" + b.getNombre() + " apellidos=" + b.getApellidos() + 
                        ", sexo=" + b.getSexo() + ", edad=" + b.getEdad() + ", numSuspensos=" + b.getNumSuspensos() + 
                        ", resFamiliar=" + b.getResFamiliar() + ", ingresosAnuales=" + b.getIngresosAnuales() + '}').getBytes());
                System.out.println("");
            }
        } catch (IOException ex) {
            Logger.getLogger(Becario.class.getName()).log(Level.SEVERE, null, ex);
        }
        crearXMLstatic();

    }

    //Leemos los datos del fichero
    private void leerFichero() {

        try {
            RandomAccessFile fi = new RandomAccessFile("datosbeca.bin", "r");
            int a = 0;
            a = fi.read(); //Lee el fichero en entero mostrando el -1 al final de la frase

            while (a != -1) {

                a = fi.read();

                //char letra = (char) a; //Nos transforma los codigos a su correspondiente caracter
                //System.out.print(letra);
                System.out.print(a);
            }
        } catch (IOException ex) {
            Logger.getLogger(Becario.class.getName()).log(Level.SEVERE, null, ex);

        }

    }
}
