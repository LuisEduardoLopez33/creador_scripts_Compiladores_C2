package analizador.Controller;

import analizador.Model.analizadorLexico;
import analizador.Model.analizadorSintacticoConTabla;
import analizador.Model.generadorScript;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.paint.Color;

import java.util.StringTokenizer;

public class controllerInicio {
    ObservableList<String> entrada = FXCollections.observableArrayList();
    ObservableList<String> entrada2 = FXCollections.observableArrayList();
    analizadorSintacticoConTabla analizadorS;
    generadorScript gen = new generadorScript();
    analizadorLexico lexico = new analizadorLexico();

    @FXML
    private TextArea classMain;

    @FXML
    private TextArea classObjet;

    @FXML
    private Label mensaje;

    @FXML
    private TextArea resultado;

    String resltadoObtenido;

    public controllerInicio() {
    }

    @FXML
    void iniciarAnalizis(ActionEvent event) {
        mensaje.setText("");
        resultado.setText("");
        entrada.clear();
        entrada2.clear();

        StringTokenizer res = new StringTokenizer(classObjet.getText());
        if(classMain.getLength() != 0 && classObjet.getLength() != 0){
            while (res.hasMoreTokens()) {
                //System.out.println(st.nextToken());
                entrada.add(res.nextToken());
            }

            StringTokenizer st = new StringTokenizer(classMain.getText());
            while (st.hasMoreTokens()) {
                //System.out.println(st.nextToken());
                entrada2.add(st.nextToken());
            }

            if(lexico.inicio(entrada, entrada2)){
                analizadorS = new analizadorSintacticoConTabla();
                resltadoObtenido = analizadorS.recivirDato(entrada, entrada2);
            }else{
                System.out.println("ingresaste algunos simbolos que no son validos");
            }



            insertMensaje();
        }else {
            mensaje.setText("Los Cuadros no Tienen que estar Vacio");
        }

    }

    public void ImprimirArray(){
        for (String s : entrada) {
            System.out.println(s);
        }
    }

    public void insertMensaje(){
//        String[] aux = resltadoObtenido.split(",");

        if ("error".equals(resltadoObtenido.split("\\.")[0])){
            System.out.println("Esta en lo cierto: " + resltadoObtenido.split("\\.")[0]);
            mensaje.setText(resltadoObtenido.split("\\.")[1]);
            mensaje.setTextFill(Color.RED);
            System.out.println(resltadoObtenido);
            resultado.setText(resltadoObtenido.split("\\.")[2]);
        }else {
            if (resltadoObtenido.split("\\.")[0].equals("bien")) {
                mensaje.setText("Todo Resulto bien");
                mensaje.setTextFill(Color.GREEN);
                resultado.setText(resltadoObtenido);
            }else {
                mensaje.setText("Error");
                mensaje.setTextFill(Color.RED);
                resultado.setText("Ningun Dato Encontrado");
            }
        }



    }


}
