public class Producto{
    /**Codigo de barras que caracteriza cada producto */
    protected long codigo_de_barras;
    protected String nombre;
    protected String departamento;
    protected double precio;

    public Producto(long codigo_de_barras, String nombre, String departamento, double precio){
        this.codigo_de_barras = codigo_de_barras;
        this.nombre = nombre;
        this.departamento = departamento;
        this.precio = precio;
    }
    @Override
    public final String toString(){
        String producto = "Codigo de barras: " + codigo_de_barras + "\n";
        producto += "Nombre: " + nombre + "\n";
        producto += "Departamento: " + departamento + "\n";
        producto += "Precio: " + precio + "\n";
    return producto;
    }
    public double obtenerCodigo(){
        return codigo_de_barras;
    }
}