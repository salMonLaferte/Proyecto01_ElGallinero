/** Clase que representa la interfaz de cliente */
public interface ClienteInterface {

    /**
     * Metodo que nos confirma si el monto de la compra fue realizada,
     * tomando en cuenta el monto de la compra y el dinero del cliente.
     * @param amount Monto de la compra.
     * @return Regresa false cuando ha sido descontado el monto.
     */
    public boolean realizarCompra(double amount);
    
    /**
     * Metodo getter del nombre real del cliente
     * @return Nombre del cliente
     */
    public String getNombre();

    /**
     * Metodo getter del pais del cliente
     * @return Pais del cliente
     */
    public String getPais();

    /**
     * Metodo getter de la dirección del cliente
     * @return Dirección del cliente
     */
    public String getDireccion();

    /**
     * Metodo getter del telefono del cliente
     * @return Telefono del cliente
     */
    public String getTelefono();

    /**
     * Metodo getter del nombre de usuario del cliente
     * @return Nombre de usuario
     */
    public String getNombreDeUsuario();
    
}
