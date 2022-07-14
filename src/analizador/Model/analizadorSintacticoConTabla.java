package analizador.Model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.Arrays;
import java.util.Stack;

public class analizadorSintacticoConTabla {

    String[][] tabla = {
            {"","public","class","a...z","A...Z","(",")","{","}",",",".","private","int","String","float",";","super","this","=","import","new","main","static","void","[","]","args","0..1","\""},
            {"SA","INICIO C","","","","","","","","","","","","","","","","","","","","","","","","","","",""},
            {"INICIO","PUB CLA NOM CUERPO","","","","","","","","","","","","","","","","","","","","","","","","","","",""},
            {"PUB","public","","","","","","","","","","","","","","","","","","","","","","","","","","",""},
            {"CLA","","class","","","","","","","","","","","","","","","","","","","","","","","","","",""},
            {"NOM","","","PALABRA RESTPALABRA","PALABRA RESTPALABRA","","","","","","","","","","","","","","","","","","","","","","","",""},
            {"PALABRA","","","a..z","A..Z","","","","","","","","","","","","","","","","","","","","","","","",""},
            {"RESTPALABRA","","","PALABRA RESTPALABRA","PALABRA RESTPALABRA","PA","PC","LLA","","CM","","","","","","PYC","","","IG","","","","","","","","","",""},
            {"PA","","","","","(","","","","","","","","","","","","","","","","","","","","","","",""},
            {"PC","","","","","",")","","","","","","","","","","","","","","","","","","","","","",""},
            {"LLA","","","","","","","{","","","","","","","","","","","","","","","","","","","","",""},
            {"LLC","","","","","","","","}","","","","","","","","","","","","","","","","","","","",""},
            {"CM","","","","","","","","",",","","","","","","","","","","","","","","","","","","",""},
            {"PT","","","","","","","","","",".","","","","","","","","","","","","","","","","","",""},
            {"CUERPO","","","","","","","LLA ATRIBU CONS LLC","","","","","","","","","","","","","","","","","","","","",""},
            {"ATRIBU","","","","","","","","","","","PRIV TIPO NOM PYC RESTA","","","","","","","","","","","","","","","","",""},
            {"PRIV","","","","","","","","","","","private","","","","","","","","","","","","","","","","",""},
            {"TIPO","","","","","","","","","","","","int","String","float","","","","","","","","","","","","","",""},
            {"PYC","","","","","","","","","","","","","","",";","","","","","","","","","","","","",""},
            {"RESTA","PUB","","","","","","","","","","ATRIBU","","","","","","","","","","","","","","","","",""},
            {"CONS","PUB NOM PA PARAM PC RESTCONS","","","","","","","","","","","","","","","","","","","","","","","","","","",""},
            {"PARAM","","","","","","","","","","","","TIPO NOM RESTPARAM","TIPO NOM RESTPARAM","TIPO NOM RESTPARAM","","","","","","","","","","","","","",""},
            {"RESTPARAM","","","","","","PC","","","CM PARAM","","","","","","","","","","","","","","","","","","",""},
            {"RESTCONST","","","","","","","LLA SUP REF LLC","","","","","","","","","","","","","","","","","","","","",""},
            {"SUP","","","","","","","","","","","","","","","","S PA PC PYC","","","","","","","","","","","",""},
            {"S","","","","","","","","","","","","","","","","super","","","","","","","","","","","",""},
            {"REF","","","","","","","","","","","","","","","","","TH PT NOM IG NOM PYC ULTIMOREST","","","","","","","","","","",""},
            {"TH","","","","","","","","","","","","","","","","","this","","","","","","","","","","",""},
            {"IG","","","","","","","","","","","","","","","","","","=","","","","","","","","","",""},
            {"ULTIMOREST","","","","","","","","LLC","","","","","","","","","REF","","","","","","","","","","",""},
            {"C","","","","","","","","","","","","","","","","","","","PACK DEFCLA LLA MN PC LLA CUER LLC LLC","","","","","","","","",""},
            {"PACK","","","","","","","","","","","","","","","","","","","IMP NOM PT NOM PT NOM PYC","","","","","","","","",""},
            {"IMP","","","","","","","","","","","","","","","","","","","import","","","","","","","","",""},
            {"DEFCLA","PUB CLA MA","","","","","","","","","","","","","","","","","","","","","","","","","","",""},
            {"NE","","","","","","","","","","","","","","","","","","","","new","","","","","","","",""},
            {"MN","PUB NOAC RTP MA PA STR CCI CCF AR","","","","","","","","","","","","","","","","","","","","","","","","","","",""},
            {"MA","","","","","","","","","","","","","","","","","","","","","main","","","","","","",""},
            {"NOAC","","","","","","","","","","","","","","","","","","","","","","static","","","","","",""},
            {"RTP","","","","","","","","","","","","","","","","","","","","","","","void","","","","",""},
            {"STR","","","","","","","","","","","","","String","","","","","","","","","","","","","","",""},
            {"CCI","","","","","","","","","","","","","","","","","","","","","","","","[","","","",""},
            {"CCF","","","","","","","","","","","","","","","","","","","","","","","","","]","","",""},
            {"AR","","","","","","","","","","","","","","","","","","","","","","","","","","args","",""},
            {"CUER","","","NE NOM PA PASS PC PYC","NE NOM PA PASS PC PYC","","","","","","","","","","","","","","","","","","","","","","","",""},
            {"PASS","","","","","","","","","","","","","","","","","","","","","","","","","","","NUM",""},
            {"RESPASS","","","","","","PC","","","CM PASS","","","","","","","","","","","","","","","","","","",""},
            {"NUM","","","","","","","","","","","","","","","","","","","","","","","","","","","","DIGITO",""},
            {"RESNUM","","","","","","PC","","",",","","","","","","","","","","","","","","","","","","NUM",""},
            {"DIGITO","","","","","","","","","","","","","","","","","","","","","","","","","","","0..9",""},
            {"COM","","","","","","","","","","","","","","","","","","","","","","","","","","","","\""}
    };

