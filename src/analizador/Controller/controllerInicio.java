package analizador.Controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextArea;

import java.util.StringTokenizer;

public class controllerInicio {
    ObservableList<String> entrada = FXCollections.observableArrayList();

    @FXML
    private TextArea classMain;

    @FXML
    private TextArea classObjet;

    @FXML
    void iniciarAnalizis(ActionEvent event) {

        StringTokenizer res = new StringTokenizer(classObjet.getText());
        while (res.hasMoreTokens()) {
            //System.out.println(st.nextToken());
            entrada.add(res.nextToken());
        }

        StringTokenizer st = new StringTokenizer(classMain.getText());
        while (st.hasMoreTokens()) {
            //System.out.println(st.nextToken());
            entrada.add(st.nextToken());
        }


        ImprimirArray();
    }

    public void ImprimirArray(){
        for (String s : entrada) {
            System.out.println(s);
        }
    }

}
