import java.util.ArrayList;
import java.util.Iterator;

/** Clase que representa al cliente Proxy*/
public class ProxyCliente implements ClienteInterface {
    /** Cliente real */
    private Cliente clienteReal;

    /** Carrito que guarda la compra del cliente */
    private ArrayList<Producto> carrito = new ArrayList<>();

    /**
     * Metodo constructor de la clase ProxyCliente
     * @param cliente Cliente proxy
     */

    ProxyCliente (Cliente cliente){
        clienteReal = cliente;
    }

    /**
     * Metodo que agrega los productos a comprar al
     * carrito.
     * @param producto Producto a comprar.
     */
    public void agregarAlCarrito(Producto producto){
        carrito.add(producto);
    }

    /**
     * Regresa un iterador para recorrer los productos del carrito
     * @return
     */
    public Iterator<Producto> obtenerIteradorCarrito(){
        return carrito.iterator();
    }

    /**
     * Metodo que pide validar la cuenta del Cliente,
     * para poder proceder con la compra.
     */
    public boolean validarCuenta(String cuenta) {
        return clienteReal.getCuentaBancaria().equals(cuenta);
    }

    /**
     * Metodo que realiza la compra y el cobro, en caso
     * de acompletarse la compra lanza true, en caso contrario
     * false.
     */
    @Override
    public boolean realizarCompra(double amount) {
        if(clienteReal.getDineroDisponible() >= amount){
            clienteReal.realizarCompra(amount);
            return true;
        }
        return false;
    }
    /**
     * {@inheritDoc}}
     */
    @Override
    public String getNombre() {
        return clienteReal.getNombre();
    }

    /**
     * {@inheritDoc}}
     */
    @Override
    public String getPais() {
        return clienteReal.getPais();
    }

    /**
     * {@inheritDoc}}
     */
    @Override
    public String getDireccion() {
        return clienteReal.getDireccion();
    }

    /**
     * {@inheritDoc}}
     */
    @Override
    public String getTelefono() {
        return clienteReal.getDireccion();
    }

    /**
     * {@inheritDoc}}
     */
    @Override
    public String getNombreDeUsuario() {
        return clienteReal.getNombreDeUsuario();
    }


}
