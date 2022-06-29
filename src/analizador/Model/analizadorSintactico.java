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
expresionesRegulares expReg;


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

    public void sap(){
        pilaProceso.pop();
        ingresarCadenaAlaPila(sa);
        imprimirPila();
        inicioP();
    }

    public void inicioP(){
        pilaProceso.pop();
        ingresarCadenaAlaPila(inicio);
        imprimirPila();
            if(pub()){
                if (cla()){
                    boolean result = expresiones.validarLetras(cadenaCodigo.get(cadenaCodigo.size()-1));
                    if (result){
                        popDatos();
                        imprimirPila();
                        cuerpop();
                    }
                }
            }

    }
    public boolean pub(){
        boolean aux = false;
        pilaProceso.pop();
        pilaProceso.push("public");
        if (pilaProceso.peek().equals(cadenaCodigo.get(cadenaCodigo.size()-1))) {
            popDatos();
            imprimirPila();
            aux = true;
        }
        return aux;
    }
    public boolean cla(){
        boolean aux = false;
        pilaProceso.pop();
        pilaProceso.push("class");
        if (pilaProceso.peek().equals(cadenaCodigo.get(cadenaCodigo.size()-1))) {
            popDatos();
            imprimirPila();
            aux = true;
        }
        return aux;
    }


    public void cuerpop(){
        pilaProceso.pop();
        ingresarCadenaAlaPila(cuerpo);
        imprimirPila();

            if(lla()){
                if(atribup()){
                    if(consp()){
                        if(llc()){
                            System.out.println("final de la primera parte de la gramatica");
                        }
                    }
                }
            }



    }

    public boolean lla(){
        boolean aux = false;
        pilaProceso.pop();
        pilaProceso.push("{");
        if (pilaProceso.peek().equals(cadenaCodigo.get(cadenaCodigo.size()-1))) {
            popDatos();
            imprimirPila();
            aux = true;
        }
        return aux;
    }

    public boolean llc(){
        boolean aux = false;
        pilaProceso.pop();
        pilaProceso.push("}");
        if (pilaProceso.peek().equals(cadenaCodigo.get(cadenaCodigo.size()-1))) {
            popDatos();
            imprimirPila();
            aux = true;
        }
        return aux;
    }

    public boolean atribup(){
        boolean aux = false;
        boolean bucle = true;
        pilaProceso.pop();
        ingresarCadenaAlaPila(atribu);
        imprimirPila();
        while(bucle){
            if (priv()){
                aux = false;
                if(tipo()){
                    boolean result = expresiones.validarLetras(cadenaCodigo.get(cadenaCodigo.size()-1));
                    if (result){
                        popDatos();
                        imprimirPila();
                        if (pyc()){
                            pilaProceso.pop();
                            if(pilaProceso.peek().equals("private")){
                                ingresarCadenaAlaPila(atribu);
                                imprimirPila();
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

    public boolean priv(){
        boolean aux = false;
        pilaProceso.pop();
        pilaProceso.push("private");
        if (pilaProceso.peek().equals(cadenaCodigo.get(cadenaCodigo.size()-1))) {
            popDatos();
            imprimirPila();
            aux = true;
        }
        return aux;
    }
    public boolean tipo(){
        boolean aux = false;
        if(cadenaCodigo.get(cadenaCodigo.size()-1).equals("int") || cadenaCodigo.get(cadenaCodigo.size()-1).equals("String") || cadenaCodigo.get(cadenaCodigo.size()-1).equals("float")) {
            popDatos();
            imprimirPila();
            aux = true;
        }
        return aux;
    }

    public boolean pyc(){
        boolean aux = false;
        pilaProceso.pop();
        pilaProceso.push(";");
        if (pilaProceso.peek().equals(cadenaCodigo.get(cadenaCodigo.size()-1))) {
            popDatos();
            imprimirPila();
            aux = true;
        }
        return aux;
    }
    public boolean consp(){
        boolean aux = false;
        pilaProceso.pop();
        ingresarCadenaAlaPila(cons);
        imprimirPila();
        if (pub()){
            boolean result = expresiones.validarLetras(cadenaCodigo.get(cadenaCodigo.size()-1));
            if (result) {
                popDatos();
                imprimirPila();
                if (pa()){
                    if(paramp()){
                        if (pc()){
                           restconsp();
                        }
                    }
                }
            }

        }
        return aux;
    }
    public boolean pa(){
        boolean aux = false;
        pilaProceso.pop();
        pilaProceso.push("(");
        if (pilaProceso.peek().equals(cadenaCodigo.get(cadenaCodigo.size()-1))) {
            popDatos();
            imprimirPila();
            aux = true;
        }
        return aux;
    }
    public boolean pc(){
        boolean aux = false;
        pilaProceso.pop();
        pilaProceso.push(")");
        if (pilaProceso.peek().equals(cadenaCodigo.get(cadenaCodigo.size()-1))) {
            popDatos();
            imprimirPila();
            aux = true;
        }
        return aux;
    }

    public boolean paramp(){
        boolean aux = false;
        boolean bucle = true;
        ingresarCadenaAlaPila(param);
        while (bucle){
            if(tipo()){
                boolean result = expresiones.validarLetras(cadenaCodigo.get(cadenaCodigo.size()-1));
                if (result) {
                    popDatos();
                    imprimirPila();
                    if(pilaProceso.peek().equals(",")){
                        popDatos();
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
        pilaProceso.pop();
        ingresarCadenaAlaPila(restcons);
        if (lla()){
            if(supp()){
                if(refp()){
                    if(llc()){
                        System.out.println("fin del constructor");
                    }
                }
            }
        }
    }

    public boolean supp(){
        boolean aux = false;
        pilaProceso.pop();
        ingresarCadenaAlaPila(sup);
        if (s()) {
           if(pa()){
               if(pc()){
                   if (pyc()){
                       aux = true;
                   }
               }
           }
        }
        return aux;
    }
    public boolean s(){
        boolean aux = false;
        pilaProceso.pop();
        pilaProceso.push("super");
        imprimirPila();
        if (pilaProceso.peek().equals(cadenaCodigo.get(cadenaCodigo.size()-1))){
            popDatos();
            imprimirPila();
            aux = true;
        }
        return aux;
    }

    public boolean refp(){
        boolean aux = false;
        boolean bucle = true;
        pilaProceso.pop();
        ingresarCadenaAlaPila(ref);
        while (bucle){
            aux = false;
            if (th()){
                if(pt()){
                    boolean result = expresiones.validarLetras(cadenaCodigo.get(cadenaCodigo.size()-1));
                    if (result) {
                        if(ig()){
                            boolean result2 = expresiones.validarLetras(cadenaCodigo.get(cadenaCodigo.size()-1));
                            if (result2) {
                                if (pyc()){
                                    pilaProceso.pop();
                                    if(pilaProceso.peek().equals("this")){
                                        ingresarCadenaAlaPila(ref);
                                        imprimirPila();
                                    }else{
                                        bucle = false;
                                    }
                                    aux = true;
                                }
                            }
                        }
                    }
                }
            }
        }
        return aux;
    }
    public boolean th(){
        boolean aux = false;
        pilaProceso.pop();
        pilaProceso.push("this");
        if (pilaProceso.peek().equals(cadenaCodigo.get(cadenaCodigo.size()-1))) {
            popDatos();
            imprimirPila();
            aux = true;
        }
        return aux;
    }
    public boolean ig(){
        boolean aux = false;
        pilaProceso.pop();
        pilaProceso.push("=");
        if (pilaProceso.peek().equals(cadenaCodigo.get(cadenaCodigo.size()-1))) {
            popDatos();
            imprimirPila();
            aux = true;
        }
        return aux;
    }
    public boolean pt(){
        boolean aux = false;
        pilaProceso.pop();
        pilaProceso.push(".");
        if (pilaProceso.peek().equals(cadenaCodigo.get(cadenaCodigo.size()-1))) {
            popDatos();
            imprimirPila();
            aux = true;
        }
        return aux;
    }

    public boolean c(){
        boolean aux = false;
        if (pack()){
            if (defcla()){
                if (lla()){
                    if (ext()){
                        if (mn()){
                            if (pc()){
                                if (lla()){
                                    if (cuer()){
                                        if (llc()){
                                            if (llc()){
                                                aux = true;
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }

        return aux;
    }

    public boolean pack(){
        return imp();
    }

    public boolean imp(){
        boolean aux = false;
        pilaProceso.pop();
        pilaProceso.push("import");
        if (pilaProceso.peek().equals(cadenaCodigo.get(cadenaCodigo.size()-1))) {
            popDatos();
            imprimirPila();
            aux = true;
        }
        return aux;
    }

    public boolean defcla(){
        return ma();
    }

    public boolean ext(){
        return ne();
    }

    public boolean ne(){
        boolean aux = false;
        pilaProceso.pop();
        pilaProceso.push("new");
        if (pilaProceso.peek().equals(cadenaCodigo.get(cadenaCodigo.size()-1))) {
            popDatos();
            imprimirPila();
            aux = true;
        }
        return aux;
    }

    public boolean mn(){
        boolean aux = false;
        if (noac()){
            if (rtp()){
                if (str()){
                    if (ma()){
                        if (cci()){
                            if (ccf()){
                                if (ar()){
                                    aux = true;
                                }
                            }
                        }
                    }
                }
            }
        }

        return aux;
    }

    public boolean ma(){
        boolean aux = false;
        pilaProceso.pop();
        pilaProceso.push("main");
        if (pilaProceso.peek().equals(cadenaCodigo.get(cadenaCodigo.size()-1))) {
            popDatos();
            imprimirPila();
            aux = true;
        }
        return aux;

    }

    public boolean noac(){
        boolean aux = false;
        pilaProceso.pop();
        pilaProceso.push("static");
        if (pilaProceso.peek().equals(cadenaCodigo.get(cadenaCodigo.size()-1))) {
            popDatos();
            imprimirPila();
            aux = true;
        }
        return aux;
    }

    public boolean rtp(){
        boolean aux = false;
        pilaProceso.pop();
        pilaProceso.push("void");
        if (pilaProceso.peek().equals(cadenaCodigo.get(cadenaCodigo.size()-1))) {
            popDatos();
            imprimirPila();
            aux = true;
        }
        return aux;

    }

    public boolean str(){
        boolean aux = false;
        pilaProceso.pop();
        pilaProceso.push("String");
        if (pilaProceso.peek().equals(cadenaCodigo.get(cadenaCodigo.size()-1))) {
            popDatos();
            imprimirPila();
            aux = true;
        }
        return aux;

    }

    public boolean cci(){
        boolean aux = false;
        pilaProceso.pop();
        pilaProceso.push("[");
        if (pilaProceso.peek().equals(cadenaCodigo.get(cadenaCodigo.size()-1))) {
            popDatos();
            imprimirPila();
            aux = true;
        }
        return aux;
    }

    public boolean ccf(){
        boolean aux = false;
        pilaProceso.pop();
        pilaProceso.push("]");
        if (pilaProceso.peek().equals(cadenaCodigo.get(cadenaCodigo.size()-1))) {
            popDatos();
            imprimirPila();
            aux = true;
        }
        return aux;
    }

    public boolean ar(){
        boolean aux = false;
        pilaProceso.pop();
        pilaProceso.push("args");
        if (pilaProceso.peek().equals(cadenaCodigo.get(cadenaCodigo.size()-1))) {
            popDatos();
            imprimirPila();
            aux = true;
        }
        return aux;
    }

    public boolean cuer(){
        boolean aux = false;
        if (dat()){
            if (cuer()){
                aux = true;
            }
        }

        return aux;
    }

    public boolean dat(){
        boolean aux = false;
        if (num()){
            if (resnum()){
                aux = true;
            }
        }else {
            if (com()){
                if (expReg.validarLetras(pilaProceso.peek())){
                    if (com()){
                        aux = true;
                    }
                }
            }
        }

        return aux;
    }

    public boolean num(){
        boolean aux = false;
        if (expReg.validarNumeros(cadenaCodigo.get(cadenaCodigo.size()-1))) {
            imprimirPila();
            aux = true;
        }
        return aux;
    }

    public boolean resnum(){
        boolean aux = false;
        if (expReg.validarNumeros(cadenaCodigo.get(cadenaCodigo.size()-1))){
            resnum();
            aux = true;
        }
        num();
        return aux;
    }

    public boolean com(){
        boolean aux = false;
        pilaProceso.pop();
        pilaProceso.push("\"");
        if (pilaProceso.peek().equals(cadenaCodigo.get(cadenaCodigo.size()-1))) {
            popDatos();
            imprimirPila();
            aux = true;
        }
        return aux;
    }
}
