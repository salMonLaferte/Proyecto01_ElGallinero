/**Clase que representa un producto del catalogo */
public class Producto{
    /**Codigo de barras que caracteriza cada producto */
    protected long codigoDeBarras;
    /** Nombre del producto */
    protected String nombre;
    /** Departamento del producto */
    protected String departamento;
    /** Precio del producto */
    protected double precio;

    /**
     * Metodo constructor de la clase Producto
     * @param codigoDeBarras Codigo de barras del producto
     * @param nombre Nombre del producto
     * @param departamento Departamento del producto
     * @param precio Precio del producto
     */
    public Producto(long codigoDeBarras, String nombre, String departamento, double precio){
        this.codigoDeBarras = codigoDeBarras;
        this.nombre = nombre;
        this.departamento = departamento;
        this.precio = precio;
    }

    /**
     * Metodo que devuelve una representación del Producto.
     */
    @Override
    public final String toString(){
        String producto = "Codigo de barras: " + codigoDeBarras + "\n";
        producto += "Nombre: " + nombre + "\n";
        producto += "Departamento: " + departamento + "\n";
        producto += "Precio: " + precio + "\n";
        return producto;
    }

    public double getPrecio(){
        return precio;
    }

    public String getNombre(){
        return nombre;
    }

    public String getDepartamento(){
        return departamento;
    }

    public long getCodigoDeBarras(){
        return codigoDeBarras;
    } 

}