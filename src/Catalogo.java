import java.util.ArrayList;
import java.util.Iterator;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

public class Catalogo {

    protected ArrayList<Producto> productos;

    public Catalogo(boolean crearloVacio){
        productos = new ArrayList<Producto>();

    }
    public  void agregarProducto(Producto producto1){
        for(Producto p : productos){
            if(p.obtenerCodigo() == producto1.obtenerCodigo()){
                return;
            }
            productos.add(producto1);
        }
    }
    public Iterator<Producto> obtenerIterador(){
        return productos.iterator();
    }
     
    protected void obtenerTextos(){
        File archivo = null;
        FileReader fileReader = null;
        BufferedReader bufferedReader = null;
        try{
            archivo = new File("catalogo.txt");
            fileReader = new FileReader(archivo);
            bufferedReader = new BufferedReader(fileReader);
            String linea;
            while( (linea = bufferedReader.readLine() ) != null){
                String[] keyVal = linea.split("--");
                if(keyVal.length == 4){
                    Producto producto = new Producto(Long.parseLong(keyVal[0]), keyVal[1], keyVal[2], Double.parseDouble(keyVal[3]));
                    productos.add(producto);
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
}
