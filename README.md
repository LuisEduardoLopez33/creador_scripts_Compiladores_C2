"# creador_scripts_Compiladores_C2" 
public class Product {
    private int idProducto ;
    private String nombreProducto ;
    private int precioProducto ;
    private String categoriaProducto ;
    private int existencia ;
    public Product ( int idProducto , String nombreProducto , int precioProducto , String categoriaProducto , int existencia ) {
        super ( ) ;
        this . idProducto = idProducto ;
        this . nombreProducto = nombreProducto ;
        this . precioProducto = precioProducto ;
        this . categoriaProducto = categoriaProducto ;
        this . existencia = existencia ;
    } }
    
    // otro
    --------------------------------------------------------------------
    import PuntoVenta . Model . Product ;
    
    
       public class main {
    	
        public static void main ( String [ ] args ) {
    
    		new Product ( 1 , " pan " , 12 , " panes " , 69 ) ;
    	} 
    }

