/** Clase que representa la interfaz de catalogo */
public interface CatalogoInterface {
    
    /**
     * Metodo que imprime el catalogo original
     * o el catalogo Proxy.
     */
    public void ImprimirCatalogo();
    
    /**
     * Metodo que dado un indice se obtiene el producto
     * contenido en dicho indice.
     * @param index Indice del producto
     * @return Producto
     */
    public Producto obtenerProducto(int index);
    
}
