package analizador.Model;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class expresionesRegulares {
    public boolean validarLetras(String dato){
        Pattern pat = Pattern.compile("[A-Za-z]+");
        Matcher mat = pat.matcher(dato);

        return mat.matches();
    }

    public boolean validarPalabrasRecervadas(String dato){
        Pattern pat = Pattern.compile("^(import|public|main|class|new|String|void|args|this|private|super|int)$");
        Matcher mat = pat.matcher(dato);

        return mat.matches();
    }

    public boolean validarDelimitadores(String dato){
        Pattern pat = Pattern.compile("^(\\(|\\]|\\[|\\}|\\{|\\)|;)$");
        Matcher mat = pat.matcher(dato);

        return mat.matches();
    }

    public boolean validarSignos(String dato){
        Pattern pat = Pattern.compile("^(\\.|\"|,|=)$");
        Matcher mat = pat.matcher(dato);

        return mat.matches();
    }

    public boolean validarNumeros(String dato){
        Pattern pat = Pattern.compile("[0-9]+");
        Matcher mat = pat.matcher(dato);

        return mat.matches();
    }
}

