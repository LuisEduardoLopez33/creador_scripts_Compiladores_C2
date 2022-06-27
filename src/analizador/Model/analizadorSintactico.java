package analizador.Model;


import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import javax.print.attribute.standard.PrinterMessageFromOperator;
import java.util.Arrays;
import java.util.Stack;

public class analizadorSintactico {
ObservableList<String> cadenaCodigo = FXCollections.observableArrayList();
expresionesRegulares expresiones = new expresionesRegulares();
String[] sa = {"INICIO", "C"};
String[] inicio ={"PUB","CLA", "NOM", "CUERPO"};
String[] cuerpo = {"LLA","ATRIBU","CONS", "LLC"};
String[] atribu = {"PRIV","TIPO","NOM","PYC","RESTA"};
String[] cons = {"PUB","NOM","PA","PARAM","PC","RESTCONS"};
String[] param = {"TIPO", "NOM","RESTPARAM"};
String[] restcons = {"LLA", "SUP","REF", "LLC"};
String[] sup = {"S","PA","PC","PYC"};
String[] ref = {"TH","PT","NOM","IG","NOM","PYC","ULTIMOREST"};
String[] c = {"PACK", "DEFCLA","LLA","EXT","MN","PC","LLA","CUER", "LLC","LLC"};


Stack<String> pilaProceso = new Stack<String>();

    public boolean recivirDato(ObservableList datos){
        System.out.println("inicia el proceso");
        cadenaCodigo = datos;
        pilaProceso.push("S");
        if (pilaProceso.size()==1){

        }
        return true;
    }
    // INGRESAR A LA PILA LOS ELEMTOS DEL ARRAY
    public void  ingresarCadenaAlaPila(String[] lista){
        for (int i = lista.length-1; i >= 0; i--){
            pilaProceso.push(lista[i]);
        }
    }
    // IMPRIMIR LA PILA
    public void imprimirPila(){
        //System.out.println("elementos en pila");
        System.out.println(Arrays.asList(pilaProceso));
    }
    //ELIMINAR LOS DATOS DESPUES DE LA COMPARACION DE DATOS
    public void popDatos(){
        pilaProceso.pop();
        cadenaCodigo.remove(cadenaCodigo.size()-1);
    }

    public void s(){
        pilaProceso.pop();
        ingresarCadenaAlaPila(sa);
        imprimirPila();
        inicioP();
    }

    public void inicioP(){
        pilaProceso.pop();
        ingresarCadenaAlaPila(inicio);
        imprimirPila();
        pilaProceso.pop();
        pilaProceso.push("public");
        if (pilaProceso.peek().equals(cadenaCodigo.get(cadenaCodigo.size()-1))){
            popDatos();
            pilaProceso.push("class");
            imprimirPila();
            pilaProceso.pop();
            if(pilaProceso.peek().equals(cadenaCodigo.get(cadenaCodigo.size()-1))){
                popDatos();
                boolean result = expresiones.validarLetras(cadenaCodigo.get(cadenaCodigo.size()-1));
                if (result){
                    popDatos();
                    imprimirPila();
                    cuerpop();
                }
            }
        }
    }

    public void cuerpop(){
        pilaProceso.pop();
        ingresarCadenaAlaPila(cuerpo);
        imprimirPila();
        pilaProceso.pop();
        pilaProceso.push("{");
        if (pilaProceso.peek().equals(cadenaCodigo.get(cadenaCodigo.size()-1))){
            popDatos();
            imprimirPila();
            if(atribup()){
                if(consp()){
                    pilaProceso.pop();
                    pilaProceso.push("}");
                    if (pilaProceso.peek().equals(cadenaCodigo.get(cadenaCodigo.size()-1))){
                        popDatos();
                    }
                }
            }

        }
    }

    public boolean atribup(){
        boolean aux = false;
        boolean bucle = true;
        pilaProceso.pop();
        ingresarCadenaAlaPila(atribu);
        imprimirPila();
        pilaProceso.pop();
        pilaProceso.push("private");
        while(bucle){
            if (pilaProceso.peek().equals(cadenaCodigo.get(cadenaCodigo.size()-1))){
                aux = false;
                popDatos();
                imprimirPila();
                if(cadenaCodigo.get(cadenaCodigo.size()-1).equals("int") || cadenaCodigo.get(cadenaCodigo.size()-1).equals("String") || cadenaCodigo.get(cadenaCodigo.size()-1).equals("float")){
                    popDatos();
                    imprimirPila();
                    boolean result = expresiones.validarLetras(cadenaCodigo.get(cadenaCodigo.size()-1));
                    if (result){
                        popDatos();
                        imprimirPila();
                        pilaProceso.pop();
                        pilaProceso.push(";");
                        if (pilaProceso.peek().equals(cadenaCodigo.get(cadenaCodigo.size()-1))){
                            popDatos();
                            pilaProceso.pop();
                            if(pilaProceso.peek().equals("private")){
                                ingresarCadenaAlaPila(atribu);
                                imprimirPila();
                                pilaProceso.pop();
                                pilaProceso.push("private");
                            }else{
                                bucle = false;
                            }
                            aux = true;
                        }
                    }
                }
            }
        }

        return aux;
    }

    public boolean consp(){
        boolean aux = false;
        ingresarCadenaAlaPila(cons);
        pilaProceso.pop();
        pilaProceso.push("public");
        imprimirPila();
        if (pilaProceso.peek().equals(cadenaCodigo.get(cadenaCodigo.size()-1))){
            boolean result = expresiones.validarLetras(cadenaCodigo.get(cadenaCodigo.size()-1));
            if (result) {
                popDatos();
                imprimirPila();
                pilaProceso.pop();
                pilaProceso.push("(");
                if (pilaProceso.peek().equals(cadenaCodigo.get(cadenaCodigo.size()-1))){
                    popDatos();
                    if(paramp()){
                        pilaProceso.pop();
                        pilaProceso.push(")");
                        imprimirPila();
                        if (pilaProceso.peek().equals(cadenaCodigo.get(cadenaCodigo.size()-1))){
                            popDatos();

                        }
                    }
                }
            }

        }
        return aux;
    }

    public boolean paramp(){
        boolean aux = false;
        boolean bucle = true;
        ingresarCadenaAlaPila(param);
        while (bucle){
            if(cadenaCodigo.get(cadenaCodigo.size()-1).equals("int") || cadenaCodigo.get(cadenaCodigo.size()-1).equals("String") || cadenaCodigo.get(cadenaCodigo.size()-1).equals("float")){
                popDatos();
                boolean result = expresiones.validarLetras(cadenaCodigo.get(cadenaCodigo.size()-1));
                if (result) {
                    popDatos();
                    imprimirPila();
                    if(pilaProceso.peek().equals(",")){
                        ingresarCadenaAlaPila(param);
                    }else{
                        bucle = false;
                        aux = true;
                    }
                }
            }
        }

        return aux;
    }

    public void  restconsp(){
        ingresarCadenaAlaPila(restcons);
        pilaProceso.pop();
        pilaProceso.push("{");
        imprimirPila();
        if (pilaProceso.peek().equals(cadenaCodigo.get(cadenaCodigo.size()-1))){
            popDatos();
            if(supp()){

            }
        }
    }

    public boolean supp(){
        imprimirPila();
        return true;
    }
}
