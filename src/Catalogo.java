import java.util.ArrayList;
import java.util.Iterator;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

public class Catalogo implements CatalogoInterface{

    private ArrayList<Producto> productos;
    private static Catalogo instanciaUnica;

    private Catalogo(){
        productos = new ArrayList<Producto>();
    }

    public static Catalogo obtenerInstanciaUnica(){
        if(instanciaUnica == null){
            instanciaUnica = new Catalogo();
            leerCatalogoDeArchivo();
        }
        return instanciaUnica;
    }

    public  Iterator<Producto> obtenerIterador(){
        return productos.iterator();
    }
     
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
                    System.out.println(producto);
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

    @Override
    public void ImprimirCatalogo() {
        for (Producto producto : productos) {
            System.out.println(producto);
        }
    }

    @Override
    public Producto obtenerProducto(int index) {
        return productos.get(index);
    }
}
