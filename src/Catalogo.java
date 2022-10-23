import java.util.ArrayList;
import java.util.Iterator;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

public class Catalogo {

    public static ArrayList<Producto> productos;

    public Catalogo(){
        this.productos = new ArrayList<Producto>();
    }

    public Iterator<Producto> obtenerIterador(){
        return productos.iterator();
    }
     
    public static ArrayList<Producto> obtenerTextos(){
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
        return productos;
    }

    public Producto get(int i) {
        Producto producto = productos.get(i);
        return producto;
    }

}
