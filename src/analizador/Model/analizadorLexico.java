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

    public void inicio(String datos){
        entrad.clear();
        reservados.clear();
        delimitadores.clear();
        signos.clear();
        numeros.clear();
        palabra.clear();
        incorrectos.clear();
        // guarda la entrada en elemntos separados
        StringTokenizer st = new StringTokenizer(datos);
        while(st.hasMoreTokens()){
            entrad.add(st.nextToken());
        }
        for (int i = 0; i < entrad.size(); i++){
            comprobarReservados(entrad.get(i));
        }
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
        }
    }

    void datoIncorreto(String dato){
        incorrectos.add(dato);
    }

}
