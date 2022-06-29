package analizador.Model;

public class Gramatica {

    expresionesRegulares expReg;
    String dat;

    public void c(){
        pack();
        defcla();
        ext();
        mn();
        cuer();
    }

    public void pack(){
        imp();
    }

    public void imp(){
        if ("import".equals(dat)){
            System.out.println("True");
        }
    }

    public void defcla(){
        ma();
    }

    public void ext(){
        ne();
    }

    public void ne(){
        if ("new".equals(dat)){
            System.out.println("True");
        }
    }

    public void mn(){
        noac();
        rtp();
        str();
        ma();
        cci();
        ccf();
        ar();
    }

    public void ma(){
        if("main".equals(dat)){
            System.out.println("True");
        }

    }

    public void noac(){
        if ("static".equals(dat)){
            System.out.println("True");
        }
    }

    public void rtp(){
        if ("void".equals(dat)){
            System.out.println("True");
        }

    }

    public void str(){
        if ("String".equals(dat)){
            System.out.println("True");
        }

    }

    public void cci(){
        if ("[".equals(dat))
            System.out.println("True");

    }

    public void ccf(){
        if ("]".equals(dat))
            System.out.println("true");

    }

    public void ar(){
        if ("args".equals(dat))
            System.out.println("True");
    }

    public void cuer(){
        dat();
        cuer();
    }

    public void dat(){
        num();
        resnum();
    }

    public void num(){
        if (expReg.validarNumeros(dat))
            System.out.println("True");
    }

    public void resnum(){
        num();
        resnum();
    }

    public void com(){
        if ("\"".equals(dat))
            System.out.println("True");
    }



}
