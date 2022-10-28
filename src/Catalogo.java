import java.util.ArrayList;
import java.util.Iterator;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
/** Clase que representa la clase original del Catalogo  */

public class Catalogo implements CatalogoInterface{

    /** Array que guardara nuestros productos leidos por el txt */
    private ArrayList<Producto> productos;
    /** Catalogo original */
    private static Catalogo instanciaUnica;

    /**
     * Metodo constructor de la clase Catalogo
     */
    private Catalogo(){
        productos = new ArrayList<Producto>();
    }

    /**
     * Metodo estatico que manda a llamar el metodo
     * leerCatalogoDeArchivo(), regresando el catalogo
     * original.
     * @return Catalogo original
     */
    public static Catalogo obtenerInstanciaUnica(){
        if(instanciaUnica == null){
            instanciaUnica = new Catalogo();
            leerCatalogoDeArchivo();
        }
        return instanciaUnica;
    }

    /**
     * Metodo que obtiene el iterador del ArrayList que 
     * contiene los productos.
     * @return Iterador de lista.
     */
    public  Iterator<Producto> obtenerIterador(){
        return productos.iterator();
    }

    /**
     * Metodo que permite leer el archivo txt donde se 
     * encuentra contenido el catalogo original y asi mismo
     * crear los productos que se irán agregando al ArrayList.
     */
    private static void leerCatalogoDeArchivo(){
        File archivo = null;
        FileReader fileReader = null;
        BufferedReader bufferedReader = null;
        try{
            archivo = new File("./catalogo/catalogo.txt");
            fileReader = new FileReader(archivo);
            bufferedReader = new BufferedReader(fileReader);
            String linea;
            while( (linea = bufferedReader.readLine() ) != null){
                String[] keyVal = linea.split("--");
                if(keyVal.length == 4){
                    Producto producto = new Producto(Long.parseLong(keyVal[0]), keyVal[1], keyVal[2], Double.parseDouble(keyVal[3])); 
                    instanciaUnica.productos.add(producto);
                }
            }
        }catch(Exception e){
            e.printStackTrace();
        }finally{
            try{
                if(null != fileReader){
                    fileReader.close();
                }
            }catch(Exception e2){
                e2.printStackTrace();
            }
        }
    }

    /**
     * Metodo que imprime el ArrayList que representa el 
     * catalogo original.
     */
    @Override
    public void ImprimirCatalogo() {
        for (Producto producto : productos) {
            System.out.println(producto);
        }
    }
    
    /**
     * {@inheritDoc}}
     */
    @Override
    public Producto obtenerProducto(int index) {
        return productos.get(index);
    }

    @Override
    public int obtenerTamano() {
        return productos.size();
    }
}
