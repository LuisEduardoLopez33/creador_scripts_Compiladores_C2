package analizador.Model;


import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import java.util.Arrays;
import java.util.Stack;

public class analizadorSintactico {
ObservableList<String> cadenaCodigo = FXCollections.observableArrayList();
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


Stack pilaProceso = new Stack();

    public boolean recivirDato(ObservableList datos){
        System.out.println("inicia el proceso");
        cadenaCodigo = datos;
        pilaProceso.push("S");
        if (pilaProceso.size()==1){

        }
        return true;
    }

    public void s(){
        pilaProceso.pop();
        for (int i = sa.length-1; i >= 0; i--){
            pilaProceso.push(sa[i]);
        }
        System.out.println("evaluar: "+ cadenaCodigo.get(cadenaCodigo.size()-1));
    }
}
