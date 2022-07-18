package analizador.Model;
import javafx.collections.ObservableList;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;

public class generadorScript {
    public void generator(ObservableList<String> tipo, ObservableList<String> variableNom){
        try {
            String ruta = "scripts/tabla.txt";
            StringBuilder contenido = new StringBuilder("create table nombre (");
            contenido.append("\n");
            for (int i = 0; i< tipo.size(); i++){
                if(tipo.get(i).equals("String")){
                    contenido.append(variableNom.get(i)).append(" ").append("varchar(200),").append("\n");
                }else{
                    contenido.append(variableNom.get(i)).append(" ").append(tipo.get(i)).append(",").append("\n");
                }
            }
            contenido.append("primary key(").append(variableNom.get(0)).append("));");
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
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
