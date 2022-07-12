package analizador.Model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

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
            {"PASS","","","","","","","","","","","","","","","","","","","","","","","","","","","",""},
            {"RESPASS","","","","","","","","","CM PASS","","","","","","","","","","","","","","","","","","",""},
            {"NUM","","","","","","","","","","","","","","","","","","","","","","","","","","","",""},
            {"RESNUM","","","","","","","","","","","","","","","","","","","","","","","","","","","",""},
            {"DIGITO","","","","","","","","","","","","","","","","","","","","","","","","","","","0..9",""},
            {"COM","","","","","","","","","","","","","","","","","","","","","","","","","","","","\""}
    };

    ObservableList<String> cadenaCodigo = FXCollections.observableArrayList();
    expresionesRegulares expresiones = new expresionesRegulares();
    Stack<String> pilaProceso = new Stack<String>();

    public String recivirDato(ObservableList<String> datos, ObservableList<String> datos2){
        String aux = "";
        System.out.println("inicia el proceso");
        for (int i = datos2.size()-1; i>=0; i--){
            cadenaCodigo.add(datos2.get(i));
        }
        for (int i = datos.size()-1; i>=0; i--){
            cadenaCodigo.add(datos.get(i));
        }
        return aux;
    }
}
