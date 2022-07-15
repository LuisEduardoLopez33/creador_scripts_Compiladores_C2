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
            {"RESTPALABRA","","","PALABRA RESTPALABRA","PALABRA RESTPALABRA","∑","∑","∑","","∑","","","","","","∑","","","∑","","","","","","","","","",""},
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
            {"RESTA","∑","","","","","","","","","","ATRIBU","","","","","","","","","","","","","","","","",""},
            {"CONS","PUB NOM PA PARAM PC RESTCONST","","","","","","","","","","","","","","","","","","","","","","","","","","",""},
            {"PARAM","","","","","","","","","","","","TIPO NOM RESTPARAM","TIPO NOM RESTPARAM","TIPO NOM RESTPARAM","","","","","","","","","","","","","",""},
            {"RESTPARAM","","","","","","∑","","","CM PARAM","","","","","","","","","","","","","","","","","","",""},
            {"RESTCONST","","","","","","","LLA SUP REF LLC","","","","","","","","","","","","","","","","","","","","",""},
            {"SUP","","","","","","","","","","","","","","","","S PA PC PYC","","","","","","","","","","","",""},
            {"S","","","","","","","","","","","","","","","","super","","","","","","","","","","","",""},
            {"REF","","","","","","","","","","","","","","","","","TH PT NOM IG NOM PYC ULTIMOREST","","","","","","","","","","",""},
            {"TH","","","","","","","","","","","","","","","","","this","","","","","","","","","","",""},
            {"IG","","","","","","","","","","","","","","","","","","=","","","","","","","","","",""},
            {"ULTIMOREST","","","","","","","","∑","","","","","","","","","REF","","","","","","","","","","",""},
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
            {"CUER","","","","","","","","","","","","","","","","","","","","NE NOM PA PASS PC PYC","","","","","","","",""},
            {"PASS","","","","","","","","","","","","","","","","","","","","","","","","","","","NUM RESPASS","COM NOM COM RESPASS"},
            {"RESPASS","","","","","","∑","","","CM PASS","","","","","","","","","","","","","","","","","","",""},
            {"NUM","","","","","","","","","","","","","","","","","","","","","","","","","","","","DIGITO",""},
            {"RESNUM","","","","","","∑","","","∑","","","","","","","","","","","","","","","","","","NUM",""},
            {"DIGITO","","","","","","","","","","","","","","","","","","","","","","","","","","","0..9",""},
            {"COM","","","","","","","","","","","","","","","","","","","","","","","","","","","","\""}
    };

    ObservableList<String> cadenaCodigo = FXCollections.observableArrayList();
    expresionesRegulares expresiones = new expresionesRegulares();
    Stack<String> pilaProceso = new Stack<String>();
    public void imprimirPila(){
        //System.out.println("elementos en pila");
        System.out.println(Arrays.asList(pilaProceso));
    }
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

             for(int j = 0; j < 50; j++){
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
                     for(int j = 0; j < 50; j++){
                         if (tabla[j][0].equals(auxInterseccion[0])) {
                             isNTerminal = true;
                             break;
                         }
                     }

                     if(isNTerminal){
                         pilaProceso.pop();
                         System.out.println("es no terminal");
                         gramaticaEntrada = interseccion.split(" ");
                         ingresarCadenaAlaPila(gramaticaEntrada);
                         imprimirPila();
                     }else{
                         System.out.println("es terminal");
                         gramaticaEntrada = interseccion.split(" ");
                         String vacio = "∑";
                         if(vacio.equals(gramaticaEntrada[0])){
                             pilaProceso.pop();
                         }else{
                             System.out.println(cadenaCodigo.get(cadenaCodigo.size()-1));
                             imprimirPila();
                             popDatos();
                             imprimirPila();

                         }

                     }
                 }
             }else{
                 if (!encontradoCol) {
                     if(!palabra()){
                         if(!noum()){
                             bucle = false;
                             System.out.println("error en la cadena de entrada");
                             System.out.println(" ultimo en cadena de entrada: " + cadenaCodigo.get(cadenaCodigo.size()-1));
                         }
                     }
                 }else {
                     bucle = false;
                     System.out.println("Ultimo en la cadena de Entrada: " + cadenaCodigo.get(cadenaCodigo.size()-1));
                 }

             }

         }

     }
     public boolean palabra(){
        boolean aux =  false;
        if(expresiones.validarLetras(cadenaCodigo.get(cadenaCodigo.size()-1)) && pilaProceso.peek().equals("NOM")){
            popDatos();
            imprimirPila();
            aux = true;
        }
        return aux;
     }
     public boolean noum(){
        boolean aux =  false;
        if(expresiones.validarNumeros(cadenaCodigo.get(cadenaCodigo.size()-1)) && pilaProceso.peek().equals("PASS")){
            pilaProceso.pop();
            String[] gramaticaEntrada = tabla[44][27].split(" ");
            ingresarCadenaAlaPila(gramaticaEntrada);
            imprimirPila();
            aux =  true;
        }
         if(expresiones.validarNumeros(cadenaCodigo.get(cadenaCodigo.size()-1)) && pilaProceso.peek().equals("NUM")){
             popDatos();
             imprimirPila();
             aux =  true;
         }
        return aux;
     }

}
