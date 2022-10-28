/** Clase que representa la interfaz de catalogo */
public interface CatalogoInterface {
    
    /**
     * Metodo que imprime el catalogo 
     */
    public void ImprimirCatalogo();
    
    /**
     * Metodo que dado un indice se obtiene el producto
     * contenido en dicho indice.
     * @param index Indice del producto
     * @return Producto
     */
    public Producto obtenerProducto(int index);

    /**
     * Regresa el la cantidad de productos que contiene el catalogo.
     */
    public int obtenerTamano();
    
}
