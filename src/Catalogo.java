import java.util.ArrayList;
import java.util.Iterator;

public class Catalogo {
    private ArrayList<Producto> productos;

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
}
