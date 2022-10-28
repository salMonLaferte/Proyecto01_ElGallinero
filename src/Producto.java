public class Producto{
    /**Codigo de barras que caracteriza cada producto */
    protected long codigoDeBarras;
    protected String nombre;
    protected String departamento;
    protected double precio;

    public Producto(long codigoDeBarras, String nombre, String departamento, double precio){
        this.codigoDeBarras = codigoDeBarras;
        this.nombre = nombre;
        this.departamento = departamento;
        this.precio = precio;
    }

    @Override
    public final String toString(){
        String producto = "Codigo de barras: " + codigoDeBarras + "\n";
        producto += "Nombre: " + nombre + "\n";
        producto += "Departamento: " + departamento + "\n";
        producto += "Precio: " + precio + "\n";
        return producto;
    }
}