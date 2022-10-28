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
        int count = 1;
        while(it.hasNext()){
            System.out.println(count + ". " + it.next());
            count++;
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
        Producto producto = catalogoReal.obtenerProducto(index-1);
        Producto copia = new Producto(producto.codigoDeBarras, producto.nombre , producto.departamento, producto.precio);
        return copia;
    }

    public int obtenerTamano(){
        return catalogoReal.obtenerTamano();
    }

}