    ObservableList<String> cadenaCodigo = FXCollections.observableArrayList();
    expresionesRegulares expresiones = new expresionesRegulares();
    Stack<String> pilaProceso = new Stack<String>();

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

    public void  ingresarCadenaAlaPila(String[] lista){
        for (int i = lista.length-1; i >= 0; i--){
            pilaProceso.push(lista[i]);
        }
    }
    public String recivirDato(ObservableList<String> datos, ObservableList<String> datos2){
        String aux = "";
        System.out.println("inicia el proceso");
        for (int i = datos2.size()-1; i>=0; i--){
            cadenaCodigo.add(datos2.get(i));
        }
        for (int i = datos.size()-1; i>=0; i--){
            cadenaCodigo.add(datos.get(i));
        }
        System.out.println(cadenaCodigo);
        inicio();
        return aux;
    }
     public void inicio(){
        boolean bucle = true;
        pilaProceso.push("SA");
         while(bucle){
             String[] gramaticaEntrada;
             boolean encontradoCol = false;
             boolean encontradoFil = false;
             boolean isNTerminal = false;
             String interseccion = "";
             String apuntador1 = pilaProceso.peek();
             String apuntador2 = cadenaCodigo.get(cadenaCodigo.size()-1);
             int posicion = 0;
             int posicion2 = 0;
             for(int i = 0; i < 29; i++){
                 if (tabla [0][i].equals(apuntador2) ){
                     posicion = i;
                     encontradoCol = true;
                     break;
                 }
             }
             for(int j = 0; j < 30; j++){
                 if(tabla[j][0].equals(apuntador1)){
                     posicion2 = j;
                     encontradoFil = true;
                     break;
                 }
             }
             if(encontradoCol && encontradoFil){
                 interseccion = tabla[posicion2][posicion];
                 String[] auxInterseccion = interseccion.split(" ");
                 if(interseccion.isEmpty()){
                     bucle= false;
                     System.out.println("esta vacio xd");
                 }else{
                     for(int j = 0; j < 48; j++){
                         if (tabla[j][0].equals(auxInterseccion[0])) {
                             isNTerminal = true;
                             break;
                         }
                     }
                     if(isNTerminal){
                         pilaProceso.pop();
                         //arreglar porque en caso de que tenga mas de un elemento no lo estaria agregando como diferentes elemntos sino como uno solo
                         gramaticaEntrada = interseccion.split(" ");
                         ingresarCadenaAlaPila(gramaticaEntrada);
                         imprimirPila();
                     }else{
                         pilaProceso.pop();
                         imprimirPila();
                         pilaProceso.push(interseccion);
                         popDatos();
                         imprimirPila();
                     }
                 }
             }else{
                 bucle = false;
             }

         }

     }

///  validar en que posicion se encuentran las palabras, para validadarlas en una funcion por separado

}
