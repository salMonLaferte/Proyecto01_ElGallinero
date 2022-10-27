import java.util.Iterator;
/** Clase que representa el catalogo Proxy */
public class CatalogoProxy implements CatalogoInterface{
    /** Catalogo Real */
    private Catalogo catalogoReal = Catalogo.obtenerInstanciaUnica();

    /**
     * Metodo que dado un iterador, imprime el catalogo.
     */
    @Override
    public void ImprimirCatalogo() {
        Iterator<Producto> it = catalogoReal.obtenerIterador();
        while(it.hasNext()){
            System.out.println(it.next());
        }
    }
    
    /**
     * Metodo que dado un indice se obtiene la copia del 
     * producto contenido en dicho indice.
     * @param index Indice del producto
     * @return Producto copia
     */
    @Override
    public Producto obtenerProducto(int index) {
        Producto producto = catalogoReal.obtenerProducto(index);
        Producto copia = new Producto(producto.codigoDeBarras, producto.nombre , producto.departamento, producto.precio);
        return copia;
    }
}
