package analizador.Model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.StringTokenizer;

public class analizadorLexico {
    expresionesRegulares expresiones = new expresionesRegulares();
    ObservableList<String> entrad = FXCollections.observableArrayList();
    ObservableList<String> reservados = FXCollections.observableArrayList();
    ObservableList<String> delimitadores = FXCollections.observableArrayList();
    ObservableList<String> signos = FXCollections.observableArrayList();
    ObservableList<String> numeros = FXCollections.observableArrayList();
    ObservableList<String> palabra = FXCollections.observableArrayList();
    ObservableList<String> incorrectos = FXCollections.observableArrayList();
    boolean encontrado = true;

    public boolean inicio(ObservableList<String> datos, ObservableList<String> datos2){
        entrad.clear();
        reservados.clear();
        delimitadores.clear();
        signos.clear();
        numeros.clear();
        palabra.clear();
        incorrectos.clear();
        // guarda la entrada en elemntos separados
        System.out.println("inicia el proceso");
        entrad.addAll(datos);
        entrad.addAll(datos2);
        for (String s : entrad) {
            comprobarReservados(s);
        }
        return encontrado;
    }

    void comprobarReservados(String dato){
        boolean aux = false;
        if(expresiones.validarPalabrasRecervadas(dato)){
            reservados.add(dato);
            aux = true;
        }else {
            comprobarPalabras(dato);
        }

    }
    void comprobarPalabras(String dato){
        if(expresiones.validarLetras(dato)){
            palabra.add(dato);
        }else{
            comprobarDelimitadores(dato);
        }
    }

    void comprobarDelimitadores(String dato){
        if(expresiones.validarDelimitadores(dato)){
            delimitadores.add(dato);
        }else{
            comprobarSignos(dato);
        }
    }

    void comprobarSignos(String dato){
        if(expresiones.validarSignos(dato)){
            signos.add(dato);
        }else{
            comprobarNumeros(dato);
        }
    }

    void comprobarNumeros(String dato){
        if(expresiones.validarNumeros(dato)){
            numeros.add(dato);
        }else{
            datoIncorreto(dato);
            encontrado = false;
        }
    }

    void datoIncorreto(String dato){
        incorrectos.add(dato);
    }

}
