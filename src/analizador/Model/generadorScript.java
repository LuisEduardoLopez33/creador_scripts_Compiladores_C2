package analizador.Model;
import javafx.collections.ObservableList;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;

public class generadorScript {
    public String generator(ObservableList<String> tipo, ObservableList<String> variableNom, ObservableList<String> valores, ObservableList<String> instancia){
        String aux = "";
        try {
            String ruta = "scripts/tabla.txt";
            StringBuilder contenido = new StringBuilder("create table ");
            contenido.append(instancia.get(0)).append(" (").append("\n");
            for (int i = 0; i< tipo.size(); i++){
                if(tipo.get(i).equals("String")){
                    contenido.append(variableNom.get(i)).append(" ").append("varchar(200),").append("\n");
                }else{
                    contenido.append(variableNom.get(i)).append(" ").append(tipo.get(i)).append(",").append("\n");
                }
            }
            contenido.append("primary key(").append(variableNom.get(0)).append("));");
            contenido.append("\n");
            contenido.append("insert into ").append(instancia.get(0)).append(" (");
            for (String s : variableNom) {
                contenido.append(s).append(", ");
            }
            contenido.append(") values( ");
            for(String s: valores){
                contenido.append("'").append(s).append("'").append(", ");
            }
            contenido.append(");");

            File file = new File(ruta);
            // Si el archivo no existe es creado
            if (!file.exists()) {
                final var newFile = file.createNewFile();
            }
           
            FileWriter fw = new FileWriter(file);
            BufferedWriter bw = new BufferedWriter(fw);
            bw.write(contenido.toString());
            bw.close();
            System.out.println(contenido);
            aux = contenido.toString();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return aux;
    }

}
