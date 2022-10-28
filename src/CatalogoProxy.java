import java.util.Iterator;

public class CatalogoProxy implements CatalogoInterface{

    private Catalogo catalogoReal = Catalogo.obtenerInstanciaUnica();

    @Override
    public void ImprimirCatalogo() {
        Iterator<Producto> it = catalogoReal.obtenerIterador();
        while(it.hasNext()){
            System.out.println(it.next());
        }
    }

    @Override
    public Producto obtenerProducto(int index) {
        Producto producto = catalogoReal.obtenerProducto(index);
        Producto copia = new Producto(producto.codigoDeBarras, producto.nombre , producto.departamento, producto.precio);
        return copia;
    }
}
