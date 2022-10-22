import java.util.ArrayList;
import java.util.Iterator;

public class catalogo {
    private ArrayList<producto> productos;

    public catalogo(boolean crearloVacio){
        productos = new ArrayList<producto>();

    }
    public  void agregarProducto(producto producto1){
        for(producto p : productos){
            if(p.obtenerCodigo() == producto1.obtenerCodigo()){
                return;
            }
            productos.add(producto1);
        }
    }
    public Iterator<producto> obtenerIterador(){
        return productos.iterator();
    }
}
