package analizador.Model;


import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import javax.print.attribute.standard.PrinterMessageFromOperator;
import java.security.SecureRandom;
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
    String[] pack = {"IMP","NOM","PT","NOM","PT","NOM", "PYC"};
    String[] defcla = {"PUB","CLA","MA"};
    String[] ext ={"NOM","NOM","IG","NE","NOM","PA","PC","PYC"};
    String[] mn ={"PUB","NOAC","RTP","MA","PA","STR","CCI","CCF","AR"};
    String[] cuer ={"NOM","PT","NOM","PA","DAT","PC","PYC","RESTCUER"};
    String[] dat1 = {"NUM", "RESNUM" };
    String[] dat2 = {"COMA", "NOM", "COMC"};
    expresionesRegulares expReg;
    String ultimo = "";
    boolean str = true;


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
        pilaProceso.push("S");
        if (pilaProceso.size()==1){
            if (sap()){
                aux = "true, ";
            }else {
                if (str) {
                    aux = "false, " + cadenaCodigo.get(cadenaCodigo.size() - 1);
                }else {
                    aux = "false, "+ pilaProceso.peek();
                }
            }

        }
        return aux;
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

    public boolean sap(){
        boolean aux = false;
        pilaProceso.pop();
        ingresarCadenaAlaPila(sa);
        imprimirPila();
//        inicioP();
        if (inicioP()) {
            //pilaProceso.pop();
            if (c()) {
                System.out.println("termino proceso");
                aux = true;
            }
        }
        return aux;
    }

    public boolean inicioP(){
        boolean aux = false;
        pilaProceso.pop();
        ingresarCadenaAlaPila(inicio);
        imprimirPila();
        if(pub()){
            if (cla()){
                boolean result = expresiones.validarLetras(cadenaCodigo.get(cadenaCodigo.size()-1));
                if (result){
                    popDatos();
                    imprimirPila();
                    if(cuerpop()){
                        aux = true;
                    }
                }
            }
        }
        return aux;
    }
    public boolean pub(){
        boolean aux = false;
        pilaProceso.pop();
        pilaProceso.push("public");
        imprimirPila();
        System.out.println(cadenaCodigo.get(cadenaCodigo.size()-1));
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


    public boolean cuerpop(){
        boolean aux = false;
        pilaProceso.pop();
        ingresarCadenaAlaPila(cuerpo);
        imprimirPila();
        if(lla()){
            if(atribup()){
                if(consp()){
                    if(llc()){
                        aux = true;
                    }
                }
            }
        }


        return aux;
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
        String priva = "private";
        pilaProceso.pop();
        ingresarCadenaAlaPila(atribu);
        imprimirPila();
        while(bucle){
            bucle = false;
            aux = false;
            if (priv()){
                if(tipo()){
                    boolean result = expresiones.validarLetras(cadenaCodigo.get(cadenaCodigo.size()-1));
                    if (result){
                        popDatos();
                        imprimirPila();
                        if (pyc()){
                            pilaProceso.pop();
                            imprimirPila();
                            if(cadenaCodigo.get(cadenaCodigo.size()-1).equals(priva)){
                                ingresarCadenaAlaPila(atribu);
                                imprimirPila();
                                bucle=true;
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
        imprimirPila();
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
                            if(restconsp()){
                                aux = true;
                            }
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
        pilaProceso.pop();
        ingresarCadenaAlaPila(param);
        while (bucle){
            bucle = false;
            if(tipo()){
                boolean result = expresiones.validarLetras(cadenaCodigo.get(cadenaCodigo.size()-1));
                if (result) {
                    popDatos();
                    imprimirPila();
                    if(cadenaCodigo.get(cadenaCodigo.size()-1).equals(",")){
                        popDatos();
                        ingresarCadenaAlaPila(param);
                        bucle=true;
                    }else{
                        pilaProceso.pop();
                        bucle = false;
                        aux = true;
                    }
                }
            }
        }

        return aux;
    }

    public boolean  restconsp(){
        boolean aux = false;
        pilaProceso.pop();
        ingresarCadenaAlaPila(restcons);
        if (lla()){
            if(supp()){
                if(refp()){
                    if(llc()){
                        System.out.println("fin del constructor");
                        aux = true;
                    }
                }
            }
        }
        return aux;
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
        String thi = "this";
        pilaProceso.pop();
        ingresarCadenaAlaPila(ref);
        while (bucle){
            aux = false;
            bucle = false;
            imprimirPila();
            if (th()){
                if(pt()){
                    boolean result = expresiones.validarLetras(cadenaCodigo.get(cadenaCodigo.size()-1));
                    if (result) {
                        popDatos();
                        if(ig()){
                            boolean result2 = expresiones.validarLetras(cadenaCodigo.get(cadenaCodigo.size()-1));
                            if (result2) {
                                popDatos();
                                if (pyc()){
                                    pilaProceso.pop();
                                    if(cadenaCodigo.get(cadenaCodigo.size()-1).equals(thi)){
                                        bucle = true;
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

    public boolean c() {
        pilaProceso.pop();
        //cadenaCodigo.remove(cadenaCodigo.size()-1);
        ingresarCadenaAlaPila(c);
        imprimirPila();
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
        boolean aux = false;
        pilaProceso.pop();
        ingresarCadenaAlaPila(pack);
        imprimirPila();
        if(imp()){
            boolean result = expresiones.validarLetras(cadenaCodigo.get(cadenaCodigo.size()-1));
            if (result) {
                popDatos();
                if(pt()){
                    boolean result2 = expresiones.validarLetras(cadenaCodigo.get(cadenaCodigo.size()-1));
                    if (result2) {
                        popDatos();
                        if(pt()){
                            boolean result3 = expresiones.validarLetras(cadenaCodigo.get(cadenaCodigo.size()-1));
                            if (result3) {
                                popDatos();
                                if(pyc()){
                                    aux = true;
                                }
                            }
                        }
                    }
                }
            }
        }
        return  aux;
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
        boolean aux = false;
        pilaProceso.pop();
        ingresarCadenaAlaPila(defcla);
        if(pub()){
            if (cla()){
                if(ma())
                    aux = true;
            }
        }
        return aux;
    }

    public boolean ext(){
        boolean aux = false;
        pilaProceso.pop();
        ingresarCadenaAlaPila(ext);
        boolean result = expresiones.validarLetras(cadenaCodigo.get(cadenaCodigo.size()-1));
        if (result) {
            popDatos();
            boolean result2 = expresiones.validarLetras(cadenaCodigo.get(cadenaCodigo.size()-1));
            if (result2) {
                popDatos();
                if (ig()){
                    if(ne()){
                        boolean result3 = expresiones.validarLetras(cadenaCodigo.get(cadenaCodigo.size()-1));
                        if (result3) {
                            popDatos();
                            if(pa()){
                                if (pc()){
                                    if (pyc())
                                        aux = true;
                                }
                            }
                        }
                    }
                }
            }
        }
        return  aux;
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
        pilaProceso.pop();
        ingresarCadenaAlaPila(mn);
        if(pub()){
            if (noac()){
                if (rtp()){
                    if (ma()){
                        if(pa()){
                            if (str()){
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
        boolean bucle = true;
        pilaProceso.pop();
        ingresarCadenaAlaPila(cuer);
        while (bucle){
            boolean result = expresiones.validarLetras(cadenaCodigo.get(cadenaCodigo.size()-1));
            bucle=false;
            if (result) {
                popDatos();
                if(pt()){
                    boolean result2 = expresiones.validarLetras(cadenaCodigo.get(cadenaCodigo.size()-1));
                    if (result2) {
                        popDatos();
                        if(pa()){
                            if(dat()){
                                if(pc()){
                                    if(pyc()){
                                        pilaProceso.pop();
                                        boolean result3 = expresiones.validarLetras(cadenaCodigo.get(cadenaCodigo.size()-1));
                                        if (result3) {
                                            bucle = true;
                                            ingresarCadenaAlaPila(cuer);
                                        }else{
                                            bucle = false;
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
        return aux;
    }

    public boolean dat(){
        boolean aux = false;
        boolean bucle = true;
        pilaProceso.pop();
        if (isnum()){
            ingresarCadenaAlaPila(dat1);
            while(bucle){
                if (num()){
                    pilaProceso.pop();
                    if(isnum()){
                        ingresarCadenaAlaPila(dat1);
                    }
                }  else
                    bucle = false;
                aux = true;
            }

        }else{
            ingresarCadenaAlaPila(dat2);
            if(coma()){
                boolean result = expresiones.validarLetras(cadenaCodigo.get(cadenaCodigo.size()-1));
                if (result) {
                    popDatos();
                    if(coma()){
                        aux = true;
                    }
                }
            }
        }

        return aux;
    }

    public boolean isnum(){
        boolean aux = false;
        if (expresiones.validarNumeros(cadenaCodigo.get(cadenaCodigo.size()-1))) {
            imprimirPila();
            aux = true;
        }
        return aux;
    }

    public boolean num(){
        boolean aux = false;
        if (expresiones.validarNumeros(cadenaCodigo.get(cadenaCodigo.size()-1))) {
            popDatos();
            imprimirPila();
            aux = true;
        }
        return aux;
    }


    public boolean coma(){
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
